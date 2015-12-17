package com.france.sharereader.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.france.sharereader.R;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

/**
 * Created by Administrator on 2015/11/20.
 */
public class TopicDetailActivity extends BaseActivity{
    @ViewInject(id = R.id.tl_custom)
    private Toolbar toolbar;
    @ViewInject(id=R.id.topic_book1)
    private ImageView topic_book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_detail);
        FinalActivity.initInjectedView(this);//实现IOC注解组件 放setContentView后
        topic_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent intent=new Intent(TopicDetailActivity.this,BookDetailActivity.class);
                startActivity(intent);
            }
        });
    }
}
