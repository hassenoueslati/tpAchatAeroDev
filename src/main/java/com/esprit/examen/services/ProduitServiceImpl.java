package com.esprit.examen.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.CategorieProduitRepository;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.StockRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProduitServiceImpl implements IProduitService {

	@Autowired
	ProduitRepository produitRepository;
	@Autowired
	StockRepository stockRepository;
	@Autowired
	CategorieProduitRepository categorieProduitRepository;

	@Override
	public List<Produit> retrieveAllProduits() {
		List<Produit> produits = (List<Produit>) produitRepository.findAll();
		for (Produit produit : produits) {
			log.info(" Produit : " + produit);
		}
		return produits;
	}

	@Transactional
	public Produit addProduit(Produit p) {
		produitRepository.save(p);
		return p;
	}

	

	@Override
	public void deleteProduit(Long produitId) {
		produitRepository.deleteById(produitId);
	}

	@Override
	public Produit updateProduit(Produit p) {
		return produitRepository.save(p);
	}

	@Override
	public Produit retrieveProduit(Long produitId) {
		Produit produit = produitRepository.findById(produitId).orElse(null);
		log.info("produit :" + produit);
		return produit;
	}

	@Override
	public void assignProduitToStock(Long idProduit, Long idStock) {
		Produit produit = produitRepository.findById(idProduit).orElse(null);
		Stock stock = stockRepository.findById(idStock).orElse(null);
		produit.setStock(stock);
		produitRepository.save(produit);

	}
	
	@Override
	@Transactional

	public void deleteProduitById(Long id) {
			log.debug("methode deleteProduitById ");
			try {
				Optional<Produit> prr = produitRepository.findById(id);
				if(prr.isPresent()){
					Produit pr = prr.get();
					produitRepository.delete(pr);
				log.debug("deleteProduitById fini avec succes ");
				}
				else {
					log.error("erreur methode deleteProduitById : " );
				}
			} catch (Exception e) {
				log.error("erreur methode deleteProduitById : " +e);
			}

		}

	@Override
	public Produit getProduitById(Long id) {
			log.debug("methode getProduitById ");
			try {
				Produit p= produitRepository.findById(id).orElse(null);
				log.debug("getProduitById fini avec succes ");
				return p;
			} catch (Exception e) {
				log.error("erreur methode getProduitById : " +e);
				return null;

			}
	}
	
	@Override
	public void UpdateLibelleById(String libelle, Long id) {
		Produit p = produitRepository.findById(id).orElse(null);
		if(p!= null) {
			p.setLibelleProduit(libelle);
			produitRepository.save(p);
		}
		
	}
}