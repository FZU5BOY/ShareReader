package com.france.sharereader.ui.activity;

/**
 * Created by Lareina on 2015/11/5.
 */

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.france.sharereader.R;
import com.france.sharereader.bean.Plan;
import com.france.sharereader.util.DateTimePickDialogUtil;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

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
    @ViewInject(id=R.id.save_plan)
    private Button save_plan;

    private String initDateTime;
    private Plan plan = new Plan();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_detail);
        FinalActivity.initInjectedView(this);//实现IOC注解组件

        plan_content.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        plan_content.setGravity(Gravity.TOP);
        plan_content.setSingleLine(false);
        plan_content.setHorizontallyScrolling(false);

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        initDateTime=sdf.format(new java.util.Date());
        display_time.setText(initDateTime);
        Bundle bundle = this.getIntent().getExtras();
        if(bundle!=null) {
            plan=(Plan)bundle.getSerializable("plan");
            title.setText(plan.getTitle());
            plan_content.setText(plan.getContent());
        }
        setTime();
        SavePlan();
        back();
    }

    private void setTime(){
        display_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        PlanDetailActivity.this, initDateTime);
                dateTimePicKDialog.dateTimePicKDialog(display_time);
            }
        });
        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        PlanDetailActivity.this, initDateTime);
                dateTimePicKDialog.dateTimePicKDialog(display_time);
            }
        });
    }
    private void SavePlan(){
        save_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(plan.getPlanId()<=0) {
                    ShowLog("insert into db success?:" + baseDaoImpl.addPlan(title.getText().toString().trim(), plan_content.getText().toString().trim(),display_time.getText().toString().trim()));
                }
                else{
                    plan.setTitle(title.getText().toString().trim());
                    plan.setContent(plan_content.getText().toString().trim());
                    plan.setRemindTime(display_time.getText().toString().trim());
                    ShowLog("update db success?:" + baseDaoImpl.update(plan));
                }
                Intent intent = new Intent(PlanDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void back(){
        back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(PlanDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(PlanDetailActivity.this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    private void editTitle(){
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}