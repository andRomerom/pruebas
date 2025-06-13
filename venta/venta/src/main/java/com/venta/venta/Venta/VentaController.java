package com.venta.venta.Venta;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/venta")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;

    // CREATE
    @PostMapping
    public void createVenta(@RequestBody Venta venta) {
        ventaService.createVenta(venta);
    }

    // READ: obtener todas las ventas
    @GetMapping
    public List<Venta> getAllVentas() {
        return ventaService.getAllVentas();
    }

    // READ: obtener una venta por ID
    @GetMapping("/{id}")
    public Venta getVentaById(@PathVariable Integer id) {
        return ventaService.getVentaById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }

    // UPDATE
    @PutMapping("/{id}")
    public void updateVenta(@PathVariable Integer id, @RequestBody Venta venta) {
        ventaService.updateVenta(id, venta);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteVenta(@PathVariable Integer id) {
        ventaService.deleteVenta(id);
    }
}
