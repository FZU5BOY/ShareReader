package com.france.sharereader.config;

import android.os.Environment;

/**
 * Created by Administrator on 2015/11/20.
 */
public  class Config {
    public static String applicationId = "667ad374691382edeb12f4fdc226f061";//这是Bmob的ApplicationId,用于初始化操作
    static public String[] TOPICS = {"编译原理", "软件工程", "数据挖掘", "电子设计"};
    public static String extern = Environment.getExternalStorageDirectory().getPath();
}
