package com.xiaobozheng.gankio.data.model;

import java.util.List;

/**
 * Created by xiaobozheng on 6/23/2016.
 */
public class RecentBeanResult<T> {
    private List<String> category;
    private boolean error;
    private T results;

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
