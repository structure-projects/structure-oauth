package cn.structured.oauth.user.configuration;

import cn.structure.common.constant.AuthConstant;
import cn.structure.starter.oauth.common.util.SecurityUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author cqliut
 * @version 2022.0726
 * @since 1.0.1
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("delFlag", Boolean.FALSE, metaObject);
        this.setFieldValByName("enabled", Boolean.TRUE, metaObject);
        this.setFieldValByName("deleted", Boolean.FALSE, metaObject);
        this.setFieldValByName("createDate", LocalDateTime.now(), metaObject);
        this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("createBy", getUserId(), metaObject);
        this.setFieldValByName("updateDate", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateBy", getUserId(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateDate", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateBy", getUserId(), metaObject);
    }

    /**
     * 获取用户ID
     *
     * @return 用户ID
     */
    private Object getUserId() {
        try {
            JSONObject user = JSON.parseObject(JSON.toJSONString(SecurityUtils.getUser()));
            return user.getLong(AuthConstant.USER_ID);
        } catch (Exception e) {
            log.debug("get user id is error -> message = {}", e.getMessage());
        }
        return null;
    }
}
