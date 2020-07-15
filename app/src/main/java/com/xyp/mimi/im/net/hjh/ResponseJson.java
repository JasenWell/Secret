package com.xyp.mimi.im.net.hjh;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 返回data为对象
 * @author hjh
 * 2015-1-8上午4:10:54
 * @param <T>
 */
public final class ResponseJson<T> implements Serializable {

	private static final long serialVersionUID = -4214315238349813842L;
	@Expose
	@SerializedName("code")
	private int status;
	
	@Expose
	@SerializedName("msg")
	private String info;
	
	@Expose
	private  T data;

	@Expose
	private String gisurl;

	@Expose
	private String yanzhenma;

	private String json;//原始json数据

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static  ResponseJson fromJson(String json, Class clazz) {
        Gson gson = new Gson();
        Type objectType = type(ResponseJson.class, clazz);
        return gson.fromJson(json, objectType);
    }
	
	/**
	 * 只导出注解
	 * @param json
	 * @param clazz
	 * @param expose
	 * @return
	 */
	public static ResponseJson fromJson(String json, Class clazz,boolean expose){
		 Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	        Type objectType = type(ResponseJson.class, clazz);
	        return gson.fromJson(json, objectType);
	}

	/**
	 * 将对象转换为JSON字符串(Object to json string)
	 * @param object
	 * @return
	 */
	public static String objectToJson(Object object){
		return new Gson().toJson(object);
	}

    public  String toJson(Class<T> clazz) {
        Gson gson = new Gson();
        Type objectType = type(ResponseJson.class, clazz);
        return gson.toJson(this, objectType);
    }

    private static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }

	public String getGisurl() {
		return gisurl;
	}

	public void setGisurl(String gisurl) {
		this.gisurl = gisurl;
	}

	public String getYanzhenma() {
		return yanzhenma;
	}

	public void setYanzhenma(String yanzhenma) {
		this.yanzhenma = yanzhenma;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
}
