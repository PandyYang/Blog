package com.pandy.blog.common;

import java.util.List;

/**
 * @author Pandy
 */
public class PageResult<T> {

    private List<T> items;

    private int total;

    public PageResult(List<T> items, int total) {
        this.items = items;
        this.total = total;
    }

    public PageResult() {
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
