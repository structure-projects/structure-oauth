package cn.structured.oauth.sdk.service;

import cn.structured.oauth.api.dto.client.ClientDto;

/**
 * 远程调用客户端
 *
 * @author chuck
 * @since JDK1.8
 */
public interface IRemoteClientService {


    /**
     * 注册
     *
     * @param clientDto
     */
    void register(ClientDto clientDto);

    /**
     * 销毁
     *
     * @param clientId
     */
    void destroy(String clientId);

    /**
     * 变更
     *
     * @param clientDto
     */
    void change(ClientDto clientDto);

    /**
     * 查询
     *
     * @param clientId
     * @return
     */
    ClientDto findClientById(String clientId);

}
