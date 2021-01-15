package com.algaworks.osworks.domain.model;

import com.algaworks.osworks.domain.ValidationGroups;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cliente")
@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class Cliente {

    @NotNull(groups = ValidationGroups.ClienteId.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String nome;

    @NotEmpty
    @Size(max = 255)
    @Email
    @Column(nullable = false , unique = true)
    private String email;

    @NotEmpty
    @Size(max = 20)
    @Column(nullable = false , length = 20, unique = true)
    private String telefone;
}
