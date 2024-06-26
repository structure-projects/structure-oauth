package cn.structured.oauth.user.controller.assembler;

import cn.structure.starter.oauth.common.entity.StructureAuthUser;
import cn.structured.oauth.user.api.dto.user.UserDetailDto;
import cn.structured.oauth.user.entity.User;

/**
 * 用户装配器
 *
 * @author cqliut
 * @version 2023.0711
 * @since 1.0.1
 */
public class UserAssembler {

    /**
     * 私有构造为防止构建实例
     */
    private UserAssembler() {

    }

    public static UserDetailDto assembler(User user) {
        UserDetailDto userDetailDto = new UserDetailDto();
        userDetailDto.setId(user.getId());
        userDetailDto.setUsername(user.getUsername());
        userDetailDto.setNickName(user.getNickName());
        userDetailDto.setAvatar(user.getAvatar());
        userDetailDto.setCreateTime(user.getCreateTime());
        return userDetailDto;
    }

    public static StructureAuthUser assemblerAuthUser(User user) {
        StructureAuthUser authUser = new StructureAuthUser();
        authUser.setId(user.getId());
        authUser.setUsername(user.getUsername());
        authUser.setPassword(user.getPassword());
        authUser.setEnable(user.getEnabled());
        authUser.setUnlocked(user.getUnlocked());
        authUser.setUnexpired(user.getUnexpired());
        return authUser;
    }

}
