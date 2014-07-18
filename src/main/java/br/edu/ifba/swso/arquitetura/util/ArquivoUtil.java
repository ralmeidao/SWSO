package br.edu.ifba.swso.arquitetura.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.fileupload.FileItem;

public class ArquivoUtil {

	public static byte[] getBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);

		long length = file.length();

		if (length > Integer.MAX_VALUE) {
			is.close();
			throw new IOException("Arquivo muito grande.");
		}

		byte[] bytes = new byte[(int) length];

		int offset = 0;
		int numRead = 0;

		try {
			numRead = is.read(bytes, offset, bytes.length - offset);
			while (offset < bytes.length && numRead >= 0) {
				offset += numRead;
			}
		} catch (IOException e) {
			throw e;
		} finally {
			is.close();
		}

		if (offset < bytes.length) {
			throw new IOException(
					"Nao foi possivel completar a leitura do arquivo "
							+ file.getName());
		}
		return bytes;
	}

	public static byte[] getBytesFromFile(FileItem fileItem) throws IOException {
		InputStream is = fileItem.getInputStream();

		long length = fileItem.getSize();

		if (length > Integer.MAX_VALUE) {
			is.close();
			throw new IOException("Arquivo muito grande.");
		}

		byte[] bytes = new byte[(int) length];

		int offset = 0;
		int numRead = 0;

		try {
			numRead = is.read(bytes, offset, bytes.length - offset);
			while (offset < bytes.length && numRead >= 0) {
				offset += numRead;
			}
		} catch (IOException e) {
			throw e;
		} finally {
			is.close();
		}

		if (offset < bytes.length) {
			throw new IOException(
					"Nao foi possivel completar a leitura do arquivo "
							+ fileItem.getName());
		}

		return bytes;
	}

	public static byte[] getFile(String path, Long nomeArquivo) throws IOException {
		File file = new File(path + nomeArquivo);
		return getBytesFromFile(file);
	}

	public static double getBytesSizeEmMB(byte[] bytes) {
		double size = 0;
		if (!Util.isNullOuVazio(bytes)) {
			size = (double) bytes.length / (1024 * 1024);
		}
		return size;
	}

	public static Boolean validaUrl(String url) {
		Pattern padrao = Pattern.compile("^(http?|ftp|file)://.+$");
		Matcher matcher = padrao.matcher(url);
		return matcher.matches();
	}

	/**
	 * Remove arquivos no sistema de arquivos do servidor, conforme o id salvo
	 * 
	 * @param path
	 * @param idArquivo
	 * @throws Exception
	 */
	public static void removerArquivosServidor(String path, Long idArquivo) {
		File file = new File(path + idArquivo);
		if (!Util.isNullOuVazio(file)) {
			file.delete();
		}
	}
	
	
	/**
	 * Remove arquivos no sistema de arquivos do servidor usando o nome completo do arquivo
	 * 
	 * @param nomeCompleto
	 * @throws Exception
	 */
	public static void removerArquivosServidor(String nomeCompleto) {
		if (!Util.isNullOuVazio(nomeCompleto)){
			File file = new File(nomeCompleto);
			if (!Util.isNullOuVazio(file)) {
				file.delete();
			}
		}
	}
	
	/**
	 *
	 * 
	 * @param dir
	 * @return
	 */
	public static boolean removeDiretorio(File dir) {  
	    if (dir.isDirectory()) {  
	        String[] children = dir.list();  
	        for (int i=0; i<children.length; i++) {  
	            boolean success = removeDiretorio(new File(dir, children[i] ));  
	            if (!success) {  
	                return false;  
	            }  
	        }  
	    }  
	    return dir.delete();  
	} 

	/**
	 * Retorna um hash utilizando o InputStream do FileUpload.
	 * 
	 * @param nomeArquivo 
	 * @param inputStream
	 * @return
	 * @throws Exception 
	 */
	public static String geraHash(InputStream inputStream) throws Exception {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] buffer = new byte[8192];
			int read = 0;
			String output = null;

			while ((read = inputStream.read(buffer)) > 0) {
				messageDigest.update(buffer, 0, read);
			}

			byte[] md5sum = messageDigest.digest();
			BigInteger bigInt = new BigInteger(1, md5sum);
			output = bigInt.toString(16);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("Erro ao recuperar a instância com o MessageDigest.getInstance para o 'SHA-256'. Mensagem: " + e.getMessage());
		} catch (IOException ioe) {
			throw new Exception("Erro ao ler o Buffer. Mensagem: " + ioe.getMessage());
		}
	}
}