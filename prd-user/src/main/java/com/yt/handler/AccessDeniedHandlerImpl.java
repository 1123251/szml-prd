package com.yt.handler;

import com.alibaba.fastjson.JSON;
import com.yt.entity.LoginUser;
import com.yt.util.WebUtils;
import com.yt.vo.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e)  {

        ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(), "权限不足");

        String json = JSON.toJSONString(result);
        WebUtils.renderString(httpServletResponse,json);
    }

}
