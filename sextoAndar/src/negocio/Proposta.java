package negocio;

import dados.Gerador_de_dados;

public class Proposta {
	private String data;
	private double valorProposta;
	private Usuario usuario;
	private Imovel imovel;
	private Proprietario proprietario;
	public Proposta(String data, double valorProposta, Usuario usuario, Imovel imovel, Proprietario proprietario) {
		super();
		this.data = data;
		this.valorProposta = valorProposta;
		this.usuario = usuario;
		this.imovel = imovel;
		this.proprietario = proprietario;
	}
	
	public String toString() {
		return "\nProposta:\n"
				+ "Data da proposta: " + getData() + "\n"
				+ "Valor da proposta: " + getValorProposta() + "\n"
				+ "Usuário que fez a proposta: " + usuario.getNomeUsuario() + ", (" + usuario.getEmail() + ")\n"
				+ "Proprietário do imóvel: " + imovel.getP().getNomeUsuario() + ", (" + imovel.getP().getEmail() + ")\n"
				+ "Id do imóvel a ser visitado: " + (proprietario.getImoveisCadastrados().indexOf(imovel) + 1) + ", Id no sistema: " + Gerador_de_dados.imoveisCadastrados.indexOf(imovel) + "\n";
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public double getValorProposta() {
		return valorProposta;
	}
	public void setValorProposta(double valorProposta) {
		this.valorProposta = valorProposta;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Imovel getImovel() {
		return imovel;
	}
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	
	
}
