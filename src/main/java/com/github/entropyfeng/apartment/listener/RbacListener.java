package com.github.entropyfeng.apartment.listener;



import com.github.entropyfeng.apartment.dao.AuthRoleDao;
import com.github.entropyfeng.apartment.domain.to.RoleAndResource;
import com.github.entropyfeng.apartment.event.LoadRbacEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * @author entropyfeng
 */
@Component
public class RbacListener implements ApplicationListener<LoadRbacEvent> {

    private static final Logger logger = LoggerFactory.getLogger(RbacListener.class);


    final
    StringRedisTemplate redisTemplate;

    final
    AuthRoleDao authRoleDao;

    @Autowired
    public RbacListener(StringRedisTemplate redisTemplate, AuthRoleDao authRoleDao) {
        this.redisTemplate = redisTemplate;
        this.authRoleDao = authRoleDao;
    }

    /**
     * 加载前首先清空对应的redis数据
     *
     * @param rbacEvent 加载/重新加载RBAC 信息到redis
     */
    @Override
    public void onApplicationEvent(LoadRbacEvent rbacEvent) {

        logger.info("load or reload RBAC");
        //加载前首先清空对应的redis数据
        Collection<String> res = redisTemplate.keys("*");
        if (!CollectionUtils.isEmpty(res)) {
            redisTemplate.delete(res);
        }
        List<RoleAndResource> list = authRoleDao.queryRoleAndResource();
        if (list == null) {
            logger.error("can not find rbac information");
        } else {
            //可以用pipeline 优化
            list.forEach(temp -> redisTemplate.opsForSet().add(temp.getAuthRoleName(), temp.getAuthMethod() + temp.getAuthPath()));
        }

    }


}
