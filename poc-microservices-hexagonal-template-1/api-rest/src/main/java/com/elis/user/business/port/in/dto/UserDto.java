package com.elis.user.business.port.in.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@ApiModel(description = "user bean desciption")
public class UserDto {
    private Long id;

    @NotBlank(message = "{validation.not.blanck}")
    private String username;


    @Email(message = "{validation.email}")
    private String email;

    @NotNull
    @Size(min = 4, max = 15, message = "{validation.password}")
    private String password;

    @NotBlank
    private String address;

    @Min(value = 18, message = "{validation.age}")
    private int age;

    private List<CommandDto> commands;
}
