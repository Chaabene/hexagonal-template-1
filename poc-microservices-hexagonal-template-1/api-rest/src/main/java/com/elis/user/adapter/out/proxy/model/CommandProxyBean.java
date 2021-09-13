package com.elis.user.adapter.out.proxy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandProxyBean {

    private Long idCommand;

    private Long idUser;

    private String article;

    private Double prix;
}