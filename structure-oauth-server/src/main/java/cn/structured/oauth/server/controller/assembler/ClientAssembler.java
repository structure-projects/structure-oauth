package cn.structured.oauth.server.controller.assembler;

import cn.structure.common.constant.SymbolConstant;
import cn.structured.oauth.api.dto.client.ClientDto;
import cn.structured.oauth.server.entity.OauthClientDetails;

import java.util.Arrays;

/**
 * 客户端装配器
 *
 * @author chuck
 * @since JDK1.8
 */
public class ClientAssembler {
    /**
     * 将DTO装配为PO
     *
     * @param clientDto 客户端DTO
     * @return
     */
    public static OauthClientDetails assembler(ClientDto clientDto) {
        OauthClientDetails oauthClientDetails = new OauthClientDetails();
        oauthClientDetails.setClientId(clientDto.getClientId());
        oauthClientDetails.setResourceIds(String.valueOf(clientDto.getResourceIds()));
        oauthClientDetails.setClientSecret(clientDto.getClientSecret());
        oauthClientDetails.setScope(clientDto.getScope());
        oauthClientDetails.setAuthorizedGrantTypes(String.valueOf(clientDto.getAuthorizedGrantTypes()));
        oauthClientDetails.setAuthorities(String.valueOf(clientDto.getAuthorities()));
        oauthClientDetails.setAccessTokenValidity(clientDto.getAccessTokenValidity());
        oauthClientDetails.setRefreshTokenValidity(clientDto.getRefreshTokenValidity());
        oauthClientDetails.setAdditionalInformation(clientDto.getAdditionalInformation());
        oauthClientDetails.setAutoapprove(String.valueOf(clientDto.getAutoApprove()));
        oauthClientDetails.setWebServerRedirectUri(clientDto.getWebServerRedirectUri());
        oauthClientDetails.setCreateTime(clientDto.getCreateTime());
        return oauthClientDetails;
    }

    /**
     * 将客户端详情转换为DTO
     *
     * @param clientDetails 客户端
     * @return
     */
    public static ClientDto assembler(OauthClientDetails clientDetails) {
        ClientDto clientDto = new ClientDto();
        clientDto.setClientId(clientDetails.getClientId());
        clientDto.setResourceIds(Arrays.asList(clientDetails.getResourceIds().split(SymbolConstant.COMMA)));
        clientDto.setClientSecret(clientDetails.getClientSecret());
        clientDto.setScope(clientDetails.getScope());
        clientDto.setAuthorizedGrantTypes(Arrays.asList(clientDetails.getAuthorizedGrantTypes().split(SymbolConstant.COMMA)));
        clientDto.setAuthorities(Arrays.asList(clientDetails.getAuthorities().split(SymbolConstant.COMMA)));
        clientDto.setAccessTokenValidity(clientDetails.getAccessTokenValidity());
        clientDto.setRefreshTokenValidity(clientDetails.getRefreshTokenValidity());
        clientDto.setAdditionalInformation(clientDetails.getAdditionalInformation());
        clientDto.setAutoApprove(Boolean.valueOf(clientDetails.getAutoapprove()));
        clientDto.setWebServerRedirectUri(clientDetails.getWebServerRedirectUri());
        clientDto.setCreateTime(clientDetails.getCreateTime());
        return clientDto;

    }
}
