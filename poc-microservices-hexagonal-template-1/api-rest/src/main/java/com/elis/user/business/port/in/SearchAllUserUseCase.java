package com.elis.user.business.port.in;

import com.elis.user.business.port.in.dto.UserDto;

import java.util.List;

public interface SearchAllUserUseCase {

    List<UserDto> searchAllUser();


}
