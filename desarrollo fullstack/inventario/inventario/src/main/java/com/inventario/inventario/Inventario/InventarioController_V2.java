package com.inventario.inventario.Inventario;

import com.inventario.inventario.assemblers.InventarioModelAssembler;
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
@RequestMapping("/api/inventarios")

public class InventarioController_V2 {
    @Autowired
    private InventarioService inventarioService;
    @Autowired
    private InventarioModelAssembler assembler; 
    // Busca todos los inventarios
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Inventario>> getAllInventarios() {
        List<EntityModel<Inventario>> inventarios = inventarioService.getAllInventarios().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(inventarios, linkTo(methodOn(InventarioController_V2.class).getAllInventarios()).withSelfRel());
    }
    // Busca un inventario por id
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Inventario> getInventarioById(@PathVariable int id) {
        Inventario inventario = inventarioService.getInventarioById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado con ID: " + id));
        return assembler.toModel(inventario);
    }

    // Crea un nuevo inventario
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Inventario>> createInventario(@RequestBody Inventario inventario) {
        inventarioService.createInventario(inventario);
        EntityModel<Inventario> entityModel = assembler.toModel(inventario);
        return ResponseEntity.created(linkTo(methodOn(InventarioController_V2.class).getInventarioById(inventario.getId())).toUri()).body(entityModel);
    }
    // Actualiza un inventario existente
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)      
    public ResponseEntity<EntityModel<Inventario>> updateInventario(@PathVariable int id, @RequestBody Inventario inventario) {
        inventarioService.updateInventario(id, inventario);
        EntityModel<Inventario> entityModel = assembler.toModel(inventario);
        return ResponseEntity.ok(entityModel);
    }
    // Elimina un inventario por id
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)       
    public ResponseEntity<Void> deleteInventario(@PathVariable int id) {
        inventarioService.deleteInventario(id);
        return ResponseEntity.noContent().build();
    }
    
}
