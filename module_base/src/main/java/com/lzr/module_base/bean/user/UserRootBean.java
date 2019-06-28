package com.lzr.module_base.bean.user;

/**
 * Created by 10302 on 2018/10/30.
 */

public class UserRootBean {

    /**
     *
     result	true
     code	10000
     data
     id	1
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
     msg	"获取个人资料成功"
     */

    private boolean result;
    private String code;
    private User data;
    private String msg;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
