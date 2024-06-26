package cn.structured.oauth.server.configuration;

import cn.structure.common.entity.IResult;
import cn.structure.common.exception.CommonException;
import cn.structure.common.utils.IResultUtil;
import cn.structure.common.utils.ResultUtilSimpleImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

/**
 * create by chuck 2024/6/16
 *
 * @author chuck
 * @since JDK1.8
 */
public class OauthWebResponseExceptionTranslator implements WebResponseExceptionTranslator {

    private WebResponseExceptionTranslator<OAuth2Exception>  defaultWebResponseExceptionTranslator = new DefaultWebResponseExceptionTranslator();

    @Override
    public ResponseEntity<IResult> translate(Exception e) throws Exception {
        ResponseEntity<OAuth2Exception> translate = defaultWebResponseExceptionTranslator.translate(e);
        IResultUtil resultUtil = new ResultUtilSimpleImpl();
        IResult result;
        if ( e instanceof CommonException) {
            CommonException commonException = (CommonException) e;
            result = resultUtil.fail(commonException.getCode(),commonException.getMsg());
        }else {
            OAuth2Exception oAuth2Exception = translate.getBody();
            result = resultUtil.exception(oAuth2Exception.getCause().getMessage());
        }
        return new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
