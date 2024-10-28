package com.imane.controle.controllers;

import com.imane.controle.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/produit")
public class ProduitController {
    @Autowired
    private ProduitRepository produitRepository;

    @GetMapping
    public String showProduits(Model model){
        model.addAttribute("produits", produitRepository.findAll());
        return "produit";
    }
}
