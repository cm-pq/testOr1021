package com.offcn.pojo;

import java.io.Serializable;
import java.util.List;

public class PageResult implements Serializable {
    private static final long serialVersionUID = -4466618894817967865L;
    private long total;//总条数
    private List rows;//当前页的结果

    public PageResult(long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public List getRows() {
        return rows;
    }
}
