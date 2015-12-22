package com.france.sharereader.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;


import com.france.sharereader.R;
import com.france.sharereader.bean.BookComment;
import com.france.sharereader.bean.BookCommentGrade;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

import java.util.List;
import java.util.Objects;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;


/**
 * Created by Lareina on 2015/12/1.
 */
public class CommentActivity extends BaseActivity {
    @ViewInject(id=R.id.submit_comm)
    private Button submit;
    @ViewInject(id=R.id.cancel_comm)
    private Button cancel;
    @ViewInject(id=R.id.comm_content)
    private TextView comm_content;
    @ViewInject(id=R.id.rate_stars)
    private RatingBar rate_star;
    private String bookname;
    private String bookTopic;
    private String commContent;
    private double rateStars;
    BookCommentGrade b = new BookCommentGrade();
    private int count;
    private  double egrade;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_star);
       // comment event
        FinalActivity.initInjectedView(this);//实现IOC注解组件
        Bundle bundle = this.getIntent().getExtras();
        if(bundle!=null) {
           bookname=bundle.getString("bookname");
           bookTopic=bundle.getString("bookTopic");
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                commContent = comm_content.getText().toString();
                rateStars = rate_star.getRating();
                addComment();
                queryCommentGrade();
                Intent intent = new Intent(CommentActivity.this, BookDetailActivity.class);
                startActivity(intent);
                CommentActivity.this.finish();
            }
        });
        // cancel event
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("zjx", "comment canceled,back to shop detail");
                Intent intent = new Intent(CommentActivity.this, PDFViewActivity.class);
                startActivity(intent);
                CommentActivity.this.finish();
            }
        });
    }
    private void addComment(){
        BookComment b1 = new BookComment();
        b1.setBookName(bookname);
        b1.setCommentContent(commContent);
        b1.setGrade(rateStars);
        b1.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                //toast("添加数据成功，返回objectId为：" + p2.getObjectId());
            }

            @Override
            public void onFailure(int code, String msg) {
                // TODO Auto-generated method stub
                //toast("创建数据失败：" + msg);
            }
        });
    }
    private void addCommentGrade(){
        b.setBookName(bookname);
        b.setCount(1);
        b.seteGrade(rateStars);
        b.setTopic(bookTopic);
        b.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                ShowToast("添加评论成功");
            }

            @Override
            public void onFailure(int code, String msg) {
                // TODO Auto-generated method stub
                ShowToast("创建评论失败：" + msg);
            }
        });
    }
//    private void calculateGrade(){
//        if(b.getBookName()!=null){
//            b.seteGrade(rateStars);
//            b.setCount(1);
//        }else{
//            queryCommentGrade();
//        }
//    }
    private void queryCommentGrade(){
        BmobQuery<BookCommentGrade> bmobQuery = new BmobQuery<BookCommentGrade>();
        bmobQuery.addWhereEqualTo("bookName",bookname);
        bmobQuery.findObjects(this, new FindListener<BookCommentGrade>(){
            @Override
            public void onSuccess(List<BookCommentGrade> object) {
                // TODO Auto-generated method stub
                    for (BookCommentGrade bookCommentGrade : object) {
                        count = bookCommentGrade.getCount();
                        egrade = bookCommentGrade.geteGrade();
                        id = bookCommentGrade.getObjectId();
                    }
                    //ShowToast("查询成功:" + count + "___" + egrade);
                    updateCommentGrade();
                }

            @Override
            public void onError(int code, String msg) {
                // TODO Auto-generated method stub
                //ShowToast("查询失败：" + msg);
                addCommentGrade();
            }
        });

    }
    private void updateCommentGrade() {
        BookCommentGrade p2 = new BookCommentGrade();
        p2.seteGrade((count*egrade+rateStars)/(count+1));
        p2.setCount(++count);
        p2.update(this, id, new UpdateListener() {
            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                //ShowToast("更新成功");
            }
            @Override
            public void onFailure(int code, String msg) {
                // TODO Auto-generated method stub
                //ShowToast("更新失败：" + msg);
            }
        });
    }
}
