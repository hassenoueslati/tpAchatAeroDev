package com.esprit.examen.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esprit.examen.entities.Operateur;
import com.esprit.examen.repositories.OperateurRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j

@Service
public class OperateurServiceImpl implements IOperateurService {

	@Autowired
	OperateurRepository  operateurRepository;
	@Override
	public List<Operateur> retrieveAllOperateurs() {
		return (List<Operateur>) operateurRepository.findAll();
	}

	@Override
	public Operateur addOperateur(Operateur o) {
		operateurRepository.save(o);
		return o;
	}

	@Override
	public void deleteOperateur(Long id) {
		operateurRepository.deleteById(id);
		
	}

	@Override
	public Operateur updateOperateur(Operateur o) {
		operateurRepository.save(o);
		return o;
	}

	@Override
	public Operateur retrieveOperateur(Long id) {
		return operateurRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional

	public void deleteOperateurById(Long id) {
			log.debug("methode deleteOperateurById ");
			try {
				Optional<Operateur> opp = operateurRepository.findById(id);
				if(opp.isPresent()){
				Operateur op = opp.get();
				operateurRepository.delete(op);
				log.debug("deleteOperateurById fini avec succes ");
				}
				else {
					log.error("erreur methode deleteOperateurById : " );
				}
			} catch (Exception e) {
				log.error("erreur methode deleteOperateurById : " +e);
			}

		}

	@Override
	public Operateur getOperateurById(Long id) {
			log.debug("methode getOperateurById ");
			try {
				Operateur o= operateurRepository.findById(id).orElse(null);
				log.debug("getOperateurById fini avec succes ");
				return o;
			} catch (Exception e) {
				log.error("erreur methode getOperateurById : " +e);
				return null;
			}
		}

	@Override
	public void UpdateNomById(String nom, Long id) {
		Operateur o = operateurRepository.findById(id).orElse(null);
		if(o!= null) {
			o.setNom(nom);
			operateurRepository.save(o);
		}
		
	}

	
}