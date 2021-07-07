package cn.structure.starter.oauth.service.impl;

import cn.structure.starter.oauth.entity.Authority;
import cn.structure.starter.oauth.entity.Role;
import cn.structure.starter.oauth.entity.RoleAuthorityUser;
import cn.structure.starter.oauth.mapper.RoleMapper;
import cn.structure.starter.oauth.mapper.UserAuthorityMapper;
import cn.structure.starter.oauth.service.IAdminService;
import cn.structure.starter.oauth.vo.admin.AuthorityVo;
import cn.structure.starter.oauth.vo.admin.UserAuthorizationVo;
import cn.structure.starter.oauth.vo.login.CurrentAuthorityVo;
import cn.structure.starter.oauth.vo.login.MenuVo;
import cn.structure.starter.oauth.vo.role.AddRoleVo;
import cn.structure.starter.oauth.vo.role.EditRoleVo;
import cn.structure.starter.oauth.vo.role.RoleVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  管理实现类
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/4/6 22:03
 */
@Service
public class AdminServiceImpl implements IAdminService {

    @Resource
    private UserAuthorityMapper userAuthorityMapper;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public CurrentAuthorityVo findCurrentAuthorityByUserId(Integer userId) {
        List<Authority> authorities = userAuthorityMapper.findAuthorityAllByUserId(userId);
        CurrentAuthorityVo currentAuthorityVo = new CurrentAuthorityVo();

        List<String> authList = authorities.stream()
                .map(i -> (i.getAuthority())).collect(Collectors.toList());

        currentAuthorityVo.setAuthority(authList);

        Map<Integer, List<Authority>> parentMap = authorities.stream()
                .sorted(Comparator.comparing(Authority::getSort))
                .collect(Collectors.groupingBy(item -> (item.getParentId())));

        Map<Integer, MenuVo> menuVoMap = authorities.stream()
                .collect(Collectors.toMap(item -> (item.getId()), (it) -> {
                    MenuVo menuVo = new MenuVo();
                    menuVo.setName(it.getName());
                    menuVo.setMenuType(it.getMenuType());
                    menuVo.setPath(it.getPath());
                    menuVo.setIcon(it.getIcon());
                    menuVo.setSort(it.getSort());
                    return menuVo;
                }));
        List<MenuVo> menuVoList = new ArrayList<>();
        parentMap.keySet().forEach((parentId) -> {
            if(menuVoMap.containsKey(parentId)) {
                //子节点
                MenuVo menuVo = menuVoMap.get(parentId);
                List<Authority> authoritiesList = parentMap.get(parentId);
                List<MenuVo> menuVos = new ArrayList<>();
                authoritiesList.forEach(it -> menuVos.add(menuVoMap.get(it.getId())));
                menuVo.setChildren(menuVos);
            }else {
                //不存在表示跟节点
                List<Authority> authorityList = parentMap.get(parentId);
                for (Authority authority : authorityList) {
                    MenuVo menuVo = menuVoMap.get(authority.getId());
                    menuVoList.add(menuVo);
                }
            }
        });
        currentAuthorityVo.setMenuList(menuVoList);
        return currentAuthorityVo;
    }

    @Override
    public List<AuthorityVo> findAllAuthority() {

        List<Authority> authorities = userAuthorityMapper.finAllAuthority();

        Map<Integer, List<Authority>> parentMap = authorities.stream()
                .sorted(Comparator.comparing(Authority::getSort))
                .collect(Collectors.groupingBy(item -> (item.getParentId())));

        Map<Integer, AuthorityVo> authorityVoMap = authorities.stream()
                .collect(Collectors.toMap(item -> (item.getId()), (it) -> {
                    AuthorityVo menuVo = new AuthorityVo();
                    menuVo.setKey(it.getId());
                    menuVo.setTitle(it.getName());
                    return menuVo;
                }));

        List<AuthorityVo> authorityVoList = new ArrayList<>();
        parentMap.keySet().forEach((parentId) -> {
            if(authorityVoMap.containsKey(parentId)) {
                //子节点
                AuthorityVo authorityVo = authorityVoMap.get(parentId);
                List<Authority> authoritiesList = parentMap.get(parentId);
                List<AuthorityVo> authorityVos = new ArrayList<>();
                authoritiesList.forEach(it -> authorityVos.add(authorityVoMap.get(it.getId())));
                authorityVo.setChildren(authorityVos);
            }else {
                //不存在表示跟节点
                List<Authority> authorityList = parentMap.get(parentId);
                for (Authority authority : authorityList) {
                    AuthorityVo menuVo = authorityVoMap.get(authority.getId());
                    authorityVoList.add(menuVo);
                }
            }
        });
        return authorityVoList;
    }

    @Override
    public List<RoleVo> findAllRole() {
        List<RoleVo> roleVoList = roleMapper.findRoleList().stream().map(role -> {
            RoleVo roleVo = new RoleVo();
            roleVo.setId(role.getId());
            roleVo.setName(role.getName());
            roleVo.setValue(role.getValue());
            roleVo.setEnable(role.getEnable());
            roleVo.setCreateTime(role.getCreateTime());
            roleVo.setUpdateTime(role.getUpdateTime());
            return roleVo;
        }).collect(Collectors.toList());
        return roleVoList;
    }

    @Override
    @Transactional
    public Integer addRole(AddRoleVo addRoleVo) {
        LocalDateTime dateTime = LocalDateTime.now();
        Role role = new Role();
        role.setName(addRoleVo.getName());
        role.setValue(addRoleVo.getValue());
        role.setEnable(Boolean.TRUE);
        role.setIsDelete(Boolean.FALSE);
        role.setCreateTime(dateTime);
        role.setUpdateTime(dateTime);
        roleMapper.insertRole(role);
        List<Integer> authorityIds = addRoleVo.getAuthorityIds();
        if (null != authorityIds && authorityIds.size() > 0) {
            List<RoleAuthorityUser> roleAuthorityList = getRoleAuthorityList(dateTime, role.getId(), authorityIds);
            roleMapper.insertRoleAuthorityList(roleAuthorityList);
        }
        return role.getId();
    }

    @Override
    public Integer editRole(EditRoleVo editRoleVo) {
        LocalDateTime dateTime = LocalDateTime.now();
        Role role = new Role();
        role.setId(editRoleVo.getId());
        role.setName(editRoleVo.getName());
        role.setValue(editRoleVo.getValue());
        role.setEnable(editRoleVo.getEnable());
        role.setUpdateTime(dateTime);
        List<Integer> authorityIds = editRoleVo.getAuthorityIds();
        if (null != authorityIds && authorityIds.size()> 0) {
            roleMapper.delete(role.getId());
            List<RoleAuthorityUser> roleAuthorityList = getRoleAuthorityList(dateTime, role.getId(), authorityIds);
            roleMapper.insertRoleAuthorityList(roleAuthorityList);
        }
        return roleMapper.updateRole(role);
    }

    private List<RoleAuthorityUser> getRoleAuthorityList(LocalDateTime dateTime, Integer roleId, List<Integer> authorityIds) {
        List<RoleAuthorityUser> roleAuthorityList = authorityIds.stream().map(authorityId -> {
            RoleAuthorityUser roleAuthorityUser = new RoleAuthorityUser();
            roleAuthorityUser.setRoleId(roleId);
            roleAuthorityUser.setAuthorityId(authorityId);
            roleAuthorityUser.setCreateTime(dateTime);
            return roleAuthorityUser;
        }).collect(Collectors.toList());
        return roleAuthorityList;
    }

    @Override
    public Integer deleteRole(Integer roleId) {
        Role role = new Role();
        role.setId(roleId);
        role.setIsDelete(Boolean.TRUE);
        role.setUpdateTime(LocalDateTime.now());
        return roleMapper.updateRole(role);
    }

    @Override
    @Transactional
    public Integer userAuthorization(UserAuthorizationVo userAuthorizationVo) {
        Integer userId = userAuthorizationVo.getUserId();
        List<Integer> authorityIds = userAuthorizationVo.getAuthorityIds();
        if (null != authorityIds && authorityIds.size() > 0) {
            userAuthorityMapper.deleteUserAuthorityByUserId(userId);
            userAuthorityMapper.insertUserAuthorityList(authorityIds,userId, LocalDateTime.now());
        }
        List<Integer> roleIds = userAuthorizationVo.getRoleIds();
        if (null != roleIds && roleIds.size() > 0) {
            userAuthorityMapper.deleteUserRoleByUserId(userId);
            userAuthorityMapper.insertUserRoleList(roleIds,userId, LocalDateTime.now());
        }
        return userId;
    }
}
