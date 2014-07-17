package br.com.lordmonssa.exemplo.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.lordmonssa.exemplo.arquitetura.util.Util;

public class UtilTest {

	public static final String STRING_VAZIA = "";

	private static final String WILDCARD = "%";

	public static final String LETRAS_COM_ACENTUACAO = "ÁÀÃÂÄÉÈÊËÍÌÏÎÓÒÕÔÖÚÙÛÜÇÑÝŸáàãâäéèêëíìïîóòõôöúùûüçñýÿ";
	public static final String LETRAS_SEM_ACENTUACAO = "AAAAAEEEEIIIIOOOOOUUUUCNYYaaaaaeeeeiiiiooooouuuucnyy";

	@Test
	public final void testIsNull() {

		Object objetoTeste = null;

		assertTrue(Util.isNull(objetoTeste));

		objetoTeste = new Object();

		assertFalse(Util.isNull(objetoTeste));

		String testeString = null;

		assertTrue(Util.isNull(testeString));

		testeString = STRING_VAZIA;

		assertFalse(Util.isNull(testeString));

		List<String> testeLista = null;

		assertTrue(Util.isNull(testeLista));

		testeLista = new ArrayList<String>();

		assertFalse(Util.isNull(testeLista));

	}

	@Test
	public final void testIsEmptyString() {

		String testeString = STRING_VAZIA;

		assertTrue(Util.isEmptyString(testeString));

		testeString = "Teste Vazio";

		assertFalse(Util.isEmptyString(testeString));

	}

	@Test
	public final void testIsNullOuVazioObject() {

		String testeString = null;

		assertTrue(Util.isNullOuVazio(testeString));

		testeString = STRING_VAZIA;

		assertTrue(Util.isNullOuVazio(testeString));

		testeString = "Nao mais vazia";

		assertFalse(Util.isNullOuVazio(testeString));

		List<String> testeLista = null;

		assertTrue(Util.isNullOuVazio(testeLista));

		testeLista = new ArrayList<String>();

		assertTrue(Util.isNullOuVazio(testeLista));

		testeLista.add(testeString);

		assertFalse(Util.isNullOuVazio(testeLista));

	}

	@Test
	public final void testIsNullOuVazioInteger() {

		Integer testeInteger = null;

		assertTrue(Util.isNullOuVazio(testeInteger));

		testeInteger = 0;

		assertFalse(Util.isNullOuVazio(testeInteger));

		testeInteger = 10;

		assertFalse(Util.isNullOuVazio(testeInteger));

	}

	@Test
	public final void testRemoveAcentos() {

		String testeString = "a-áàâãä e-éèêë i-íìîï o-óôõö u-ùúûü c-ç";
		String esperadaString = "a-aaaaa e-eeee i-iiii o-oooo u-uuuu c-c";

		assertEquals(Util.removeAcentos(testeString), esperadaString);
	}

	@Test
	public final void testPesquisaPorNome() {

		String testeString = "teste acentuação";
		String esperadaString = " AND TRANSLATE(UPPER(campoBanco), '"+LETRAS_COM_ACENTUACAO+"', '"+LETRAS_SEM_ACENTUACAO+"') like '" + WILDCARD + "TESTE ACENTUACAO" + WILDCARD + "'";

		assertEquals(Util.pesquisaPorNome(testeString, "campoBanco"), esperadaString);

	}

	@Test
	public final void testFormatData() {

		Date data = new Date(1311781583373L);

		String pFormato = "dd/MM/yyyy";

		assertEquals(Util.formatData(data, pFormato), "27/07/2011");

	}

	@Test
	public final void testFormatDataHora() {
		
		Date data = new Date(1311781583373L);

		assertEquals(Util.formatDataHora(data), "27/07/2011/12:46");
	}

	@Test
	public final void testLpad() {
		
		String testeString = "teste";
		String esperadaString = "00000teste";

		assertEquals(Util.lpad(testeString, '0', 10), esperadaString);
	}

	@Test
	public final void testFormataCPF() {
		
		Long testeString = 82525263226L;
		String esperadaString = "825.252.632-26";

		assertEquals(Util.formataCPF(testeString), esperadaString);
		
		testeString = 525263226L;
		esperadaString = "005.252.632-26";

		assertEquals(Util.formataCPF(testeString), esperadaString);
		
	}

}
