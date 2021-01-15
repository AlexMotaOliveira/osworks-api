package com.algaworks.osworks.api.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ComentarioModel {

    private Long Id;
    private String descricao;
    private OffsetDateTime dataEnvio;


}
