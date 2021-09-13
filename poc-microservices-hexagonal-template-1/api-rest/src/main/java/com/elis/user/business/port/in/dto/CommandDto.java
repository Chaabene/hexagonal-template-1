package com.elis.user.business.port.in.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@ApiModel(description = "Command bean desciption")
public class CommandDto {

    private Long idCommand;

    private Long idUser;

    private String article;

    private Double prix;
}
