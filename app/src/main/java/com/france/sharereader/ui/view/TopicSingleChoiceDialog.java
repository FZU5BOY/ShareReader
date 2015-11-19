package com.france.sharereader.ui.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/11/15.
 */
public class TopicSingleChoiceDialog extends AlertDialog.Builder implements DialogInterface.OnClickListener {
    private int topicID=-1;//真正要获取的数据
    private OnTestListening onTestListening;
    public TopicSingleChoiceDialog(Context context,OnTestListening onTestListening) {
        super(context);
        this.onTestListening=onTestListening;
        this.setTitle("选择所属话题");
        final String[] hobbies = {"编译原理", "软件工程", "数据挖掘", "电子设计"};
        //    设置一个单项选择下拉框
        /**
         * 第一个参数指定我们要显示的一组下拉多选框的数据集合
         * 第二个参数代表哪几个选项被选择，如果是null，则表示一个都不选择，如果希望指定哪一个多选选项框被选择，
         * 需要传递一个boolean[]数组进去，其长度要和第一个参数的长度相同，例如 {true, false, false, true};
         * 第三个参数给每一个多选项绑定一个监听器
         */
        this.setSingleChoiceItems(hobbies, 0, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                setTopicID(which);
            }
        });
        this.setPositiveButton("确定", this);
        this.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        this.show();
    }
    public interface OnTestListening{
         void getTopicID(int topicID);
    }


    public void setTopicID(int topicID) {
        this.topicID = topicID;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        onTestListening.getTopicID(topicID);
    }
}
