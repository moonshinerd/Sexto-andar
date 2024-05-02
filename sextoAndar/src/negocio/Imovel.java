package negocio;

public abstract class Imovel {
	protected String endereco;
	protected double tamanhoImovel;
	protected String descricao;
	protected double valorDoImovel;
	protected String dataPublicacao;
	protected TipoDeVenda tipoDeVenda;
	protected Proprietario p;
	protected TipoDeImovel tipoDeImovel;
	private ArrayListPersonalizado<Proposta> propostasRegistradas = new ArrayListPersonalizado<>();
	private ArrayListPersonalizado<Visita> visitasAgendades = new ArrayListPersonalizado<>();

	public Imovel(String endereco, double tamanhoImovel, String descricao, double valorDoImovel, String dataPublicacao,
			TipoDeVenda tipoDeVenda, Proprietario p, TipoDeImovel tipoDeImovel) {
		super();
		this.endereco = endereco;
		this.tamanhoImovel = tamanhoImovel;
		this.descricao = descricao;
		this.valorDoImovel = valorDoImovel;
		this.dataPublicacao = dataPublicacao;
		this.tipoDeVenda = tipoDeVenda;
		this.p = p;
		this.tipoDeImovel = tipoDeImovel;
	}
	public void setTipoDeVenda(TipoDeVenda tipoDeVenda) {
		this.tipoDeVenda = tipoDeVenda;
	}
	public String getEndereco() {
		return endereco;
	}
	public TipoDeVenda getTipoDeVenda() {
		return tipoDeVenda;
	}
	public double getTamanhoImovel() {
		return tamanhoImovel;
	}
	public String getDescricao() {
		return descricao;
	}
	public double getValorDoImovel() {
		return valorDoImovel;
	}
	public String getDataPublicacao() {
		return dataPublicacao;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public void setTamanhoImovel(double tamanhoImovel) {
		this.tamanhoImovel = tamanhoImovel;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setValorDoImovel(double valorDoImovel) {
		this.valorDoImovel = valorDoImovel;
	}
	public void setDataPublicacao(String dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}
	public Proprietario getP() {
		return p;
	}
	public void setP(Proprietario p) {
		this.p = p;
	}
	public TipoDeImovel getTipoDeImovel() {
		return tipoDeImovel;
	}
	public void setTipoDeImovel(TipoDeImovel tipoDeImovel) {
		this.tipoDeImovel = tipoDeImovel;
	}
	public ArrayListPersonalizado<Proposta> getPropostasRegistradas() {
		return propostasRegistradas;
	}
	public void setPropostasRegistradas(ArrayListPersonalizado<Proposta> propostasRegistradas) {
		this.propostasRegistradas = propostasRegistradas;
	}
	public ArrayListPersonalizado<Visita> getVisitasAgendades() {
		return visitasAgendades;
	}
	public void setVisitasAgendades(ArrayListPersonalizado<Visita> visitasAgendades) {
		this.visitasAgendades = visitasAgendades;
	}
	
	public void agendarVisita(Visita visita) {
		visitasAgendades.add(visita);
	}
	
	public void fazerProposta(Proposta proposta) {
		propostasRegistradas.add(proposta);
	}
	
	
}
