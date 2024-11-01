package com.imane.controle.controllers;

import com.imane.controle.entities.Produit;
import com.imane.controle.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produit")
public class ProduitController {
    @Autowired
    private ProduitRepository produitRepository;

    @GetMapping
    public String showProduits(Model model) {
        model.addAttribute("produits", produitRepository.findAll());
        return "produit";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduit(@PathVariable("id") long id, Model model) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid produit Id:" + id));
        produitRepository.delete(produit);
        model.addAttribute("produits", produitRepository.findAll());
        return "produit";
    }

    @GetMapping("/add")
    public String produitForm(Produit produit,Model model){
        model.addAttribute("produits", produitRepository.findAll());
        return "produitForm";
    }

    @PostMapping("/create")
    public String addProduit(Produit produit, Model model) {
        produitRepository.save(produit);
        model.addAttribute("produits", produitRepository.findAll());
        return "produit";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() ->new IllegalArgumentException("Invalid produit Id:" + id));
        model.addAttribute("produit", produit);
        return "produitEdit";
    }

    @PostMapping("/update/{id}")
    public String updateProduit(@PathVariable("id") long id, Produit produit, Model model) {
        Produit produitToUpdate = produitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid produit Id:" + id));
        produitRepository.delete(produitToUpdate);
        produitRepository.save(produit);
        model.addAttribute("produits", produitRepository.findAll());
        return "produit";
    }

}
