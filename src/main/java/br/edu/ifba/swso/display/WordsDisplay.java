package br.edu.ifba.swso.display;

import br.edu.ifba.swso.business.abstractions.ByteSWSO;


public class WordsDisplay {
	private int positionColumn01;
	private ByteSWSO valorColumn01;
	
	private int positionColumn02;
	private ByteSWSO valorColumn02;
	
	private int positionColumn03;
	private ByteSWSO valorColumn03;
	
	private int positionColumn04;
	private ByteSWSO valorColumn04;
	
	private int positionColumn05;
	private ByteSWSO valorColumn05;
	
	private int positionColumn06;
	private ByteSWSO valorColumn06;
	
	private int positionColumn07;
	private ByteSWSO valorColumn07;
	
	private int positionColumn08;
	private ByteSWSO valorColumn08;
	
	//COLUNA 01
	public String getPositionColumn01() {
		return converterIntToString(positionColumn01, 4);
	}
	public void setPositionColumn01(int positionColumn01) {
		this.positionColumn01 = positionColumn01;
	}
	public ByteSWSO getValorColumn01() {
		return valorColumn01;
	}
	public void setValorColumn01(ByteSWSO valorColumn01) {
		this.valorColumn01 = valorColumn01;
	}
	
	//COLUNA 02
	public String getPositionColumn02() {
		return converterIntToString(positionColumn02, 4);
	}
	public void setPositionColumn02(int positionColumn02) {
		this.positionColumn02 = positionColumn02;
	}
	public ByteSWSO getValorColumn02() {
		return valorColumn02;
	}
	public void setValorColumn02(ByteSWSO valorColumn02) {
		this.valorColumn02 = valorColumn02;
	}
	
	//COLUNA 03
	public String getPositionColumn03() {
		return converterIntToString(positionColumn03, 4);
	}
	public void setPositionColumn03(int positionColumn03) {
		this.positionColumn03 = positionColumn03;
	}
	public ByteSWSO getValorColumn03() {
		return valorColumn03;
	}
	public void setValorColumn03(ByteSWSO valorColumn03) {
		this.valorColumn03 = valorColumn03;
	}
	
	//COLUNA 04
	public String getPositionColumn04() {
		return converterIntToString(positionColumn04, 4);
	}
	public void setPositionColumn04(int positionColumn04) {
		this.positionColumn04 = positionColumn04;
	}
	public ByteSWSO getValorColumn04() {
		return valorColumn04;
	}
	public void setValorColumn04(ByteSWSO valorColumn04) {
		this.valorColumn04 = valorColumn04;
	}
	
	//COLUNA 05
	public String getPositionColumn05() {
		return converterIntToString(positionColumn05, 4);
	}
	public void setPositionColumn05(int positionColumn05) {
		this.positionColumn05 = positionColumn05;
	}
	public ByteSWSO getValorColumn05() {
		return valorColumn05;
	}
	public void setValorColumn05(ByteSWSO valorColumn05) {
		this.valorColumn05 = valorColumn05;
	}
	
	//COLUNA 06
	public String getPositionColumn06() {
		return converterIntToString(positionColumn06, 4);
	}
	public void setPositionColumn06(int positionColumn06) {
		this.positionColumn06 = positionColumn06;
	}
	public ByteSWSO getValorColumn06() {
		return valorColumn06;
	}
	public void setValorColumn06(ByteSWSO valorColumn06) {
		this.valorColumn06 = valorColumn06;
	}
	
	//COLUNA 07
	public String getPositionColumn07() {
		return converterIntToString(positionColumn07, 4);
	}
	public void setPositionColumn07(int positionColumn07) {
		this.positionColumn07 = positionColumn07;
	}
	public ByteSWSO getValorColumn07() {
		return valorColumn07;
	}
	public void setValorColumn07(ByteSWSO valorColumn07) {
		this.valorColumn07 = valorColumn07;
	}
	
	//COLUNA 08
	public String getPositionColumn08() {
		return converterIntToString(positionColumn08, 4);
	}
	public void setPositionColumn08(int positionColumn08) {
		this.positionColumn08 = positionColumn08;
	}
	public ByteSWSO getValorColumn08() {
		return valorColumn08;
	}
	public void setValorColumn08(ByteSWSO valorColumn08) {
		this.valorColumn08 = valorColumn08;
	}
	
	
	//CONVERSOR
	private String converterIntToString(int numero, int precisao) {
		String hexa = Integer.toHexString(numero).toUpperCase();
		while (hexa.length() < precisao){
			hexa = "0" + hexa;
		}
		return hexa;
	}
	
}
