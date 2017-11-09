package com.gs.bean;

import java.io.Serializable;
import java.util.Date;

public class LeaveApply implements Serializable {
    private String username;
    private Integer days;
    private String reason;
    private Date submitTime;

    public LeaveApply() {

    }

    public LeaveApply(String username, Integer days, String reason, Date submitTime) {
        this.username = username;
        this.days = days;
        this.reason = reason;
        this.submitTime = submitTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    @Override
    public String toString() {
        return "LeaveApply{" + "username='" + username + '\'' + ", days=" + days + ", reason='" + reason + '\'' + ", submitTime=" + submitTime + '}';
    }
}
