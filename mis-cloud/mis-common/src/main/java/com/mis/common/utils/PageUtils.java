package com.mis.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mis.common.constant.Constants;

import java.util.List;

public class PageUtils {
    /**
     * 当前页码
     */
    private int pageNum;

    /**
     * 每页数量
     */
    private int pageSize;

    /**
     * 总记录数
     */
    private long total;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 列表数据
     */
    private List<?> list;

    /**
     * 分页
     */
    public PageUtils(List<?> list, long total) {
        this.list = list;
        this.total = total;
    }

    /**
     * 分页
     */
    public PageUtils(IPage<?> page) {
        this.list = page.getRecords();
        this.total = page.getTotal();
        this.pageSize = (int) page.getSize();
        this.pageNum = (int) page.getCurrent();
        this.totalPage = (int) page.getPages();
    }

    /**
     * 分页
     */
    public static <T> Page<T> getPage() {
        int pageNum = ServletUtils.getParameterToInt(Constants.PAGE_NUM, 1);
        int pageSize = ServletUtils.getParameterToInt(Constants.PAGE_SIZE, 10);
        return new Page<>(pageNum, pageSize);
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
} 