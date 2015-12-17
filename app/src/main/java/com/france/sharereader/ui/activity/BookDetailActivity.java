package com.france.sharereader.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.france.sharereader.R;
import com.france.sharereader.config.Config;

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
        setContentView(R.layout.book_detail);
        FinalActivity.initInjectedView(this);//实现IOC注解组件
        book_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinalHttp fh = new FinalHttp();
                //调用download方法开始下载
                HttpHandler handler = fh.download("http://120.24.251.94:8888/ShareRead/file/README.md", //这里是下载的路径
                        //true:断点续传 false:不断点续传（全新下载）
                        Config.extern+"/ShareReader/download/README.md", //这是保存到本地的路径
                        true,
                        new AjaxCallBack<File>() {
                            @Override
                            public void onLoading(long count, long current) {
                                Log.i("zjx", "下载进度：" + current + "/" + count);
                            }
                            @Override
                            public void onSuccess(File f) {
                                Log.i("zjx",f == null ? "null" :f.getAbsoluteFile().toString());
                            }
                            @Override
                            public void onFailure(Throwable t, int errorNo, String strMsg) {
                                Log.i("zjx","下载失败"+strMsg);
                            }
                            @Override
                            public void onStart() {
                                Log.i("zjx","开始下载");
                            }
                        });
            }
        });
    }
}
