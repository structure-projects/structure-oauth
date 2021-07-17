package cn.structure.starter.oauth.configuration;

import cn.structure.common.entity.ResResultVO;
import cn.structure.common.enums.ExceptionRsType;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录异常捕获处理
 * @author Administrator
 */
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,AuthenticationException authException) throws ServletException {
        Throwable cause = authException.getCause();
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        try {
            if(cause instanceof InvalidTokenException) {
                //坏的token或过期token
                ResResultVO result = ResResultVO.fail(ExceptionRsType.INVALID_AUTHENTICATION.getCode(),authException.getMessage());
                response.getWriter().write(JSONObject.toJSONString(result));
            }else if (cause instanceof UnauthorizedUserException) {
                ResResultVO result = ResResultVO.fail("LOGIN_ERR",authException.getMessage());
                response.getWriter().write(JSONObject.toJSONString(result));
            }else{
                //用户未登录
                ResResultVO result = ResResultVO.fail(ExceptionRsType.NOT_LOGGED_IN.getCode(), authException.getMessage());
                response.getWriter().write(JSONObject.toJSONString(result));
            }
        } catch (Exception e) {
            throw new ServletException();
        }
    }
}