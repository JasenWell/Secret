package com.xyp.mimi.im.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.xyp.mimi.im.db.dao.FriendDao;
import com.xyp.mimi.im.db.dao.GroupDao;
import com.xyp.mimi.im.db.dao.GroupMemberDao;
import com.xyp.mimi.im.db.dao.UserDao;
import com.xyp.mimi.im.db.model.BlackListEntity;
import com.xyp.mimi.im.db.model.FriendDescription;
import com.xyp.mimi.im.db.model.FriendInfo;
import com.xyp.mimi.im.db.model.GroupEntity;
import com.xyp.mimi.im.db.model.GroupExitedMemberInfo;
import com.xyp.mimi.im.db.model.GroupMemberInfoDes;
import com.xyp.mimi.im.db.model.GroupMemberInfoEntity;
import com.xyp.mimi.im.db.model.GroupNoticeInfo;
import com.xyp.mimi.im.db.model.PhoneContactInfoEntity;
import com.xyp.mimi.im.db.model.UserInfo;

@Database(entities = {UserInfo.class, FriendInfo.class, GroupEntity.class, GroupMemberInfoEntity.class,
        BlackListEntity.class, GroupNoticeInfo.class, GroupExitedMemberInfo.class, FriendDescription.class,
        GroupMemberInfoDes.class,PhoneContactInfoEntity.class}, version = 3, exportSchema = false)
@TypeConverters(com.xyp.mimi.im.db.TypeConverters.class)
public abstract class SealTalkDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();

    public abstract FriendDao getFriendDao();

    public abstract GroupDao getGroupDao();

    public abstract GroupMemberDao getGroupMemberDao();
}
