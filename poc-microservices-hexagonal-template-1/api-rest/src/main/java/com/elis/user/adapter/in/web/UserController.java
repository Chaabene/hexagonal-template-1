package com.elis.user.adapter.in.web;

import com.elis.common.annotation.WebAdapter;
import com.elis.user.business.port.in.*;
import com.elis.user.business.port.in.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@WebAdapter(path = "/users")
@RequiredArgsConstructor
@Api(value = "API to manage users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final SearchAllUserUseCase searchAllUserUseCase;
    private final SearchOneUserUseCase searchOneUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final SearchCommandsByUserUseCase searchCommandsByUserUseCase;


    @ApiOperation(value = "View a list of available users", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
    })
    @GetMapping
    public List<UserDto> searchAllUser() {
        return searchAllUserUseCase.searchAllUser();
    }


    @ApiOperation(value = "Add a user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully added user"),
    })
    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody UserDto userDto) {
        UserDto userSaved = createUserUseCase.createUser(userDto);
        // location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userSaved.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "Search user by id", response = EntityModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved user"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("{id}")
    public EntityModel<UserDto> searchOneUser(@PathVariable("id") long idUser) {
        UserDto user = searchOneUserUseCase.searchOneUser(idUser);
        // Hateoas
        EntityModel<UserDto> resource = EntityModel.of(user);
        WebMvcLinkBuilder linkToBuilder = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .searchAllUser());
        resource.add(linkToBuilder.withRel("all-users"));
        return resource;
    }

    @ApiOperation(value = "Delete user")
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") long id) {
        deleteUserUseCase.deleteUser(id);
    }

    @ApiOperation(value = "Update user")
    @PutMapping("{id}")
    public void updateUser(@PathVariable("id") long idUser, @RequestBody UserDto user) {
        updateUserUseCase.updateUser(idUser, user);
    }

    @GetMapping("{id}/commands")
    public EntityModel<UserDto> searchOneUserWithDetailsCommand(@PathVariable("id") long idUser) {
        UserDto user = searchCommandsByUserUseCase.SearchCommandsByUser(idUser);
        // Hateoas
        EntityModel<UserDto> resource = EntityModel.of(user);
        WebMvcLinkBuilder linkToBuilder = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                        .searchAllUser());
        resource.add(linkToBuilder.withRel("all-users"));
        return resource;
    }
}
