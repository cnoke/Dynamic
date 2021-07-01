package com.knd.common.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActionArraysBean implements Serializable {
    private List<ActionArrayBean> records;
    private int total;
    private int size;
    private int current;
    private int pages;
    private boolean optimizeCountSql;
    private boolean hitCount;
    private boolean searchCount;

    /**
     *      "total": 4,
     *     "size": 10,
     *     "current": 1,
     *     "orders": [],
     *     "optimizeCountSql": true,
     *     "hitCount": false,
     *     "searchCount": true,
     *     "pages": 1
     */

    public ActionArraysBean() {
    }

    @Override
    public String toString() {
        return "ActionArraysBean{" +
                "total='" + total + '\'' +
                ", size='" + size + '\'' +
                ", current='" + current + '\'' +
                ", optimizeCountSql='" + optimizeCountSql + '\'' +
                ", hitCount='" + hitCount + '\'' +
                ", searchCount='" + searchCount + '\'' +
                ", pages='" + pages + '\'' +
                '}';
    }

    public List<ActionArrayBean> getRecords() {
        return records;
    }

    public void setRecords(List<ActionArrayBean> records) {
        this.records = records;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean isOptimizeCountSql() {
        return optimizeCountSql;
    }

    public void setOptimizeCountSql(boolean optimizeCountSql) {
        this.optimizeCountSql = optimizeCountSql;
    }

    public boolean isHitCount() {
        return hitCount;
    }

    public void setHitCount(boolean hitCount) {
        this.hitCount = hitCount;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }

    public class ActionArrayBean implements Serializable {
        private String id;
        private String coverAttachUrl;
        private String actionArrayName;
        private String actionQuantity;
        private String totalDuration;
        private String shareStatus;
        private boolean isSelect = false;
        //下载状态
        private int state;
        //下载百分比
        private int percent;
        //下载key
        private String key;
        private ArrayList<VideoUrlBean> actionVideoUrl;
        /**
         * "actionArrayName": "test007",
         *         "coverAttachUrl": ,
         *         "actionVideoUrl": [
         *         ],
         *         "actionQuantity": "1",
         *         "totalDuration": "",
         *         "shareStatus": "0",
         *         "userId": "Lr5uq4bH"
         */

        public ActionArrayBean() {
        }

        @Override
        public String toString() {
            return "ActionArrayBean{" +
                    "id='" + id + '\'' +
                    ", coverAttachUrl='" + coverAttachUrl + '\'' +
                    ", actionArrayName='" + actionArrayName + '\'' +
                    ", actionQuantity='" + actionQuantity + '\'' +
                    ", totalDuration='" + totalDuration + '\'' +
                    ", shareStatus='" + shareStatus + '\'' +
                    ", isSelect=" + isSelect +
                    '}';
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getPercent() {
            return percent;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCoverAttachUrl() {
            return coverAttachUrl;
        }

        public void setCoverAttachUrl(String coverAttachUrl) {
            this.coverAttachUrl = coverAttachUrl;
        }

        public String getActionArrayName() {
            return actionArrayName;
        }

        public void setActionArrayName(String actionArrayName) {
            this.actionArrayName = actionArrayName;
        }

        public String getActionQuantity() {
            return actionQuantity;
        }

        public void setActionQuantity(String actionQuantity) {
            this.actionQuantity = actionQuantity;
        }

        public String getTotalDuration() {
            return totalDuration;
        }

        public void setTotalDuration(String totalDuration) {
            this.totalDuration = totalDuration;
        }

        public String getShareStatus() {
            return shareStatus;
        }

        public void setShareStatus(String shareStatus) {
            this.shareStatus = shareStatus;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public ArrayList<VideoUrlBean> getActionVideoUrl() {
            return actionVideoUrl;
        }

        public void setActionVideoUrl(ArrayList<VideoUrlBean> actionVideoUrl) {
            this.actionVideoUrl = actionVideoUrl;
        }
    }

    public class VideoUrlBean implements Serializable{
        private String filePath;
        private String fileSize;

        public VideoUrlBean() {
        }

        public String getFilePath() {
            return filePath;
        }

        @Override
        public String toString() {
            return "videoUrlBean{" +
                    "filePath='" + filePath + '\'' +
                    ", fileSize='" + fileSize + '\'' +
                    '}';
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getFileSize() {
            return fileSize;
        }

        public void setFileSize(String fileSize) {
            this.fileSize = fileSize;
        }
    }
}
