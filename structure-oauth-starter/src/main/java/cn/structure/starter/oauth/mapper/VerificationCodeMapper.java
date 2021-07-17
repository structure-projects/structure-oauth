package cn.structure.starter.oauth.mapper;

import cn.structure.starter.oauth.entity.VerificationCodeInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * <p>
 * 验证码Mapper
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/6/21 16:35
 */
@Mapper
public interface VerificationCodeMapper {

    @Insert(value = "INSERT INTO verification_code_info (code,address,create_time)VALUES(#{code},#{address},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(VerificationCodeInfo verificationCodeInfo);

    @ResultType(Integer.class)
    @Select("SELECT COUNT(1) FROM verification_code_blacklist WHERE address = #{address}")
    int findVerificationCodeBlacklistByAddress(@Param("address") String address);

    @Results(id = "baseResult",value = {
            @Result(column = "is_check",property = "check",jdbcType = JdbcType.TINYINT),
            @Result(column = "create_time",property = "createTime",jdbcType = JdbcType.DATETIMEOFFSET),
    })
    @Select("SELECT id,code,address,is_check,create_time FROM verification_code_info WHERE address = #{address} AND  TO_DAYS(create_time) = TO_DAYS(NOW())")
    List<VerificationCodeInfo> findOneDayCodeListByAddress(@Param("address") String ipAddress);

    @ResultMap("baseResult")
    @Select("SELECT id,code,address,is_check,create_time FROM verification_code_info WHERE id = #{id}")
    VerificationCodeInfo findCodeById(@Param("id") Integer id);

    @Update("UPDATE verification_code_info SET is_check = 1 WHERE id = #{id} ")
    int updateCheckById(@Param("id") Integer id);

}
