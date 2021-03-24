package com.github.entropyfeng.apartment.service;

/**
 *
 * @author entropyfeng
 */
public interface AuthIdService {

    Long getNextAuthUserId();

    Long getNextAuthRoleId();

    Long getNextAuthResourceId();

    void clearAuthUserId();

    void clearAuthRoleId();

    void clearAuthResourceId();

    /**
     * 获取tokenId
     * @return token
     */
    String getNextTokenId();
}
