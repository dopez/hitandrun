package com.bbm.gps.adm.csnst.service;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CsnstMeno extends CsnstManageKey implements Serializable{

    private String memoSn;

    private String memoWrterId;

    private String memoWrterNm; 

    private String memoPassword;

    private String memoCn;

    public String getMemoSn() {
        return memoSn;
    }

    public void setMemoSn(String memoSn) {
        this.memoSn = memoSn;
    }

    public String getMemoWrterId() {
        return memoWrterId;
    }

    public void setMemoWrterId(String memoWrterId) {
        this.memoWrterId = memoWrterId;
    }

    public String getMemoWrterNm() {
        return memoWrterNm;
    }

    public void setMemoWrterNm(String memoWrterNm) {
        this.memoWrterNm = memoWrterNm;
    }

    public String getMemoPassword() {
        return memoPassword;
    }

    public void setMemoPassword(String memoPassword) {
        this.memoPassword = memoPassword;
    }

    public String getMemoCn() {
        return memoCn;
    }

    public void setMemoCn(String memoCn) {
        this.memoCn = memoCn;
    }

}