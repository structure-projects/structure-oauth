package cn.structure.starter.oauth.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.structure.common.entity.ResResultVO;
import cn.structure.starter.oauth.configuration.OauthProperties;
import cn.structure.starter.oauth.enums.ErrCodeEnum;
import cn.structure.starter.oauth.service.IUserService;
import cn.structure.starter.oauth.service.IVerificationCodeService;
import cn.structure.starter.oauth.vo.login.RegisterByUsernameAndPasswordVo;
import cn.structure.starter.oauth.vo.login.ReqLoginVo;
import cn.structure.starter.oauth.vo.login.TokenUserInfoVo;
import cn.structure.starter.oauth.vo.login.VerificationCodeVo;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 默认登录控制器接口
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/4/2 17:50
 */
@RestController
@RequestMapping("/")
@Api(tags = "登录模块",description = "登录相关接口,除登录接口需要token其他接口均需要token")
public class LoginController  {

    @Autowired
    private IVerificationCodeService iVerificationCodeService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private OauthProperties oauthProperties;

    @GetMapping(value = "verificationCode")
    @ApiOperation(value = "获取验证码接口", notes =
            "100601,用户存在验证码黑名单中！\n" +
            "100602,用户一分钟内调用超过5次拒绝执行！\n" +
            "100603,用户30分钟内调用超过20次拒绝执行！\n" +
            "100604,用户一小时内调用超过50次拒绝执行！\n" +
            "100605,用户一日内调用超过100次拒绝执行！\n" )
    public ResResultVO<VerificationCodeVo> verificationCode(HttpServletRequest request) {
        String remoteAddress = request.getRemoteAddr();
        if (oauthProperties.getIsVerificationCode()) {
            boolean existBlacklist = iVerificationCodeService.isExistBlacklist(remoteAddress);
            if (existBlacklist) {
                return ResResultVO.fail(ErrCodeEnum.ERR_CODE_601.getCode(),ErrCodeEnum.ERR_CODE_601.getMsg());
            }
            int existFrequently = iVerificationCodeService.isExistFrequently(remoteAddress);
            //分支返回
            switch (existFrequently){
                case 1:
                    return ResResultVO.fail(ErrCodeEnum.ERR_CODE_602.getCode(),ErrCodeEnum.ERR_CODE_602.getMsg());
                case 2:
                    return ResResultVO.fail(ErrCodeEnum.ERR_CODE_603.getCode(),ErrCodeEnum.ERR_CODE_603.getMsg());
                case 3:
                    return ResResultVO.fail(ErrCodeEnum.ERR_CODE_604.getCode(),ErrCodeEnum.ERR_CODE_604.getMsg());
                case 4:
                    return ResResultVO.fail(ErrCodeEnum.ERR_CODE_605.getCode(),ErrCodeEnum.ERR_CODE_605.getMsg());
            }
        }
        //生成验证码图片
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 25);
        //从带有圆圈类型的图形验证码图片中获取它的字符串验证码(获取字符串验证码要在图形验证码写出wirte后面才行，不然得到的值为null)
        String code = circleCaptcha.getCode();
        Integer codeId = iVerificationCodeService.save(remoteAddress, code);
        String imageBase64 = circleCaptcha.getImageBase64();
        VerificationCodeVo verificationCode = new VerificationCodeVo();
        verificationCode.setId(codeId);
        verificationCode.setImageBase(imageBase64);
        return ResResultVO.success(verificationCode);
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "用户登录接口", notes = "用户登录成功后返回用户信息")
    public ResResultVO<TokenUserInfoVo> login(@RequestBody ReqLoginVo login) {

        if (oauthProperties.getIsCheckVerificationCode()) {
            return ResResultVO.fail(ErrCodeEnum.ERR_NOT_ENABLE_REGISTER.getCode(), ErrCodeEnum.ERR_NOT_ENABLE_REGISTER.getMsg());
        }

        if (oauthProperties.getIsCheckVerificationCode()) {
            //验证验证码
            boolean chuck = iVerificationCodeService.check(login.getVerificationCodeId(), login.getVerificationCode());
            //判断验证码是否校验成功
            if (!chuck) {
                return ResResultVO.fail(ErrCodeEnum.VERIFICATION_CODE_CHECK_FAILURE.getCode(), ErrCodeEnum.VERIFICATION_CODE_CHECK_FAILURE.getMsg());
            }
        }

        try {
            TokenUserInfoVo userInfo = getUserInfo(login.getUsername(), login.getPassword());
            return ResResultVO.success(userInfo);
        } catch (Exception ex) {
            return ResResultVO.fail(ErrCodeEnum.ERR_USER_PASSWORD.getCode(), ErrCodeEnum.ERR_USER_PASSWORD.getMsg());
        }
    }

    @PostMapping(value = "/register")
    @ApiOperation(value = "注册用户")
    public ResResultVO<TokenUserInfoVo> register(@Validated @RequestBody RegisterByUsernameAndPasswordVo register){
        if (oauthProperties.getIsUserRegister()) {
            return ResResultVO.fail(ErrCodeEnum.ERR_NOT_ENABLE_REGISTER.getCode(),ErrCodeEnum.ERR_NOT_ENABLE_REGISTER.getMsg());
        }
        if (oauthProperties.getIsCheckVerificationCode()) {
            //验证验证码
            boolean chuck = iVerificationCodeService.check(register.getVerificationCodeId(), register.getVerificationCode());
            //判断验证码是否校验成功
            if (!chuck) {
                return ResResultVO.fail(ErrCodeEnum.VERIFICATION_CODE_CHECK_FAILURE.getCode(),ErrCodeEnum.VERIFICATION_CODE_CHECK_FAILURE.getMsg());
            }
        }
        iUserService.register(register);
        try {
            TokenUserInfoVo userInfo = getUserInfo(register.getUsername(), register.getPassword());
            return ResResultVO.success(userInfo);
        }catch (Exception ex) {
            return ResResultVO.fail(ErrCodeEnum.ERR_USER_LOCK.getCode(),ErrCodeEnum.ERR_USER_LOCK.getMsg());
        }
    }

    private TokenUserInfoVo getUserInfo(String username,String password){
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("username",username);
        requestBody.add("password",password);
        requestBody.add("grant_type","password");
        HttpHeaders requestHeaders = new HttpHeaders();
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String token  = base64Encoder.encode((oauthProperties.getClientId() + ":"+ oauthProperties.getClientSecret()).getBytes());
        String authorization = "Basic " + token;
        requestHeaders.add(HttpHeaders.AUTHORIZATION, authorization);
        requestHeaders.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
        HttpEntity<MultiValueMap> httpEntity = new HttpEntity(requestBody,requestHeaders);
        ResponseEntity<JSONObject> exchange = restTemplate.exchange(oauthProperties.getOauthHost(), HttpMethod.POST, httpEntity, JSONObject.class);
        JSONObject body = exchange.getBody();
        String accessToken = body.getString("access_token");
        String refreshToken = body.getString("refresh_token");
        Integer expiresIn = body.getInteger("expires_in");
        String tokenType = body.getString("token_type");
        String scope = body.getString("scope");
        TokenUserInfoVo userInfo = new TokenUserInfoVo();
        userInfo.setAccessToken(accessToken);
        userInfo.setRefreshToken(refreshToken);
        userInfo.setExpiresIn(expiresIn);
        userInfo.setTokenType(tokenType);
        userInfo.setScope(scope);
        String nickName = body.getString("nick_name");
        userInfo.setNickName(nickName);
        String headPortrait = body.getString("head_portrait");
        userInfo.setHeadPortrait(headPortrait);
        Integer userId = body.getInteger("user_id");
        userInfo.setUserId(userId);
        return userInfo;
    }

}
