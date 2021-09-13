package com.elis.user.adapter.out.proxy;

import com.elis.user.adapter.out.proxy.model.CommandProxyBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "currency-exchange", url = "${COMMAND_URI:https://0be09fa7-616c-4279-a800-4d003a801fcf.mock.pstmn.io}")
public interface CommandProxy {

    @GetMapping("/commands/{idUser}")
    public List<CommandProxyBean> retrieveCommandsByUser(@PathVariable Long idUser);
}
