package com.algaworks.osworks.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class Cliente {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
}
