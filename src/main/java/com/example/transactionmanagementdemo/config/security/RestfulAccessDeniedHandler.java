package com.example.transactionmanagementdemo.config.security;

//import com.example.authentication.common.domain.ResultDto;
//import com.example.authentication.common.domain.ResultMsg;
//import com.example.authentication.common.util.JsonUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * user does not have access authorization
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

//        ResultDto result = new ResultDto(HttpStatus.FORBIDDEN.value(), ResultMsg.FORBIDDEN);
//
//        response.getWriter().write( JsonUtil.objToString(result));
    }
}
