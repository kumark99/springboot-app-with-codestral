package com.tn.inventory.test;

import com.tn.inventory.controller.InventoryController;
import com.tn.inventory.model.Inventory;
import com.tn.inventory.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(InventoryController.class)
public class InventoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryService inventoryService;

    @Test
    public void testGetAllInventory() throws Exception {
    	Inventory inventory1 = new Inventory();
        inventory1.setId(1L);
        inventory1.setInv_name("Item 1");
        inventory1.setInv_desc("Description 1");
        inventory1.setQuantity(10);

        Inventory inventory2 = new Inventory();
        inventory2.setId(2L);
        inventory2.setInv_name("Item 2");
        inventory2.setInv_desc("Description 2");
        inventory2.setQuantity(20);

        when(inventoryService.getAllInventory()).thenReturn(Arrays.asList(inventory1, inventory2));

        mockMvc.perform(get("/api/inventory"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].inv_name").value("Item 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].inv_name").value("Item 2"));

        verify(inventoryService, times(1)).getAllInventory();
    }

    // Add more tests for other endpoints
    

    @Test
    public void testGetInventoryById() throws Exception {
        Inventory inventory = new Inventory();
        inventory.setId(1L);
        inventory.setInv_name("Item 1");
        inventory.setInv_desc("Description 1");
        inventory.setQuantity(10);

        when(inventoryService.getInventoryById(1L)).thenReturn(Optional.of(inventory));

        mockMvc.perform(get("/api/inventory/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.inv_name").value("Item 1"));

        verify(inventoryService, times(1)).getInventoryById(1L);
    }

    @Test
    public void testSaveInventory() throws Exception {
        Inventory inventory = new Inventory();
        inventory.setInv_name("Item 1");
        inventory.setInv_desc("Description 1");
        inventory.setQuantity(10);

        when(inventoryService.saveInventory(any(Inventory.class))).thenReturn(inventory);

        mockMvc.perform(post("/api/inventory")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(inventory)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.inv_name").value("Item 1"));

        verify(inventoryService, times(1)).saveInventory(any(Inventory.class));
    }

    @Test
    public void testDeleteInventory() throws Exception {
        Long id = 1L;

        doNothing().when(inventoryService).deleteInventory(id);

        mockMvc.perform(delete("/api/inventory/1"))
                .andExpect(status().isOk());

        verify(inventoryService, times(1)).deleteInventory(id);
    }

}
