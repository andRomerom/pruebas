package com.example.Marcket.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.Marcket.Admin.AdminController_V2;
import com.example.Marcket.Cliente.Cliente;
import com.example.Marcket.Cliente.ClienteController_v2;
import com.example.Marcket.Producto.Producto;
import com.example.Marcket.Producto.ProductoController_v2;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.example.Marcket.Admin.Admin;

@Component

public class MarketModelAssembler implements RepresentationModelAssembler<Admin, EntityModel<Admin>> {
@Override
public EntityModel<Admin> toModel(Admin admin) {
return EntityModel.of(admin,linkTo(methodOn(AdminController_V2.class).getAllAdmins()).withRel("admins"),
    linkTo(methodOn(AdminController_V2.class).getAdminById(admin.getId())).withSelfRel(),
    linkTo(methodOn(AdminController_V2.class).getAdminById(admin.getId())).withRel("admin"));
}

public EntityModel<Cliente> toModel(Cliente cliente) {  
    return EntityModel.of(cliente, linkTo(methodOn(ClienteController_v2.class).getAllClientes()).withSelfRel(),
        linkTo(methodOn(ClienteController_v2.class).getClienteById(cliente.getId())).withRel("cliente"),
    linkTo(methodOn(ClienteController_v2.class).getClienteById(cliente.getId())).withRel("Clientes"));
}

public EntityModel<Producto> toModel(Producto producto) {  
    return EntityModel.of(producto, linkTo(methodOn(ProductoController_v2.class).getAllProductos()).withSelfRel(),
        linkTo(methodOn(ProductoController_v2.class).getProductoById(producto.getId())).withRel("producto"),
    linkTo(methodOn(ProductoController_v2.class).getProductoById(producto.getId())).withRel("productos"));
}

}

    

