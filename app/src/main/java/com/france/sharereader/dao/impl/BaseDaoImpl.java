package com.france.sharereader.dao.impl;

import android.content.Context;

import com.france.sharereader.R;
import com.france.sharereader.bean.Book;
import com.france.sharereader.bean.Plan;
import com.france.sharereader.bean.Theme;
import com.france.sharereader.dao.BaseDao;

import net.tsz.afinal.FinalDb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public Book addPdf(String pdfName, String pdfPath) {
        Book book=new Book();
        List<Book> bookList=new ArrayList<>();
        bookList=db.findAllByWhere(Book.class,"bookName='"+pdfName+"'");
        if(bookList.size()>0){
            return bookList.get(0);
        }
        else{
            book.setBookName(pdfName);
            book.setLocalPath(pdfPath);
            book.setCreatTime(new Date().toString());
//            book.setThemeId(-1);
//            book.setProgress(1);
            db.save(book);
            return book;
        }
    }
    @Override
    public boolean addPlan(String planTitle,String content,String addtime){
        Plan plan=new Plan();
        plan.setTitle(planTitle);
        plan.setContent(content);
        plan.setCreatTime(new Date().toString());
        plan.setRemindTime(addtime);
        db.save(plan);
        return true;
    }
    @Override
    public  List<Plan> FindAllPlan(){
        List<Plan> plan = db.findAll(Plan.class);
        return plan;
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

    @Override
    public List<Book> findAllBook() {
        return db.findAll(Book.class);
    }

    @Override
    public boolean addTopic(String topic){
        if(db.findAllByWhere(Theme.class,"name='"+topic+"'").size()>0){
            return false;
        }
        else {
            Theme theme = new Theme();
            theme.setName(topic);
            db.save(theme);
            return true;
        }
    }
    @Override
    public List<Theme> FindAllTopic(){
        return db.findAll(Theme.class);
    }
}
