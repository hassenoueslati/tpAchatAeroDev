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
import com.esprit.examen.entities.Facture;
import com.esprit.examen.repositories.FactureRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)

public class FactureServiceImplTest {
	
	private static final Logger l = LogManager.getLogger(FactureServiceImplTest.class);

	@Mock
	private FactureRepository or;
	@InjectMocks
	private FactureServiceImpl os;

	@Autowired 
	IFactureService factureservice	;

	Facture o1 = new Facture("14.5", "20","20-01-2022","25-01-2022","1");
	List<Facture> facturelist = Arrays.asList(o1);
	@Test
	@Order(1)
	public void TestAddFacture () {
		when(or.save(o1)).thenReturn(o1);
	    assertNotNull(o1);
	    assertEquals(o1,os.addFacture(o1));
		System.out.print("facture "+ o1.montantFacture()+ " added succesfully");
		}


	@Test
	@Order(2)
	public void TestUpdateNomById() {
		l.debug("Test méthode Modifier Nom d'un facture by id");
		try {
			String nom= "devops";

			factureservice.UpdateNomById(nom, (long) 3);

			Facture o = factureservice.getFactureById((long) 3);

			assertThat(o.getMontantFacture()).isEqualTo(montantFacture);
			l.info(" montant Facture modified successfully!");

		} catch (Exception e) {
			l.error(String.format("ERROR : %s ", e));
		}
	}

	@Test
	@Order(3)
	public void TestUpdateFacture() {
	    when(or.save(o1)).thenReturn(o1);
	    assertNotNull(o1);
	    assertEquals(o1, os.updateFacture(o1));

	    System.out.println("Facture Updated Successfully !");
	}
	@Test
	@Order(4)
	public void TestRetrieveAllFactures() {
			l.debug("Test méthode Retrieve Factures");
	        when(or.findAll()).thenReturn(facturelist);
			List<Facture> facturelist = (List<Facture>) factureservice.retrieveAllFactures();
			Assertions.assertNotNull(factureslist);
	        l.info("Retrieve  All Factures done !!!");
			}


	@Test
	@Order(5)
	public void TestDeleteFacture() {
		l.debug("Test méthode DeleteFacture");
		try {
			factureservice.deleteFactureById((long) 6); 

			assertNull(os.getFactureById((long) 6));
			l.info(" facture deleted succesfully");
		} catch (Exception e) {
			l.error("méthode Delete Facture error :"+ e);
		}

		}

	}	

}
