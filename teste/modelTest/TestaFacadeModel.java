package modelTest;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import model.FacadeModel;


public class TestaFacadeModel {
	FacadeModel f = new FacadeModel(4);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testaNumJogador() {
		
		// recebe numero de jogadores
		assertNotNull(f);
		
	}
	
	@Test
	public void testaSorteReves() {
		assertNotNull(f.getCartaSorteReves());
	}
	
	@Test
	public void testaEfeitoCarta() {
		assertNotSame(f.getEfeitoCarta(1),f.getEfeitoCarta(2));
	}
	
	@Test
	public void testaTerrenoPossuiCasa() {
		// Se a posicao for diferente de 0, o valor esperado sera 0
		assertEquals(0,f.terrenoPossuiCasa(1));
	}
	
	@Test
	public void testaTerrenoPossuiHotel() {
		// Se a posicao for diferente de 0, o valor esperado sera False
		assertFalse(f.terrenoPossuiHotel(1));
	}
	
	@Test
	public void testaPrecoTerreno() {
		// Se a posicao for diferente de 0, o valor esperado sera 0
		assertEquals(0,f.getPrecoTerreno(2));
		
	}
	
	@Test
	public void testaTerrenoConstrucaoPreco() {
		// Se a posicao for diferente de 0, o valor esperado sera 0
		assertNotNull(f.getTerrenoConstrucaoPreco(1));
	}
	
}
