package com.esprit.examen.services;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import com.esprit.examen.entities.Operateur;
import com.esprit.examen.repositories.OperateurRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OperateurServiceImplTest {

@Mock
OperateurRepository or;
@InjectMocks
OperateurServiceImpl os;

Operateur o = new Operateur("oueslati","hassen","****");

Long getId()
{
    for (Operateur op: or.findAll()) {
        return op.getIdOperateur();
    }
    return 0L;
}
@Test
@Order(0)
void TestaddOperateur() {
	Operateur op = new Operateur();
    List<Operateur> Operateurs = new ArrayList<>();
    for (Long i=1L;i<=5L;i++) {
        op.setIdOperateur(i);
        op.setNom("wael");
        op.setPrenom("amri");
        op.setPassword("aaaaa");

        Operateur ca=or.save(op);
        Operateurs.add(ca);
    }
    assertEquals(10,Operateurs.size());
}
@Test
@Order(3)
void TestdeleteAllOperateur() {
    or.deleteAll();
    assertEquals(0,or.findAll().spliterator().estimateSize());
}
@Test
@Order(2)
void TestretrieveOperateur() {
    Mockito.when(or.findById(Mockito.anyLong())).thenReturn(Optional.of(o));

    Mockito.when(or.findById(Mockito.anyLong())).thenReturn(Optional.of(o))
    ;
    Operateur op = os.retrieveOperateur(2L);
    Assertions.assertNotNull(op);


}
@Test
@Order(4)
void TestgetAllOperateur(){
    Iterable<Operateur> Operateurs = or.findAll();
    Assertions.assertNotNull(Operateurs);
}

}	


	
	
	
	

	
	
	



