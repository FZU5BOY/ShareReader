package com.france.sharereader.bean;


import cn.bmob.im.bean.BmobChatUser;

/**
 * Created by Administrator on 2015/10/29.
 */
public class User extends BmobChatUser {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * //显示数据拼音的首字母
     */
    private String sortLetters;

    /**
     * //性别-true-男
     */
    private Boolean sex;

    private Integer hight;
    public Integer getHight() {
        return hight;
    }
    public void setHight(Integer hight) {
        this.hight = hight;
    }
    public Boolean getSex() {
        return sex;
    }
    public void setSex(Boolean sex) {
        this.sex = sex;
    }
    public String getSortLetters() {
        return sortLetters;
    }
    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }
}