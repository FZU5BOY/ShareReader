package com.france.sharereader.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
    private ProgressDialog progressDialog;
    // 记录进度对话框的完成百分比
    int progressStatus = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail);
        FinalActivity.initInjectedView(this);//实现IOC注解组件
        final Handler updateHandler = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                // 表明消息是由该程序发送的。
                if (msg.what == 0x123)
                {
                    progressDialog.setProgress(progressStatus);
                }
            }
        };
        book_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinalHttp fh = new FinalHttp();
                //调用download方法开始下载
                File destDir = new File(Config.extern+"/ShareReader");
                if (!destDir.exists()) {
                    destDir.mkdirs();
                }
                HttpHandler handler = fh.download("http://120.24.251.94:8888/ShareRead/file/构建之法：现代软件工程.epub", //这里是下载的路径
                        //true:断点续传 false:不断点续传（全新下载）
                        Config.extern+"/ShareReader/构建之法：现代软件工程.epub", //这是保存到本地的路径
                        true,
                        new AjaxCallBack<File>() {
                            @Override
                            public void onLoading(long count, long current) {
                                progressStatus = (int)(100.0*(double)current/(double)count);
                                // 发送空消息到Handler
//                                updateHandler.sendEmptyMessage(0x123);
                                progressDialog.setProgress(progressStatus);
                                Log.i("zjx", "下载进度：" + current + "/" + count);
                            }
                            @Override
                            public void onSuccess(File f) {
                                Log.i("zjx",f == null ? "null" :f.getAbsoluteFile().toString());
                                progressDialog.dismiss();//销毁对话框
                                ShowToast("下载成功！请在sd卡根目录ShareReader文件夹下查找");
                            }
                            @Override
                            public void onFailure(Throwable t, int errorNo, String strMsg) {
                                Log.i("zjx","下载失败"+strMsg);
                            }
                            @Override
                            public void onStart() {
                                progressDialog=new ProgressDialog(BookDetailActivity.this);
                                progressDialog.setIcon(R.drawable.ic_launcher);
                                progressDialog.setTitle("构建之法");
                                progressDialog.setMessage("下载中...");
                                // 设置对话框不能用“取消”按钮关闭
                                progressDialog.setCancelable(false);
                                // 设置对话框的进度条风格
                                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                                // 设置对话框的进度条是否显示进度
                                progressDialog.setIndeterminate(true);
                                Log.i("zjx","开始下载");
                                progressDialog.show();
                            }
                        });
            }
        });
    }
}
