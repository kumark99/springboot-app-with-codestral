package com.tn.inventory.test;

import com.tn.inventory.model.Inventory;
import com.tn.inventory.repository.InventoryRepository;
import com.tn.inventory.service.InventoryService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceTest {
    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryService inventoryService;

    @Test
    public void testGetAllInventory() {
        Inventory inventory1 = new Inventory();
        inventory1.setInv_name("Item 1");
        inventory1.setInv_desc("Description 1");
        inventory1.setQuantity(10);

        Inventory inventory2 = new Inventory();
        inventory2.setInv_name("Item 2");
        inventory2.setInv_desc("Description 2");
        inventory2.setQuantity(20);

        when(inventoryRepository.findAll()).thenReturn(Arrays.asList(inventory1, inventory2));

        List<Inventory> inventoryList = inventoryService.getAllInventory();

        assertEquals(2, inventoryList.size());
        verify(inventoryRepository, times(1)).findAll();
    }

    // Add more tests for other methods
    
    @Test
    public void testGetInventoryById() {
        Inventory inventory = new Inventory();
        inventory.setId(1L);
        inventory.setInv_name("Item 1");
        inventory.setInv_desc("Description 1");
        inventory.setQuantity(10);

        when(inventoryRepository.findById(1L)).thenReturn(Optional.of(inventory));

        Optional<Inventory> foundInventory = inventoryService.getInventoryById(1L);

        assertTrue(foundInventory.isPresent());
        assertEquals("Item 1", foundInventory.get().getInv_name());
        verify(inventoryRepository, times(1)).findById(1L);
    }

    @Test
    public void testSaveInventory() {
        Inventory inventory = new Inventory();
        inventory.setInv_name("Item 1");
        inventory.setInv_desc("Description 1");
        inventory.setQuantity(10);

        when(inventoryRepository.save(inventory)).thenReturn(inventory);

        Inventory savedInventory = inventoryService.saveInventory(inventory);

        assertEquals("Item 1", savedInventory.getInv_name());
        verify(inventoryRepository, times(1)).save(inventory);
    }

    @Test
    public void testDeleteInventory() {
        Long id = 1L;

        doNothing().when(inventoryRepository).deleteById(id);

        inventoryService.deleteInventory(id);

        verify(inventoryRepository, times(1)).deleteById(id);
    }
}
