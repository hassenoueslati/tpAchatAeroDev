package com.esprit.examen.services;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.esprit.examen.entities.Operateur;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.repositories.OperateurRepository;
import com.esprit.examen.repositories.ProduitRepository;

@RunWith(SpringRunner.class)
@TestMethodOrder(OrderAnnotation.class)

public class ProduitServiceImplTest {
private static final Logger l = LogManager.getLogger(ProduitServiceImplTest.class);

@Mock
private ProduitRepository pr;
@InjectMocks
private ProduitServiceImpl ps;

@Autowired 
IProduitService produitservice	;

Produit p1 = new Produit("4ghjg", "lait",12);
Produit p2 = new Produit("546h", "yaourt",25);
List<Produit> produitlist = Arrays.asList(p1,p2);
@Test
@Order(1)
public void TestAddProduit () {
	when(pr.save(p1)).thenReturn(p1);
    assertNotNull(p1);
    assertEquals(p1,ps.addProduit(p1));
	System.out.print("produit "+ p1.getLibelleProduit() + " added succesfully");
	}

@Test
@Order(5)

public void TestDeleteProduit() {
	l.debug("Test méthode DeleteProduit");
	try {
		produitservice.deleteProduitById((long) 6); 
		
		assertNull(ps.getProduitById((long) 6));
		l.info(" Produit deleted succesfully");
	} catch (Exception e) {
		l.error("méthode Delete Produit error :"+ e);
	}
	
}
@Test
@Order(2)
public void TestUpdateNomProduitById() {
	l.debug("Test méthode Modifier Nom d'un Produit by id");
	try {
		String libelle= "Gaucho";

		produitservice.UpdateLibelleById(libelle, (long) 3);

		Produit p = produitservice.getProduitById((long) 3);

		assertThat(p.getLibelleProduit()).isEqualTo(libelle);
		l.info("nom Produit modified successfully!");
		
	} catch (Exception e) {
		l.error(String.format("ERROR : %s ", e));
	}
}

@Test
@Order(3)
public void TestUpdateProduit() {
    when(pr.save(p1)).thenReturn(p1);
    assertNotNull(p1);
    assertEquals(p1, ps.updateProduit(p1));

    System.out.println("Produit Updated Successfully !");
}
@Test
@Order(4)
public void TestRetrieveAllProduits() {
		l.debug("Test méthode Retrieve Produits");
        when(pr.findAll()).thenReturn(produitlist);
		List<Produit> produitlist = (List<Produit>) produitservice.retrieveAllProduits();
		Assertions.assertNotNull(produitlist);
        l.info("Retrieve  All Produits done !!!");
		}
	}



	


	
	
	
	

	
	
	



