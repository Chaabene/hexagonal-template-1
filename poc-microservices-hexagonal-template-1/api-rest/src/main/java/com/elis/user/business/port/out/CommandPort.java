package com.elis.user.business.port.out;

import com.elis.user.business.domain.Command;

import java.util.List;


public interface CommandPort {

    List<Command> retrieveCommandsByUser(final Long userId);

}
