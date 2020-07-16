package com.xyp.mimi.im.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import com.jess.arms.di.component.AppComponent;
import com.xyp.mimi.MainActivity;
import com.xyp.mimi.R;
import com.xyp.mimi.app.base.BaseSupportActivity;
import com.xyp.mimi.im.bean.ResponseWrapperInfo;
import com.xyp.mimi.im.db.model.FriendShipInfo;
import com.xyp.mimi.im.db.model.FriendStatus;
import com.xyp.mimi.im.event.MessageEvent;
import com.xyp.mimi.im.im.IMManager;
import com.xyp.mimi.im.model.Resource;
import com.xyp.mimi.im.model.Status;
import com.xyp.mimi.im.net.hjh.HttpHelper;
import com.xyp.mimi.im.net.hjh.ResponseJson;
import com.xyp.mimi.im.net.hjh.callback.IBaseCallBack;
import com.xyp.mimi.im.net.hjh.imp.AsynModelImp;
import com.xyp.mimi.im.sp.UserCache;
import com.xyp.mimi.im.ui.activity.NewFriendListActivity;
import com.xyp.mimi.im.ui.activity.UserDetailActivity;
import com.xyp.mimi.im.ui.adapter.CommonListAdapter;
import com.xyp.mimi.im.ui.adapter.ListWithSideBarBaseAdapter;
import com.xyp.mimi.im.ui.adapter.models.FunctionInfo;
import com.xyp.mimi.im.ui.adapter.models.ListItemModel;
import com.xyp.mimi.im.viewmodel.CommonListBaseViewModel;
import com.xyp.mimi.im.viewmodel.MainContactsListViewModel;

import org.greenrobot.eventbus.Subscribe;

import io.rong.imkit.RongIM;
import io.rong.imkit.utilities.OptionsPopupDialog;
import io.rong.imlib.model.Conversation;

import static com.xyp.mimi.im.common.IntentExtra.STR_TARGET_ID;

public class MainContactsListFragment extends CommonListBaseFragment implements IBaseCallBack {
    private static final String TAG = "MainContactsListFragment";

    //    private ContactsAdapter adapter;
    private MainContactsListViewModel viewModel;
    private MainActivity mainActivity;
    private AsynModelImp asynModelImp;


    @Subscribe
    public void onEventMainThread(MessageEvent event){
        //接收到发布者发布的事件后，进行相应的处理操作
        if(event.getType() == MessageEvent.EventType.REFRESH_FRIEND_LIST){
            asynModelImp.searchFriendList(HttpHelper.BUSINESS.REQUEST_FRIEND_LIST,UserCache.getInstance().getCurrentUserId());
        }
    }

    @Override
    public void onInitView(Bundle savedInstanceState, Intent intent) {
        super.onInitView(savedInstanceState, intent);
    }


    @Override
    protected boolean isUseSideBar() {
        return true;
    }

    @Override
    protected CommonListBaseViewModel createViewModel() {
        viewModel = ViewModelProviders.of(MainContactsListFragment.this).get(MainContactsListViewModel.class);
        asynModelImp = new AsynModelImp(this);
        viewModel.setAsynModelImp(asynModelImp);
        viewModel.getRefreshItem().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                //getAdapter().notifyItemChanged(integer);
            }
        });
        viewModel.getLoadHjhAllFriendInfoResult().observe(this, new Observer<Resource<ResponseWrapperInfo>>() {
            @Override
            public void onChanged(Resource<ResponseWrapperInfo> listResource) {
                if (listResource.status == Status.SUCCESS) {
                    if (listResource.data != null && listResource.data.getFriendslist().size() > 0) {
                        updateDotNum(listResource.data.getFriendslist());
                    }
                }
            }
        });
        asynModelImp.searchFriendList(HttpHelper.BUSINESS.REQUEST_FRIEND_LIST,UserCache.getInstance().getCurrentUserId());
        return viewModel;
    }

    /**
     * 更新未读状态数量
     *
     * @param list
     */
    private void updateDotNum(List<?> list) {
        int dotNum = 0;
        for (Object info : list) {
            FriendShipInfo friendShipInfo = null;
            if (info instanceof FriendShipInfo) {
                friendShipInfo = (FriendShipInfo) info;
            } else if (info instanceof ListItemModel) {
                ListItemModel listItemModel = (ListItemModel) info;
                if (listItemModel.getData() instanceof FriendShipInfo) {
                    friendShipInfo = (FriendShipInfo) listItemModel.getData();
                }
            }
            //待处理状态时数量加1
            if (friendShipInfo != null && friendShipInfo.getStatus() == FriendStatus.RECEIVE_REQUEST.getStatusCode()) {
                dotNum++;
            }
        }
        int position;
        if (dotNum > 0) {
            position = viewModel.setFunctionShowRedDot("1", dotNum, true);
        } else {
            position = viewModel.setFunctionShowRedDot("1", dotNum, false);
        }
        if (mainActivity == null) {
            mainActivity = (MainActivity) getActivity();
        }
        mainActivity.mainViewModel.setNewFriendNum(dotNum);
        getAdapter().notifyItemChanged(position);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected ListWithSideBarBaseAdapter getListAdapter() {
        return createAdapter();
    }

    /**
     * 创建 Adapter
     */
    private CommonListAdapter createAdapter() {
        CommonListAdapter listAdapter = new CommonListAdapter(CommonListAdapter.CHECK_MODE_UNCHECK);
        // Adapter 的点击监听
        listAdapter.setOnItemClickListener(new CommonListAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position, ListItemModel data) {
                final ListItemModel.ItemView.Type type = data.getItemView().getType();
                switch (type) {
                    case FUN:
                        FunctionInfo functionInfo = (FunctionInfo) data.getData();
                        handleFunItemClick(functionInfo);
                        break;
                    case FRIEND:
                        FriendShipInfo friendShipInfo = (FriendShipInfo) data.getData();
                        handleFriendItemClick(friendShipInfo);
                        break;
                    default:
                        //Do nothing
                        break;
                }
            }
        });
        // 好友长按点击事件
        listAdapter.setOnItemLongClickListener(new CommonListAdapter.OnItemLongClickListener() {
            @Override
            public boolean onLongClick(View v, int position, ListItemModel data) {
                final ListItemModel.ItemView.Type type = data.getItemView().getType();
                if (type == ListItemModel.ItemView.Type.FRIEND) {
                    FriendShipInfo friendShipInfo = (FriendShipInfo) data.getData();
                    handleFriendItemLongClick(friendShipInfo);
                    return true;
                }

                return false;
            }
        });
        return listAdapter;
    }

    /**
     * 处理 好友 item 点击事件
     *
     * @param friendShipInfo
     */
    private void handleFriendItemClick(FriendShipInfo friendShipInfo) {
        if (friendShipInfo.getUser().getId().equals(IMManager.getInstance().getCurrentId())) {
            String title = TextUtils.isEmpty(friendShipInfo.getDisplayName()) ? friendShipInfo.getUser().getNickname() : friendShipInfo.getDisplayName();
            RongIM.getInstance().startConversation(getActivity(), Conversation.ConversationType.PRIVATE, friendShipInfo.getUser().getId(), title);
        } else {
            Intent intent = new Intent(getContext(), UserDetailActivity.class);
            intent.putExtra(STR_TARGET_ID, friendShipInfo.getUser().getId());
            startActivity(intent);
        }
    }

    /**
     * 处理 好友 item 长按点击事件
     *
     * @param friendShipInfo
     */
    private void handleFriendItemLongClick(FriendShipInfo friendShipInfo) {
        String[] items = new String[]{getString(R.string.contact_multi_delete_friend)};
        OptionsPopupDialog.newInstance(getContext(), items).setOptionsPopupDialogListener(new OptionsPopupDialog.OnOptionsItemClickedListener() {

            @Override
            public void onOptionsItemClicked(int index) {
                switch (index) {
                    case 0:
//                        Intent intent = new Intent(getContext(), MultiDeleteFriendsActivity.class);
//                        startActivity(intent);
                        break;
                }
            }
        }).show();
    }


    /**
     * 处理功能事件
     *
     * @param functionInfo
     */
    private void handleFunItemClick(FunctionInfo functionInfo) {
        final String id = functionInfo.getId();
        switch (id) {
            case "1":
                //新的朋友
                viewModel.setFunRedDotShowStatus(id, false);
                Intent intent = new Intent(getActivity(), NewFriendListActivity.class);
                startActivity(intent);
                break;
            case "2":
                //群组
//                intent = new Intent(getActivity(), GroupListActivity.class);
//                startActivity(intent);
                break;
            case "3":
                //公众号
//                intent = new Intent(getActivity(), PublicServiceActivity.class);
//                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
//        asynModelImp.searchFriendRequest(HttpHelper.BUSINESS.REQUEST_SEARCH_FRIEND_REQUEST, UserCache.getInstance().getCurrentUserId());
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showErrorInfo(int code, String devMsg) {

    }

    @Override
    public void onSuccess(Object object, int type) {
        ResponseJson responseJson = (ResponseJson) object;
        if(type == HttpHelper.BUSINESS.REQUEST_SEARCH_FRIEND_REQUEST.getCode()){
            ResponseWrapperInfo wrapperInfo = (ResponseWrapperInfo) responseJson.getData();
            if(wrapperInfo != null){

            }
        }else if(type == HttpHelper.BUSINESS.REQUEST_FRIEND_LIST.getCode()){

        }
    }

    @Override
    public BaseSupportActivity getCallBackActivity() {
        return (BaseSupportActivity) getActivity();
    }
}
