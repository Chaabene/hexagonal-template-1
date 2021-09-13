package com.elis.user.business.port.in;

import com.elis.user.business.port.in.dto.UserDto;

public interface UpdateUserUseCase {
    void updateUser(final Long userId, final UserDto userDto);

}
