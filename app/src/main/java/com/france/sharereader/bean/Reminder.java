package com.france.sharereader.bean;

import net.tsz.afinal.annotation.sqlite.Id;
import net.tsz.afinal.annotation.sqlite.ManyToOne;
import net.tsz.afinal.annotation.sqlite.Table;

/**
 * Created by Administrator on 2015/10/29.
 */
@Table(name="reminder")
public class Reminder {
    @Id(column="reminderId")
    private int reminderId;
    private String remindTime;

    public int getReminderId() {
        return reminderId;
    }

    public void setReminderId(int reminderId) {
        this.reminderId = reminderId;
    }

    public String getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(String remindTime) {
        this.remindTime = remindTime;
    }
}
