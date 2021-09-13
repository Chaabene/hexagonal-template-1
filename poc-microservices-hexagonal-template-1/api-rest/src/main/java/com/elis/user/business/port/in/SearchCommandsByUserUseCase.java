package com.elis.user.business.port.in;

import com.elis.user.business.port.in.dto.UserDto;

public interface SearchCommandsByUserUseCase {

    UserDto SearchCommandsByUser(final Long userId);

}
