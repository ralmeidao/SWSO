package br.com.lordmonssa.exemplo.converter;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.lordmonssa.exemplo.converter.CpfConverter;

public class CpfConverterTest {

	@Test
	public final void testGetAsObject() {

		String testeString = "048.748.055-40";

		CpfConverter cpfConverter = new CpfConverter();

		assertEquals(cpfConverter.getAsObject(null, null, testeString), "04874805540");

		testeString = "003.698.745-58";

		assertEquals(cpfConverter.getAsObject(null, null, testeString), "00369874558");
		
		testeString = "873.698.745-58";

		assertEquals(cpfConverter.getAsObject(null, null, testeString), "87369874558");

	}

	@Test
	public final void testGetAsString() {

		String testeString = "4874805540";

		CpfConverter cpfConverter = new CpfConverter();

		assertEquals(cpfConverter.getAsString(null, null, testeString), "048.748.055-40");

		testeString = "369874558";

		assertEquals(cpfConverter.getAsString(null, null, testeString), "003.698.745-58");
		
		testeString = "87369874558";

		assertEquals(cpfConverter.getAsString(null, null, testeString), "873.698.745-58");
		
		
		Long testeLong = 4874805540L;

		assertEquals(cpfConverter.getAsString(null, null, testeLong), "048.748.055-40");

		testeLong = 369874558L;

		assertEquals(cpfConverter.getAsString(null, null, testeLong), "003.698.745-58");
		
		testeLong = 87369874558L;

		assertEquals(cpfConverter.getAsString(null, null, testeLong), "873.698.745-58");
	}

}
