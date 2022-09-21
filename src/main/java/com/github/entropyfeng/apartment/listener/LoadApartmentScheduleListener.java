package com.github.entropyfeng.apartment.listener;

import com.github.entropyfeng.apartment.event.LoadApartmentScheduleEvent;
import com.github.entropyfeng.apartment.service.ApartmentScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 在redis中检查过期的计划,从数据库中查找存在的计划
 */
@Component
public class LoadApartmentScheduleListener implements ApplicationListener<LoadApartmentScheduleEvent> {
    private static final Logger logger = LoggerFactory.getLogger(LoadApartmentScheduleListener.class);

    private final ApartmentScheduleService scheduleService;

    @Autowired
    public LoadApartmentScheduleListener(ApartmentScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Override
    public void onApplicationEvent(LoadApartmentScheduleEvent loadApartmentScheduleEvent) {

        scheduleService.publishLoadSchedule();
    }
}
