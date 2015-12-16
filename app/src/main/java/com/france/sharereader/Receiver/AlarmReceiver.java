package com.france.sharereader.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.france.sharereader.ui.activity.AlarmActivity;

/**
 * Created by Administrator on 2015/12/16.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i=new Intent(context, AlarmActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
