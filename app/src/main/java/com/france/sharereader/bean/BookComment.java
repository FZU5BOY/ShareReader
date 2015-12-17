package com.france.sharereader.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Lareina on 2015/12/17.
 */
public class BookComment extends BmobObject {
    private String bookName;
    private String commentContent;
    private double grade;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

}
