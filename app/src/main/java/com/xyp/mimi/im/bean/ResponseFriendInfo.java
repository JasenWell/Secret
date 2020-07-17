package com.xyp.mimi.im.bean;


import java.io.Serializable;
import java.util.List;

/**
 * xiang
 */
public class ResponseFriendInfo implements Serializable {

    /**
     * code : 0
     * msg :
     * count : 0
     * data : {"Friendslist":[{"id":33,"mianUid":103,"username":"t5","imgUrl":"http://www.wulin0009.com:80/mall/file/showPic?fileName=1594828645027_.png","viceUid":99,"createTime":1594948401000,"status":1},{"id":36,"mianUid":103,"username":"阳光小伙","imgUrl":"http://www.wulin0009.com:80/mall/file/showPic?fileName=1594867707563_headImage.jpg","viceUid":101,"createTime":1594949149000,"status":1}]}
     */

    private int code;
    private String msg;
    private int count;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<FriendslistBean> Friendslist;

        public List<FriendslistBean> getFriendslist() {
            return Friendslist;
        }

        public void setFriendslist(List<FriendslistBean> Friendslist) {
            this.Friendslist = Friendslist;
        }

        public static class FriendslistBean {
            /**
             * id : 33
             * mianUid : 103
             * username : t5
             * imgUrl : http://www.wulin0009.com:80/mall/file/showPic?fileName=1594828645027_.png
             * viceUid : 99
             * createTime : 1594948401000
             * status : 1
             */

            private int id;
            private int mianUid;
            private String username;
            private String imgUrl;
            private int viceUid;
            private long createTime;
            private int status;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMianUid() {
                return mianUid;
            }

            public void setMianUid(int mianUid) {
                this.mianUid = mianUid;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public int getViceUid() {
                return viceUid;
            }

            public void setViceUid(int viceUid) {
                this.viceUid = viceUid;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
