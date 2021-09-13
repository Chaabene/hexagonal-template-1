package com.elis.user.business.interactor;


import com.elis.common.annotation.UseCase;
import com.elis.user.business.domain.User;
import com.elis.user.business.port.in.*;
import com.elis.user.business.port.in.dto.CommandDto;
import com.elis.user.business.port.in.dto.UserDto;
import com.elis.user.business.port.out.CommandPort;
import com.elis.user.business.port.out.CrudUserPort;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UseCase
@RequiredArgsConstructor
public class ManagementUserService implements CreateUserUseCase, DeleteUserUseCase, SearchOneUserUseCase, SearchAllUserUseCase, UpdateUserUseCase,SearchCommandsByUserUseCase {

	public static final String USER_NOT_EXIST = "User with id %s does not exist";
	private final CrudUserPort crudUserPort;
	private final CommandPort commandPort;
	private final ModelMapper modelMapper;

	@Override
	public List<UserDto> searchAllUser() {
		List<User> users = crudUserPort.findAllUser();
		return	Optional.ofNullable(users)
				        .map(Collection::stream)
				        .orElseGet(Stream::empty)
			            .map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
	}

	@Override
	public UserDto searchOneUser(final Long userId) {
		return crudUserPort.findUserById(userId).map(user -> modelMapper.map(user, UserDto.class)).<ResponseStatusException>orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format(USER_NOT_EXIST,userId));
		});
	}

	@Override
	public UserDto createUser(final UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		User savedUser = crudUserPort.saveUser(user);
		return modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public void deleteUser(final Long userId) {
		boolean exists = crudUserPort.existsUser(userId);
		if (!exists) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format(USER_NOT_EXIST,userId));
		}
		crudUserPort.deleteUser(userId);
	}

	@Override
	public void updateUser(final Long userId, final UserDto userDto) {
		boolean exists = crudUserPort.existsUser(userId);
		if (!exists) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format(USER_NOT_EXIST,userId));
		}
		userDto.setId(userId);
		crudUserPort.updateUser(userId, modelMapper.map(userDto, User.class));
	}

	@Override
	public UserDto SearchCommandsByUser(final Long userId) {
		UserDto userDto = crudUserPort.findUserById(userId).map(user -> modelMapper.map(user, UserDto.class)).<ResponseStatusException>orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(USER_NOT_EXIST, userId));
		});
		List<CommandDto> commands = commandPort.retrieveCommandsByUser(userId).stream().map(command -> modelMapper.map(command, CommandDto.class)).collect(Collectors.toList());
		userDto.setCommands(commands);
		return userDto;
	}
}
