package com.example.Marcket.Admin;

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
@RequestMapping("/api/admins")
public class AdminController_V2 {
    @Autowired
    private  AdminServicio adminServicio;
    @Autowired
    private  MarketModelAssembler assembler;
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Admin>> getAllAdmins() {
        List<EntityModel<Admin>> admins = adminServicio.getAllAdmins().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(admins, linkTo(methodOn(AdminController_V2.class).getAllAdmins()).withSelfRel());
    }

    // Busca un administrador por id
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Admin>> getAdminById(@PathVariable int id) {
        Admin admin = adminServicio.getAdminById(id)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado con ID: " + id));
        return ResponseEntity.ok(assembler.toModel(admin));
    }
    // Busca un administrador por nombre 
   // @GetMapping(value = "/nombre/{nombre}", produces = MediaTypes.HAL_JSON_VALUE)
   // public ResponseEntity<EntityModel<Admin>> getAdminByNombre(@PathVariable String nombre) {
    //    Admin admin = adminServicio.getAdminByNombre(nombre)
    //            .orElseThrow(() -> new RuntimeException("Administrador no encontrado con nombre: " + nombre));
    //    return ResponseEntity.ok(assembler.toModel(admin));
   // }
// Crea un nuevo administrador
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Admin>> createAdmin(@RequestBody Admin admin) {
        adminServicio.createAdmin(admin);
        EntityModel<Admin> entityModel = assembler.toModel(admin);
        return ResponseEntity.created(linkTo(methodOn(AdminController_V2.class).getAdminById(admin.getId())).toUri()).body(entityModel);
    }   
    // Actualiza un administrador   
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Admin>> updateAdmin(@PathVariable int id, @RequestBody Admin admin) {
        adminServicio.updateAdmin(id, admin);
        EntityModel<Admin> entityModel = assembler.toModel(admin);
        return ResponseEntity.ok(entityModel);
    }       
// Elimina un administrador por id
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)       
    public ResponseEntity<Void> deleteAdmin(@PathVariable int id) {
        adminServicio.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }   
    
}
