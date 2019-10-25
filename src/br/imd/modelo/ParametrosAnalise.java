package br.imd.modelo;

public class ParametrosAnalise {
	
	private MedidaDistancia medida;
	private int k;
	private String imagem;	
	
	public ParametrosAnalise() {
		super();
	}
	
	public ParametrosAnalise(MedidaDistancia medida, int k, String imagem) {
		super();
		this.medida = medida;
		this.k = k;
		this.imagem = imagem;
	}
	
	public MedidaDistancia getMedida() {
		return medida;
	}
	public void setMedida(MedidaDistancia medida) {
		this.medida = medida;
	}
	public int getK() {
		return k;
	}
	public void setK(int k) {
		this.k = k;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	

}
