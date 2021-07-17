package cn.structure.starter.oauth.mapper;


import cn.structure.starter.oauth.entity.Authority;
import cn.structure.starter.oauth.entity.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserAuthorityMapper {

    /**
     * 查询一个用户的权限
     */
    @Results(id = "baseResult",value = {
            @Result(id= true,column = "id",property = "id",jdbcType = JdbcType.INTEGER),
            @Result(column = "name",property = "name",jdbcType = JdbcType.VARCHAR),
            @Result(column = "parent_id",property = "parentId",jdbcType = JdbcType.INTEGER),
            @Result(column = "menu_type",property = "menuType",jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time",property = "createTime",jdbcType = JdbcType.TIMESTAMP),
    })
    @Select("SELECT a.id,a.name,a.value,a.parent_id,a.type,a.path,a.menu_type,a.icon,a.sort FROM oauth_authority a \n" +
            "JOIN oauth_user_authority_mapping ra ON a.id = ra.authority_id \n" +
            "WHERE ra.user_id = #{userId}")
    List<Authority> findAuthorityByUserId(Integer userId);


    /**
     * 查询一个用户所有的角色
     */
    @Results(value = {
            @Result(id= true,column = "id",property = "id",jdbcType = JdbcType.INTEGER),
            @Result(column = "name",property = "name",jdbcType = JdbcType.VARCHAR),
            @Result(column = "value",property = "value",jdbcType = JdbcType.VARCHAR)
    })
    @Select("SELECT r.id,r.name,r.value FROM oauth_role r \n" +
            "JOIN oauth_user_role_mapping ur ON r.id = ur.role_id AND ur.user_id = #{userId}")
    List<Role> findRoleByUserId(Integer userId);

    /**
     * 查询一个用户所有的权限包含角色的权限
     */
    @ResultMap("baseResult")
    @Select("SELECT a.id,a.name,a.value,a.parent_id,a.type,a.path,a.menu_type,a.icon,a.sort FROM oauth_authority a \n" +
            "JOIN oauth_user_authority_mapping ra ON a.id = ra.authority_id \n" +
            "WHERE ra.user_id = #{userId}\n" +
            "UNION\n" +
            "SELECT a.id,a.name,a.value,a.parent_id,a.type,a.path,a.menu_type,a.icon,a.sort FROM oauth_authority a \n" +
            "JOIN oauth_role_authority_mapping ra ON a.id = ra.authority_id \n" +
            "JOIN oauth_user_role_mapping ur ON ur.user_id = #{userId}\n")
    List<Authority> findAuthorityAllByUserId(Integer userId);

    @ResultMap("baseResult")
    @Select("SELECT a.id,a.name,a.value,a.parent_id,a.type,a.path,a.menu_type,a.icon,a.sort,a.create_time " +
            "FROM oauth_authority a WHERE is_deleted = 0")
    List<Authority> finAllAuthority();


    /**
     * 给用户添加权限
     */
    @Insert("<script>" +
            "INSERT INTO oauth_user_authority_mapping (user_id,authority_id,create_time) VALUES " +
            "<foreach collection='ids' item='item' separator=','>" +
            "(#{userId},#{item},#{createTime})" +
            "</foreach>" +
            "</script>")
    int insertUserAuthorityList(@Param("ids") List<Integer> authorityIds, @Param("userId") Integer userId, @Param("createTime") LocalDateTime createTime);

    /**
     * 删除用户权限
     */
    @Delete("DELETE FROM oauth_user_authority_mapping WHERE user_id =#{userId}")
    int deleteUserAuthorityByUserId(Integer userId);

    /**
     * 给用户添加角色
     */
    @Insert("<script>" +
            "INSERT INTO oauth_user_role_mapping (user_id,role_id,create_time) VALUES " +
            "<foreach collection='ids' item='item' separator=','>" +
            "(#{userId},#{item},#{createTime})" +
            "</foreach>" +
            "</script>")
    int insertUserRoleList(@Param("ids") List<Integer> roleIds, @Param("userId") Integer userId, @Param("createTime") LocalDateTime createTime);

    /**
     * 给用户删除角色
     */
    @Delete("DELETE FROM oauth_user_role_mapping WHERE user_id =#{userId}")
    int deleteUserRoleByUserId(Integer userId);

}
