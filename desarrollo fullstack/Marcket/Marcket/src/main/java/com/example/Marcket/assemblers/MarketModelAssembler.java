package com.example.Marcket.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.Marcket.Admin.AdminController_V2;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.example.Marcket.Admin.Admin;
@Component

public class MarketModelAssembler implements RepresentationModelAssembler<Admin, EntityModel<Admin>> {
@Override
public EntityModel<Admin> toModel(Admin admin) {
return EntityModel.of(admin,linkTo(methodOn(AdminController_V2.class).getAllAdmins()).withRel("carreras"));
}
}

    

