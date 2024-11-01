package com.imane.controle.services;

import com.imane.controle.entities.Produit;
import com.imane.controle.entities.Vente;
import com.imane.controle.repositories.VenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VenteService {
    @Autowired
    private VenteRepository venteRepository;

    public void addVente(Produit produit, String client) {

        Vente vente = new Vente();
        vente.setDate(new Date());
        vente.setClient(client);
        vente.setMontantTotal(produit.getPrix());

        List<Produit> products = new ArrayList<>();
        produit.setVente(vente);
        products.add(produit);
        vente.setProduits(products);

        venteRepository.save(vente);
    }
}
