package com.lzr.module_base.bean.user;

import java.io.Serializable;

/**
 * Created by 10302 on 2018/10/30.
 */

public class User implements Serializable {
    /**
     *      id	1
     time	1540437482
     nickname	"朱丽叶"
     avatar	"http://avatar.17sysj.com/5bd032d071de4"
     mobile	"13719154524"
     email	""
     qq	"343586231"
     sex	0
     address	""
     attention	0
     fans	0
     cover	""
     isOpenId	"0"
     horizonId	3
     vipStatus	1
     vipInfo	null
     */
    private String id;
    private String time;
    private String nickname;
    private String avatar;
    private String mobile;
    private String email;
    private String qq;
    private String sex;
    private String address;
    private String attention;
    private String fans;
    private String cover;
    private String isOpenId;
    private String horizonId;
    private String vipStatus;
    private String vipInfo;

    public static final String USER_NOTLOGIN = "-1";
    public static final String LOGIN_WITH_MOBILE = "0";
    public static final String LOGIN_WITH_OTHER = "1";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getFans() {
        return fans;
    }

    public void setFans(String fans) {
        this.fans = fans;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getIsOpenId() {
        return isOpenId;
    }

    public void setIsOpenId(String isOpenId) {
        this.isOpenId = isOpenId;
    }

    public String getHorizonId() {
        return horizonId;
    }

    public void setHorizonId(String horizonId) {
        this.horizonId = horizonId;
    }

    public String getVipStatus() {
        return vipStatus;
    }

    public void setVipStatus(String vipStatus) {
        this.vipStatus = vipStatus;
    }

    public String getVipInfo() {
        return vipInfo;
    }

    public void setVipInfo(String vipInfo) {
        this.vipInfo = vipInfo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", time='" + time + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", qq='" + qq + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", attention='" + attention + '\'' +
                ", fans='" + fans + '\'' +
                ", cover='" + cover + '\'' +
                ", isOpenId='" + isOpenId + '\'' +
                ", horizonId='" + horizonId + '\'' +
                ", vipStatus='" + vipStatus + '\'' +
                ", vipInfo='" + vipInfo + '\'' +
                '}';
    }
}
