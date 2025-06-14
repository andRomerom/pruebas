package com.venta.venta.Venta;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VentaService {

    private final VentaRepository ventaRepo;

    // CREATE
    public void createVenta(Venta venta) {
        ventaRepo.save(venta);
    }

    // READ: obtener todos
    public List<Venta> getAllVentas() {
        return ventaRepo.findAll();
    }

    // READ: obtener por ID
    public Optional<Venta> getVentaById(Integer id) {
        return ventaRepo.findById(id);
    }

    // UPDATE
    public void updateVenta(Integer id, Venta venta) {
        if (ventaRepo.existsById(id)) {
            venta.setId(id);
            ventaRepo.save(venta);
        } else {
            throw new RuntimeException("Venta no encontrada para actualizar");
        }
    }

    // DELETE
    public void deleteVenta(Integer id) {
        if (ventaRepo.existsById(id)) {
            ventaRepo.deleteById(id);
        } else {
            throw new RuntimeException("Venta no encontrada para eliminar");
        }
    }
}
