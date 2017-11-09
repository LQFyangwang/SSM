package com.gs.bean;

import java.util.List;

public class Pager<T> {

    private Integer pageNo;
    private Integer pageSize;
    private List<T> results;
    private Integer total;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getBeginIndex() {
        return (pageNo - 1) * pageSize;
    }
}
