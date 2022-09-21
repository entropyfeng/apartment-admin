package com.github.entropyfeng.apartment.event;

import org.springframework.context.ApplicationEvent;

public class LoadApartmentScheduleEvent extends ApplicationEvent {
    public LoadApartmentScheduleEvent(Object source) {
        super(source);
    }
}
