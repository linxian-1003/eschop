package com.neu.query;

public class BaseQuery {

    //当前第几页
    private Integer pageNum=1;

    //每页最大显示多少条
    private Integer pageSize=10;

    //排序方式
    private String sortOrder;

    private boolean paging=true;


    public boolean isPaging() {
        return paging;
    }

    public void setPaging(boolean paging) {
        this.paging = paging;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        //TODO
        this.pageSize = pageSize;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
}
