package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Collections;
import java.util.Locale;

import dados.Gerador_de_dados;
import negocio.*;

public class Main {
	public static ArrayListPersonalizado<Proprietario> listaDeProprietario = new ArrayListPersonalizado<>();
	public static ArrayListPersonalizado<Usuario> listaDeUsuario = new ArrayListPersonalizado<>();
	public static ArrayListPersonalizado<Imovel> listaDeImoveis = new ArrayListPersonalizado<>();
	public static Usuario contaUsuario, keep, sc;
	public static Proprietario contaProprietario;

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		for (int i = 0; i < 15; i++) {
			listaDeProprietario.add(Gerador_de_dados.geradorDeProprietario()); // gerando contas de proprietarios e
																				// imoveis
		}
		for (int i = 0; i < 15; i++) {
			listaDeUsuario.add(Gerador_de_dados.geradorDeUsuario()); // gerando contas de proprietarios e imoveis
		}
		listaDeImoveis = Gerador_de_dados.imoveisCadastrados;

		// System.out.print(listaDeProprietario);
		// Criando conta para facilitar os testes
		Usuario conta = new Usuario("Maria", "celular", "maria@gmail.com", "123");
		Proprietario prop = new Proprietario("Victor", "931293912", "victor@gmail.com", "123");
		Imovel imovTeste = new Casa("", 1.0, "", 2.0, "", TipoDeVenda.ALUGUEL, prop, TipoDeImovel.CASA, 2.0, true);
		listaDeImoveis.add(imovTeste);
		prop.cadastrarImovel(listaDeImoveis.get(listaDeImoveis.indexOf(imovTeste)));
		listaDeUsuario.add(conta);
		listaDeProprietario.add(prop);

		boolean keep = true;
		Scanner sc = new Scanner(System.in);

		do {
			if (contaUsuario != null || contaProprietario != null) {
				if (contaUsuario != null) {
					keep = exibirMenuLogadoUsuario(contaUsuario, keep, sc);
				} else if (contaProprietario != null) {
					keep = exibirMenuLogadoProprietario(contaProprietario, keep, sc);
				}
				// keep = (contaUsuario != null) ? exibirMenuLogadoUsuario(contaUsuario, keep,
				// sc)
				// : exibirMenuLogadoProprietario(contaProprietario, keep, sc);
				System.out.println("");
			} else {
				keep = exibirMenuAutenticacao(keep, sc);
				System.out.println("");
			}
		} while (keep);

		sc.close();
		System.out.println("Muito obrigado por usar o nosso programa!");
		System.out.println("Esse processo foi encerrado");
	}

	public static boolean exibirMenuAutenticacao(boolean keep, Scanner sc) {
		int selecaoMenu = 0;
		System.out.print("Seja bem vindo ao Sexto Andar. Selecione a opção desejada:\n" + "1. Realizar Login\n"
				+ "2. Realizar Cadastro\n" + "3. Entrar como administrador\n" + "4. Encerrar\n");
		selecaoMenu = sc.nextInt();
		switch (selecaoMenu) {
		case 1:
			System.out.print("Selecione o tipo da sua conta:\n" + "1. Conta Usuario\n" + "2. Conta Proprietario\n");
			int selecaoLogin = sc.nextInt();

			if (selecaoLogin == 1) { // PROGRAMAR AS OPÇÕES DE USUARIO NESSE ESCOPO
				if (listaDeUsuario.size() != 0) { // Checagem provisória, verifica se existem usuários cadastrados antes
													// de fazer o login. Poderá ser removida quando os dados da
													// aplicação forem adicionados automaticamente;
					System.out.print("Digite o email cadastrado:\n");
					sc.nextLine();
					String email = sc.nextLine();
					for (Usuario p : listaDeUsuario) {
						if (p.getEmail().equals(email)) {
							System.out.print("Digite a senha da sua conta:\n");
							String senha = sc.nextLine();
							if (p.getSenha().equals(senha)) {
								contaUsuario = p;
								System.out.println("Autenticado como: " + contaUsuario.getNomeUsuario());
							} else {
								System.out.print("A senha está incorreta. Reinicie o processo.\n");
								break;
							}
						}
					}
					if (contaUsuario == null) {
						System.out.println("Não há uma conta cadastrada com este email no sistema.");
					}
				} else {
					System.out
							.println("Não há contas de usuário cadastradas no sistema. Por favor, efetue um cadastro.");
				}

			} else if (selecaoLogin == 2) { // PROGRAMAR AS OPÇÕES DE PROPRIETARIOS NESSE ESCOPO
				if (listaDeProprietario.size() != 0) {
					sc.nextLine();
					System.out.print("Digite o email cadastrado:\n");
					String email = sc.nextLine();
					for (int i = 0; i < listaDeProprietario.size(); i++) {
						Proprietario p = listaDeProprietario.get(i);
						if (p.getEmail().equals(email)) {
							System.out.print("Digite a senha da sua conta:\n");
							String senha = sc.nextLine();
							if (p.getSenha().equals(senha)) {
								contaProprietario = p;
								System.out.println("Autenticado como: " + contaProprietario.getNomeUsuario());
							} else {
								System.out.print("A senha está incorreta. Reinicie o processo.\n");
								break;
							}
						}
					}
					if (contaProprietario == null) {
						System.out.println("Não há uma conta cadastrada com este email no sistema.");
					}
				} else {
					System.out.println(
							"Não há contas de proprietário cadastradas no sistema. Por favor, efetue o cadastro.");
				}
			}
			break;
		case 2:
			System.out.print("Selecione o tipo de conta que deseja cadastrar: \n" + "1. Usuario\n" + "2. Proprietario\n"
					+ "3. Retornar\n");
			int selecaoCadastro = sc.nextInt();
			TipoConta contaSelecionada = null;
			if (selecaoCadastro == 1) {
				contaSelecionada = TipoConta.USUARIO;
				cadastrarConta(sc, contaSelecionada);
				System.out.print("Você foi cadastrado com sucesso. \nVolte ao menu inicial para realizar seu login.\n"
						+ "1. Voltar ao menu inicial\n" + "2. Encerrar\n");
				selecaoCadastro = sc.nextInt();
				if (selecaoCadastro == 2) {
					keep = false;
				}
			} else if (selecaoCadastro == 2) {
				contaSelecionada = TipoConta.PROPRIETARIO;
				cadastrarConta(sc, contaSelecionada);
				System.out.print("Você foi cadastrado com sucesso. \nVolte ao menu inicial para realizar seu login.\n"
						+ "1. Voltar ao menu inicial\n" + "2. Encerrar\n");
				selecaoCadastro = sc.nextInt();
				if (selecaoCadastro == 2) {
					keep = false;
				} // Keep recebe false, retornando à função main e encerrando o programa
			}
			break;
		case 3:
			while (keep)
				keep = exibirMenuAdministrador(sc, keep);
			keep = true;
			break;
		case 4:
			keep = false;
			break;
		default:
			System.out.println("Opção inválida. Pressione qualquer tecla para continuar.\n");
			String abc = sc.nextLine();
			if (abc != null) {
				sc.nextLine(); // limpando o scanner
				break;
			}
		}
		return keep;
	}

	public static boolean exibirMenuLogadoUsuario(Usuario u, boolean keep, Scanner sc) {
		System.out.println("Seja bem-vindo(a) " + u.getNomeUsuario());
		System.out.print("Selecione a Opção desejada:\n" + "1 - Visualizar dados da Conta.\n"
				+ "2 - Visualizar Imóveis.\n"
				+ "3 - Visualizar Imoveis favoritos\n" 
				+ "4 - Deslogar.\n" 
				+ "5 - Sair do sistema\n");
		int selecaoConta = sc.nextInt();
		switch (selecaoConta) {
		case 1:
			System.out.println("");
			System.out.println(u);
			System.out.print("Deseja editar seus dados? (s/n): ");
			sc.nextLine(); // limpando o scanner
			String selecaoResposta = sc.nextLine();
			if (selecaoResposta.toLowerCase().charAt(0) == 's') {
				System.out.print("Digite seu nome:\n");
				String nome = sc.nextLine();
				System.out.print("Digite seu numero de celular:\n");
				String celular = sc.nextLine();
				System.out.print("Digite seu email:\n");
				String email = sc.nextLine();
				System.out.print("Escolha uma nova senha para sua conta:\n");
				String senha = sc.nextLine();
				u.setNomeUsuario(nome);
				u.setNumeroCelular(celular);
				u.setEmail(email);
				u.setSenha(senha);
				System.out.println("Dados alterados com sucesso.");
				break;
			} else {
				break;
			}
		case 2:
			System.out.println("VEJA NOSSOS IMÓVEIS:");
			sc.nextLine();
			for (int i = listaDeImoveis.size()-1; i >= 0; i--) {

				boolean verificar = false;
				do {
					verificar = false;
					System.out.println(
							"|--------------------------|" + listaDeImoveis.get(i) + "|--------------------------|");
					System.out.println("Pressione 'n' para visualizar o próximo imóvel.\n\n"
							+ "Ficou interessado, mas ainda não tem certeza?"
							+ " Marque já a sua visita apertando a tecla 'v'!\n\n"
							+ "Quer adicionar esse imovel a sua lista de imoveis favoritos? Aperte a tecla 'f'\n\n "
							+ "Gostou muito? Faça já a sua proposta apertanto a tecla 'p'.\n\n"
							+ "Pressione a tecla 'q' para voltar.");
					String z = sc.nextLine();
					switch (z) {
					case "n":
						break;
					case "v":
						System.out.print("\nInforme a data que você deseja realizar a visita: ");
						String dataVisita = sc.nextLine();
						Visita visitaAgendada = new Visita(dataVisita, contaUsuario, listaDeImoveis.get(i).getP(),
								false, listaDeImoveis.get(i));
						listaDeImoveis.get(i).agendarVisita(visitaAgendada);
						System.out.print("\nSua visita foi agendada com sucesso!\n"
								+ "1 - Continuar a visualizar os Imóveis.\n" + "2 - Voltar ao menu anterior.");
						int respostaVisita = sc.nextInt();
						if (respostaVisita == 2) {
							i = -1;
						}
						sc.nextLine();
						break;
					case "f":
						contaUsuario.favoritar(listaDeImoveis.get(i));
						System.out.print("Imovel favoritado com sucesso! Siga para o proximo imovel\n");
						break;
					case "p":
						System.out.print("\nInforme o valor da sua proposta em reais: R$");
						double valorProposta = sc.nextDouble();
						LocalDate data = LocalDate.now();
						DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						Proposta propostaRealizada = new Proposta(data.format(fmt), valorProposta, contaUsuario,
								listaDeImoveis.get(i), listaDeImoveis.get(i).getP());
						listaDeImoveis.get(i).fazerProposta(propostaRealizada);
						System.out.print("\nSua proposta foi enviada com sucesso!\n"
								+ "1 - Continuar a visualizar os Imóveis.\n" + "2 - Voltar ao menu anterior.");
						int respostaProposta = sc.nextInt();
						if (respostaProposta == 2) {
							i = -1;
						}
						sc.nextLine();
						break;
					case "q":
						i = -1;
						break;
					default:
						System.out.println("Você selecionou uma opção inválida. Tente novamente.");
						verificar = true;
						break;
					}
				} while (verificar);
			}
			break;
		case 3:
			if(contaUsuario.getFavoritos().size() == 0) {
				System.out.print("Você não possui imoveis favoritos retornando ao menu principal\n");
				break;
			}
			
			for (int i =0; i<contaUsuario.getFavoritos().size();i++) {
				System.out.print(contaUsuario.getFavoritos().get(i));
				System.out.print("Selecione a opção desejada:\n"
						+ "1 - Ver proximo imovel\n"
						+ "2 - Marcar visita\n"
						+ "3 - Fazer proposta\n"
						+ "4 - Sair\n");
				sc.nextLine();
				String opciones = sc.nextLine();
				if (opciones == "4") break;
				switch(opciones) {
				case "1":
					break;
				case "2":
					System.out.print("\nInforme a data que você deseja realizar a visita: ");
					String dataVisita = sc.nextLine();
					Visita visitaAgendada = new Visita(dataVisita, contaUsuario, listaDeImoveis.get(i).getP(),
							false, listaDeImoveis.get(i));
					listaDeImoveis.get(i).agendarVisita(visitaAgendada);
					System.out.print("\nSua visita foi agendada com sucesso!\n"
							+ "1 - Continuar a visualizar os Imóveis.\n" + "2 - Voltar ao menu anterior.");
					int respostaVisita = sc.nextInt();
					if (respostaVisita == 2) {
						i = -1;
					}
					sc.nextLine();
					break;
				case "3":
					System.out.print("\nInforme o valor da sua proposta em reais: R$");
					double valorProposta = sc.nextDouble();
					LocalDate data = LocalDate.now();
					DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					Proposta propostaRealizada = new Proposta(data.format(fmt), valorProposta, contaUsuario,
							listaDeImoveis.get(i), listaDeImoveis.get(i).getP());
					listaDeImoveis.get(i).fazerProposta(propostaRealizada);
					System.out.print("\nSua proposta foi enviada com sucesso!\n"
							+ "1 - Continuar a visualizar os Imóveis.\n" + "2 - Voltar ao menu anterior.");
					int respostaProposta = sc.nextInt();
					if (respostaProposta == 2) {
						i = -1;
					}
					sc.nextLine();
					break;
				}
			}
			break;
		case 4:
			contaUsuario = null;
			break;
		case 5:
			keep = false;
			break;
		default:
			System.out.println("Opção inválida, tente novamente.\n");
			break;
		}
		return keep;
	}

	public static boolean exibirMenuLogadoProprietario(Proprietario c, boolean keep, Scanner sc) {
		System.out.println("Seja bem-vindo(a) " + c.getNomeUsuario());
		System.out.print("Selecione a Opção desejada:\n" 
						+ "1 - Visualizar dados da Conta.\n"
						+ "2 - Cadastrar Imovel.\n" 
						+ "3 - Visualizar seus Imoveis\n" 
						+ "4 - Deslogar\n" 
						+ "5 - Sair do sistema\n");
		int selecaoConta = sc.nextInt();
		switch (selecaoConta) {
		case 1:
			System.out.println("");
			System.out.println(c);
			System.out.print("Deseja editar seus dados? (s/n): ");
			sc.nextLine(); // limpando o scanner
			String selecaoResposta = sc.nextLine();
			if (selecaoResposta.toLowerCase().charAt(0) == 's') {
				System.out.print("Digite seu nome:\n");
				String nome = sc.nextLine();
				System.out.print("Digite seu numero de celular:\n");
				String celular = sc.nextLine();
				System.out.print("Digite seu email:\n");
				String email = sc.nextLine();
				System.out.print("Escolha uma nova senha para sua conta:\n");
				String senha = sc.nextLine();
				c.setNomeUsuario(nome);
				c.setNumeroCelular(celular);
				c.setEmail(email);
				c.setSenha(senha);
				System.out.println("Dados alterados com sucesso.");
				break;
			} else {
				break;
			}
		case 2:
			System.out.print(
					"Selecione o tipo de imovel que você deseja cadastrar;\n" + "1 - Casa\n" + "2 - Apartamento\n");
			int tipo = sc.nextInt();
			sc.nextLine(); // limpando o scanner
			switch (tipo) {
			case 1:
				System.out.print("Digite o endereço no qual se localiza a casa:\n");
				String endereco = sc.nextLine();
				System.out.print("Digite o tamanho do seu imovel em metros quadrados:\n");
				double tamanhoimovel = sc.nextDouble();
				sc.nextLine(); // Limpa o buffer do scanner
				System.out.print("Informe uma descrição breve do seu Imóvel:\n");
				String descricao = sc.nextLine();
				System.out.print("Digite o valor do imovel em reais:\n");
				double valorimovel = sc.nextDouble();
				sc.nextLine(); // Limpa o buffer do scanner
				LocalDate datanostring = LocalDate.now();
				String data = datanostring.toString();
				System.out.print("Qual o tipo de venda do seu imovel:\n" + "1 - Aluguel\n" + "2 - Venda\n");
				int opcao = sc.nextInt();
				sc.nextLine(); // limpando buffer
				TipoDeVenda tipoDeVenda = null;
				switch (opcao) {
				case 1:
					tipoDeVenda = TipoDeVenda.ALUGUEL;
					break;
				case 2:
					tipoDeVenda = TipoDeVenda.VENDA;
					break;
				default:
					System.out.println("Opção inválida, pressione qualquer tecla para continuar.\n");
					String anychar = sc.nextLine(); // Limpa o buffer do scanner
					if (anychar != null)
						break;
				}
				if (opcao != 1 && opcao != 2)
					break; // encerrando processo por erro do usuario
				System.out.print("Digite o preço do terreno em que o Imovel se localiza:\n");
				double precoTerreno = sc.nextDouble();
				sc.nextLine(); // Limpa o buffer do scanner
				System.out.print("É a única casa no terreno:\n" + "1 - Sim\n" + "2 - Não\n");
				boolean casaUnicaTerreno = true;
				int opcao1 = sc.nextInt();
				sc.nextLine(); // limpando buffer
				switch (opcao1) {
				case 1:
					casaUnicaTerreno = true;
					break;
				case 2:
					casaUnicaTerreno = false;
					break;
				default:
					System.out.println("Opção inválida, pressione qualquer tecla para continuar.\n");
					String h = sc.nextLine();
					if (h != null) {
						sc.nextLine(); // limpando o scanner
						break;
					}
				}
				if (opcao1 != 1 && opcao1 != 2)
					break; // encerrando processo por erro do usuario
				Casa casa = new Casa(endereco, tamanhoimovel, descricao, valorimovel, data, tipoDeVenda,
						contaProprietario, TipoDeImovel.CASA, precoTerreno, casaUnicaTerreno);
				listaDeImoveis.add(casa);
				contaProprietario.cadastrarImovel(listaDeImoveis.get(listaDeImoveis.indexOf(casa)));
				System.out.print("Seu imovel foi cadastrado com sucesso.\n");
				System.out.println("Aperte qualquer tecla para continuar\n");
				String g = sc.nextLine();
				if (g != null) {
					sc.nextLine(); // limpando o scanner
					break;
				}
				break;
			case 2:
				System.out.print("Digite o endereço no qual se localiza o apartamento:\n");
				String endereco1 = sc.nextLine();
				System.out.print("Digite o tamanho do seu imovel em metros quadrados:\n");
				double tamanhoimovel1 = sc.nextDouble();
				sc.nextLine(); // Limpa o buffer do scanner
				System.out.print("Descreva em poucas palavras uma breve descrição do imovel:\n");
				String descricao1 = sc.nextLine();
				System.out.print("Digite o valor do imovel em reais:\n");
				double valorimovel1 = sc.nextDouble();
				sc.nextLine(); // Limpa o buffer do scanner
				LocalDate datanostring1 = LocalDate.now();
				String data1 = datanostring1.toString();
				System.out.print("O condominio possui area de convivencia:\n" + "1 - Sim\n" + "2 - Não\n");
				int opcao2 = sc.nextInt();
				sc.nextLine(); // limpando scanner
				boolean areaconvivencia = (opcao2 == 1 ? true : false);
				switch (opcao2) {
				case 1:
					areaconvivencia = true;
					break;
				case 2:
					areaconvivencia = false;
					break;
				default:
					System.out.println("Opção inválida, pressione qualquer tecla para continuar.\n");
					sc.nextLine(); // Limpa o buffer do scanner
					break;
				}
				if (opcao2 != 1 && opcao2 != 2)
					break; // encerrando processo por erro do usuario
				System.out.println("Selecione o tipo de venda: " + "\n1 - Aluguel\n2 - Venda");
				int opcao6 = sc.nextInt();
				sc.nextLine(); // limpando buffer
				TipoDeVenda tipoDeVendaApartamento = null;
				switch (opcao6) {
				case 1:
					tipoDeVendaApartamento = TipoDeVenda.ALUGUEL;
					break;
				case 2:
					tipoDeVendaApartamento = TipoDeVenda.VENDA;
					break;
				default:
					System.out.println("Opção inválida, pressione qualquer tecla para continuar.\n");
					sc.nextLine(); // Limpa o buffer do scanner
					break;
				}
				if (opcao6 != 1 && opcao6 != 2)
					break; // encerrando processo por erro do usuario
				System.out.print("Digite o preço do terreno em que o Imovel se localiza:\n");
				double precoCondominio = sc.nextDouble();
				sc.nextLine(); // Limpa o buffer do scanner
				System.out.print("É permitido animais:\n" + "1 - Sim\n" + "2 - Não\n");
				int opcao9 = sc.nextInt();
				boolean pet = (opcao9 == 1 ? true : false);
				switch (opcao9) {
				case 1:
					pet = true;
					break;
				case 2:
					pet = false;
					break;
				default:
					System.out.println("Opção inválida, pressione qualquer tecla para continuar.\n");
					String h = sc.nextLine();
					if (h != null) {
						sc.nextLine(); // limpando o scanner
						break;
					}
				}
				if (opcao9 != 1 && opcao9 != 2)
					break; // encerrando processo por erro do usuario
				System.out.print("Digite em qual andar se localiza o apartamento:\n");
				int andar = sc.nextInt();
				Apartamento aptm = new Apartamento(endereco1, tamanhoimovel1, descricao1, valorimovel1, data1,
						tipoDeVendaApartamento, contaProprietario, TipoDeImovel.APARTAMENTO, precoCondominio,
						areaconvivencia, andar, pet);
				listaDeImoveis.add(aptm);
				contaProprietario.cadastrarImovel(listaDeImoveis.get(listaDeImoveis.indexOf(aptm)));
				System.out.print("Seu imovel foi cadastrado com sucesso.\n");
				System.out.println("Aperte qualquer tecla para continuar\n");
				String j = sc.nextLine();
				if (j != null) {
					sc.nextLine(); // limpando o scanner
					break;
				}
				break;
			default:
				System.out.println("Opção inválida, pressione qualquer tecla para continuar.\n");
				String ab = sc.nextLine();
				if (ab != null) {
					sc.nextLine(); // limpando o scanner
					break;
				}
			}
			break;
		case 3:
			if(contaProprietario.getImoveisCadastrados().size() == 0) {
				System.out.println("Não há imóveis cadastrados nessa conta!");
			} else {
				for(int i = contaProprietario.getImoveisCadastrados().size()-1; i >= 0; i--) {
					Imovel imovel = contaProprietario.getImoveisCadastrados().get(i);
					boolean verificaResposta = true;
					do {
						System.out.println("---------------- Imóvel " + (contaProprietario.getImoveisCadastrados().indexOf(imovel) + 1) + " ----------------");
						System.out.println(imovel);
						System.out.println("Selecione uma opção:" 
											+ "\n1 - Visualizar propostas do imóvel" 
											+ "\n2 - Visualizar visitas agendadas" 
											+ "\n3 - Visualizar próximo imóvel" 
											+ "\n4 - Sair");
						int resposta = sc.nextInt();
						switch(resposta) {
						case 1:
							if(imovel.getPropostasRegistradas().size() == 0) {
								System.out.println("Não foi realizada nenhuma proposta para esse imóvel.");
							} else {
								System.out.print(imovel.getPropostasRegistradas());
							}
							System.out.println("Aperte qualquer tecla para continuar");
							String tocontinue = sc.nextLine();
							if (tocontinue != null) {
								sc.nextLine(); // limpando o scanner
								break;
							}
							break;
						case 2:
							if(imovel.getVisitasAgendades().size() == 0) {
								System.out.println("Nenhuma visita foi agendada para esse imóvel.");
							} else {
								System.out.print(imovel.getVisitasAgendades());
							}
							System.out.println("Aperte qualquer tecla para continuar");
							tocontinue = sc.nextLine();
							if (tocontinue != null) {
								sc.nextLine(); // limpando o scanner
								break;
							}
							break;
						case 3:
							verificaResposta = false;
							break;
						case 4:
							i = -1;
							verificaResposta = false;
							break;
						default:
							System.out.println("A opção escolhida é inválida! Tente novamente.");
							break;
						}
					} while(verificaResposta);
				}
			}
			break;
		case 4:
			contaProprietario = null;
			break;
		case 5:
			keep = false;
			break;
		default:
			System.out.println("Opção inválida, pressione qualquer tecla para continuar.\n");
			String ab = sc.nextLine();
			if (ab != null) {
				sc.nextLine(); // limpando o scanner
				break;
			}
		}
		return keep;
	}

	public static boolean exibirMenuAdministrador(Scanner sc, boolean keep) {
		System.out.print("Seja bem vindo administrador, selecione a opção desejada:\n"
				+ "1- Visualizar Usuarios cadastrados\n" + "2- Editar/Excluir Usuario\n"
				+ "3- Visualizar Proprietarios cadastrados\n" + "4- Editar/Excluir Proprietarios\n"
				+ "5- Visualzar Imoveis cadastrados\n" + "6- Excluir Imóveis cadastrados\n" + "7- Voltar\n");
		int opc = sc.nextInt();
		sc.nextLine(); // limpar buffer
		switch (opc) {
		case 1:
			for (int i = 0; i < listaDeUsuario.size(); i++) {
				System.out.print(listaDeUsuario.get(i));
				if (i / 5 == listaDeUsuario.size() / 5) {
					System.out.print("\nVocê chegou ao fim da lista. Aperte qualquer botão para voltar ao menu.\n");
					String qualquer = sc.nextLine();
					if (qualquer != null) {
						sc.nextLine();
						break;
					}
				}
				if (i % 5 == 0 && i != 0) {
					System.out.print("\nSelecione uma opcao:\n" + "1- Ver mais usuarios\n" + "2- Voltar para o menu\n");
					int opcao = sc.nextInt();
					sc.nextLine();
					if (opcao == 1)
						continue;
					else
						break;
				}
			}
			break;
		case 2:
			System.out.print("Digite o email do usuario que deseja editar/excluir:\n");
			String email = sc.nextLine();
			sc.nextLine();
			for (int i = 0; i < listaDeUsuario.size(); i++) {
				if (listaDeUsuario.get(i).getEmail().equals(email)) {
					System.out.print("Você selecionou o seguinte Usuario:\n");
					System.out.print(listaDeUsuario.get(i));
					System.out.print("Selecione a opção desejada:\n" + "1- Editar dados\n" + "2- Apagar Usuario\n");
					int opcione = sc.nextInt();
					switch (opcione) {
					case 1:
						System.out.print("Digite o novo nome:\n");
						String nome = sc.nextLine();
						System.out.print("Digite o novo numero de celular:\n");
						String celular = sc.nextLine();
						System.out.print("Digite o novo email:\n");
						String email1 = sc.nextLine();
						System.out.print("Escolha uma nova senha para a conta:\n");
						String senha = sc.nextLine();
						listaDeUsuario.get(i).setNomeUsuario(nome);
						listaDeUsuario.get(i).setNumeroCelular(celular);
						listaDeUsuario.get(i).setEmail(email1);
						listaDeUsuario.get(i).setSenha(senha);
						System.out.println("Dados alterados com sucesso. Aperte qualquer tecla para continuar");
						String continuar = sc.nextLine();
						if (continuar != null) {
							sc.nextLine();
							break;
						}
						break;
					case 2:
						listaDeUsuario.remove(i);
						System.out.print("Usuario removido com sucesso aperte qualquer tecla para continuar.");
						String continuar1 = sc.nextLine();
						if (continuar1 != null) {
							sc.nextLine();
							break;
						}
						break;
					}
				} else if (i == listaDeProprietario.size() - 1) {
					System.out.print(
							"Não encontramos esse email na nossa base de proprietarios. Pressione qualquer tecla para continuar\n");
					String continuar1 = sc.nextLine();
					if (continuar1 != null) {
						sc.nextLine();
						break;
					}
				}
			}
			break;
		case 3:
			for (int i = 0; i < listaDeProprietario.size(); i++) {
				System.out.print(listaDeProprietario.get(i));
				if (i / 5 == listaDeProprietario.size() / 5) {
					System.out.print("\nVocê chegou ao fim da lista. Aperte qualquer botão para voltar ao menu.\n");
					String qualquer = sc.nextLine();
					if (qualquer != null) {
						sc.nextLine();
						break;
					}
				}
				if (i % 5 == 0 && i != 0) {
					System.out.print(
							"\nSelecione uma opcao:\n" + "1- Ver mais proprietarios\n" + "2- Voltar para o menu\n");
					int opcao = sc.nextInt();
					sc.nextLine();
					if (opcao == 1)
						continue;
					else
						break;
				}
			}
			break;
		case 4:
			System.out.print("Digite o email do Proprietario que deseja editar/excluir:\n");
			String email3 = sc.nextLine();
			for (int i = 0; i < listaDeProprietario.size(); i++) {
				if (listaDeProprietario.get(i).getEmail().equals(email3)) {
					System.out.print("Você selecionou o seguinte Proprietario:\n");
					System.out.print(listaDeProprietario.get(i));
					System.out
							.print("Selecione a opção desejada:\n" + "1- Editar dados\n" + "2- Apagar Proprietario\n");
					int opcione = sc.nextInt();
					sc.nextLine();
					switch (opcione) {
					case 1:
						System.out.print("Digite o novo nome:\n");
						String nome = sc.nextLine();
						System.out.print("Digite o novo numero de celular:\n");
						String celular = sc.nextLine();
						System.out.print("Digite o novo email:\n");
						String email4 = sc.nextLine();
						System.out.print("Escolha uma nova senha para a conta:\n");
						String senha = sc.nextLine();
						listaDeProprietario.get(i).setNomeUsuario(nome);
						listaDeProprietario.get(i).setNumeroCelular(celular);
						listaDeProprietario.get(i).setEmail(email4);
						listaDeProprietario.get(i).setSenha(senha);
						System.out.println("\nDados alterados com sucesso. Aperte qualquer tecla para continuar");
						String continuar = sc.nextLine();
						if (continuar != null) {
							sc.nextLine();
							break;
						}
						break;
					case 2:
						listaDeProprietario.remove(i);
						System.out.print("\nProprietario removido com sucesso aperte qualquer tecla para continuar.");
						String continuar1 = sc.nextLine();
						if (continuar1 != null) {
							sc.nextLine();
							break;
						}
						break;
					}
				} else if (i == listaDeProprietario.size() - 1) {
					System.out.print(
							"\nNão encontramos esse email na nossa base de proprietarios. Pressione qualquer tecla para continuar\n");
					String continuar1 = sc.nextLine();
					if (continuar1 != null) {
						sc.nextLine();
						break;
					}
				}
			}
			break;
		case 5:
			ArrayListPersonalizado<Imovel> Exibir = listaDeImoveis;
			Collections.shuffle(Exibir);
			for (int k = 0; k < Exibir.size(); k++) {
				System.out.print(Exibir.get(k));
				if (k / 5 == Exibir.size() / 5) {
					System.out.print("\nVocê chegou ao fim da lista. Aperte qualquer botão para voltar ao menu.\n");
					String qualquer = sc.nextLine();
					if (qualquer != null) {
						sc.nextLine();
						break;
					}
				}
				if (k % 5 == 0 && k != 0) {
					System.out.print("\nSelecione uma opcao:\n" + "1- Ver mais Imoveis\n" + "2- Voltar para o menu\n");
					int opcao = sc.nextInt();
					sc.nextLine();
					if (opcao == 1)
						continue;
					else
						break;
				}
			}
			break;
		case 6:
			System.out.print("\nDigite o email do proprietario do imovel que você deseja excluir:\n");
			String emailprop = sc.nextLine();
			sc.nextLine();
			for (int i = 0; i < listaDeProprietario.size(); i++) {
				if (listaDeProprietario.get(i).getEmail().equals(emailprop)) {
					if (listaDeProprietario.get(i).getImoveisCadastrados().isEmpty()) {
						System.out.print(
								"Esse proprietario não possui imoveis cadastrados pressione qualquer tecla para retornar ao menu\n");
						String qualquer = sc.nextLine();
						if (qualquer != null) {
							sc.nextLine();
							break;
						}
					}
					System.out.print("Mostrando os imoveis do proprietario selecionado:\n");
					for (int j = 0; j < listaDeProprietario.get(i).getImoveisCadastrados().size(); j++) {
						System.out.print("Imóvel número " + (j + 1) + ":");
						System.out.print(listaDeProprietario.get(i).getImoveisCadastrados().get(j));
					}
					System.out.print(
							"\nDeseja deletar algum dos imóveis acima?\n" + "1- Sim\n" + "2- Retornar ao menu\n");
					int opcaoesc = sc.nextInt();
					sc.nextLine();
					if (opcaoesc == 1) {
						System.out.print("Digite o número do imovel que você deseja deletar:\n");
						int numImovel = sc.nextInt();
						listaDeImoveis.remove(listaDeProprietario.get(i).getImoveisCadastrados().get(numImovel - 1));
						listaDeProprietario.get(i)
								.removerImovel(listaDeProprietario.get(i).getImoveisCadastrados().get(numImovel - 1));
						System.out.print("O imovel foi removido com sucesso, aperte qualquer tecla para continuar.\n");
						String qualquer = sc.nextLine();
						if (qualquer != null) {
							sc.nextLine();
							break;
						}
					} else
						System.out.print("Opção inválida aperte qualquer tecla para retornar ao menu.\n");
					String qualquer = sc.nextLine();
					if (qualquer != null) {
						sc.nextLine();
						break;
					}
					break;
				} else if (i == listaDeProprietario.size() - 1) {
					System.out.print(
							"\nNão encontramos esse proprietario na nossa base de dados. Pressione qualquer tecla para continuar.\n");
					String continuar1 = sc.nextLine();
					if (continuar1 != null) {
						sc.nextLine();
						break;
					}
				}
			}
			break;
		case 7:
			keep = false;
			break;
		}
		return keep;

	}

	public static void cadastrarConta(Scanner sc, TipoConta contaSelecionada) {
		sc.nextLine();
		System.out.print("Digite seu nome:\n");
		String nome = sc.nextLine();
		System.out.print("Digite seu numero de celular:\n");
		String celular = sc.nextLine();
		System.out.print("Digite seu email:\n");
		String email = sc.nextLine();
		System.out.print("Escolha uma senha para sua conta:\n");
		String senha = sc.nextLine();
		switch (contaSelecionada) {
		case USUARIO:
			Usuario contaUsuario = new Usuario(nome, celular, email, senha);
			listaDeUsuario.add(contaUsuario);
			break;
		case PROPRIETARIO:
			Proprietario contaProprietario = new Proprietario(nome, celular, email, senha);
			listaDeProprietario.add(contaProprietario);
			break;
		}
		return;
	}

}
