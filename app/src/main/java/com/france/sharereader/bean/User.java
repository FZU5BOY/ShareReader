package com.france.sharereader.bean;

import net.tsz.afinal.annotation.sqlite.Id;
import net.tsz.afinal.annotation.sqlite.OneToMany;
import net.tsz.afinal.annotation.sqlite.Table;
import net.tsz.afinal.db.sqlite.OneToManyLazyLoader;

/**
 * Created by Administrator on 2015/10/29.
 */
@Table(name="user")
public class User {
    @Id(column="userId")
    private String userId;
    private String username;
    private String password;
    private String email;
    @OneToMany(manyColumn = "userId")
    private OneToManyLazyLoader<User ,Plan> plan;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OneToManyLazyLoader<User, Plan> getPlan() {
        return plan;
    }

    public void setPlan(OneToManyLazyLoader<User, Plan> plan) {
        this.plan = plan;
    }
}
