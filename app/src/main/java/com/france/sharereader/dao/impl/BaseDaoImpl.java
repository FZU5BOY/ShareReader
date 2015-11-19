package com.france.sharereader.dao.impl;

import android.content.Context;

import com.france.sharereader.R;
import com.france.sharereader.bean.Book;
import com.france.sharereader.bean.User;
import com.france.sharereader.dao.BaseDao;
import com.france.sharereader.util.UuidUtil;

import net.tsz.afinal.FinalDb;

import java.util.Date;

/**
 * Created by Administrator on 2015/10/30.
 */
public class BaseDaoImpl implements BaseDao {
    Context context;
    FinalDb db ;
    public BaseDaoImpl(Context context){
        this.context=context;
        db=FinalDb.create(this.context, context.getResources().getString(R.string.app_name));
    }

    @Override
    public boolean addPdf(String pdfName, String pdfPath) {
        if(db.findAllByWhere(Book.class,"bookName='"+pdfName+"'").size()>0){
            return false;
        }
        else{
            Book book=new Book();
            book.setBookName(pdfName);
            book.setLocalPath(pdfPath);
            book.setCreatTime(new Date().toString());
            db.save(book);
            return true;
        }
    }

    @Override
    public boolean update(Object o) {
        db.update(o);
        return true;
    }

    @Override
    public boolean save(Object o) {
        db.save(o);
        return true;
    }
}
