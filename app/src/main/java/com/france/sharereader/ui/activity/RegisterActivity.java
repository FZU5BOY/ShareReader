package com.france.sharereader.ui.activity;

import android.os.Bundle;

import com.france.sharereader.R;

import net.tsz.afinal.FinalActivity;

/**
 * Created by Lareina on 2015/11/22.
 */
public class RegisterActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FinalActivity.initInjectedView(this);//实现IOC注解组件
    }
}
