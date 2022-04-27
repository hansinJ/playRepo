package com.text.demo.entity;

import java.util.Date;
import java.util.List;

public class PeopleEntity {
    int id;
    String nickName;
    String portrait;
    String lifePhoto;
    String intro;
    String phone;
    String businessStatus;
    double price;
    double discountPrice;
    double packagePrice;
    Date createTime;
    int createUser;
    int lastUpdateUser;
    Date lastUpdateTime;
    int playState;
    String playName;
    String labels;
    String makeMoney;
    String status;
    String realName;
    List<PlayStateEntity> playStateList;
    List<Integer> playStates;
    private Integer sort;

    private Integer sortTemp;

    public Integer getSortTemp() {
        return sortTemp;
    }

    public void setSortTemp(Integer sortTemp) {
        this.sortTemp = sortTemp;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Integer> getPlayStates() {
        return playStates;
    }

    public void setPlayStates(List<Integer> playStates) {
        this.playStates = playStates;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMakeMoney() {
        return makeMoney;
    }

    public List<PlayStateEntity> getPlayStateList() {
        return playStateList;
    }

    public void setPlayStateList(List<PlayStateEntity> playStateList) {
        this.playStateList = playStateList;
    }

    public void setMakeMoney(String makeMoney) {
        this.makeMoney = makeMoney;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPlayName() {
        return playName;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public void setPlayName(String playName) {
        this.playName = playName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getLifePhoto() {
        return lifePhoto;
    }

    public void setLifePhoto(String lifePhoto) {
        this.lifePhoto = lifePhoto;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public double getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(double packagePrice) {
        this.packagePrice = packagePrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getCreateUser() {
        return createUser;
    }

    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }

    public int getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(int lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public int getPlayState() {
        return playState;
    }

    public void setPlayState(int playState) {
        this.playState = playState;
    }

    @Override
    public String toString() {
        return "PeopleEntity{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", portrait='" + portrait + '\'' +
                ", lifePhoto='" + lifePhoto + '\'' +
                ", intro='" + intro + '\'' +
                ", phone='" + phone + '\'' +
                ", businessStatus='" + businessStatus + '\'' +
                ", price=" + price +
                ", discountPrice=" + discountPrice +
                ", packagePrice=" + packagePrice +
                ", createTime=" + createTime +
                ", createUser=" + createUser +
                ", lastUpdateUser=" + lastUpdateUser +
                ", lastUpdateTime=" + lastUpdateTime +
                ", playState=" + playState +
                ", playName='" + playName + '\'' +
                ", labels='" + labels + '\'' +
                ", makeMoney='" + makeMoney + '\'' +
                ", status='" + status + '\'' +
                ", realName='" + realName + '\'' +
                ", playStateList=" + playStateList +
                ", playStates=" + playStates +
                ", sort=" + sort +
                ", sortTemp=" + sortTemp +
                '}';
    }
}
