package com.example.Marcket.Producto;

import com.example.Marcket.Producto.Producto;
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
@RequestMapping("/api/productos")
public class ProductoController_v2 {
@Autowired
    private ProductoServicio productoServicio;
    @Autowired
    private MarketModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Producto>> getAllProductos() {
        List<EntityModel<Producto>> productos = productoServicio.getAllProductos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(productos, linkTo(methodOn(ProductoController_v2.class).getAllProductos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Producto>> getProductoById(@PathVariable int id) {
        Producto producto = productoServicio.getProductoById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        return ResponseEntity.ok(assembler.toModel(producto));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Producto>> createProducto(@RequestBody Producto producto) {
        productoServicio.createProducto(producto);
        EntityModel<Producto> entityModel = assembler.toModel(producto);
        return ResponseEntity.created(linkTo(methodOn(ProductoController_v2.class).getProductoById(producto.getId())).toUri()).body(entityModel);
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Producto>> updateProducto(@PathVariable int id, @RequestBody Producto producto) {
        productoServicio.updateProducto(id, producto);
        EntityModel<Producto> entityModel = assembler.toModel(producto);
        return ResponseEntity.ok(entityModel);
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> deleteProducto(@PathVariable int id) {
        productoServicio.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }

    
}
