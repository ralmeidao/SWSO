package br.com.lordmonssa.exemplo.arquitetura.util;



import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

/**
 * Classse utilizadas para colocar o objeto na sessão e ser utilizado no questionário de perguntas. 
 * 
 * @author esouzaa
 * @author jteixeira
 * @author jsouzasa
 * @author rmotal
 * 
 */

public class Util {

	public static final String STRING_VAZIA = "";
	
	private static final String WILDCARD = "%";
	
	public static final String LETRAS_COM_ACENTUACAO = "ÁÀÃÂÄÉÈÊËÍÌÏÎÓÒÕÔÖÚÙÛÜÇÑÝŸáàãâäéèêëíìïîóòõôöúùûüçñýÿ";
    public static final String LETRAS_SEM_ACENTUACAO = "AAAAAEEEEIIIIOOOOOUUUUCNYYaaaaaeeeeiiiiooooouuuucnyy";

    
    /**
     *  Método para verificar se um objeto do tipo Boolean é nulo ;
     * 
     *
     */	
    
	public static Boolean isNull(Object objeto) {
		return (objeto == null) ? Boolean.TRUE : Boolean.FALSE;
	}

	
    /**
     *  Método para verificar se um objeto do tipo String é nulo;
     * 
     *
     */	
	
	public static Boolean isEmptyString(String str) {
		return (!Util.isNull(str) && str.length() == 0) ? Boolean.TRUE : Boolean.FALSE;
	}

    /**
     *  Método para verificar se um objeto é nulo ou vazio;
     * 
     *
     */	
	
	@SuppressWarnings("rawtypes")
	public static boolean isNullOuVazio(Object pObjeto) {

		if (pObjeto == null) {

			return true;

		} else if (pObjeto instanceof Collection) {

			return ((Collection) pObjeto).isEmpty();

		} else if (pObjeto instanceof String) {

			return ((String) pObjeto).trim().equals(STRING_VAZIA);

		} else if (pObjeto instanceof Integer) {

			return ((Integer) pObjeto).intValue() == 0;
		}

		return false;
	}
	
	public static boolean isNullOuVazio(Integer integer) {
		return integer == null;
	}
	
	/**
	 * Remove acentos de uma String
	 *
	 * @param String string
	 */
	
	public static String removeAcentos(String string) {
		return Normalizer.normalize(string.trim(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
	
	public static String pesquisaPorNome(String nomeParametroPesquisa, String campoBanco) {
		StringBuilder hql = new StringBuilder();
//        String nomeModificado = WILDCARD + translate(nomeParametroPesquisa.trim().toUpperCase()) + WILDCARD;
		String nomeModificado = WILDCARD + removeAcentos(nomeParametroPesquisa.trim().toUpperCase()) + WILDCARD;
        hql.append(" AND TRANSLATE(UPPER(" + campoBanco + "), '"+LETRAS_COM_ACENTUACAO+"', '"+LETRAS_SEM_ACENTUACAO+"') like '"+nomeModificado+"'");
        return hql.toString();
    }
	
/*	public static String formatData(Date data, String pFormato){
		SimpleDateFormat sdate = new SimpleDateFormat(pFormato);
		return sdate.format(data);
	}*/
	
	public static String formatData(Date data, String pFormato){
		SimpleDateFormat sdate = new SimpleDateFormat(pFormato);
		return sdate.format(data);
	}
	
	public static String formatDataHora(Date data){
		SimpleDateFormat sdate = new SimpleDateFormat("dd/MM/yyyy/HH:mm");
		return sdate.format(data);
	}	

	/*MÉTODO PARA INSERIR ZEROS A ESQUERDA*/
	public static String lpad(String input, char padding, int length) {
		String output = "";

		if (input != null) {
			output = input;
		}

		if (output.length() >= length) {
			return output;
		} else {
			StringBuffer result = new StringBuffer();
			int numChars = length - output.length();
			for (int i = 0; i < numChars; i++) {
				result.append(padding);
			}
			result.append(output);
			return result.toString();
		}
	}

    /**
     *  Método para formatação de CPF;
     */	
	public static String formataCPF(Long cpf) {
		String cpfString = lpad(Long.toString(cpf), '0', 11);

		if (cpfString != null) {
			cpfString = cpfString.substring(0, 3) + "." + cpfString.substring(3, 6) + "." + cpfString.substring(6, 9) + "-" + cpfString.substring(9, 11);
		}  	

		return cpfString;
	}
	
	/**
	 * Método para calcular a idade da pessoa e será exibido na tela Conferencia
	 * Documental.
	 */
	public static int calculaIdade(Date dataMaior, Date dataMenor) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
        Integer dataMaiorInteiro = new Integer(sd.format(dataMaior));
        Integer dataMenorInteiro = new Integer(sd.format(dataMenor));
        Integer diferenca = dataMaiorInteiro - dataMenorInteiro;
        double total = diferenca / 10000;
        int idade = (int) total;
        
        return idade;
	}
	
	public static String retornaEspacos(int qtdEspacos) {
		String espaco = "";
		for (int i=0; i<qtdEspacos; i++){
			espaco += " ";
		}
		return espaco;
	}

	public static boolean nullSafeEquals(Object o1, Object o2) {
		if (o1 == o2) {
			return true;
		}
		if (o1 == null || o2 == null) {
			return false;
		}
		if (o1.equals(o2)) {
			return true;
		}
		if (o1 instanceof Object[] && o2 instanceof Object[]) {
			return Arrays.equals((Object[]) o1, (Object[]) o2);
		}
		if (o1 instanceof boolean[] && o2 instanceof boolean[]) {
			return Arrays.equals((boolean[]) o1, (boolean[]) o2);
		}
		if (o1 instanceof byte[] && o2 instanceof byte[]) {
			return Arrays.equals((byte[]) o1, (byte[]) o2);
		}
		if (o1 instanceof char[] && o2 instanceof char[]) {
			return Arrays.equals((char[]) o1, (char[]) o2);
		}
		if (o1 instanceof double[] && o2 instanceof double[]) {
			return Arrays.equals((double[]) o1, (double[]) o2);
		}
		if (o1 instanceof float[] && o2 instanceof float[]) {
			return Arrays.equals((float[]) o1, (float[]) o2);
		}
		if (o1 instanceof int[] && o2 instanceof int[]) {
			return Arrays.equals((int[]) o1, (int[]) o2);
		}
		if (o1 instanceof long[] && o2 instanceof long[]) {
			return Arrays.equals((long[]) o1, (long[]) o2);
		}
		if (o1 instanceof short[] && o2 instanceof short[]) {
			return Arrays.equals((short[]) o1, (short[]) o2);
		}
		return false;
	}
}