package com.goldze.mvvmhabit.entity.response;

import java.util.List;

public class GetLeaveListResponseEntity {

    /**
     * currentPage : 1
     * pageSize : 2
     * totalCount : 25
     * data : [{"id":"23616cb6-8790-4afb-abb6-7108d2348b2a","startTime":"2019/09/06 17:02:00","endTime":"2019/09/13 17:03:00","state":0,"reason":"dock","refuseReason":null,"createTime":"2019/09/05 17:03:27"},{"id":"498c5ab5-423a-4056-ab1d-da3050cd1b5f","startTime":"2019/09/05 16:59:00","endTime":"2019/09/06 16:59:00","state":0,"reason":"阿鲁","refuseReason":null,"createTime":"2019/09/05 17:00:32"}]
     */

    private int currentPage;
    private int pageSize;
    private int totalCount;
    private List<DataBean> data;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 23616cb6-8790-4afb-abb6-7108d2348b2a
         * startTime : 2019/09/06 17:02:00
         * endTime : 2019/09/13 17:03:00
         * state : 0
         * reason : dock
         * refuseReason : null
         * createTime : 2019/09/05 17:03:27
         */

        private String id = "";
        private String startTime= "";
        private String endTime= "";
        private int state;
        private String reason= "";
        private Object refuseReason= "";
        private String createTime= "";

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public Object getRefuseReason() {
            return refuseReason;
        }

        public void setRefuseReason(Object refuseReason) {
            this.refuseReason = refuseReason;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
