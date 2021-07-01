package com.knd.common.bean;



import java.io.Serializable;
import java.util.List;

public class UserInfo implements Serializable {
    private String userId;
    private String gender;
    private String birthDay;
    private String height;
    private String weight;
    private String bmi;
    private String trainHisFlag;
    private String targetId;
    private String target;
    private String postType;
    private String mobile;
    private String password;
    private String token;
    private String nickName;
    private String headPicUrl;
    private String headPicUrlId;
    // 0普通会员1个人会员2家庭主会员3家庭副会员
    private int vipStatus;
    private String vipBeginDate;
    private String vipEndDate;
    private String trainLevel;
    private String testTrain;
    private String trainPeriodBeginTime;
    private String totalSeconds;
    private String actTrainSeconds;
    private String loginHisId;
    private String latestTrainLevel;
    private String totalTrainKg;
    private String shape;
    private String shapeId;
    private List<String> hobbyId;
    private String perSign;
    private String totalCalorie;

    public UserInfo() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public String getTrainHisFlag() {
        return trainHisFlag;
    }

    public void setTrainHisFlag(String trainHisFlag) {
        this.trainHisFlag = trainHisFlag;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadPicUrlId() {
        return headPicUrlId;
    }

    public void setHeadPicUrlId(String headPicUrlId) {
        this.headPicUrlId = headPicUrlId;
    }

    public String getHeadPicUrl() {
        return headPicUrl;
    }

    public void setHeadPicUrl(String headPicUrl) {
        this.headPicUrl = headPicUrl;
    }

    public int getVipStatus() {
        return vipStatus;
    }

    public void setVipStatus(int vipStatus) {
        this.vipStatus = vipStatus;
    }

    public String getVipBeginDate() {
        return vipBeginDate;
    }

    public void setVipBeginDate(String vipBeginDate) {
        this.vipBeginDate = vipBeginDate;
    }

    public String getVipEndDate() {
        return vipEndDate;
    }

    public void setVipEndDate(String vipEndDate) {
        this.vipEndDate = vipEndDate;
    }

    public String getTrainLevel() {
        return trainLevel;
    }

    public void setTrainLevel(String trainLevel) {
        this.trainLevel = trainLevel;
    }

    public String getTrainPeriodBeginTime() {
        return trainPeriodBeginTime;
    }

    public void setTrainPeriodBeginTime(String trainPeriodBeginTime) {
        this.trainPeriodBeginTime = trainPeriodBeginTime;
    }

    public String getTotalSeconds() {
        return totalSeconds;
    }

    public void setTotalSeconds(String totalSeconds) {
        this.totalSeconds = totalSeconds;
    }

    public String getActTrainSeconds() {
        return actTrainSeconds;
    }

    public void setActTrainSeconds(String actTrainSeconds) {
        this.actTrainSeconds = actTrainSeconds;
    }

    public String getLoginHisId() {
        return loginHisId;
    }

    public void setLoginHisId(String loginHisId) {
        this.loginHisId = loginHisId;
    }

    public String getLatestTrainLevel() {
        return latestTrainLevel;
    }

    public void setLatestTrainLevel(String latestTrainLevel) {
        this.latestTrainLevel = latestTrainLevel;
    }

    public String getTotalTrainKg() {
        return totalTrainKg;
    }

    public void setTotalTrainKg(String totalTrainKg) {
        this.totalTrainKg = totalTrainKg;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getShapeId() {
        return shapeId;
    }

    public void setShapeId(String shapeId) {
        this.shapeId = shapeId;
    }

    public List<String> getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(List<String> hobbyId) {
        this.hobbyId = hobbyId;
    }

    public String getPerSign() {
        return perSign;
    }

    public void setPerSign(String perSign) {
        this.perSign = perSign;
    }

    public String getTotalCalorie() {
        return totalCalorie;
    }

    public void setTotalCalorie(String totalCalorie) {
        this.totalCalorie = totalCalorie;
    }

    public String getTestTrain() {
        return testTrain;
    }

    public void setTestTrain(String testTrain) {
        this.testTrain = testTrain;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId='" + userId + '\'' +
                ", gender='" + gender + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", bmi='" + bmi + '\'' +
                ", trainHisFlag='" + trainHisFlag + '\'' +
                ", targetId='" + targetId + '\'' +
                ", target='" + target + '\'' +
                ", postType='" + postType + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", nickName='" + nickName + '\'' +
                ", trainLevel='" + trainLevel + '\'' +
                ", testTrain='" + testTrain + '\'' +
                ", trainPeriodBeginTime='" + trainPeriodBeginTime + '\'' +
                ", totalSeconds='" + totalSeconds + '\'' +
                ", actTrainSeconds='" + actTrainSeconds + '\'' +
                ", loginHisId='" + loginHisId + '\'' +
                ", latestTrainLevel='" + latestTrainLevel + '\'' +
                ", totalTrainKg='" + totalTrainKg + '\'' +
                ", shape='" + shape + '\'' +
                ", perSign='" + perSign + '\'' +
                ", totalCalorie='" + totalCalorie + '\'' +
                '}';
    }
}
