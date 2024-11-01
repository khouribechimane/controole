package com.imane.controle.controllers;

import com.imane.controle.entities.Produit;
import com.imane.controle.entities.Vente;
import com.imane.controle.repositories.ProduitRepository;
import com.imane.controle.repositories.VenteRepository;
import com.imane.controle.services.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/market")
public class MarketController {
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private VenteService venteService;

    @GetMapping
    public String showMarket(Model model) {
        model.addAttribute("produits", produitRepository.findAll());
        return "market";
    }

    @GetMapping("/admin")
    public String showAdminHome(Model model) {
        return "home";
    }

    @PostMapping("/basket/add")
    public String addToBasket(@RequestParam("productId") long productId, @RequestParam("client") String client, Model model) {
        Produit produit = produitRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + productId));
        venteService.addVente(produit, client);
        model.addAttribute("produits", produitRepository.findAll());
        return "market";
    }
}
