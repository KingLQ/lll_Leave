package com.goldze.mvvmhabit.entity.request;

public class GetLeaveListRequestEntity {

    /**
     * currentPage : 0
     * pageSize : 0
     */

    private int currentPage;
    private int pageSize;

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
}
