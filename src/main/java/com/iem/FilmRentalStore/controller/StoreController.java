package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.entity.Store;
import com.iem.FilmRentalStore.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/stores")
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;

    // Read: Show all stores
    @GetMapping("getAll")
    public String listStores(Model model) {
        model.addAttribute("stores", storeRepository.findAll());
        return "stores/list";
    }

    // Create: Show the blank form
    @GetMapping("/new")
    public String createStoreForm(Model model) {
        model.addAttribute("store", new Store());
        return "stores/form";
    }

    // Create/Update: Save the data from the form
    @PostMapping
    public String saveStore(@ModelAttribute("store") Store store) {
        storeRepository.save(store);
        return "redirect:/stores"; // Refresh the list page
    }

    // Update: Show the form with existing data pre-filled
    @GetMapping("/edit/{id}")
    public String editStoreForm(@PathVariable Integer id, Model model) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid store Id:" + id));
        model.addAttribute("store", store);
        return "stores/form";
    }

    // Delete: Remove a store
    @GetMapping("/delete/{id}")
    public String deleteStore(@PathVariable Integer id) {
        storeRepository.deleteById(Integer.valueOf(id));
        return "redirect:/stores";
    }
}