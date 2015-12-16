package com.france.sharereader.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.france.sharereader.R;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.HttpHandler;

import java.io.File;

/**
 * Created by Administrator on 2015/11/20.
 */
public class BookDetailActivity extends BaseActivity{
    @ViewInject(id = R.id.book_download)
    private Button book_download;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FinalActivity.initInjectedView(this);//实现IOC注解组件
        setContentView(R.layout.topic_detail);
        book_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinalHttp fh = new FinalHttp();
                //调用download方法开始下载
                HttpHandler handler = fh.download("http://120.24.251.94:8888/ShareRead/file/README.md", //这里是下载的路径
                        true,//true:断点续传 false:不断点续传（全新下载）
                        "/mnt/sdcard/ShareReader/download/", //这是保存到本地的路径
                        new AjaxCallBack() {
                            @Override
                            public void onLoading(long count, long current) {
                                Log.i("zjx", "下载进度：" + current + "/" + count);
                            }
                            @Override
                            public void onSuccess(File t) {
//                                textView.setText(t==null?"null":);
                                Log.i("zjx","");
                            }

                        });


                //调用stop()方法停止下载
                handler.stop();
            }
        });
    }
}
