package com.xyp.mimi.mvp.http.entity.circle;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class CircleListResult {


    /**
     * code : 0
     * count : 0
     * data : {"dlist":[{"context":"123","createTime":1594692491000,"id":10,"uid":78},{"context":"666","createTime":1594692824000,"id":11,"uid":78}]}
     * msg :
     */

    private int code;
    private int count;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        private List<DlistBean> dlist;

        public List<DlistBean> getDlist() {
            return dlist;
        }

        public void setDlist(List<DlistBean> dlist) {
            this.dlist = dlist;
        }

        public static class DlistBean implements MultiItemEntity {
            /**
             * context : 123
             * createTime : 1594692491000
             * id : 10
             * uid : 78
             */

            private String context;
            private long createTime;
            private String id;
            private int uid;

            public String getContext() {
                return context;
            }

            public void setContext(String context) {
                this.context = context;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            @Override
            public int getItemType() {
                return 0;
            }
        }
    }
}