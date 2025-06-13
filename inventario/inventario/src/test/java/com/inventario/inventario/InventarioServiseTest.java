package com.inventario.inventario;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inventario.inventario.Inventario.Inventario;
import com.inventario.inventario.Inventario.InventarioRepository;
import com.inventario.inventario.Inventario.InventarioService;

@ExtendWith(MockitoExtension.class)
public class InventarioServiseTest {

@Mock
    private InventarioRepository inventarioRepo;

    @InjectMocks
    private InventarioService inventarioService;

    private Inventario inventario;

    @BeforeEach
    void setUp() {
        inventario = new Inventario();
        inventario.setId(1);
        inventario.setNombre("Producto Test");
        inventario.setCantidad(10);
    }

    @Test
    void testCreateInventario() {
        inventarioService.createInventario(inventario);
        verify(inventarioRepo, times(1)).save(inventario);
    }

    @Test
    void testGetAllInventarios() {
        when(inventarioRepo.findAll()).thenReturn(Arrays.asList(inventario));
        List<Inventario> result = inventarioService.getAllInventarios();
        assertEquals(1, result.size());
        assertEquals("Producto Test", result.get(0).getNombre());
    }

    @Test
    void testGetInventarioById_found() {
        when(inventarioRepo.findById(1)).thenReturn(Optional.of(inventario));
        Optional<Inventario> result = inventarioService.getInventarioById(1);
        assertTrue(result.isPresent());
        assertEquals("Producto Test", result.get().getNombre());
    }

    @Test
    void testUpdateInventario_found() {
        when(inventarioRepo.existsById(1)).thenReturn(true);
        inventarioService.updateInventario(1, inventario);
        verify(inventarioRepo).save(inventario);
    }

    @Test
    void testUpdateInventario_notFound() {
        when(inventarioRepo.existsById(1)).thenReturn(false);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            inventarioService.updateInventario(1, inventario);
        });
        assertEquals("Inventario no encontrado para actualizar", exception.getMessage());
    }

    @Test
    void testDeleteInventario_found() {
        when(inventarioRepo.existsById(1)).thenReturn(true);
        inventarioService.deleteInventario(1);
        verify(inventarioRepo).deleteById(1);
    }

    @Test
    void testDeleteInventario_notFound() {
        when(inventarioRepo.existsById(1)).thenReturn(false);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            inventarioService.deleteInventario(1);
        });
        assertEquals("Inventario no encontrado para eliminar", exception.getMessage());
    }







}
