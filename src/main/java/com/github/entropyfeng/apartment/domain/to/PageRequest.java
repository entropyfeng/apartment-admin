package com.github.entropyfeng.apartment.domain.to;

/**
 * @author https://www.cnblogs.com/xifengxiaoma/p/11027551.html
 */
public class PageRequest {

    private int pageNo;

    private int pageSize;

    public int getPageNo() {
        return pageNo;
    }

    public PageRequest(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public PageRequest(){

    }
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
