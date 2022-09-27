package com.thaddeus.bean;

import java.util.List;
import java.util.Map;

public class Page<T> {

    public static final Integer PAGE_SIZE = 8;

    // 当前页码
    private Integer pageNo;

    // 总页码
    private Integer pageTotalNo;

    // 当前页显示数量
    private Integer pageSize = PAGE_SIZE;

    // 总记录数
    private Integer pageTotalCount;

    // 当前页数据
    private List<T> items;

    private String url;

    public Page() {
    }

    public Page(Integer pageNo, Integer pageTotalNo, Integer pageSize, Integer pageTotalCount, List<T> items, String url) {
        this.pageNo = pageNo;
        this.pageTotalNo = pageTotalNo;
        this.pageSize = pageSize;
        this.pageTotalCount = pageTotalCount;
        this.items = items;
        this.url = url;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageTotalNo() {
        return pageTotalNo;
    }

    public void setPageTotalNo(Integer pageTotalNo) {
        this.pageTotalNo = pageTotalNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotalNo=" + pageTotalNo +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
