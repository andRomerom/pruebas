package com.venta.venta.Venta;


import com.venta.venta.assemblers.VentaModelAssembler;

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
@RequestMapping("/api/ventas")
public class VentaController_V2 {
    @Autowired
    private VentaService ventaService;
    @Autowired
    private VentaModelAssembler assembler;
    //busca todas las ventas
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Venta>> getAllVentas()
     {
           List<EntityModel<Venta>> ventas = ventaService.getAllVentas().stream().map(assembler::toModel).collect(Collectors.toList());
            return CollectionModel.of(ventas,linkTo(methodOn(VentaController_V2.class).getAllVentas()).withSelfRel());
     }
     //Busca un venta por id
     @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Venta> getCarreraByCodigo(@PathVariable int id) 
        {
           Venta venta = ventaService.getVentaById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + id));
            return assembler.toModel(venta);
        }

    //Crea una nueva venta
        @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
       public ResponseEntity<EntityModel<Venta>> createVenta(@RequestBody Venta venta) {
           ventaService.createVenta(venta);
           EntityModel<Venta> entityModel = assembler.toModel(venta);
           return ResponseEntity.created(linkTo(methodOn(VentaController_V2.class).getCarreraByCodigo(venta.getId())).toUri()).body(entityModel);
       }
       // public ResponseEntity<EntityModel<Venta>> getVentaById(@RequestBody Venta venta) {
               // Venta newVenta = 
       //        ventaService.createVenta(venta);
       // return ResponseEntity.created(linkTo(methodOn(VentaController_V2.class).getVentaById(newVenta.getId())).toUri()).body(assembler.toModel(newVenta));
}




