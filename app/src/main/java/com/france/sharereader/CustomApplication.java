package com.france.sharereader;

import android.app.Application;

/**
 * 自定义全局Applcation类
 * @ClassName: CustomApplcation
 * @Description: TODO
 * @author smile
 * @date 2014-5-19 下午3:25:00
 */
public class CustomApplication extends Application {

	public static CustomApplication mInstance;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		// 是否开启debug模式--默认开启状态
		mInstance = this;
	}
	public static CustomApplication getInstance() {
		return mInstance;
	}

}
