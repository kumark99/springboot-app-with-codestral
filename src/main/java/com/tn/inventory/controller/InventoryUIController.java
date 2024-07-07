package com.tn.inventory.controller;

import com.tn.inventory.model.Inventory;
import com.tn.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ui/inventory")
public class InventoryUIController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public String getAllInventory(Model model) {
        List<Inventory> inventoryList = inventoryService.getAllInventory();
        model.addAttribute("inventoryList", inventoryList);
        return "inventory-list";
    }

    @GetMapping("/add")
    public String showAddInventoryForm(Model model) {
        model.addAttribute("inventory", new Inventory());
        return "inventory-add";
    }

    @PostMapping("/add")
    public String addInventory(@ModelAttribute("inventory") Inventory inventory) {
        inventoryService.saveInventory(inventory);
        return "redirect:/ui/inventory";
    }

    @GetMapping("/edit/{id}")
    public String showEditInventoryForm(@PathVariable Long id, Model model) {
        Inventory inventory = inventoryService.getInventoryById(id).orElseThrow(() -> new RuntimeException("Inventory not found"));
        model.addAttribute("inventory", inventory);
        return "inventory-edit";
    }

    @PostMapping("/edit/{id}")
    public String editInventory(@PathVariable Long id, @ModelAttribute("inventory") Inventory inventory) {
        inventory.setId(id);
        inventoryService.saveInventory(inventory);
        return "redirect:/ui/inventory";
    }

    @GetMapping("/delete/{id}")
    public String deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return "redirect:/ui/inventory";
    }
}
