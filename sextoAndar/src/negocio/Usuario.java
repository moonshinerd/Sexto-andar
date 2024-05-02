package negocio;
import java.util.List;

public class Usuario extends Conta {
	private List<Imovel> imoveisFavoritos = new ArrayListPersonalizado<>();

	public Usuario(String nomeUsuario, String numeroCelular, String email, String senha) {
		super(nomeUsuario, numeroCelular, email, senha);
	}

	public boolean favoritar(Imovel i) {
		imoveisFavoritos.add(i);
		return true;
	}

	public List<Imovel> getFavoritos() {
		return this.imoveisFavoritos;
	}

	public boolean desfavoritar(Imovel i) {
		if(imoveisFavoritos.contains(i)) {
			imoveisFavoritos.remove(i);
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "\nConta de Usuário\n" + "------------------------\n" + "Nome do usuário cadastrado: " + this.nomeUsuario + 
				"\nNúmero do celular cadastrado: " + this.numeroCelular +
				"\nEmail da conta: " + this.email + 
				"\nQuantidade de imóveis favoritados: " + imoveisFavoritos.size();
	}
}
