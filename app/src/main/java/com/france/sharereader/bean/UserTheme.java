package com.france.sharereader.bean;

import net.tsz.afinal.annotation.sqlite.Id;
import net.tsz.afinal.annotation.sqlite.OneToMany;
import net.tsz.afinal.annotation.sqlite.Table;
import net.tsz.afinal.db.sqlite.OneToManyLazyLoader;

/**
 * Created by Administrator on 2015/10/29.
 */
@Table(name="user_theme")
public class UserTheme {
    @Id(column="userThemeId")
    private int userThemeId;
    private int userId;
    private int themeId;

    public int getUserThemeId() {
        return userThemeId;
    }

    public void setUserThemeId(int userThemeId) {
        this.userThemeId = userThemeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }
}
