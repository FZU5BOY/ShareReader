package com.france.sharereader.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Lareina on 2015/11/20.
 */
//注意：receiver记得在manifest.xml注册
public  class alarmreceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
// TODO Auto-generated method stub
        if(intent.getAction().equals("short")){
            Toast.makeText(context, "short alarm", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context, "repeating alarm",Toast.LENGTH_LONG).show();
        }
    }
}
