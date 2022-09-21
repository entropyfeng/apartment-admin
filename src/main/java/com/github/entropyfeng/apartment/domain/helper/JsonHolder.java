package com.github.entropyfeng.apartment.domain.helper;

public class JsonHolder<T> {
    private final T obj;

    public JsonHolder(T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }
}
