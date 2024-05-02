package main;

import negocio.*;
import dados.*;
import java.util.Scanner;
import java.util.Collections;

public class feedImoveis {
	public void exibirImoveis(Scanner sc,ArrayListPersonalizado<Imovel> Exibir) {
		Collections.shuffle(Exibir);
		for(int z=0; z<Exibir.size(); z++){
			System.out.print(Exibir.get(z));
			if(z/5 == Exibir.size()/5){
				System.out.print("Você chegou ao fim da lista. Aperte qualquer botão para voltar ao menu\n");
				String resp = sc.nextLine();
				if(resp != null){
					sc.nextLine();
					break;
				}
			}
			if(z%5==0 && z!=0){
				System.out.print("\nSelecione uma opcao:\n"
								+ "1- Ver mais Imoveis\n"
								+ "2- Voltar para o menu\n");
				int opcao = sc.nextInt();
				sc.nextLine();
				if (opcao ==1) continue;
				else break;
			}			
		}
	}
	
	public void filtrarFeedDeImoveis(Scanner sc, ArrayListPersonalizado<Imovel> Exibir, TipoDeVenda tipoDeVenda, TipoDeImovel tipoDeImovel) {
		ArrayListPersonalizado<Imovel> Filtrado = new ArrayListPersonalizado<>();
		for(int i = 0; i<Exibir.size();i++) {
			if(Exibir.get(i).getTipoDeImovel().equals(tipoDeImovel) && Exibir.get(i).getTipoDeVenda().equals(tipoDeVenda)){
				Filtrado.add(Exibir.get(i));
			}
			Collections.shuffle(Filtrado);
			for(int z=0; z<Filtrado.size(); z++){
				System.out.print(Filtrado.get(z));
				if(z/5 == Filtrado.size()/5){
					System.out.print("Você chegou ao fim da lista. Aperte qualquer botão para voltar ao menu\n");
					String resp = sc.nextLine();
					if(resp != null){
						sc.nextLine();
						break;
					}
				}
				if(z%5==0 && z!=0){
					System.out.print("\nSelecione uma opcao:\n"
									+ "1- Ver mais Imoveis\n"
									+ "2- Voltar para o menu\n");
					int opcao = sc.nextInt();
					sc.nextLine();
					if (opcao ==1) continue;
					else break;
				}			
			}
			
		}

	}
}
