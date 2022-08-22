package modelo;

public class No {
	
	int chave;
	String valor;
	int alturaNo;
	No esq, dir;
	
	public No() {}
	
	public No(int ch, String v) {
		
		this.setChave(ch);
		this.setValor(v);
		this.setEsq(null);
		this.setDir(null);
		
	}
	
	
	public int getChave() {
		return chave;
	}
	
	public void setChave(int chave) {
		this.chave = chave;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public void setEsq(No esq) {
		this.esq = esq;
	}

	public No getEsq() {
		return esq;
	}
	
	public void setDir(No dir) {
		this.dir = dir;
	}
	
	public No getDir() {
		return dir;
	}

}
