package com.algaworks.osworks.api.controller;

import com.algaworks.osworks.api.model.ComentarioInpput;
import com.algaworks.osworks.api.model.ComentarioModel;
import com.algaworks.osworks.domain.exception.EntityNaoEncontradaException;
import com.algaworks.osworks.domain.exception.NegocioException;
import com.algaworks.osworks.domain.model.Comentario;
import com.algaworks.osworks.domain.model.OrdemServico;
import com.algaworks.osworks.domain.repository.OrdemServicoRepository;
import com.algaworks.osworks.domain.service.GestaoOrdemServicoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

    @Autowired
    private GestaoOrdemServicoService gestaoOrdemServicoService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComentarioModel adicionar(@PathVariable Long ordemServicoId,
                                     @Valid @RequestBody ComentarioInpput comentarioInpput) throws NegocioException {

        Comentario comentario = gestaoOrdemServicoService.adicionarComentario(ordemServicoId,
                comentarioInpput.getDescricao());

        return toModel(comentario);
    }

    @GetMapping
    public List<ComentarioModel> listar(@PathVariable Long ordemServicoId) throws EntityNaoEncontradaException {
        OrdemServico ordemServico = buscar(ordemServicoId);

        return toCollectionModel(ordemServico.getComentarios());
    }

    private OrdemServico buscar(Long ordemServicoId) throws EntityNaoEncontradaException {
        return ordemServicoRepository.findById(ordemServicoId)
                .orElseThrow(() -> new EntityNaoEncontradaException("Ordem de serviço não encontrada"));
    }

    private List<ComentarioModel> toCollectionModel(List<Comentario> comentarios) {
        return comentarios.stream().map(comentario -> toModel(comentario))
                .collect(Collectors.toList());
    }

    private ComentarioModel toModel(Comentario comentario){
        return modelMapper.map(comentario, ComentarioModel.class);
    }

}
