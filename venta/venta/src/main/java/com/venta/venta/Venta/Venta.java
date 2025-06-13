package com.venta.venta.Venta;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Venta {
  @Id
  @GeneratedValue
   private Integer id;
   @Basic
    private Integer  precio;
    private Integer  cantidad;
    private  Integer numeroDeOrden ;
    private String   fechaDeVenta;
    private String   fechaDeEntrega;


}
