package com.france.sharereader.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.france.sharereader.R;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;


/**
 * Created by Lareina on 2015/12/1.
 */
public class CommentActivity extends BaseActivity {
    @ViewInject(id=R.id.submit_comm)
    private Button submit;
    @ViewInject(id=R.id.cancel_comm)
    private Button cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_star);
//        comment event
        FinalActivity.initInjectedView(this);//实现IOC注解组件
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            }
        });
//        cancel event

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

}
