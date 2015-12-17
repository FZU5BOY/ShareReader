package com.france.sharereader.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.france.sharereader.R;

import net.tsz.afinal.annotation.view.ViewInject;

/**
 * Created by Administrator on 2015/11/20.
 */
public class TopicDetailActivity extends BaseActivity{
    @ViewInject(id = R.id.tl_custom)
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_detail);
    }
}
