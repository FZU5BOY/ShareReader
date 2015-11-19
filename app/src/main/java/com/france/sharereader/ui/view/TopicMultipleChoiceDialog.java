package com.france.sharereader.ui.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.france.sharereader.ui.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/15.
 */
public class TopicMultipleChoiceDialog extends AlertDialog.Builder implements DialogInterface.OnClickListener{
    final String[] hobbies = {"编译原理", "软件工程", "数据挖掘", "电子设计"};
    boolean[] isSelectItem=new boolean[hobbies.length];
    private OnTestListening onTestListening;
    public TopicMultipleChoiceDialog(final Context context,OnTestListening onTestListening) {
        super(context);
        this.setTitle("选择所属话题");
        this.onTestListening=onTestListening;
        //    设置一个单项选择下拉框
        /**
         * 第一个参数指定我们要显示的一组下拉多选框的数据集合
         * 第二个参数代表哪几个选项被选择，如果是null，则表示一个都不选择，如果希望指定哪一个多选选项框被选择，
         * 需要传递一个boolean[]数组进去，其长度要和第一个参数的长度相同，例如 {true, false, false, true};
         * 第三个参数给每一个多选项绑定一个监听器
         */
        this.setMultiChoiceItems(hobbies, null, new DialogInterface.OnMultiChoiceClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                isSelectItem[which]=isChecked;
            }
        });
        this.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            StringBuffer sb = new StringBuffer(100);



            @Override
            public void onClick(DialogInterface dialog, int which) {
                for(int i=0;i<isSelectItem.length;i++){
                    if (isSelectItem[i]) {
                        sb.append(hobbies[i] + ", ");
                    }
                }
                Toast.makeText(context, "爱好为：" + sb.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        this.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        this.show();
    }
    public interface OnTestListening{
        void getMutilChoice(boolean[] isSelectItem);
    }
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        onTestListening.getMutilChoice(isSelectItem);
    }
}
