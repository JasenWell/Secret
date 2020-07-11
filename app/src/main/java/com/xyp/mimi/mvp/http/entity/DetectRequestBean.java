package com.xyp.mimi.mvp.http.entity;

import java.io.Serializable;

/**
 * Description:
 * Created by hjh on 2020/4/4.
 */
public class DetectRequestBean implements Serializable {

    private String resource_type;
    private String zdnsuser;//admin
    private String attrs;



    public interface ResourceType{
        String QUERY_DETECT_LIST = "get_nodes_info";//响应为List<DetectNode>,attrs为空
        String ADD_DETECT_DNS = "dig_item";//attrs,响应为List<DetectAttrs>
        String ADD_DETECT_PING = " ping_item";//attrs,响应为List<DetectAttrs>
        String ADD_DETECT_HTTP = "http_item";//attrs,响应为List<DetectAttrs>
        String QUERY_DNS_DATA = "get_dns_result";//响应为List<DetectRecord>,attrs为DetectAttrs
        String QUERY_PING_DATA = "get_ping_result ";//响应为List<DetectRecord>,attrs为DetectAttrs
        String QUERY_HTTP_DATA  = "get_http_result";//响应为List<DetectRecord>,attrs为DetectAttrs
    }

    public String getResource_type() {
        return resource_type;
    }

    public void setResource_type(String resource_type) {
        this.resource_type = resource_type;
    }

    public String getZdnsuser() {
        return zdnsuser;
    }

    public void setZdnsuser(String zdnsuser) {
        this.zdnsuser = zdnsuser;
    }

    public String getAttrs() {
        return attrs;
    }

    public void setAttrs(String attrs) {
        this.attrs = attrs;
    }
}
