package com.esprit.examen.services;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.ArrayList;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.repositories.FournisseurRepository;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class FournisseurServiceImpTest {
private static final Logger l = LogManager.getLogger(FournisseurServiceImpTest.class);

@Mock
private FournisseurRepository fr;
@InjectMocks
private FournisseurServiceImpl fs;

@Autowired 
IFournisseurService fournisseurservice	;

Fournisseur f1 = new Fournisseur("A58455xe8", "Adidas");
Fournisseur f2 = new Fournisseur("sD8556Dxc6", "Nike");
List<Fournisseur> Fournisseurlist = Arrays.asList(f1,f2);
@Test
@Order(1)
public void TestAddFournisseur () {
	Fournisseur four = new Fournisseur();
    List<Fournisseur> LFourn = new ArrayList<>();
    for (Long i=1L;i<=10L;i++) {
    	four.setIdFournisseur(i);
    	four.setCode("58Xs96513");
    	four.setLibelle("Adidas");

    	Fournisseur fa=fr.save(four);
    	LFourn.add(fa);
    }
    assertEquals(10,LFourn.size());
	}

@Test
@Order(5)

public void TestDeleteFournisseur() {
	l.debug("Test méthode DeleteFournisseur");
	try {
		fournisseurservice.deleteFournisseurById((long) 6); 
		
		assertNull(fs.getFournisseurById((long) 6));
		l.info(" Fournisseur deleted succesfully");
	} catch (Exception e) {
		l.error("méthode Delete Fournisseur error :"+ e);
	}
	
}
@Test
@Order(2)
public void TestUpdateNomById() {
	l.debug("Test méthode Modifier Nom d'un Fournisseur by id");
	try {
		String libelle= "Decathlon";

		fournisseurservice.UpdateLibelleFournisseurById(libelle, (long) 3);

		Fournisseur f = fournisseurservice.getFournisseurById((long) 3);

		assertThat(f.getLibelle()).isEqualTo(libelle);
		l.info("nom Fournisseur modified successfully!");
		
	} catch (Exception e) {
		l.error(String.format("ERROR : %s ", e));
	}
}

@Test
@Order(3)
public void TestUpdateFournisseur() {
    when(fr.save(f1)).thenReturn(f1);
    assertNotNull(f1);
    assertEquals(f1, fs.updateFournisseur(f1));
    
    System.out.println("Fournisseur Updated Successfully !");
}
@Test
@Order(4)
public void TestRetrieveAllFournisseurs() {
		l.debug("Test méthode Retrieve Fournisseurs");
        when(fr.findAll()).thenReturn(Fournisseurlist);
		List<Fournisseur> Fournisseurlist = (List<Fournisseur>) fournisseurservice.retrieveAllFournisseurs();
		Assertions.assertNotNull(Fournisseurlist);
        l.info("Retrieve  All Fournisseurs done !!!");
		}
	}



	

	
	
	
	

	
	
	



