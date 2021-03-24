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

public class CurrentUserHandlerMethodArgResolver implements HandlerMethodArgumentResolver {
    private static final Logger logger = LoggerFactory.getLogger(CurrentUserHandlerMethodArgResolver.class);

    @Autowired
    AuthProperties authProperties;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {

        return methodParameter.getParameterAnnotation(CurrentUserAnno.class) != null && methodParameter.getParameterType() == CurrentUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        // 取得 HttpServletRequest
        HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();

        String authToken = request.getHeader(AUTH_TOKEN_FIELD_NAME);
        JwtAccount jwtAccount = JWTUtil.parseJwt(authToken, authProperties.getJwtSecretKey());

        //String json = request.getHeader(CommonConst.CURRENT_USER_FIELD_NAME);

        CurrentUser currentUser = new CurrentUser();
        currentUser.setUserId(Long.parseLong(jwtAccount.getUserId()));
        currentUser.setUserName(jwtAccount.getUsername());
        currentUser.setRoles(jwtAccount.getRoles());
        return currentUser;

    }
}
