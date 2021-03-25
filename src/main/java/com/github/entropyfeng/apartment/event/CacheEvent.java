package com.github.entropyfeng.apartment.event;

import org.springframework.context.ApplicationEvent;

public class CacheEvent extends ApplicationEvent {
    public CacheEvent(Object source) {
        super(source);
    }
}
