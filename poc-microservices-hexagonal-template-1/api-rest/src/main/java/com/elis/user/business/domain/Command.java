package com.elis.user.business.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Command {

    private Long idCommand;

    private Long idUser;

    private String article;

    private Double prix;
}
