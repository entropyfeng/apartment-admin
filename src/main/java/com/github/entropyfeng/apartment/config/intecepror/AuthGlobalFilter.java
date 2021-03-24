package com.github.entropyfeng.apartment.config.intecepror;

import com.github.entropyfeng.apartment.config.AuthProperties;
import com.github.entropyfeng.apartment.service.AuthService;
import com.github.entropyfeng.common.domain.Message;
import com.github.entropyfeng.common.util.JWTUtil;
import com.github.entropyfeng.common.util.JwtAccount;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.github.entropyfeng.common.config.CommonConst.AUTH_TOKEN_FIELD_NAME;

@Component
public class AuthGlobalFilter implements HandlerInterceptor {


    @Autowired
    public AuthGlobalFilter(AuthProperties authProperties, AuthService authService) {
        this.authProperties = authProperties;
        this.authService = authService;
    }

    AuthProperties authProperties;

    AuthService authService;


    private final Logger logger = LoggerFactory.getLogger(AuthGlobalFilter.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        String path = request.getServletPath();
        String authToken = request.getHeader(AUTH_TOKEN_FIELD_NAME);
        Message message = new Message();
        JwtAccount jwtAccount = null;
        if (!StringUtils.isEmpty(authToken)) {
            try {
                jwtAccount = JWTUtil.parseJwt(authToken, authProperties.getJwtSecretKey());
            } catch (ExpiredJwtException e) {
                logger.info("token {} expired", authToken);
                message.setSuccess(false);
                message.setMsg("token expired");
            } catch (SignatureException e) {
                logger.info("token {} signature check fail", authToken);
                message.setSuccess(false);
                message.setMsg("signature check fail");

            } catch (Exception e) {
                logger.info("handle  illegal {} ", authToken);
                message.setSuccess(false);
                message.setMsg("token illegal");
            }

        } else {
            message.setSuccess(false);
            message.setMsg("required " + AUTH_TOKEN_FIELD_NAME);
        }
        return false;


    }

}
