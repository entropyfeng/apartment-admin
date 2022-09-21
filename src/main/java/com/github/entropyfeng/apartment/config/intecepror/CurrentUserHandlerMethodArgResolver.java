package com.github.entropyfeng.apartment.config.intecepror;

import com.github.entropyfeng.apartment.config.AuthProperties;
import com.github.entropyfeng.common.config.anno.CurrentUserAnno;
import com.github.entropyfeng.common.domain.CurrentUser;
import com.github.entropyfeng.common.util.JWTUtil;
import com.github.entropyfeng.common.util.JwtAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

import static com.github.entropyfeng.common.config.CommonConst.AUTH_TOKEN_FIELD_NAME;

/**
 * 为参数添加相关增强，即在函数中作注解为类型注入值
 */
public class CurrentUserHandlerMethodArgResolver implements HandlerMethodArgumentResolver {
    private static final Logger logger = LoggerFactory.getLogger(CurrentUserHandlerMethodArgResolver.class);

    @Autowired
    AuthProperties authProperties;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {

        //该形参上是否支持注解参数
        return methodParameter.getParameterAnnotation(CurrentUserAnno.class) != null && methodParameter.getParameterType() == CurrentUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        // 取得 HttpServletRequest
        HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();

        //从头部中获取token
        String authToken = request.getHeader(AUTH_TOKEN_FIELD_NAME);
        //将String token 转换成对象
        JwtAccount jwtAccount = JWTUtil.parseJwt(authToken, authProperties.getJwtSecretKey());

        //创建当前User
        CurrentUser currentUser = new CurrentUser();
        currentUser.setUserId(Long.parseLong(jwtAccount.getUserId()));
        currentUser.setUserName(jwtAccount.getUsername());
        currentUser.setRoles(jwtAccount.getRoles());
        return currentUser;

    }
}
