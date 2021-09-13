package com.elis.user.business.port.in;

import com.elis.user.business.port.in.dto.UserDto;

public interface CreateUserUseCase {
    UserDto createUser(final UserDto userDto);
}
