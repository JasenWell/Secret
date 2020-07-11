package com.xyp.mimi.mvp.http.entity;

import java.io.Serializable;

/**
 * Description:响应的探测记录
 * Created by hjh on 2020/4/4.
 */
public class DetectRecord implements Serializable {

    private String task_id;
    private int rtt;
    private String status_code;// 200   597,599表示超时,596表示rcode有错误
    private String answer;
    private String str_rcode;//
    private String raw_msg;
    private String node;
    private String isp;
    private String node_count;

    //ping
    private int average;//100
    private int max;
    private int min;
    private int lost_rate;
    private int ping_count;

    //HTTP
    private int dns_parse_time;//毫秒
    private int create_connect_time;//毫秒
    private int download_time;//毫秒
    private int first_body_byte_time;//毫秒
    private int all_time;//毫秒
    private int download_speed;//字节

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public int getRtt() {
        return rtt;
    }

    public void setRtt(int rtt) {
        this.rtt = rtt;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getStr_rcode() {
        return str_rcode;
    }

    public void setStr_rcode(String str_rcode) {
        this.str_rcode = str_rcode;
    }

    public String getRaw_msg() {
        return raw_msg;
    }

    public void setRaw_msg(String raw_msg) {
        this.raw_msg = raw_msg;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getNode_count() {
        return node_count;
    }

    public void setNode_count(String node_count) {
        this.node_count = node_count;
    }

    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
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

    public int getLost_rate() {
        return lost_rate;
    }

    public void setLost_rate(int lost_rate) {
        this.lost_rate = lost_rate;
    }

    public int getPing_count() {
        return ping_count;
    }

    public void setPing_count(int ping_count) {
        this.ping_count = ping_count;
    }

    public int getDns_parse_time() {
        return dns_parse_time;
    }

    public void setDns_parse_time(int dns_parse_time) {
        this.dns_parse_time = dns_parse_time;
    }

    public int getCreate_connect_time() {
        return create_connect_time;
    }

    public void setCreate_connect_time(int create_connect_time) {
        this.create_connect_time = create_connect_time;
    }

    public int getDownload_time() {
        return download_time;
    }

    public void setDownload_time(int download_time) {
        this.download_time = download_time;
    }

    public int getFirst_body_byte_time() {
        return first_body_byte_time;
    }

    public void setFirst_body_byte_time(int first_body_byte_time) {
        this.first_body_byte_time = first_body_byte_time;
    }

    public int getAll_time() {
        return all_time;
    }

    public void setAll_time(int all_time) {
        this.all_time = all_time;
    }

    public int getDownload_speed() {
        return download_speed;
    }

    public void setDownload_speed(int download_speed) {
        this.download_speed = download_speed;
    }
}
