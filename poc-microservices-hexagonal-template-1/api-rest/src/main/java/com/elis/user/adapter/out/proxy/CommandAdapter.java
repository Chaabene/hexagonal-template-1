package com.elis.user.adapter.out.proxy;

import com.elis.common.annotation.ProxyAdapter;
import com.elis.user.adapter.out.proxy.model.CommandProxyBean;
import com.elis.user.business.domain.Command;
import com.elis.user.business.port.out.CommandPort;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ProxyAdapter
@RequiredArgsConstructor
public class CommandAdapter implements CommandPort {

    private final ModelMapper modelMapper;
    private final CommandProxy commandProxy;


    @Override
    public List<Command> retrieveCommandsByUser(final Long userId) {
        List<CommandProxyBean> listCommands=commandProxy.retrieveCommandsByUser(userId);
        return Optional.ofNullable(listCommands)
                .map(Collection::stream)
                .orElseGet(Stream::empty)
                .map(command -> modelMapper.map(command, Command.class)).collect(Collectors.toList());
    }

}
