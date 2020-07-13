package com.xyp.mimi.im.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import android.annotation.SuppressLint;

@SuppressLint("NewApi")
public class StringTools {

	/**
	 * 字符串是否为空
	 * @param value
	 * @return
	 */
	public static boolean isNotEmpty(String value){

		if(value == null ||  "".equals(value) || value.isEmpty()){
			return false;
		}

		int length = value.length();

		for(int index = 0 ; index < length ; index++){
			char c = value.charAt(index);

			if(c != ' ' && c != '\n'  && c != '\t' && c != '\r'){
				return true;
			}
		}

		return false;
	}


	// Email正则表达式
	public static final String EMAIL_PATTERN = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

	// 手机号正则表达式
//	public static final String PHONE_PATTERN = "[1][3456789]\\d{9}";
	public static final String PHONE_PATTERN = "^(\\+?\\d+)?1[3456789]\\d{9}$";

	// 座机号码正则表达式
	public static final String WORK_PHONE_PATTERN  ="^(0[0-9]{2,3}/-)?([2-9][0-9]{6,7})+(/-[0-9]{1,4})?$";
//	public static final String WORK_PHONE_PATTERN  ="^\\d{3}-?\\d{8}|\\d{4}-?\\d{8}$";

	// 密码格式验证
	public static final String PASSWORD_PATTERN = "^[a-zA-Z0-9_@]{6,20}$";

	// 特殊字符串
	public static final String SPECIAL_STRING_PATTERN = "[`~!@#$%^&*()+=|{}':;'\",\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

	//
	private static final Map<String, String> HtmlChars = new HashMap<String, String>();

	static {
		HtmlChars.put("<", "&#060;");
		HtmlChars.put(">", "&#062;");
		//HtmlChars.put(" ", "&#160;");
		HtmlChars.put("@", "&#064;");
		HtmlChars.put("&", "&#038;");
		//HtmlChars.put("'", "&#39;");
		//HtmlChars.put("\"", "&#34;");
	}

	/**
	 * 判断对象是否为空，是否等于对象“null”，是否是空对象
	 * @param
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		return (obj == null || "".equals(obj) || "null".equals(obj));
	}


	public static boolean isEmpty(String source) {

		if (source == null || "".equals(source)  || source.trim().length() == 0 )
			return true;

		for (int i = 0; i < source.length(); i++) {
			char c = source.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}

	public static boolean matches(String source, String pattern) {

		if (source == null || !source.matches(pattern)) {
			return false;
		}

		return true;
	}

	public static boolean isSpecialString(String source) {
		return matches(source, SPECIAL_STRING_PATTERN);
	}

	public static boolean isEmail(String source) {
		return matches(source, EMAIL_PATTERN);
	}

	public static boolean isPhone(String source) {
		return matches(source, PHONE_PATTERN);
	}

	public static boolean isWorkPhone(String source){

		if(source == null || source.isEmpty() || source.length() < 10){
			return false;
		}

		for(int index = 0;index < source.length();index ++){

			char c=	source.charAt(index);

			if(index ==0 && c != '0'){
				return false;
			}

			if(index == 1 && c == '0'){
				return false;//第2位为0代表非座机
			}
		}

		return true;
	}

	public static boolean isPassword(String source) {
		return matches(source, PASSWORD_PATTERN);
	}

	public static boolean isHasEmptyChar(String source) {

		if (source == null) {
			return true;
		}

		return source.trim().indexOf(' ') >= 0;
	}

	public static String toNotNullString(String source) {
		return source == null ? "" : source;
	}

	public static String arrayToString(String[] array, String separator) {

		if (array == null || array.length == 0) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		for (String temp : array) {
			result.append(temp).append(separator);
		}
		return result.substring(0, result.length() - 2);
	}

	public static String[] toArray(String source) {

		if (isEmpty(source)) {
			return null;
		}
		return source.split(",");
	}

//	public static String[] toArray(Object[] sources) {
//
//		if (sources == null || sources.length == 0) {
//			return null;
//		}
//		String[] temps = new String[sources.length];
//		String value = "";
//		for (int i = 0; i < temps.length; i++) {
//
//			if (sources[i] == null) {
//				continue;
//			}
//			if (Date.class.equals(sources[i].getClass())) {
//				value = DateTools.toDateString((Date) sources[i],
//						DateTools.DATE_TIME_FORMAT);
//			} else {
//				value = sources[i].toString();
//			}
//			temps[i] = value;
//		}
//		return temps;
//	}

	public static String capitalize(String str) {

		if (str == null || str.length() == 0) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str.length());
		sb.append(Character.toUpperCase(str.charAt(0)));
		sb.append(str.substring(1));
		return sb.toString();
	}

	public static String getTimeName() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return dateFormat.format(date);
	}

	public static String getAdress(String string) {
		final String BZ = "省|市|自治区|行政区|特别行政区";
		String[] arr = string.split(BZ);
		String text = arr[arr.length - 1];
		return text;
	}

	public static boolean larger(String source, int length) {
		return (source != null && source.length() > length);
	}

	public static String toString(Object source) {
		return (source == null ? null : source.toString());
	}

	public static String generatorGUID(){
		return UUID.randomUUID().toString() ;
	}

	public static String replaceHtml(String s){

		if(s == null) return "" ;

		String out = s ;

		for(String ch : HtmlChars.keySet()){
			out = out.replaceAll(ch, HtmlChars.get(ch));
		}

		return out ;
	}
}
