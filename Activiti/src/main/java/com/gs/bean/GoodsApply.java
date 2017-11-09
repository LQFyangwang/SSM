package com.gs.bean;

import java.io.Serializable;
import java.util.Date;

public class GoodsApply implements Serializable {

    private String goodsName;
    private Integer quantity;
    private String reason;
    private Date returnTime;
    private Date submitTime;

    public GoodsApply() {
    }

    public GoodsApply(String goodsName, Integer quantity, String reason, Date returnTime, Date submitTime) {
        this.goodsName = goodsName;
        this.quantity = quantity;
        this.reason = reason;
        this.returnTime = returnTime;
        this.submitTime = submitTime;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    @Override
    public String toString() {
        return "GoodsApply{" + "goodsName='" + goodsName + '\'' + ", quantity=" + quantity + ", reason='" + reason + '\'' + ", returnTime=" + returnTime + ", submitTime=" + submitTime + '}';
    }
}
