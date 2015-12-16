package com.france.sharereader.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.france.sharereader.R;
import com.france.sharereader.bean.User;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

import cn.bmob.im.util.BmobLog;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Lareina on 2015/11/22.
 */
public class RegisterActivity extends BaseActivity{
    @ViewInject(id=R.id.et_username)
    private EditText nickName;
    @ViewInject(id=R.id.et_password)
    private EditText password01;
    @ViewInject(id=R.id.et_password_again)
    private EditText password02;
    @ViewInject(id=R.id.btn_register)
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FinalActivity.initInjectedView(this);//实现IOC注解组件
        onClickRegister();
    }
    private void onClickRegister(){
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }
    private void register(){
        String name = nickName.getText().toString().trim();
        String password = password01.getText().toString().trim();
        String pwd_again = password02.getText().toString().trim();
        if (name.length() <= 0 || password.length() <= 0 || pwd_again.length() <= 0) {
            ShowToast(R.string.toast_register_all_error);
            return;
        }
        if (password.length() < 6 ) {
            ShowToast(R.string.toast_register_pswlenth_error);
            return;
        }

        if (!password.equals(pwd_again)) {
            ShowToast(R.string.toast_register_psw_unequal_error);
            return;
        }

        final ProgressDialog progress = new ProgressDialog(RegisterActivity.this);
        progress.setMessage("正在注册...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();
        final User bu = new User();
        bu.setUsername(name);//昵称
        bu.setPassword(password);
        //将user和设备id进行绑定aa
        bu.setSex(true);
        bu.setDeviceType("android");
        bu.setInstallId(BmobInstallation.getInstallationId(this));
        bu.signUp(RegisterActivity.this, new SaveListener() {
            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                progress.dismiss();
                ShowToast(R.string.toast_register_success);
                // 将设备与username进行绑定
                userManager.bindInstallationForRegister(bu.getUsername());
                // 启动主页
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(int arg0, String arg1) {
                // TODO Auto-generated method stub
                BmobLog.i(arg1);
                ShowToast("注册失败:" + arg1);
                progress.dismiss();
            }
        });
    }
}
