package com.xyp.mimi.im.net.hjh;

public interface HttpType {
	int USER_LOGIN = 1;//登录
	int GET_DEPARTMENT_LIST = 2;//获取单位列表
	int GET_SCHOOL_LIST = 3;//获取学校列表
	int GET_EFFECT_INFO = 4;//获取用水分类及水表信息
	int GET_WARNING_LIST = 5;//获取告警列表
	int GET_WARNING_STATISTICS = 6;//获取告警统计
	int GET_AREA_LIST         = 7;//获取区域列表
	int GET_REPORT_DAY_LIST         = 8;//获取日报列表
	int GET_REPORT_MONTH_LIST         = 9;//获取月报列表
	int GET_REPORT_YEAR_LIST         = 10;//获取年报列表

	int UPDATE_USER_PWD = 11;//更新密码
	int CHECK_UPDATE = 12;//检查更新
	int SAVE_SUGGEST = 13;//保存意见

}
