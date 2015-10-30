package com.france.sharereader.dao.impl;

import android.content.Context;

import com.france.sharereader.R;
import com.france.sharereader.bean.User;
import com.france.sharereader.dao.BaseDao;
import com.france.sharereader.util.UuidUtil;

import net.tsz.afinal.FinalDb;

/**
 * Created by Administrator on 2015/10/30.
 */
public class BaseDaoImpl implements BaseDao {
    Context context;

    public BaseDaoImpl(Context context){
        this.context=context;
    }
    public void save(User user){
        FinalDb db = FinalDb.create(this.context, context.getResources().getString(R.string.app_name));
        db.save(user);
    }
}
