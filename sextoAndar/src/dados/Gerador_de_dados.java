package dados;

import negocio.*;
import java.util.Random;

public class Gerador_de_dados {
	private static Random random = new Random();
	private static String[] nomes = { "Ana Maria", "João Pedro", "Paulo Jose", "Louro Jose", "Maria Clara" };
	private static String[] numeros = { "(61)9 91238-2741", "(61)9 98213-1234", "(61)9 93127-2124" };
	private static String senha_geral = "123";
	private static String[] endereco = { "Avenida Esbertalina Barbosa Damiani, Lote 14", "Travessa da CDL, Lote 23",
			"Rua Arlindo Nogueira, Lote 62", "Avenida Afonso Pena, lote 5", "Rua Serra de Bragança, lote 54" };
	private static String[] descricao = { "Casa aconchegante com 3 quartos e jardim espaçoso.",
			"Apartamento moderno com vista para a cidade.", "Chalé encantador nas montanhas, perfeito para relaxar.",
			"Sobrado espaçoso com piscina e área de lazer.",
			"Loft urbano com design contemporâneo e localização central." };
	private static String[] datas = { "15/03/2017", "22/08/2018", "07/01/2019", "14/06/2021", "03/11/2023" };
	public static ArrayListPersonalizado<Imovel> imoveisCadastrados = new ArrayListPersonalizado<>();

	public static Usuario geradorDeUsuario() {
		Usuario user = new Usuario(nomes[random.nextInt(4)], numeros[random.nextInt(2)], gerarEmail(),
				senha_geral);
		return user;
	}
	public static TipoDeVenda tipoDeVenda;

	public static Proprietario geradorDeProprietario() {
		Proprietario user = new Proprietario(nomes[random.nextInt(4)], numeros[random.nextInt(2)],
				gerarEmail(), senha_geral);
		for (int i=0; i< random.nextInt(4)+1;i++) {
			if(random.nextBoolean()) {
				Imovel imovelProv = geradorDeCasa(user);
				imoveisCadastrados.add(imovelProv);
				user.cadastrarImovel(imoveisCadastrados.get(imoveisCadastrados.indexOf(imovelProv)));
			}else {
				Imovel imovelProv = geradorDeApartamento(user);
				imoveisCadastrados.add(imovelProv);
				user.cadastrarImovel(imoveisCadastrados.get(imoveisCadastrados.indexOf(imovelProv)));
			}
		}
		return user;
	}

	public static Casa geradorDeCasa(Proprietario p) {
		switch(random.nextInt(1)+1) {
		case 1: tipoDeVenda = TipoDeVenda.ALUGUEL;break;
		case 2: tipoDeVenda = TipoDeVenda.VENDA;break;
		}
		Casa casa = new Casa(endereco[random.nextInt(4)], random.nextDouble(132.00) + 32, descricao[random.nextInt(4)],
				random.nextDouble(2000000.00) + 541200.00, datas[random.nextInt(4)], tipoDeVenda,p,TipoDeImovel.CASA,
				random.nextDouble(500000.00) + 500000.00, random.nextBoolean());
		return casa;
	}
	public static Apartamento geradorDeApartamento(Proprietario p) {
		switch(random.nextInt(1)+1) {
		case 1: tipoDeVenda = TipoDeVenda.ALUGUEL;break;
		case 2: tipoDeVenda = TipoDeVenda.VENDA;break;
		}
		Apartamento apt = new Apartamento(endereco[random.nextInt(4)], random.nextDouble(132.00) + 32, descricao[random.nextInt(4)],
				random.nextDouble(2000000.00) + 541200.00, datas[random.nextInt(4)], tipoDeVenda,p,TipoDeImovel.APARTAMENTO,
				random.nextDouble(400),random.nextBoolean(),random.nextInt(8),random.nextBoolean());
		return apt;
	}
	public static String gerarEmail() {
		int tamanhoString = 8;
		String caracteres = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder randomString = new StringBuilder(tamanhoString);
	    for (int i = 0; i < tamanhoString; i++) {
	        int indiceAleatorio = random.nextInt(caracteres.length());
	        char caractereAleatorio = caracteres.charAt(indiceAleatorio);
	        randomString.append(caractereAleatorio);
	    }
	    return randomString.toString() + "@gmail.com";
	}
	}
	
