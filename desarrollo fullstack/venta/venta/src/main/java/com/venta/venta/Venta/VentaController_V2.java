package com.venta.venta.Venta;


import com.venta.venta.assemblers.VentaModelAssembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;

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

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Venta>> getAllVentas() {
List<EntityModel<Venta>> ventas = ventaService.getAllVentas().stream().map(assembler::toModel).collect(Collectors.toList());
return CollectionModel.of(ventas,linkTo(methodOn(VentaController_V2.class).getAllVentas()).withSelfRel());
}

}
