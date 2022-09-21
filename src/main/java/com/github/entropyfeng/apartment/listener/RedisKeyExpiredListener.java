package com.github.entropyfeng.apartment.listener;

import com.github.entropyfeng.apartment.service.ApartmentScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
@Component
public class RedisKeyExpiredListener extends KeyExpirationEventMessageListener {

    private static final Logger logger= LoggerFactory.getLogger(RedisKeyExpiredListener.class);
    /**
     * @param listenerContainer must not be {@literal null}.
     */
    @Autowired
    public RedisKeyExpiredListener(RedisMessageListenerContainer listenerContainer,ApartmentScheduleService scheduleService) {
        super(listenerContainer);
        this.scheduleService=scheduleService;
        logger.info("load redis key expired listener");
    }

    private final ApartmentScheduleService scheduleService;
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String channel=new String(message.getChannel(), StandardCharsets.UTF_8);
        String key=new String(message.getBody(),StandardCharsets.UTF_8);
        logger.info("in channel {},expire on key-> {}, on pattern->{}",channel,key,new String(pattern));

        if (scheduleService.supportScheduleTimeStart(key)){
            scheduleService.loadScheduleByScheduleName(scheduleService.reduceTimeStartKeyPrefix(key));
        }else if (scheduleService.supportScheduleTimeStart(key)){
            scheduleService.releaseScheduleByScheduleName(scheduleService.reduceTimeEndKeyPrefix(key));
        }

    }
}
