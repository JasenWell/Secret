package com.xyp.mimi.mvp.http.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 * Created by hjh on 2020/4/4.
 */
public class DetectAttrs implements Serializable {
    private String id;//只有响应才有
    private String zdnsuser;

    private String name_server;//8.8.8.8:53  DNS
    private String name;//url ping
    private String qname;//url DNS
    private String qtype;//a DNS
    private String method;// post http
    private String url; // http https://www.zdns.cn
    private String host;//http  www.zdns.cn

    private String begin;//%Y-%m-%dT%H:%M:%S+08:00
    private String end;
    private String order;//根据isp或者rtt进行排序
    private boolean desc;//true/false  降序还是升序

    private List<String> nodes;//["telecom_beijing"]
    private int timeout;//毫秒 5000
    private int interval;//秒 120

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

    public List<String> getNodes() {
        return nodes;
    }

    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public boolean isDesc() {
        return desc;
    }

    public void setDesc(boolean desc) {
        this.desc = desc;
    }
}
