package com.elis.user.business.port.in;

import com.elis.user.business.port.in.dto.UserDto;

public interface SearchOneUserUseCase {

    UserDto searchOneUser(final Long userId);

}
