package com.imane.controle.services;

import com.imane.controle.entities.Produit;
import com.imane.controle.entities.Vente;
import com.imane.controle.repositories.VenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VenteService {
    @Autowired
    private VenteRepository venteRepository;

    private List<Produit> products = new ArrayList<>();

    public void addVente(Produit produit) {
        products.add(produit);
        Vente vente = new Vente();
        vente.setProduits(products);
        venteRepository.save(vente);
    }
}
