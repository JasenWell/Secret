package com.xyp.mimi.im.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.R;
import com.xyp.mimi.im.common.Constant;
import com.xyp.mimi.im.common.IntentExtra;
import com.xyp.mimi.im.event.MessageEvent;
import com.xyp.mimi.im.model.AddMemberResult;
import com.xyp.mimi.im.model.GroupResult;
import com.xyp.mimi.im.model.Status;
import com.xyp.mimi.im.net.hjh.HttpHelper;
import com.xyp.mimi.im.net.hjh.param.Params;
import com.xyp.mimi.im.sp.UserCache;
import com.xyp.mimi.im.ui.view.SealTitleBar;
import com.xyp.mimi.im.utils.ImageLoaderUtils;
import com.xyp.mimi.im.utils.ToastUtils;
import com.xyp.mimi.im.utils.log.SLog;
import com.xyp.mimi.im.viewmodel.CreateGroupViewModel;

import org.greenrobot.eventbus.EventBus;

import io.rong.imkit.RongIM;
import io.rong.imkit.emoticon.AndroidEmoji;
import io.rong.imkit.widget.AsyncImageView;
import io.rong.imlib.model.Conversation;

/**
 * 创建群组
 */
public class CreateGroupActivity extends TitleBaseActivity implements View.OnClickListener {
    private final String TAG = "CreateGroupActivity";

    private EditText groupNameEt;
    private AsyncImageView groupPortraitIv;

    private Uri groupPortraitUri;
    private CreateGroupViewModel createGroupViewModel;

    private List<String> memberList;
    private String createGroupName;
    /**
     * 是否返回创建群组结果
     */
    private boolean isReturnResult;
    private boolean isCreatingGroup;
    String groupId = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SealTitleBar titleBar = getTitleBar();
        titleBar.setTitle(R.string.seal_main_title_create_group);

        setContentView(R.layout.main_activity_create_group);


        Intent intent = getIntent();
        if (intent == null) {
            SLog.e(TAG, "intent is null, finish " + TAG);
            return;
        }

        memberList = intent.getStringArrayListExtra(IntentExtra.LIST_STR_ID_LIST);
        isReturnResult = intent.getBooleanExtra(IntentExtra.BOOLEAN_CREATE_GROUP_RETURN_RESULT, false);
        if (memberList == null || memberList.size() == 0) {
            SLog.e(TAG, "memberList is 0, finish" + TAG);
            return;
        }
        //加入自己
        memberList.add(0, RongIM.getInstance().getCurrentUserId());

        initView();
        initViewModel();
    }

    private void initView() {
        groupNameEt = findViewById(R.id.main_et_create_group_name);
        groupNameEt.setHint(getString(R.string.profile_group_name_word_limit_format, Constant.GROUP_NAME_MIN_LENGTH, Constant.GROUP_NAME_MAX_LENGTH));
        groupNameEt.setFilters(new InputFilter[]{emojiFilter, new InputFilter.LengthFilter(10)});
        groupPortraitIv = findViewById(R.id.main_iv_create_group_portrait);
        groupPortraitIv.setOnClickListener(this);
        findViewById(R.id.main_btn_confirm_create).setOnClickListener(this);
    }

    private void initViewModel() {
        createGroupViewModel = ViewModelProviders.of(this).get(CreateGroupViewModel.class);

        createGroupViewModel.getCreateGroupResult().observe(this, resource -> {
            if (resource.status == Status.SUCCESS) {
                // 处理创建群组结果
                processCreateResult(resource.data);
            } else if (resource.status == Status.ERROR) {
                // 当有结果时代表群组创建成功，但上传图片失败
                if (resource.data != null) {
                    // 处理创建群组结果
                    processCreateResult(resource.data);
                } else {
                    // 仅有创建失败时重置创建群组状态
                    isCreatingGroup = false;
                }

                ToastUtils.showToast(resource.message);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_iv_create_group_portrait:
//                showSelectPortraitDialog();
                break;
            case R.id.main_btn_confirm_create:
                createGroup();
                break;
            default:
                break;
        }
    }

//    /**
//     * 显示选择图片对话框
//     */
//    private void showSelectPortraitDialog() {
//        SelectPictureBottomDialog.Builder builder = new SelectPictureBottomDialog.Builder();
//        builder.setOnSelectPictureListener(uri -> {
//            SLog.d(TAG, "select picture, uri:" + uri);
//            groupPortraitIv.setImageURI(null);
//            groupPortraitIv.setImageURI(uri);
//            groupPortraitUri = uri;
//        });
//        SelectPictureBottomDialog dialog = builder.build();
//        dialog.show(getSupportFragmentManager(), null);
//    }

    /**
     * 创建群组
     */
    private void createGroup() {
        if (isCreatingGroup) return;

        String groupName = groupNameEt.getText().toString();
        groupName = groupName.trim();

        if (groupName.length() < Constant.GROUP_NAME_MIN_LENGTH || groupName.length() > Constant.GROUP_NAME_MAX_LENGTH) {
            ToastUtils.showToast(getString(R.string.profile_group_name_word_limit_format, Constant.GROUP_NAME_MIN_LENGTH, Constant.GROUP_NAME_MAX_LENGTH));
            return;
        }

        if (AndroidEmoji.isEmoji(groupName) && groupName.length() < Constant.GROUP_NAME_EMOJI_MIN_LENGTH) {
            ToastUtils.showToast(getString(R.string.profile_group_name_emoji_too_short));
            return;
        }

        createGroupName = groupName;

        // 标记创建群组状态
        isCreatingGroup = true;
        createGroupViewModel.createGroup(groupName, groupPortraitUri, memberList);
    }

    /**
     * 处理创建结果
     *
     * @param groupResult
     */
    private void processCreateResult(GroupResult groupResult) {

        if (groupResult != null) {
            groupId = groupResult.getGid();

            // 判断是否有人需要同意加入群，显示提示信息
            if (groupResult.userStatus != null && groupResult.userStatus.size() > 0) {
                String tips = getString(R.string.seal_create_success);
                //1 为已加入, 2 为等待管理员同意, 3 为等待被邀请者同意
                for (AddMemberResult result : groupResult.userStatus) {
                    if (result.status == 3) {
                        tips = getString(R.string.seal_add_need_member_agree);
                        break;
                    }
                }
                ToastUtils.showToast(tips);
            }
        }

        // 返回结果时候设置结果并结束
        if (isReturnResult) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra(IntentExtra.GROUP_ID, groupId);
            setResult(RESULT_OK, resultIntent);
            finish();
        } else {
            asynModelImp.sendRYIdentityHashMapRequest(HttpHelper.BUSINESS.REQUEST_RY_CREATE_GROUP, Params.createGroupParam(memberList, groupResult.getGid(), createGroupName));
        }
    }

    /**
     * 跳转到群组聊天
     */
    private void toGroupChat(String groupId) {
        RongIM.getInstance().startConversation(this, Conversation.ConversationType.GROUP, groupId, createGroupName);
    }

    private void toGroupDetail(String groupId){
        Intent intent = new Intent(this, GroupDetailActivity.class);
        intent.putExtra(IntentExtra.STR_TARGET_ID, groupId);
        intent.putExtra(IntentExtra.SERIA_CONVERSATION_TYPE, Conversation.ConversationType.GROUP);
        startActivity(intent);
    }

    /**
     * 表情输入的过滤
     */
    InputFilter emojiFilter = new InputFilter() {
        Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Matcher emojiMatcher = emoji.matcher(source);
            if (emojiMatcher.find()) {
                ToastUtils.showToast("不支持输入表情");
                return "";
            }
            return null;
        }
    };

    @Override
    public void onSuccess(Object object, int type) {
        super.onSuccess(object, type);
        if(type == HttpHelper.BUSINESS.REQUEST_RY_CREATE_GROUP.getCode()){
            showToast("创建成功");
            //不返回结果时，创建成功后跳转到群组聊天中
//            toGroupChat(groupId);
            toGroupDetail(groupId);//跳转到群详情
//            EventBus.getDefault().post(new MessageEvent("跳转到群列表",MessageEvent.EventType.SWITCH_GROUP_LIST));
            finish();
        }
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return 0;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
