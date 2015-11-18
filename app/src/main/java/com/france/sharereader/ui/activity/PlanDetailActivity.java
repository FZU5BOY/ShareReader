package com.france.sharereader.ui.activity;

/**
 * Created by Lareina on 2015/11/5.
 */

import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.france.sharereader.R;
import com.france.sharereader.util.DateTimePickDialogUtil;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlanDetailActivity extends BaseActivity {
    @ViewInject(id = R.id.my_title)
    private TextView title;
    @ViewInject(id = R.id.my_clock)
    private ImageView clock;
    @ViewInject(id = R.id.my_plan)
    private EditText plan_content;
    @ViewInject(id=R.id.back_home)
    private ImageView back_home;
    @ViewInject(id=R.id.display_time)
    private EditText display_time;
    private String plan_message;
    private String plan_title;
    private String initDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_detail);
        FinalActivity.initInjectedView(this);//实现IOC注解组件
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        initDateTime=sdf.format(new java.util.Date());
        display_time.setText(initDateTime);
        Bundle bundle = this.getIntent().getExtras();
        plan_title = bundle.getString("title");
        title.setText(plan_title);
        initTextTitle();
        initEditText();
        setTime();
        back();
    }

    private void initTextTitle(){
        title.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                EditText clickEditText = (EditText) v;
                //获取当前文本
                plan_title = clickEditText.getText().toString().trim();
                return false;
            }
        });
    }
    private void initEditText(){
        plan_content.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //获取触发事件的EditText
                EditText clickEditText = (EditText)v;
                //获取当前文本
                plan_message =clickEditText.getText().toString().trim();
                return false;
            }
        });
    }
    private void setTime(){
        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        PlanDetailActivity.this, initDateTime);
                dateTimePicKDialog.dateTimePicKDialog(display_time);
            }
        });
    }
    private void back(){
        back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}