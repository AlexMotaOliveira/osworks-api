package com.algaworks.osworks.api.controller;

import com.algaworks.osworks.domain.model.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClienteController {

    @GetMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Cliente> Listar(){
        var cliente1 = new Cliente();
        cliente1.setId(1l);
        cliente1.setNome("Alex");
        cliente1.setEmail("alex.mota");
        cliente1.setTelefone("11970327634");


        var cliente2 = new Cliente();
        cliente2.setId(2l);
        cliente2.setNome("Alan");
        cliente2.setEmail("daiane.mota");
        cliente2.setTelefone("11967383998");

        return Arrays.asList(cliente1,cliente2);
    }
}
