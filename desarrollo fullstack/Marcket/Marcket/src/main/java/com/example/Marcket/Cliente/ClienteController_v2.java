package com.example.Marcket.Cliente;

import com.example.Marcket.assemblers.MarketModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/clientes")    

public class ClienteController_v2 { 
    @Autowired
    private ClienteServicio clienteServicio;
    @Autowired
    private MarketModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Cliente>> getAllClientes() {
        List<EntityModel<Cliente>> clientes = clienteServicio.getAllClientes().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(clientes, linkTo(methodOn(ClienteController_v2.class).getAllClientes()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Cliente>> getClienteById(@PathVariable int id) {
        Cliente cliente = clienteServicio.getClienteById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
        return ResponseEntity.ok(assembler.toModel(cliente));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Cliente>> createCliente(@RequestBody Cliente cliente) {
        clienteServicio.createCliente(cliente);
        EntityModel<Cliente> entityModel = assembler.toModel(cliente);
        return ResponseEntity.created(linkTo(methodOn(ClienteController_v2.class).getClienteById(cliente.getId())).toUri()).body(entityModel);
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Cliente>> updateCliente(@PathVariable int id, @RequestBody Cliente cliente) {
        clienteServicio.updateCliente(id, cliente);
        EntityModel<Cliente> entityModel = assembler.toModel(cliente);
        return ResponseEntity.ok(entityModel);
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> deleteCliente(@PathVariable int id) {
        clienteServicio.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
    
}
