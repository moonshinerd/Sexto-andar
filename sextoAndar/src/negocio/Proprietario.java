package negocio;
import java.util.List;
public class Proprietario extends Conta{
	private List<Imovel> imoveisCadastrados = new ArrayListPersonalizado<>();
	
	public Proprietario(String nomeUsuario, String numeroCelular, String email, String senha) {
		super(nomeUsuario, numeroCelular, email, senha);
	}
	
	public List<Imovel> getImoveisCadastrados() {
		return imoveisCadastrados;
	}

	public void setImoveisCadastrados(List<Imovel> imoveisCadastrados) {
		this.imoveisCadastrados = imoveisCadastrados;
	}

	public boolean cadastrarImovel(Imovel i) {
		imoveisCadastrados.add(i);
		return true;
	}

	public String exibirImoveisCadastrados() {
		String mensagem = "";
		for (Imovel i : imoveisCadastrados) {
			mensagem += i;
		}
		return mensagem;
	}
	
	public void removerImovel(Imovel i) {
		imoveisCadastrados.remove(i);
	}

	public String toString() {
		return "\nConta de Proprietário\n" + "Nome do usuário cadastrado: " + this.nomeUsuario + 
				"\nNúmero do celular cadastrado: " + this.numeroCelular +
				"\nEmail da conta: " + this.email + 
				"\nQuantidade de imóveis cadastrados na conta: " + imoveisCadastrados.size() + "\n";
	}
}
	
	
