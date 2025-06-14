package com.inventario.inventario;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventario.inventario.Inventario.Inventario;
import com.inventario.inventario.Inventario.InventarioController;
import com.inventario.inventario.Inventario.InventarioService;

import lombok.Data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;
@Data

@WebMvcTest(InventarioController.class)
@ActiveProfiles("test")

public class InventarioControllerTest {

@Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventarioService inventarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllInventarios() throws Exception {
        Inventario inv = new Inventario();
        inv.setId(1);
        inv.setNombre("Producto Test");

        when(inventarioService.getAllInventarios()).thenReturn(Arrays.asList(inv));

        mockMvc.perform(get("/inventario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Producto Test"));
    }

    @Test
    void testGetInventarioById_found() throws Exception {
        Inventario inv = new Inventario();
        inv.setId(1);
        inv.setNombre("Producto Test");

        when(inventarioService.getInventarioById(1)).thenReturn(Optional.of(inv));

        mockMvc.perform(get("/inventario/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Producto Test"));
    }

    @Test
    void testGetInventarioById_notFound() throws Exception {
        when(inventarioService.getInventarioById(99)).thenReturn(Optional.empty());

        mockMvc.perform(get("/inventario/99"))
                .andExpect(status().isInternalServerError());
    }



}
