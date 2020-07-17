package com.xyp.mimi.im.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.xyp.mimi.R;
import com.xyp.mimi.im.bean.CheckedItem;
import com.xyp.mimi.im.bean.ResponseAddingFriendInfo;
import com.xyp.mimi.im.bean.ResponseWrapperInfo;
import com.xyp.mimi.im.db.model.FriendShipInfo;
import com.xyp.mimi.im.db.model.FriendStatus;
import com.xyp.mimi.im.model.GroupMember;
import com.xyp.mimi.im.model.Resource;
import com.xyp.mimi.im.model.Status;
import com.xyp.mimi.im.sp.UserCache;
import com.xyp.mimi.im.task.FriendTask;
import com.xyp.mimi.im.task.GroupTask;
import com.xyp.mimi.im.ui.adapter.models.CharacterTitleInfo;
import com.xyp.mimi.im.ui.adapter.models.CheckType;
import com.xyp.mimi.im.ui.adapter.models.CheckableContactModel;
import com.xyp.mimi.im.ui.adapter.models.ContactModel;
import com.xyp.mimi.im.utils.CharacterParser;
import com.xyp.mimi.im.utils.SingleSourceMapLiveData;
import com.xyp.mimi.im.utils.log.SLog;

public class SelectBaseViewModel extends AndroidViewModel {

    private static final String TAG = "SelectBaseViewModel";
    private FriendTask friendTask;
    private GroupTask groupTask;
    protected SingleSourceMapLiveData<Resource<ResponseWrapperInfo>, List<ContactModel>> friendsLiveData;
    protected SingleSourceMapLiveData<List<FriendShipInfo>, List<ContactModel>> groupFriendsLiveData;
    protected SingleSourceMapLiveData<List<FriendShipInfo>, List<ContactModel>> excludeGroupLiveData;
    protected SingleSourceMapLiveData<List<GroupMember>, List<ContactModel>> allGroupMemberLiveData;
    private MutableLiveData<Integer> selectedCount = new MutableLiveData<>();
    private MutableLiveData<List<ContactModel>> currentLiveData;
    private ArrayList<String> uncheckableContactIdList;
    private ArrayList<String> excludeContactIdList;
    private ArrayList<String> checkedContactIdList;
    private ArrayList<String> checkedGroupList;

    public SelectBaseViewModel(@NonNull Application application) {
        super(application);
        friendTask = new FriendTask(application);
        groupTask = new GroupTask(application);
        friendsLiveData = new SingleSourceMapLiveData<>(new Function<Resource<ResponseWrapperInfo>, List<ContactModel>>() {
            @Override
            public List<ContactModel> apply(Resource<ResponseWrapperInfo> input) {
                if(input.status == Status.LOADING){
                    return null;
                }else {
                    return convert(input.data.getFriendslist());
                }
            }
        });

//        groupFriendsLiveData = new SingleSourceMapLiveData<>(new Function<List<FriendShipInfo>, List<ContactModel>>() {
//            @Override
//            public List<ContactModel> apply(List<FriendShipInfo> input) {
//                return convert(input);
//            }
//        });
//
//
//        excludeGroupLiveData = new SingleSourceMapLiveData<>(new Function<List<FriendShipInfo>, List<ContactModel>>() {
//            @Override
//            public List<ContactModel> apply(List<FriendShipInfo> input) {
//                return convert(input);
//            }
//        });

        allGroupMemberLiveData = new SingleSourceMapLiveData<>(new Function<List<GroupMember>, List<ContactModel>>() {
            @Override
            public List<ContactModel> apply(List<GroupMember> input) {
                return convertGroupMember(input);
            }
        });
    }

    public void loadFriendShip(ArrayList<String> uncheckableIdList, ArrayList<String> checkedUsers, ArrayList<String> checkedGroups) {
        uncheckableContactIdList = uncheckableIdList;
        checkedContactIdList = checkedUsers;
        checkedGroupList = checkedGroups;
        if (checkedContactIdList != null) {
            selectedCount.setValue(checkedContactIdList.size());
        } else {
            selectedCount.setValue(0);
        }
        friendsLiveData.setSource(friendTask.getHjhAllFriends(UserCache.getInstance().getCurrentUserId()));
        currentLiveData = friendsLiveData;
    }

    public void loadFriendShip() {
        friendsLiveData.setSource(friendTask.getHjhAllFriends(UserCache.getInstance().getCurrentUserId()));
        currentLiveData = friendsLiveData;
    }

    /**
     * 搜索关键字
     *
     * @param keyword
     */
    public void searchFriend(String keyword) {
        LiveData<List<FriendShipInfo>> searchDbLiveData = friendTask.searchFriendsFromDB(keyword);
//        // 转换数据库搜索结果
//        LiveData<Resource<List<FriendShipInfo>>> resourceLiveData = Transformations.switchMap(searchDbLiveData,
//                new Function<List<FriendShipInfo>, LiveData<Resource<List<FriendShipInfo>>>>() {
//                    @Override
//                    public LiveData<Resource<List<FriendShipInfo>>> apply(List<FriendShipInfo> input) {
//                        return new MutableLiveData<>(Resource.success(input));
//                    }
//                });
//        friendsLiveData.setSource(resourceLiveData);
    }


    /**
     * 排除群组成员的好友列表
     *
     * @param groupId
     */
    public void loadFriendShipExclude(String groupId, ArrayList<String> uncheckableIdList) {
        SLog.i(TAG, "loadFriendShipExclude groupId:" + groupId);
        uncheckableContactIdList = uncheckableIdList;
        if (checkedContactIdList != null) {
            selectedCount.setValue(checkedContactIdList.size());
        } else {
            selectedCount.setValue(0);
        }
        excludeGroupLiveData.setSource(friendTask.getAllFriendsExcludeGroup(groupId));
        currentLiveData = excludeGroupLiveData;
    }

    /**
     * 群组成员列表
     *
     * @param groupId
     */
    public void loadFriendShipExclude(String groupId) {
        SLog.i(TAG, "loadFriendShipInclude groupId:" + groupId);
        groupFriendsLiveData.setSource(friendTask.getAllFriendsExcludeGroup(groupId));
        currentLiveData = groupFriendsLiveData;
    }

    /**
     * 搜索群组成员
     *
     * @param groupId
     * @param keyword
     */
    public void searchFriendshipExclude(String groupId, String keyword) {
        groupFriendsLiveData.setSource(friendTask.searchFriendsExcludeGroup(groupId, keyword));
    }

    /**
     * 群组成员列表
     *
     * @param groupId
     */
    public void loadGroupMemberExclude(String groupId, ArrayList<String> excludeList, ArrayList<String> includeList) {
        SLog.i(TAG, "loadGroupMemberExclude groupId:" + groupId);
        excludeContactIdList = excludeList;
        checkedContactIdList = includeList;
        if (checkedContactIdList != null) {
            selectedCount.setValue(checkedContactIdList.size());
        } else {
            selectedCount.setValue(0);
        }
        loadGroupMemberExclude(groupId);
    }

    /**
     * 群组成员列表
     *
     * @param groupId
     */
    public void loadGroupMemberExclude(String groupId) {
        SLog.i(TAG, "loadGroupMemberExclude groupId:" + groupId);
        MediatorLiveData<List<GroupMember>> groupMemberListLiveData = new MediatorLiveData<>();
        groupMemberListLiveData.addSource(groupTask.getGroupMemberInfoList(groupId), new Observer<Resource<List<GroupMember>>>() {
            @Override
            public void onChanged(Resource<List<GroupMember>> resource) {
                if (resource.status != Status.LOADING) {
                    groupMemberListLiveData.setValue(resource.data);
                }
            }
        });
        allGroupMemberLiveData.setSource(groupMemberListLiveData);
        currentLiveData = allGroupMemberLiveData;
    }

    /**
     * 搜索群组内成员
     *
     * @param groupId
     * @param keyword
     */
    public void searchGroupMemberExclude(String groupId, String keyword) {
        allGroupMemberLiveData.setSource(groupTask.searchGroupMemberInDB(groupId, keyword));
    }


    public LiveData<List<ContactModel>> getFriendShipLiveData() {
        return friendsLiveData;
    }

    public int getIndex(String s) {
        if (currentLiveData.getValue() == null) return 0;
        for (int i = 0; i < currentLiveData.getValue().size(); i++) {
            Object o = currentLiveData.getValue().get(i).getBean();
            if (o instanceof CharacterTitleInfo) {
                CharacterTitleInfo characterParser = (CharacterTitleInfo) o;
                if (s.equals(characterParser.getCharacter())) {
                    return i;
                }
            }
        }
        return 0;
    }

    /**
     * @param input
     * @return
     */
    private List<ContactModel> convert(List<ResponseAddingFriendInfo> input) {
        if (input == null) return null;
        SLog.i(TAG, "convert input.size()" + input.size());
        List<ContactModel> output = new ArrayList<>();
        ContactModel model = null;
        String temp = "";
        sortByFirstChar(input);
        for (ResponseAddingFriendInfo friendShipInfo : input) {
            if (excludeContactIdList != null) {
                if (excludeContactIdList.contains(friendShipInfo.getViceUid())) {
                    continue;
                }
            }
            // 非好友不添加入列表
            if (Integer.parseInt(friendShipInfo.getStatus()) != 1) {
                continue;
            }

            String firstChar = getFirstChar(friendShipInfo);
            if (TextUtils.isEmpty(firstChar)) {
                model = new ContactModel(new CharacterTitleInfo("#"), R.layout.contact_contact_title);
                temp = "#";
                output.add(model);
            } else if (!temp.equals(firstChar)) {
                model = new ContactModel(new CharacterTitleInfo(firstChar), R.layout.contact_contact_title);
                temp = firstChar;
                output.add(model);
            }
            CheckableContactModel<ResponseAddingFriendInfo> checkableContactModel = new CheckableContactModel(friendShipInfo, R.layout.select_fragment_contact_item);
            if (uncheckableContactIdList != null && uncheckableContactIdList.contains(checkableContactModel.getBean().getViceUid())) {
                checkableContactModel.setCheckType(CheckType.DISABLE);
            }
            SLog.i(TAG, "checkableContactModel.getBean().getUser().getId(): " + checkableContactModel.getBean().getViceUid());
            if (checkedContactIdList != null && checkedContactIdList.contains(checkableContactModel.getBean().getViceUid())) {
                checkableContactModel.setCheckType(CheckType.CHECKED);
            }
            output.add(checkableContactModel);
        }
        return output;
    }

    private List<ContactModel> convertGroupMember(List<GroupMember> input) {
        if (input == null) return null;
        SLog.i(TAG, "convert input.size()" + input.size());
        List<ContactModel> output = new ArrayList<>();
        ContactModel model = null;
        String temp = "";
        sortGroupMemberByFirstChar(input);
        for (GroupMember groupMember : input) {
            if (excludeContactIdList != null) {
                if (excludeContactIdList.contains(groupMember.getUserId())) {
                    continue;
                }
            }

            String firstChar = getFirstChar(groupMember);
            if (TextUtils.isEmpty(firstChar)) {
                model = new ContactModel<>(new CharacterTitleInfo("#"), R.layout.contact_contact_title);
                temp = "#";
                output.add(model);
            } else if (!temp.equals(firstChar)) {
                model = new ContactModel<>(new CharacterTitleInfo(firstChar), R.layout.contact_contact_title);
                temp = firstChar;
                output.add(model);
            }
            CheckableContactModel<GroupMember> checkableContactModel = new CheckableContactModel<>(groupMember, R.layout.select_fragment_contact_item);
            if (uncheckableContactIdList != null && uncheckableContactIdList.contains(checkableContactModel.getBean().getUserId())) {
                checkableContactModel.setCheckType(CheckType.DISABLE);
            }
            SLog.i(TAG, "checkableContactModel.getBean().getUser().getId(): " + checkableContactModel.getBean().getUserId());
            if (checkedContactIdList != null && checkedContactIdList.contains(checkableContactModel.getBean().getUserId())) {
                checkableContactModel.setCheckType(CheckType.CHECKED);
            }
            output.add(checkableContactModel);
        }
        return output;
    }

    /**
     * 点击选取操作
     *
     * @param
     */
    public void onItemClicked(CheckableContactModel model) {
        //继承实现，选人策略
    }

    public ArrayList<String> getCheckedGroupList() {
        return checkedGroupList;
    }

    public CheckedItem getCheckedItem() {
        CheckedItem checkedItem = new CheckedItem();
        List<ContactModel> contactModels = currentLiveData.getValue();

        if (contactModels == null) return checkedItem;

        for (ContactModel model : contactModels) {
            if (model.getType() == R.layout.select_fragment_contact_item) {
                CheckableContactModel checkableContactModel = (CheckableContactModel) model;
                if (checkableContactModel.getCheckType() == CheckType.CHECKED) {
                    if (checkableContactModel.getBean() instanceof ResponseAddingFriendInfo) {
                        ResponseAddingFriendInfo info = (ResponseAddingFriendInfo) checkableContactModel.getBean();
                        checkedItem.getFriendInfoList().add(info);
                    } else if (checkableContactModel.getBean() instanceof GroupMember) {
                        GroupMember groupMember = (GroupMember) checkableContactModel.getBean();
                        checkedItem.getGroupMemberList().add(groupMember);
                    }
                }
            }
        }
        return checkedItem;
    }

    /**
     * 获取已选择的好友列表
     *
     * @return
     */
    public ArrayList<String> getCheckedFriendIdList() {
        return checkedContactIdList;
    }

    public void cancelAllCheck() {
        List<ContactModel> ContactModels = currentLiveData.getValue();
        for (ContactModel model : ContactModels) {
            if (model.getType() == R.layout.select_fragment_contact_item) {
                CheckableContactModel<FriendShipInfo> checkableContactModel = (CheckableContactModel<FriendShipInfo>) model;
                checkableContactModel.setCheckType(CheckType.NONE);
            }
        }
        if (checkedContactIdList != null) {
            checkedContactIdList.clear();
        }
    }

    public SingleSourceMapLiveData<List<FriendShipInfo>, List<ContactModel>> getGroupFriendsLiveData() {
        return groupFriendsLiveData;
    }

    public SingleSourceMapLiveData<List<FriendShipInfo>, List<ContactModel>> getExcludeGroupLiveData() {
        return excludeGroupLiveData;
    }

    public LiveData<List<ContactModel>> getAllGroupMemberLiveData() {
        return allGroupMemberLiveData;
    }

    /**
     * 添加至已选列表
     */
    public void addToCheckedList(CheckableContactModel contactModel) {
        if (checkedContactIdList == null) {
            checkedContactIdList = new ArrayList<>();
        }
        Object bean = contactModel.getBean();
        if (bean instanceof ResponseAddingFriendInfo) {
            ResponseAddingFriendInfo friendShipInfo = (ResponseAddingFriendInfo) bean;
            String id = friendShipInfo.getViceUid();
            if (!checkedContactIdList.contains(id)) {
                checkedContactIdList.add(id);
                selectedCount.setValue(checkedContactIdList.size());
            }
        } else if (bean instanceof GroupMember) {
            GroupMember groupMember = (GroupMember) bean;
            String userId = groupMember.getUserId();
            if (!checkedContactIdList.contains(userId)) {
                checkedContactIdList.add(userId);
                selectedCount.setValue(checkedContactIdList.size());
            }
        }
    }

    /**
     * 在已选列表中移除
     */
    public void removeFromCheckedList(CheckableContactModel contactModel) {
        if (checkedContactIdList == null) return;

        Object bean = contactModel.getBean();
        if (bean instanceof ResponseAddingFriendInfo) {
            ResponseAddingFriendInfo friendShipInfo = (ResponseAddingFriendInfo) bean;
            String id = friendShipInfo.getViceUid();
            boolean removed = checkedContactIdList.remove(id);
            if (removed) {
                selectedCount.setValue(checkedContactIdList.size());
            }
        } else if (bean instanceof GroupMember) {
            GroupMember groupMember = (GroupMember) bean;
            String userId = groupMember.getUserId();
            boolean removed = checkedContactIdList.remove(userId);
            if (removed) {
                selectedCount.setValue(checkedContactIdList.size());
            }
        }
    }

    /**
     * 获取选择用户的数量
     *
     * @return
     */
    public LiveData<Integer> getSelectedCount() {
        return selectedCount;
    }

    /**
     * 好友首字母排序
     *
     * @param models
     */
    private void sortByFirstChar(List<ResponseAddingFriendInfo> models) {
        Collections.sort(models, new Comparator<ResponseAddingFriendInfo>() {
            @Override
            public int compare(ResponseAddingFriendInfo lhs, ResponseAddingFriendInfo rhs) {
                if (TextUtils.isEmpty(getFirstChar(lhs))) {
                    return -1;
                }
                if (TextUtils.isEmpty(getFirstChar(rhs))) {
                    return 1;
                }
                return getFirstChar(lhs).compareTo(getFirstChar(rhs));
            }
        });
    }

    private void sortGroupMemberByFirstChar(List<GroupMember> models) {
        Collections.sort(models, new Comparator<GroupMember>() {
            @Override
            public int compare(GroupMember lhs, GroupMember rhs) {
                if (TextUtils.isEmpty(getFirstChar(lhs))) {
                    return -1;
                }
                if (TextUtils.isEmpty(getFirstChar(rhs))) {
                    return 1;
                }
                return getFirstChar(lhs).compareTo(getFirstChar(rhs));
            }
        });
    }

    // 获取首字母
    private String getFirstChar(ResponseAddingFriendInfo info) {
        String firstChar;
        String groupDisplayName = "";
        String displayName = info.getUsername();
        String nameFirstChar = "";
        if (!TextUtils.isEmpty(groupDisplayName)) {
            firstChar = CharacterParser.getInstance().getSpelling(groupDisplayName).substring(0, 1).toUpperCase();
        } else if (!TextUtils.isEmpty(displayName)) {
            firstChar = CharacterParser.getInstance().getSpelling(displayName).substring(0, 1).toUpperCase();
        } else {
            firstChar = nameFirstChar;
        }
        if (TextUtils.isEmpty(firstChar)) {
            firstChar = "#";
        }
        return firstChar;
    }

    // 获取首字母
    private String getFirstChar(GroupMember info) {
        String firstChar;
        String groupDisplayName = info.getGroupNickName();
        String displayName = info.getName();
        if (!TextUtils.isEmpty(groupDisplayName)) {
            firstChar = CharacterParser.getInstance().getSpelling(groupDisplayName).substring(0, 1).toUpperCase();
        } else {
            firstChar = CharacterParser.getInstance().getSpelling(displayName).substring(0, 1).toUpperCase();
        }
        if (TextUtils.isEmpty(firstChar)) {
            firstChar = "#";
        }
        return firstChar;
    }


}
