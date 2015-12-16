package com.france.sharereader.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.france.sharereader.dao.BaseDao;
import com.france.sharereader.dao.impl.BaseDaoImpl;

import cn.bmob.im.BmobUserManager;

public class BaseActivity extends ActionBarActivity {
    BmobUserManager userManager;
    public BaseDao baseDaoImpl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseDaoImpl=new BaseDaoImpl(BaseActivity.this);
        userManager = BmobUserManager.getInstance(this);
    }

    Toast mToast;

    public void ShowToast(final String text) {
        if (!TextUtils.isEmpty(text)) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    if (mToast == null) {
                        mToast = Toast.makeText(getApplicationContext(), text,
                                Toast.LENGTH_LONG);
                    } else {
                        mToast.setText(text);
                    }
                    mToast.show();
                }
            });

        }
    }

    public void ShowToast(final int resId) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                if (mToast == null) {
                    mToast = Toast.makeText(BaseActivity.this.getApplicationContext(), resId,
                            Toast.LENGTH_LONG);
                } else {
                    mToast.setText(resId);
                }
                mToast.show();
            }
        });
    }
    /** 打Log
     * ShowLog
     * @return void
     * @throws
     */
    public void ShowLog(String msg){
        Log.i("zjx", msg);
    }


}