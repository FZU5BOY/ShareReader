package com.france.sharereader.ui.activity;


import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.france.sharereader.R;
import com.france.sharereader.config.Config;

import cn.bmob.im.BmobChat;


public class SplashActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.RGBA_8888);

        setContentView(R.layout.splash);
        //可设置调试模式，当为true的时候，会在logcat的BmobChat下输出一些日志，包括推送服务是否正常运行，如果服务端返回错误，也会一并打印出来。方便开发者调试
        BmobChat.DEBUG_MODE = true;
        //BmobIM SDK初始化--只需要这一段代码即可完成初始化
        BmobChat.getInstance(this).init(Config.applicationId);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                /* Create an Intent that will start the Main  Activity. */
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, 1500); //2000 for release

    }
}
