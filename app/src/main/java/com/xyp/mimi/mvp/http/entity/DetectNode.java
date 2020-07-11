package com.xyp.mimi.mvp.http.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Description:响应的探测组节点信息
 * Created by hjh on 2020/4/4.
 */
public class DetectNode implements Serializable {


    private String category;//telecom,unicom,cmcc,drpeng,cernet,cstnet,other
    private String zh;//中国电信,中国联通,中国移动,鹏博士,中国教育网,中国科技网,其他
    private List<ChildNode> nodes;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

    public List<ChildNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<ChildNode> nodes) {
        this.nodes = nodes;
    }

    private static class ChildNode implements Serializable{
        private String name;//telecom_beijing
        private String zh;//北京电信

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getZh() {
            return zh;
        }

        public void setZh(String zh) {
            this.zh = zh;
        }
    }
}
