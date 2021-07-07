package cn.structure.starter.oauth.mapper;


import cn.structure.starter.oauth.entity.UserAccount;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;


/**
 * 用户账户Mapper
 *
 * @author chuck
 * @date 2020/1/15 17:04
 * @since 1.0.1
 */
@Mapper
public interface UserAccountMapper {

    /**
     *
     * 根据用户名查询用户账户
     *
     * @param username
     */
    @Results(id = "userResult",value = {
            @Result(id = true,column = "id",property = "id",jdbcType = JdbcType.INTEGER),
            @Result(column = "username",property = "username",jdbcType = JdbcType.VARCHAR),
            @Result(column = "password",property = "password",jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_unexpired",property = "unexpired",jdbcType = JdbcType.TINYINT),
            @Result(column = "is_enabled",property = "enable",jdbcType = JdbcType.TINYINT),
            @Result(column = "is_unlocked",property = "unlocked",jdbcType = JdbcType.TINYINT),
            @Result(column = "is_deleted",property = "deleted",jdbcType = JdbcType.TINYINT),
            @Result(column = "nick_name",property = "nickName",jdbcType = JdbcType.VARCHAR),
            @Result(column = "head_portrait",property = "headPortrait",jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time",property = "createTime",jdbcType = JdbcType.DATE)
    })
    @Select("SELECT id,username,nick_name,head_portrait,password,is_unexpired,is_enabled,is_unlocked,is_deleted,create_time" +
            " FROM oauth_user WHERE username = #{username} AND is_deleted = 0")
    UserAccount selectUserByUsername(String username);

    @ResultMap("userResult")
    @Select("SELECT id,username,password,nick_name,head_portrait,is_unexpired,is_enabled,is_unlocked,is_deleted,create_time" +
            " FROM oauth_user WHERE id = #{id} ")
    UserAccount selectUserById(Integer id);

    @Insert("INSERT INTO oauth_user" +
            "(username,password,nick_name,head_portrait,is_unexpired,is_enabled,is_unlocked,is_deleted,create_time,update_time) " +
            "VALUES (#{username},#{password},#{nickName},#{headPortrait},#{unexpired},#{enable},#{unlocked},#{deleted},#{createTime},#{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUserAccount(UserAccount userAccount);

    @Update("<script> " +
            "UPDATE oauth_user " +
            "<trim prefix=\"set\" suffixOverrides=\",\">" +
            "<if test='password !=null'> " +
            "password = #{password}," +
            "</if>" +
            "<if test='unexpired !=null'> " +
            "is_unexpired = #{unexpired}," +
            "</if>" +
            "<if test='enable !=null'> " +
            "is_enabled = #{enable}," +
            "</if>" +
            "<if test='unlocked !=null'> " +
            "is_unlocked = #{unlocked}," +
            "</if>" +
            "<if test='deleted !=null'> " +
            "is_deleted = #{deleted}," +
            "</if>" +
            "<if test='nickName !=null'> " +
            "nick_name = #{nickName}," +
            "</if>" +
            "<if test='headPortrait !=null'> " +
            "head_portrait = #{headPortrait}," +
            "</if>" +
            "<if test='createTime !=null'> " +
            "create_time = #{createTime}," +
            "</if>" +
            "<if test='updateTime !=null'> " +
            "update_time = #{updateTime}," +
            "</if>" +
            "</trim>" +
            "WHERE id = #{id}" +
            "</script>")
    int updateUser(UserAccount userAccount);

    @ResultMap("userResult")
    @Select("<script>" +
            "SELECT id,username,password,nick_name,head_portrait,is_unexpired,is_enabled,is_unlocked,is_deleted,create_time " +
            "FROM oauth_user" +
            "<trim prefix=\"WHERE\" suffixOverrides=\"AND\">" +
            "<if test='enable !=null'> " +
            "is_enabled = #{enable} AND" +
            "</if>" +
            "<if test='unlock !=null'> " +
            "is_unlocked = #{unlock} AND" +
            "</if>" +
            "<if test='name !=null'> " +
            "(username like CONCAT(CONCAT('%','#{name}'),'%') OR nick_name like CONCAT(CONCAT('%','#{name}'),'%') AND " +
            "</if>" +
            "</trim>" +
            "</script>")
    List<UserAccount> selectUserList(@Param("name") String name, @Param("enable") Boolean enable, @Param("unlock") Boolean unlock);

}


