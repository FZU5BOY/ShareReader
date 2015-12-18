package com.france.sharereader.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Lareina on 2015/12/17.
 */
public class BookCommentGrade extends BmobObject {
    private String bookName;
    private double eGrade;
    private int count;
    private String topic;
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double geteGrade() {
        return eGrade;
    }

    public void seteGrade(double eGrade) {
        this.eGrade = eGrade;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
