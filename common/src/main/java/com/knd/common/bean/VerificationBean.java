package com.knd.common.bean;

public class VerificationBean {
    private String verifyCodeId;

    public String getVerifyCodeId() {
        return verifyCodeId;
    }

    public void setVerifyCodeId(String verifyCodeId) {
        this.verifyCodeId = verifyCodeId;
    }

    @Override
    public String toString() {
        return "VerificationBean{" +
                "verifyCodeId='" + verifyCodeId + '\'' +
                '}';
    }
}
