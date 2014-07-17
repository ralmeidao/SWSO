package br.com.lordmonssa.exemplo.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.lordmonssa.exemplo.validator.CpfValidator;

public class CpfValidatorTest {

	@Test
	public final void testValidaCPF() {

		String testeString = "11111111111";

		assertFalse(CpfValidator.validaCPF(testeString));

		testeString = "60679993746";

		assertTrue(CpfValidator.validaCPF(testeString));

		testeString = "12312312312";

		assertFalse(CpfValidator.validaCPF(testeString));

		testeString = "04874805540";

		assertTrue(CpfValidator.validaCPF(testeString));
	}

	@Test
	public final void testIsCpfValido() {

		String testeString = "11111111111";

		CpfValidator cpfValidador = new CpfValidator();

		assertFalse(cpfValidador.isCpfValido(testeString));

		testeString = "60679993746";

		assertTrue(cpfValidador.isCpfValido(testeString));

		testeString = "12312312312";

		assertFalse(cpfValidador.isCpfValido(testeString));

		testeString = "04874805540";

		assertTrue(cpfValidador.isCpfValido(testeString));

//    Para entradas como long 
		
		Long testeLong = 11111111111L;

		assertFalse(cpfValidador.isCpfValido(testeLong));

		testeLong = 60679993746L;

		assertTrue(cpfValidador.isCpfValido(testeLong));

		testeLong = 12312312312L;

		assertFalse(cpfValidador.isCpfValido(testeLong));

		testeLong = 4874805540L;

		assertTrue(cpfValidador.isCpfValido(testeLong));

	}
}