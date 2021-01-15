package com.algaworks.osworks.domain.service;

import com.algaworks.osworks.domain.exception.NegocioException;
import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroClienteServise {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente) throws NegocioException {
        Cliente clienteEmail = clienteRepository.findByEmail(cliente.getEmail());

        if(clienteEmail != null && !clienteEmail.equals(cliente)){
            throw new NegocioException("Já existe um email cadastrado");
        }

        return  clienteRepository.save(cliente);
    }

    public void delete (Long clienteId){
        clienteRepository.deleteById(clienteId);
    }
}
