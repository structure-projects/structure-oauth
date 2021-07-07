package cn.structure.starter.oauth.mapper;

import cn.structure.starter.oauth.entity.Role;
import cn.structure.starter.oauth.entity.RoleAuthorityUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * <p>
 * 角色Mapper
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/7/1 21:30
 */
@Mapper
public interface RoleMapper {

    @Results(id = "baseResult",value = {
            @Result(column = "is_enabled",property = "enable",jdbcType = JdbcType.TINYINT),
            @Result(column = "create_time",property = "createTime",jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time",property = "createTime",jdbcType = JdbcType.TIMESTAMP),
    })
    @Select("SELECT id,name,value,is_enabled,create_time,update_time FROM oauth_role WHERE is_deleted = 0")
    List<Role> findRoleList();

    @Insert("INSERT INTO oauth_role" +
            "(name,value,is_enabled,create_time,update_time) " +
            "VALUES (#{name},#{value},#{enable},#{createTime},#{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertRole(Role role);

    @Update("<script> " +
            "UPDATE oauth_role " +
            "<trim prefix=\"set\" suffixOverrides=\",\">" +
            "<if test='name !=null'> " +
            "name = #{name}," +
            "</if>" +
            "<if test='value !=null'> " +
            "value = #{value}," +
            "</if>" +
            "<if test='enable !=null'> " +
            "is_enabled = #{enable}," +
            "</if>" +
            "<if test='isDelete !=null'> " +
            "is_deleted = #{isDelete}," +
            "</if>" +
            "<if test='updateTime !=null'> " +
            "update_time = #{updateTime}," +
            "</if>" +
            "</trim>" +
            "WHERE id = #{id}" +
            "</script>")
    int updateRole(Role role);

    @ResultMap("baseResult")
    @Select("SELECT id,name,value,is_enabled,create_time,update_time FROM oauth_role WHERE id = #{id}")
    Role selectById(Integer id);

    @Delete("DELETE FROM oauth_role WHERE id = #{id}")
    int delete(Integer id);

    @Insert("<script>" +
            "INSERT INTO oauth_role_authority_mapping (role_id,authority_id,create_time) VALUES " +
            "<foreach collection='list' item=\"item\" index=\"index\" separator=','>" +
            "(#{item.roleId},${item.authorityId},#{item.createTime})" +
            "</foreach>" +
            "</script>")
    int insertRoleAuthorityList(@Param("list") List<RoleAuthorityUser> authorityUser);

    @Delete("DELETE FROM oauth_role_authority_mapping WHERE role_id =#{roleId}")
    int deleteRoleAuthorityByRoleId(Integer roleId);

}
