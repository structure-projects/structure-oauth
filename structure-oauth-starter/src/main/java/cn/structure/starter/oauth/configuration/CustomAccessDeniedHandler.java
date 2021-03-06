package cn.structure.starter.oauth.configuration;

import cn.structure.common.entity.ResResultVO;
import cn.structure.common.enums.ExceptionRsType;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *     chuck
 *     2019/11/28 11:36
 * </p>
 * @author chuck
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException){
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=UTF-8");
        try {
            //无权限
            ResResultVO result = ResResultVO.fail(ExceptionRsType.PERMISSION_DENIED.getCode(), accessDeniedException.getMessage());
            response.getWriter().write(JSONObject.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}