package com.algaworks.osworks.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseErrors {

    @Data
    @AllArgsConstructor @NoArgsConstructor
    @Builder
    public static class Body{

        private String nome;
        private String mensagem;

    }

    private Integer status;
    private OffsetDateTime data;
    public String message;
    public List<Body> bodys;
}
