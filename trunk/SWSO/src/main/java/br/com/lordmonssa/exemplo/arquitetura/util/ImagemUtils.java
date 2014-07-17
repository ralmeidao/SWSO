package br.com.lordmonssa.exemplo.arquitetura.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import javax.servlet.ServletContext;

public class ImagemUtils {
	
	public static byte[] getFileBytes(File file) {
		int             len     = (int)file.length();    
	    byte[]          sendBuf = new byte[len];  
	    FileInputStream inFile  = null;  
	    try {
	    	inFile = new FileInputStream(file);           
	        inFile.read(sendBuf, 0, len);    
	        inFile.close();
	          
	    } catch (FileNotFoundException fnfex) {  
	        fnfex.printStackTrace();
	    } catch (IOException ioex) {  
	    	ioex.printStackTrace();
	    }
	    return sendBuf;
	}
	
	public static void salvarImagemServidor(ServletContext scontext, String pathCompleto, byte[] imagem) {
		byte[] fileBytes;		
		String arquivo;
		try{
			arquivo =  scontext.getRealPath(pathCompleto);
			// Verifica se o arquivo existe na pasta temporária.
			if (new File(arquivo).exists()){
				
				// Se existir então deve compará-lo com o array de bytes para ver se são diferentes
				fileBytes = ImagemUtils.getFileBytes(new File(arquivo));
				
				if (!Arrays.equals(fileBytes, imagem)){
					fileBytes = imagem;
					OutputStream targetFile = new FileOutputStream(arquivo);
					targetFile.write(fileBytes);
					targetFile.close();
				}							
			}else{
				fileBytes = imagem;
				OutputStream targetFile = new FileOutputStream(arquivo);
				targetFile.write(fileBytes);
				targetFile.close();							
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}					
	}

	public static void salvarImagemServidor(ServletContext scontext, String path, String nomeArquivo, byte[] imagem) {
		try{
			File file = new File(scontext.getRealPath(path));
			
			if (!file.exists()){
				file.mkdirs();
			}
			String pathCompleto = path + "/" + nomeArquivo;
			salvarImagemServidor(scontext,pathCompleto , imagem);
		}catch(Exception ex){
			ex.printStackTrace();
		}					
	}
}