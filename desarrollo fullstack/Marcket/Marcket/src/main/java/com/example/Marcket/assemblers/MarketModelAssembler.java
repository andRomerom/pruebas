package com.example.Marcket.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.Marcket.Admin.AdminController_V2;
import com.example.Marcket.Cliente.Cliente;
import com.example.Marcket.Cliente.ClienteController_v2;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.example.Marcket.Admin.Admin;
import com.example.Marcket.Cliente.Cliente;  
@Component

public class MarketModelAssembler implements RepresentationModelAssembler<Admin, EntityModel<Admin>> {
@Override
public EntityModel<Admin> toModel(Admin admin) {
return EntityModel.of(admin,linkTo(methodOn(AdminController_V2.class).getAllAdmins()).withRel("carreras"));
}

public EntityModel<Cliente> toModel(Cliente cliente) {  
    return EntityModel.of(cliente, linkTo(methodOn(ClienteController_v2.class).getAllClientes()).withSelfRel(),
        linkTo(methodOn(ClienteController_v2.class).getClienteById(cliente.getId())).withRel("cliente"),
linkTo(methodOn(ClienteController_v2.class).getClienteById(cliente.getId())).withRel("carreras"));
        
}
}

    

