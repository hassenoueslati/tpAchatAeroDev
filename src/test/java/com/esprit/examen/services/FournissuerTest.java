package com.esprit.examen.services;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions;

import com.esprit.examen.entities.Fournisseur;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


public class FournissuerTest {

	 @Autowired
	    
	    IFournisseurService fs;
	 
	 
	   @Test
	    void retrieveAllProduits() {
	    	List<Fournisseur> listFournisseurss = fs.retrieveAllFournisseurs();
	        Assertions.assertEquals(0, listFournisseurss.size());
	    }
	   
	   
	 
	   @Test
	    public void testAddFournisseur(){
	    List<Fournisseur> Fournisseurs = fs.retrieveAllFournisseurs();
	    int expected = Fournisseurs.size();
	    Fournisseur f = new Fournisseur("Sx84qDa","Nike");
	   

	    Fournisseur savedFournisseur= fs.addFournisseur(f);
	    assertEquals(expected+1, fs.retrieveAllFournisseurs().size());
	    assertNotNull(savedFournisseur.getLibelle());
	    fs.deleteFournisseur(savedFournisseur.getIdFournisseur());

	    } 
	   @Test
	    public void testUpdateFournisseur() {
		    Fournisseur f = new Fournisseur("Sx84qDa","Nike");

		    Fournisseur savedFournisseur= fs.addFournisseur(f);
	    fs.updateFournisseur(savedFournisseur);
	    assertEquals(f.getLibelle(),savedFournisseur.getLibelle());
	    fs.deleteFournisseur(savedFournisseur.getIdFournisseur());
	    }
 
	   @Test
	    public void testDeleteProduit() {
		   Fournisseur p = new Fournisseur();
	    
		   Fournisseur savedService= fs.addFournisseur(p);
	    fs.deleteFournisseur(savedService.getIdFournisseur());
	    assertNotNull(savedService.getIdFournisseur());

	    }
	   
}
