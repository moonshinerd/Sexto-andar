package negocio;
public abstract class Conta {
	protected String nomeUsuario;
	protected String numeroCelular;
	protected String email;
	protected String senha;
	public Conta(String nomeUsuario, String numeroCelular, String email, String senha) {
		this.nomeUsuario = nomeUsuario;
		this.numeroCelular = numeroCelular;
		this.email = email;
		this.senha = senha;
	}
	
	public void excluirConta(Conta c) {
		c = null;
	}

	public boolean verificarLogin(String email, String senha) {
		if(email.equals(this.email) & senha.equals(this.senha)) {
			System.out.println("Login realizado com sucesso!");
			return true;
		} else {
			System.out.println("Um ou mais campos foram preenchidos incorretamente");
			return false;
		}
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public String getNumeroCelular() {
		return numeroCelular;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}