package cn.structure.starter.oauth.service.impl;

import cn.structure.starter.oauth.entity.VerificationCodeInfo;
import cn.structure.starter.oauth.mapper.VerificationCodeMapper;
import cn.structure.starter.oauth.service.IVerificationCodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * <p>
 * 验证码Service
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/6/21 11:32
 */
@Service
public class VerificationCodeServiceImpl implements IVerificationCodeService {

    @Resource
    private VerificationCodeMapper verificationCodeMapper;

    @Override
    public boolean isExistBlacklist(String ipAddress) {
        int verificationCodeBlacklistByAddress = verificationCodeMapper.findVerificationCodeBlacklistByAddress(ipAddress);
        return (verificationCodeBlacklistByAddress > 0)? true : false;
    }

    @Override
    public int isExistFrequently(String ipAddress) {
        LocalDateTime localDateTime = LocalDateTime.now();
        List<VerificationCodeInfo> oneDayCodeListByAddress = verificationCodeMapper.findOneDayCodeListByAddress(ipAddress);
        if (oneDayCodeListByAddress.size() >= 100) {
            return 4;
        }
        //1小时内的时间
        LocalDateTime oneHour = localDateTime.minus(1, ChronoUnit.HOURS);
        //1小时内的数量
        long oneHourCount = oneDayCodeListByAddress.stream()
                .filter(m ->  m.getCreateTime().isAfter(oneHour)).count();
        if (oneHourCount >= 50) {
            return 3;
        }
        //前30分钟的时间
        LocalDateTime thirtyMinute = localDateTime.minus(30, ChronoUnit.MINUTES);
        //前30分钟内的数量
        long thirtyMinuteCount = oneDayCodeListByAddress.stream()
                .filter(m -> m.getCreateTime().isAfter(thirtyMinute)).count();
        if (thirtyMinuteCount >= 20) {
            return 2;
        }
        //前1分钟的时间
        LocalDateTime oneMinute = localDateTime.minus(1, ChronoUnit.MINUTES);
        //前1分钟内的数量
        long oneMinuteCount = oneDayCodeListByAddress.stream()
                .filter(m -> m.getCreateTime().isAfter(oneMinute)).count();
        if (oneMinuteCount >= 5) {
            return 1;
        }
        return 0;
    }

    @Override
    public Integer save(String ipAddress, String code) {
        VerificationCodeInfo verificationCodeInfo = new VerificationCodeInfo();
        verificationCodeInfo.setCode(code);
        verificationCodeInfo.setAddress(ipAddress);
        verificationCodeInfo.setCreateTime(LocalDateTime.now());
        verificationCodeMapper.save(verificationCodeInfo);
        return verificationCodeInfo.getId();
    }

    @Override
    @Transactional
    public boolean check(Integer id, String code) {
        VerificationCodeInfo codeInfo = verificationCodeMapper.findCodeById(id);
        if (null == codeInfo) {
            return false;
        }
        if (codeInfo.getCheck()) {
            return false;
        }
        if (code.equals(code)) {
            verificationCodeMapper.updateCheckById(id);
            return true;
        }
        return false;
    }
}
