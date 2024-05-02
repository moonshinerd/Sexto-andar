package negocio;

import dados.Gerador_de_dados;

public class Visita {
	private String dataVisita;
	private Usuario usuario;
	private Proprietario proprietario;
	private boolean visitaRealizada;
	private Imovel imovel;
	public Visita(String dataVisita, Usuario usuario, Proprietario proprietario, boolean visitaRealizada,
			Imovel imovel) {
		this.dataVisita = dataVisita;
		this.usuario = usuario;
		this.proprietario = proprietario;
		this.visitaRealizada = visitaRealizada;
		this.imovel = imovel;
	}
	
	public String toString() {
		return "\nVisita:\n"
				+ "Data da visita: " + getDataVisita() + "\n"
				+ "Usuário que marcou a visita: " + usuario.toString() + "\n"
				+ "Proprietário do imóvel a ser visitado: " + proprietario.getNomeUsuario() + " (" + proprietario.getEmail() + ")\n"
				+ "Status da visita: " + isVisitaRealizada() + "\n"
				+ "Id do imóvel a ser visitado: " + (proprietario.getImoveisCadastrados().indexOf(imovel) + 1) + ", Id no sistema: " + Gerador_de_dados.imoveisCadastrados.indexOf(imovel) + "\n";
	}
	public String getDataVisita() {
		return dataVisita;
	}
	public void setDataVisita(String dataVisita) {
		this.dataVisita = dataVisita;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Proprietario getProprietario() {
		return proprietario;
	}
	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}
	public boolean isVisitaRealizada() {
		return visitaRealizada;
	}
	public void setVisitaRealizada(boolean visitaRealizada) {
		this.visitaRealizada = visitaRealizada;
	}
	public Imovel getImovel() {
		return imovel;
	}
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}
	
}
