package com.knd.common.bean;

import java.math.BigDecimal;
import java.util.List;

public class SeriesCourseDetailDto {
    private String id;
    private String name;
    private String synopsis;//简介
    private String introduce;
    private String difficulty;
    private String consume;//消耗
    private String picAttachUrl;
    private List<String> attachUrl;
    private List<CourseTypeNum> courseTypeNumList;
    private List<CoursePayDto> coursePayDtoList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getConsume() {
        return consume;
    }

    public void setConsume(String consume) {
        this.consume = consume;
    }

    public String getPicAttachUrl() {
        return picAttachUrl;
    }

    public void setPicAttachUrl(String picAttachUrl) {
        this.picAttachUrl = picAttachUrl;
    }

    public List<String> getAttachUrl() {
        return attachUrl;
    }

    public void setAttachUrl(List<String> attachUrl) {
        this.attachUrl = attachUrl;
    }

    public List<CourseTypeNum> getCourseTypeNumList() {
        return courseTypeNumList;
    }

    public void setCourseTypeNumList(List<CourseTypeNum> courseTypeNumList) {
        this.courseTypeNumList = courseTypeNumList;
    }

    public List<CoursePayDto> getCoursePayDtoList() {
        return coursePayDtoList;
    }

    public void setCoursePayDtoList(List<CoursePayDto> coursePayDtoList) {
        this.coursePayDtoList = coursePayDtoList;
    }

    public class CourseTypeNum {
        private String type;
        private int num;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }

    public class CoursePayDto {
        private String id;
        private String course;
        private String picAttachUrl;
        private String courseType;
        private int trainNum;
        private BigDecimal amount;
        private String remark;
        private String videoDurationMinutes;
        private boolean pay;
        /**
         * "id": "53cb4fd461b94e88817faef5af73c211",
         *         "course": "第六课-胸部训练二",
         *         "picAttachUrl": "https://knd-vita-dev.obs.cn-east-3.myhuaweicloud.com/pro/image/1604999519534.png?AccessKeyId=XXAQGOL8KYHZVJJJ6E92&Expires=1636535523&Signature=lHt5dwE8XUmA%2BGLzpD9kpElVs5A%3D",
         *         "courseType": "特色课程",
         *         "trainNum": 0,
         *         "amount": 11.23,
         *         "remark": "第六课",
         *         "videoDurationMinutes": "4",
         *         "pay": false
         */

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public String getPicAttachUrl() {
            return picAttachUrl;
        }

        public void setPicAttachUrl(String picAttachUrl) {
            this.picAttachUrl = picAttachUrl;
        }

        public String getCourseType() {
            return courseType;
        }

        public void setCourseType(String courseType) {
            this.courseType = courseType;
        }

        public int getTrainNum() {
            return trainNum;
        }

        public void setTrainNum(int trainNum) {
            this.trainNum = trainNum;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getVideoDurationMinutes() {
            return videoDurationMinutes;
        }

        public void setVideoDurationMinutes(String videoDurationMinutes) {
            this.videoDurationMinutes = videoDurationMinutes;
        }

        public boolean isPay() {
            return pay;
        }

        public void setPay(boolean pay) {
            this.pay = pay;
        }
    }

}
