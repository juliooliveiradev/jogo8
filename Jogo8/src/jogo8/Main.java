package jogo8;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> modoInicial = new ArrayList<>();
		ArrayList<Integer> modoFinal = new ArrayList<>();
		
		
		modoInicial.add(1);
		modoInicial.add(2);
		modoInicial.add(3);
		modoInicial.add(4);
		modoInicial.add(5);
		modoInicial.add(6);
		modoInicial.add(7);
		modoInicial.add(8);
		
		modoFinal.add(1);
		modoFinal.add(2);
		modoFinal.add(3);
		modoFinal.add(4);
		modoFinal.add(5);
		modoFinal.add(6);
		modoFinal.add(7);
		modoFinal.add(8);

		Modo ei = new Modo(modoInicial);
		Modo ef = new Modo(modoFinal);

		int option;
		do{
			System.out.println("Estado inicial:");
            ei.imprimirModo();
            System.out.println();

            System.out.println("Solucao a ser procurada:");
            ef.imprimirModo();
            System.out.println();

            System.out.println("Digite:");
            System.out.println("1 - Resolver por busca em largura (BFS)");
            System.out.println("2 - Resolver por busca A*");
            System.out.println("3 - Randomizar tabuleiro");
        
            Scanner scanner = new Scanner(System.in);
                       
            option = scanner.nextInt();
            
            switch (option) {
                case 1 -> {
                    FEstrela fe = new FEstrela(ei.n,ef.n);
                    fe.resolver();
                }
                case 2 -> {
                    Estrela el = new Estrela(ei.n,ef.n);
                    el.resolver();
                }
                
                case 3 -> {
                    System.out.println("Quantos movimentos aleatorios deseja realizar?");
                    Scanner scanner2 = new Scanner(System.in);
                    int qtdMoves = scanner2.nextInt();
                    ei.randomize(qtdMoves);
                }
                
                default -> System.out.println("Digite uma opção va3lida");
            }
		}while (!(option==1 || option==2));
	}

}

