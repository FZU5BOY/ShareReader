package com.france.sharereader.dao;

import android.content.Context;

import com.france.sharereader.R;
import com.france.sharereader.bean.Book;
import com.france.sharereader.bean.Plan;
import com.france.sharereader.bean.Theme;
import com.france.sharereader.bean.User;
import com.france.sharereader.util.UuidUtil;

import net.tsz.afinal.FinalDb;

import java.util.List;

/**
 * Created by Administrator on 2015/10/30.
 */
public interface BaseDao {
     Book addPdf(String pdfName,String pdfPath);
     List<Book> findAllBook();
     boolean update(Object o);
     boolean save(Object o);
     boolean addPlan(String PlanTitle,String content,String addtime);
     List<Plan> FindAllPlan();
     boolean addTopic(String topic);
     List<Theme> FindAllTopic();
     boolean delete(Object o);
}
