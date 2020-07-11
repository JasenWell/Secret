package com.xyp.mimi.mvp.http.entity;

import java.util.List;

public class NetData {


    /**
     * result : success
     * msg : 成功
     * con : [{"id":"xx","zdnsuser":"xx","name_server":"8.8 .8 .8: 53","qname":"8.8 .8 .8: 53","qtype":"8.8 .8 .8: 53","nodes":"telecom_beijing","nodesName":"北京电信","timeout":"0","interval":"0","max":200,"min":10,"percent":100,"lost_rate":0,"jiexi":200,"lianjie":10,"xiazai":100,"shouzijie":1,"sudu":10,"zonghaoshi":10},{"id":"xx","zdnsuser":"xx","name_server":"8.8 .8 .8: 53","qname":"8.8 .8 .8: 53","qtype":"8.8 .8 .8: 53","nodes":"unicom","nodesName":"中国联通","timeout":"0","interval":"0","max":200,"min":10,"percent":120,"lost_rate":0,"jiexi":200,"lianjie":10,"xiazai":100,"shouzijie":1,"sudu":10,"zonghaoshi":10},{"id":"xx","zdnsuser":"xx","name_server":"8.8 .8 .8: 53","qname":"8.8 .8 .8: 53","qtype":"8.8 .8 .8: 53","nodes":"cmcc","nodesName":"中国移动","timeout":"0","interval":"0","max":210,"min":10,"percent":130,"lost_rate":0,"jiexi":200,"lianjie":10,"xiazai":100,"shouzijie":1,"sudu":10,"zonghaoshi":10},{"id":"xx","zdnsuser":"xx","name_server":"8.8 .8 .8: 53","qname":"8.8 .8 .8: 53","qtype":"8.8 .8 .8: 53","nodes":"drpeng","nodesName":"鹏博士","timeout":"0","interval":"0","max":100,"min":10,"percent":50,"lost_rate":0,"jiexi":200,"lianjie":10,"xiazai":100,"shouzijie":1,"sudu":10,"zonghaoshi":10},{"id":"xx","zdnsuser":"xx","name_server":"8.8 .8 .8: 53","qname":"8.8 .8 .8: 53","qtype":"8.8 .8 .8: 53","nodes":"cernet","nodesName":"中国教育网","timeout":"0","interval":"0","max":300,"min":10,"percent":110,"lost_rate":0,"jiexi":200,"lianjie":10,"xiazai":100,"shouzijie":1,"sudu":10,"zonghaoshi":10},{"id":"xx","zdnsuser":"xx","name_server":"8.8 .8 .8: 53","qname":"8.8 .8 .8: 53","qtype":"8.8 .8 .8: 53","nodes":"cstnet","nodesName":"中国科技网","timeout":"0","interval":"0","max":1000,"min":10,"percent":250,"lost_rate":0,"jiexi":200,"lianjie":10,"xiazai":100,"shouzijie":1,"sudu":10,"zonghaoshi":10}]
     */

    private String result;
    private String msg;
    private List<ConBean> con;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ConBean> getCon() {
        return con;
    }

    public void setCon(List<ConBean> con) {
        this.con = con;
    }

    public static class ConBean {
        /**
         * id : xx
         * zdnsuser : xx
         * name_server : 8.8 .8 .8: 53
         * qname : 8.8 .8 .8: 53
         * qtype : 8.8 .8 .8: 53
         * nodes : telecom_beijing
         * nodesName : 北京电信
         * timeout : 0
         * interval : 0
         * max : 200
         * min : 10
         * percent : 100
         * lost_rate : 0
         * jiexi : 200
         * lianjie : 10
         * xiazai : 100
         * shouzijie : 1
         * sudu : 10
         * zonghaoshi : 10
         */

        private String id;
        private String zdnsuser;
        private String name_server;
        private String qname;
        private String qtype;
        private String nodes;
        private String nodesName;
        private String timeout;
        private String interval;
        private int max;
        private int min;
        private int percent;
        private int lost_rate;
        private int jiexi;
        private int lianjie;
        private int xiazai;
        private int shouzijie;
        private int sudu;
        private int zonghaoshi;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getZdnsuser() {
            return zdnsuser;
        }

        public void setZdnsuser(String zdnsuser) {
            this.zdnsuser = zdnsuser;
        }

        public String getName_server() {
            return name_server;
        }

        public void setName_server(String name_server) {
            this.name_server = name_server;
        }

        public String getQname() {
            return qname;
        }

        public void setQname(String qname) {
            this.qname = qname;
        }

        public String getQtype() {
            return qtype;
        }

        public void setQtype(String qtype) {
            this.qtype = qtype;
        }

        public String getNodes() {
            return nodes;
        }

        public void setNodes(String nodes) {
            this.nodes = nodes;
        }

        public String getNodesName() {
            return nodesName;
        }

        public void setNodesName(String nodesName) {
            this.nodesName = nodesName;
        }

        public String getTimeout() {
            return timeout;
        }

        public void setTimeout(String timeout) {
            this.timeout = timeout;
        }

        public String getInterval() {
            return interval;
        }

        public void setInterval(String interval) {
            this.interval = interval;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getPercent() {
            return percent;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }

        public int getLost_rate() {
            return lost_rate;
        }

        public void setLost_rate(int lost_rate) {
            this.lost_rate = lost_rate;
        }

        public int getJiexi() {
            return jiexi;
        }

        public void setJiexi(int jiexi) {
            this.jiexi = jiexi;
        }

        public int getLianjie() {
            return lianjie;
        }

        public void setLianjie(int lianjie) {
            this.lianjie = lianjie;
        }

        public int getXiazai() {
            return xiazai;
        }

        public void setXiazai(int xiazai) {
            this.xiazai = xiazai;
        }

        public int getShouzijie() {
            return shouzijie;
        }

        public void setShouzijie(int shouzijie) {
            this.shouzijie = shouzijie;
        }

        public int getSudu() {
            return sudu;
        }

        public void setSudu(int sudu) {
            this.sudu = sudu;
        }

        public int getZonghaoshi() {
            return zonghaoshi;
        }

        public void setZonghaoshi(int zonghaoshi) {
            this.zonghaoshi = zonghaoshi;
        }
    }
}
