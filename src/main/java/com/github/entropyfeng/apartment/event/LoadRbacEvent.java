package com.github.entropyfeng.apartment.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author entropyfeng
 */
public class LoadRbacEvent extends ApplicationEvent {
    public LoadRbacEvent(Object source) {
        super(source);
    }
}
