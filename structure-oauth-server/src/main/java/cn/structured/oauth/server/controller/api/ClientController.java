package cn.structured.oauth.server.controller.api;

import cn.structure.common.entity.ResResultVO;
import cn.structure.common.utils.ResultUtilSimpleImpl;
import cn.structured.oauth.api.dto.client.ClientDto;
import cn.structured.oauth.server.controller.assembler.ClientAssembler;
import cn.structured.oauth.server.entity.OauthClientDetails;
import cn.structured.oauth.server.service.IClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 终端控制器
 *
 * @author chuck
 * @since JDK1.8
 */
@Api(tags = "终端控制器")
@RestController
@RequestMapping(value = "/client")
public class ClientController {

    @Resource
    private IClientService clientService;

    @ApiOperation(value = "注册")
    @PostMapping(value = "/register")
    public ResResultVO<Void> register(@RequestBody ClientDto clientDto) {
        clientService.save(ClientAssembler.assembler(clientDto));
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "销毁")
    @DeleteMapping(value = "/destroy/{clientId}")
    public ResResultVO<Void> destroy(@PathVariable String clientId) {
        clientService.removeById(clientId);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "变更")
    @PostMapping(value = "/change")
    public ResResultVO<Void> change(@RequestBody ClientDto clientDto) {
        clientService.updateById(ClientAssembler.assembler(clientDto));
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "查询")
    @GetMapping(value = "/get/{clientId}")
    public ResResultVO<ClientDto> get(@PathVariable String clientId) {
        OauthClientDetails clientDetails = clientService.getById(clientId);
        return ResultUtilSimpleImpl.success(ClientAssembler.assembler(clientDetails));
    }
}
