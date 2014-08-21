package br.edu.ifba.swso.controller.report;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.edu.ifba.swso.security.JsfUtil;
import br.edu.ifba.swso.util.FacesMessager;
import br.edu.ifba.swso.util.ReportUtils;

public abstract class ReportController {
	
	@Inject
	protected FacesMessager facesMessager;
	
	/**
	 * Atributo do tipo StreamedContent utilizado para o fileDownload do primefaces.
	 */
	private StreamedContent relatorio;  
	 
	/**
	 * Nome desejado para o arquivo PDF final.
	 */
	private String nomeArquivo;
	
	/**
	 * Imprime o relatorio de acordo com a lista informada
	 * @param lista
	 * @return
	 */
	protected abstract byte[] imprimirRelatorio(List<?> lista);
	
	/**
	 * Define a lista de objetos para passar para o relatório.
	 * @param lista
	 * @return
	 */
	protected abstract List<?> defineLista(Object obj);
	
	/**
	 * Pega o tipo do relatório a ser gerado.
	 * @param 
	 * @return String
	 */
	protected abstract String getTipoRelatorio();
	
	/**
	 * Pega o contentType do relatório a ser gerado.
	 * @param 
	 * @return String
	 */
	protected abstract String getContentTypeRelatorio();
	
	public StreamedContent getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(StreamedContent relatorio) {
		this.relatorio = relatorio;
	}
	

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
	public void gerarRelatorio(Object obj) {
		
		List<?> lista = defineLista(obj);
		
		byte[] bytesArray = imprimirRelatorio(lista);
		
		escreveRelatorio(bytesArray, this.nomeArquivo.toString().trim(), getTipoRelatorio(), getContentTypeRelatorio(), true);
	}
	
	public void defineRelatorioStreamedContent(Object obj) {
		
		List<?> lista = defineLista(obj);
		
		byte[] bytesArray = imprimirRelatorio(lista);
		
		InputStream stream = new ByteArrayInputStream(bytesArray);
		
		this.relatorio = new DefaultStreamedContent(stream,"application/pdf", this.nomeArquivo.toString().trim() + ".pdf");
	}
	
	
	protected void escreveRelatorio(byte[] relatorio, String nome, boolean download) {
		try {
			this.escreveRelatorioResponse(relatorio, nome, ReportUtils.REL_TIPO_PDF, "application/pdf", download);
		} catch (Exception e) {
			facesMessager.error(e.getMessage());
		}
	}

	protected void escreveRelatorio(byte[] relatorio, String nome, String tipo, String contentType, boolean download) {
		try {
			this.escreveRelatorioResponse(relatorio, nome, tipo, contentType, download);
		} catch (Exception e) {
			facesMessager.error(e.getMessage());
		}
	}
	
	protected void escreveRelatorio(byte[] relatorio, String nome, String contentType, boolean download) throws IOException {
		try {
			this.escreveRelatorioResponse(relatorio, nome, null, contentType, download);
		} catch (Exception e) {
			facesMessager.error(e.getMessage());
		}
	}

	private void escreveRelatorioResponse(byte[] relatorio, String nome,
			String tipo, String contentType, boolean download) throws IOException {
		try {
			String contentDisposition = download ? "attachment;filename=" : "inline;filename=";
			String nomeCompleto;
			
			if (tipo != null) {
				nomeCompleto =  contentDisposition + "\"" + nome + "." + tipo.toLowerCase() + "\"";
			} else {
				nomeCompleto =  contentDisposition + "\"" + nome + "\"";
			}
					
			JsfUtil.getServletResponse().setContentType(contentType);
			JsfUtil.getServletResponse().setHeader("Content-Disposition", nomeCompleto);
			JsfUtil.getServletResponse().setContentLength(relatorio.length);
			JsfUtil.getServletResponse().getOutputStream().write(relatorio);
		} catch (IOException e) {
			throw e;
		} finally {
			JsfUtil.getServletResponse().getOutputStream().flush();
			JsfUtil.getServletResponse().getOutputStream().close();
			JsfUtil.getContext().responseComplete();
		}
	}

}
