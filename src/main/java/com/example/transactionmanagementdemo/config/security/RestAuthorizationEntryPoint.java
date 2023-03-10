package com.example.transactionmanagementdemo.config.security;

//import com.example.authentication.common.domain.ResultDto;
//import com.example.authentication.common.domain.ResultMsg;
//import com.example.authentication.common.util.JsonUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User does not login
 */

@Component
public class RestAuthorizationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

//        ResultDto result = new ResultDto(HttpStatus.UNAUTHORIZED.value(), ResultMsg.UNAUTHORIZED);
//
//        response.getWriter().write( JsonUtil.objToString(result));
    }
}
