package com.esprit.examen.services;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.examen.entities.Facture;

import com.esprit.examen.repositories.DetailFactureRepository;
import com.esprit.examen.repositories.FactureRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class FactureServiceImpl implements IFactureService {

	@Autowired
	FactureRepository factureRepository;

	@Autowired
	DetailFactureRepository detailFactureRepository;

	
	@Override
	public List<Facture> retrieveAllFactures() {
		List<Facture> factures = (List<Facture>) factureRepository.findAll();
		return factures;
	}

	
	public Facture addFacture(Facture f) {
		return factureRepository.save(f);
	}

	@Override
	public void cancelFacture(Long factureId) {
		// Méthode 01
		Facture facture = factureRepository.findById(factureId).orElse(new Facture(factureId, factureId, null, null));
		facture.setArchivee(true);
		factureRepository.save(facture);
		//Méthode 02 (Avec JPQL)
		factureRepository.updateFacture(factureId);
	}

	@Override
	public Facture retrieveFacture(Long factureId) {
		Facture facture = factureRepository.findById(factureId).orElse(null);
		return facture;
	}


}