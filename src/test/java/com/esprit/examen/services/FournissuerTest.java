package com.esprit.examen.services;
import org.junit.jupiter.api.Test;

import java.util.List;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.entities.Fournisseur;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FournissuerTest {
	
	@Autowired
	IFournisseurService fs;
	 
	 @Test
	    void retrieveAllSecteurActivites() {
	    	List<Fournisseur> SecteurActivites = fs.retrieveAllFournisseurs();
	        Assertions.assertEquals(0, SecteurActivites.size());
	    }
	 
	 @Test
	    public void testAddSecteurActivite(){
	    List<Fournisseur> SecteurActivites = fs.retrieveAllFournisseurs();
	    int expected = SecteurActivites.size();
	    Fournisseur s = new Fournisseur("123136","banque");

	    Fournisseur savedSecteurActivite= fs.addFournisseur(s);
	    assertEquals(expected+1, fs.retrieveAllFournisseurs().size());
	    assertNotNull(savedSecteurActivite.getLibelle());
	    fs.deleteFournisseur(savedSecteurActivite.getIdFournisseur());

	    }
	 
	 @Test
	    public void testUpdateProduit() {
		 Fournisseur f = new Fournisseur("123136","banque");
		Fournisseur savedSecteurActivite= fs.addFournisseur(f);
		savedSecteurActivite.setLibelle("ADidas");
	    fs.updateFournisseur(savedSecteurActivite);
	    assertEquals(f.getLibelle(),savedSecteurActivite.getLibelle());
	    fs.deleteFournisseur(savedSecteurActivite.getIdFournisseur());
	    }

	    @Test
	    public void testDeleteProduit() {
	    Fournisseur f = new Fournisseur("123136","banque");
		Fournisseur savedSecteurActivite= fs.addFournisseur(f);
		fs.deleteFournisseur(savedSecteurActivite.getIdFournisseur());
		assertNotNull(savedSecteurActivite.getIdFournisseur());

	    }

	
}