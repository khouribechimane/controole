package com.imane.controle.controllers;

import com.imane.controle.entities.Produit;
import com.imane.controle.entities.Vente;
import com.imane.controle.repositories.ProduitRepository;
import com.imane.controle.repositories.VenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vente")
public class VenteController {
    @Autowired
    private VenteRepository venteRepository;
    @Autowired
    private ProduitRepository produitRepository;

    @GetMapping
    public String showVentes(Model model) {
        model.addAttribute("ventes", venteRepository.findAll());
        return "vente";
    }

    @GetMapping("/delete/{id}")
    public String deleteVente(@PathVariable("id") long id, Model model) {
        Vente vente = venteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid vente Id:" + id));
        venteRepository.delete(vente);
        model.addAttribute("ventes", venteRepository.findAll());
        return "vente";
    }

    @GetMapping("/add")
    public String venteForm(Vente vente,Model model){
        model.addAttribute("ventes", venteRepository.findAll());
        model.addAttribute("produits", produitRepository.findAll());
        return "venteForm";
    }

    @PostMapping("/create")
    public String addVente(Vente vente, Model model) {
        venteRepository.save(vente);
        model.addAttribute("ventes", venteRepository.findAll());
        return "vente";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Vente vente = venteRepository.findById(id)
                .orElseThrow(() ->new IllegalArgumentException("Invalid vente Id:" + id));
        model.addAttribute("vente", vente);
        return "venteEdit";
    }

    @PostMapping("/update/{id}")
    public String updateVente(@PathVariable("id") long id, Vente vente, Model model) {
        Vente venteToUpdate = venteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid vente Id:" + id));

        venteToUpdate.setDate(vente.getDate());
        venteToUpdate.setClient(vente.getClient());
        venteToUpdate.setMontantTotal(vente.getMontantTotal());

        venteToUpdate.getProduits().clear();
        for (Produit produit : vente.getProduits()) {
            produit.setVente(venteToUpdate);  // Set vente reference in each produit
            venteToUpdate.getProduits().add(produit);
        }
        venteRepository.save(venteToUpdate);
        model.addAttribute("ventes", venteRepository.findAll());
        return "vente";
    }

}
