package negocio;

public class Apartamento extends Imovel {
	private double precoCondominio;
	private boolean areaDeConvivencia;
	private int andar;
	private boolean pet;
	
	
	public Apartamento(String endereco, double tamanhoImovel, String descricao, double valorDoImovel,
			String dataPublicacao, TipoDeVenda tipoDeVenda, Proprietario p, TipoDeImovel tipoDeImovel,
			double precoCondominio, boolean areaDeConvivencia, int andar, boolean pet) {
		super(endereco, tamanhoImovel, descricao, valorDoImovel, dataPublicacao, tipoDeVenda, p, tipoDeImovel);
		this.precoCondominio = precoCondominio;
		this.areaDeConvivencia = areaDeConvivencia;
		this.andar = andar;
		this.pet = pet;
	}
	
	public double calcularValorTotalDoApartamento(double precoCondominio, double valorDoImovel) {
		return precoCondominio+getValorDoImovel();
	}
	public String toString() {
		return "\nTipo de Imóvel selecionado: Apartamento\n" +
			"Endereço: " + getEndereco() + "\n" +
			"Tamanho do Imóvel: " + getTamanhoImovel() + "\n" +
			"Andar: "+getAndar()+"\n"+
			"Descrição: " + getDescricao() + "\n" +
			(tipoDeVenda.equals(TipoDeVenda.ALUGUEL)? "Valor do aluguel: " : "Valor do imóvel: ") + getValorDoImovel() + "\n" +
			"Data de publicação: " + getDataPublicacao() + "\n" +
			"Tipo de Venda: " + (getTipoDeVenda().toString() == "ALUGUEL" ? "Aluguel" : "Venda") + "\n" +
			"Preço do Condomínio: " + getPrecoCondominio() + "\n" +
			"Possui Área de Convivência: " + (isAreaDeConvivencia() ? "Sim" : "Não")+"\n"+
			"Permite pets: " + (isPet() ? "Sim" : "Não")+"\n"+
			"Proprietario: "+ p.getNomeUsuario() + " ("+p.getEmail()+")\n";
	}

	public double getPrecoCondominio() {
		return precoCondominio;
	}

	public boolean isAreaDeConvivencia() {
		return areaDeConvivencia;
	}

	public int getAndar() {
		return andar;
	}

	public boolean isPet() {
		return pet;
	}

	public void setPrecoCondominio(double precoCondominio) {
		this.precoCondominio = precoCondominio;
	}

	public void setAreaDeConvivencia(boolean areaDeConvivencia) {
		this.areaDeConvivencia = areaDeConvivencia;
	}

	public void setAndar(int andar) {
		this.andar = andar;
	}

	public void setPet(boolean pet) {
		this.pet = pet;
	}
}
