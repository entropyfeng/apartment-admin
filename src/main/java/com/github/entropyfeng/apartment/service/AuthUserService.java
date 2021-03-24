package com.github.entropyfeng.apartment.service;



import com.github.entropyfeng.apartment.domain.po.AuthUser;
import com.github.entropyfeng.apartment.domain.to.PageRequest;
import com.github.pagehelper.PageInfo;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author entropyfeng
 */

public interface AuthUserService {


    void registerUser(@NotNull String authUsername, @NotNull String authPassword, String email, String phone, boolean enablePasswordCheck) ;



    /**
     * 向某用户添加角色
     *
     * @param authRoleName 角色名
     * @param authUserName 用户名
     */
    void grantRoleToUser(@NotNull String authUserName, @NotNull String authRoleName) ;

    List<String> acquireRoleNamesInUser(String authUsername);

    List<Long> acquireRoleIdsInUser(String authUsername);

    List<Long> acquireRoleIdsInUser(Long authUserId) ;

    String userLogin(@NotNull String authUsername, @NotNull String authPassword) ;

    Boolean batchUpdateRolesInUser(Long authUserId, @NonNull List<Long> deleteIds, List<Long> createIds);

    void batchDeleteUsers(@NotNull Long currentUserId, @NotEmpty List<Long> toDeleteUserIds);

    void batchDeleteUsers(@NotEmpty List<String> toDeleteUserNames);

    void deleteSingleUser(Long currentUserId, Long toDeleteUserId);

    void deleteSingleUser(String toDeleteUsername);

    void resetPassword(@NotNull String authUsername, @NotNull String newPassword, boolean enableValidatePassword) ;

    void resetPassword(@NotNull String authUsername, @NotNull String authPassword, @NotNull String newPassword, boolean enableValidatePassword) ;

    AuthUser getAuthUserByName(String authUsername) ;

    AuthUser getAuthUserById(Long authUserId) ;

    Long getAuthUserIdByName(String authUserName) ;

    List<AuthUser> allAuthUser();

    List<AuthUser> acquireAuthUserListByNames(List<String> usernames);

    Boolean updateAuthUser(AuthUser authUser);

    PageInfo<AuthUser> allAuthUserByPages(PageRequest pageRequest);


    String acquireAuthUserNameById(Integer authUserId);


}
