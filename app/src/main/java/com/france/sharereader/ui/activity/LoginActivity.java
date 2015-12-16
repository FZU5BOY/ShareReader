package com.france.sharereader.ui.activity;

import android.os.Bundle;

import com.france.sharereader.R;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

/**
 * Created by Lareina on 2015/11/22.
 */
public class LoginActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FinalActivity.initInjectedView(this);//实现IOC注解组件
    }
}
