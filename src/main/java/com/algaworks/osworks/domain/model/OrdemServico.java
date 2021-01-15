package com.algaworks.osworks.domain.model;

import com.algaworks.osworks.domain.ValidationGroups;
import com.algaworks.osworks.domain.exception.NegocioException;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ConvertGroup(from = Default.class,to = ValidationGroups.ClienteId.class)
    @NotNull
    @ManyToOne
    private Cliente cliente;

    @NotBlank
    private String descricao;

    @NotNull
    private BigDecimal preco;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    private StatusOrdemServico status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataAbertura;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataFinalizado;

    @OneToMany(mappedBy = "ordemServico", fetch = FetchType.EAGER)
    private List<Comentario> comentarios = new ArrayList<>();

    public boolean podeSerfinalizada(){
        return  StatusOrdemServico.ABERTA.equals(getStatus());
    }

    public boolean naoPodeSerfinalizada(){
        return  !podeSerfinalizada();
    }

    public void finalizar() throws NegocioException {
        if (naoPodeSerfinalizada()) {
            throw new NegocioException("Ordem Serviço não pode ser finalizada");
        }
        setStatus(StatusOrdemServico.FINALIZADA);
        setDataFinalizado(OffsetDateTime.now());
    }
}
