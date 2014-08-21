package br.edu.ifba.swso.util;

/**
 * Classse utilizadas para colocar o objeto na sessão e ser utilizado no questionário de perguntas. 
 * 
 * @author Ramon
 */

public class Constantes {
	
	public static final String CAMINHO_JASPER = "br/edu/ifba/swso/reports/";
	
	public static final String CAMINHO_IMG = "br/edu/ifba/swso/reports/images/";
	
	public static final String CONTENT_TYPE_JPG = "image/jpeg";
	
	public static final String CONTENT_TYPE_PNG = "image/png";
	
	public static int BYTE_SIZE = 8; // tamanho do byte.
	public static int WORD_SIZE = 2; // tamanho da palavra por Bytes.
	public static int BYTE_PER_PAGE = 512; // quantidade de bytes por páginas
	
	public static int DISK_SIZE = 32; // tamanho do disco. (32 pratos)
	public static int PLATE_SIZE = 2; // tamanho do prato. (2 trilhas) 
	public static int TRACK_SIZE = 4;// tamanho da trilha. (4 setores)
	public static int SECTOR_SIZE = 256; // tamanho do setor.(256 bytes)
	
	public static int MEMORY_SIZE = 32; // quantidade total da memória.
	
	public static int NUM_QUATUM = 64; // quatum é medido por palavras (neste caso).
}
