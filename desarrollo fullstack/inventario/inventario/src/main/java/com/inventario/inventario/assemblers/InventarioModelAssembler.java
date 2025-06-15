package com.inventario.inventario.assemblers;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import com.inventario.inventario.Inventario.InventarioController_V2;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import java.util.List;
import com.inventario.inventario.Inventario.Inventario;

@Component
public class InventarioModelAssembler implements RepresentationModelAssembler<Inventario, EntityModel<Inventario>> {
@Override
public EntityModel<Inventario> toModel(Inventario venta) {
return EntityModel.of(venta,linkTo(methodOn(InventarioController_V2.class).getAllInventarios()).withRel("inventarios"));
}
}
