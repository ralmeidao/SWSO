package br.edu.ifba.swso.util;

/**
 * Classse utilizadas para colocar o objeto na sessão e ser utilizado no questionário de perguntas. 
 * 
 * @author Ramon
 */

public class Constantes {
	
	public static final String PARTIAL_AJAX = "partial/ajax";
	
	public static final String FACES_REQUEST = "Faces-Request";
	
	public static final String OMNI_PARTIAL_VIEW_CONTEXT = "org.omnifaces.context.OmniPartialViewContext";
	
	public static final String CAMINHO_JASPER = "br/edu/ifba/swso/reports/";
	
	public static final String CAMINHO_IMG = "br/edu/ifba/swso/reports/images/";
	
	public static final String CONTENT_TYPE_JPG = "image/jpeg";
	
	public static final String CONTENT_TYPE_PNG = "image/png";
	
	public static int BYTE_SIZE = 8; // tamanho do byte.
	public static int WORD_SIZE = 2; // tamanho da palavra por Bytes.
	
	public static int DISK_SIZE = 9; // tamanho do disco. (32 pratos)
	public static int PLATE_SIZE = 10; // tamanho do prato. (4 trilhas) 
	public static int TRACK_SIZE = 8;// tamanho da trilha. (8 setores)
	public static int SECTOR_SIZE = 256; // tamanho do setor.(256 bytes)
	
	
	public static int NUM_QUATUM = 64; // quatum é medido por palavras (neste caso).
	
	//GERENCIA DE PROCESSOS
	public static int PRIORITY_INITIAL = 5;
	public static int TIME_SLICE = 5;
	
	//GERENCIA DE MEMORIA
	//public static int MEMORY_SIZE = 32; // quantidade total da memória.
	//public static int VIRTUAL_MEMORY_SIZE = MEMORY_SIZE*2; // quantidade total da memória virtual.
	public static int RAM_TAXA_DE_OCUPACAO_MAX = 80; //De 0 à 100%
	
}
