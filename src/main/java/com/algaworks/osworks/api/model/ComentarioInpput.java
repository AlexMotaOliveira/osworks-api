package com.algaworks.osworks.api.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ComentarioInpput {

    @NotBlank
    private String descricao;
}
