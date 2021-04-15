package com.github.entropyfeng.apartment.config.intecepror;

import com.github.entropyfeng.apartment.config.AuthProperties;
import com.github.entropyfeng.apartment.service.AuthService;
import com.github.entropyfeng.common.domain.Message;
import com.github.entropyfeng.common.util.JWTUtil;
import com.github.entropyfeng.common.util.JwtAccount;
import com.github.entropyfeng.common.util.MessageUtil;
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
import java.io.IOException;

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

    private JwtAccount parseToken(String authToken, HttpServletResponse response) {

        JwtAccount jwtAccount = null;
        if (StringUtils.isEmpty(authToken)){
            return null;
        }
        Message message = new Message();

        if (!StringUtils.isEmpty(authToken)) {
            try {
                jwtAccount = JWTUtil.parseJwt(authToken, authProperties.getJwtSecretKey());
            } catch (ExpiredJwtException e) {
                if (logger.isInfoEnabled()) {
                    logger.info("token {} expired", authToken);
                }
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

        if (jwtAccount==null) {
            String jsonString = null;
            try {
                jsonString = MessageUtil.toJsonString(message);
                response.getWriter().write(jsonString);
            } catch (IOException e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("write {} track IO Exception {}", jsonString, e.getMessage());
                }
            }
        }
        return jwtAccount;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        String path = request.getServletPath();
        if (logger.isDebugEnabled()){
            logger.debug("request method {} ,path {}",method,path);
        }
        String authToken = request.getHeader(AUTH_TOKEN_FIELD_NAME);
        boolean res = false;
        JwtAccount jwtAccount = parseToken(authToken, response);
        if (jwtAccount != null) {
            res = authService.checkPerm(jwtAccount.getRoles(), method, path);
        }
        if (!res){
            Message message=new Message();
            message.setSuccess(false);
            message.setErrorMessage("无权限");
            String jsonString = null;
            try {
                jsonString = MessageUtil.toJsonString(message);
                response.getWriter().write(jsonString);
            } catch (IOException e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("write {} track IO Exception {}", jsonString, e.getMessage());
                }
            }
            response.setStatus(401);
        }
        return res;
    }

}
