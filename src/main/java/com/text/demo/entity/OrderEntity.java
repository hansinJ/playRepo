package com.text.demo.entity;

import java.util.Date;
import java.util.List;

public class OrderEntity {
    int id;  //订单编号
    int playId; //陪玩编号
    double price;   //单价
    int hour;   //小时
    double totalAmount; //总金额
    double payAmount;   //支付金额
    String payStatus;   //支付状态
    Date createTime;    //预约时间
    Date payTimeDate;   //支付时间
    String payTime; //转换的支付时间
    String playName; // 陪玩名称
    String playType;
    String contactPhone; //联系方式
    String userName;//用户昵称
    List<PlayStateEntity> playStateList;  //陪玩类型列表
    public int getId() {
        return id;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getPlayType() {
        return playType;
    }

    public String getPlayName() {
        return playName;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public Date getPayTimeDate() {
        return payTimeDate;
    }

    public void setPayTimeDate(Date payTimeDate) {
        this.payTimeDate = payTimeDate;
    }

    public void setPlayName(String playName) {
        this.playName = playName;
    }

    public List<PlayStateEntity> getPlayStateList() {
        return playStateList;
    }

    public void setPlayStateList(List<PlayStateEntity> playStateList) {
        this.playStateList = playStateList;
    }

    public void setPlayType(String playType) {
        this.playType = playType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayId() {
        return playId;
    }

    public void setPlayId(int playId) {
        this.playId = playId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
