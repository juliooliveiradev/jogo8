package jogo8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FEstrela {
	Modo modoInicial;
	Modo modoFinal;
	Queue<Modo> q;
	Queue<Modo> passados;
	
	// construtor
	
	public FEstrela(ArrayList start, ArrayList fim) {
		modoInicial = new Modo(start);
		modoFinal = new Modo(fim);
		q = new LinkedList<>();
		q.add(modoInicial);	
	}
	
	//tratar a instancia da situacao
	public void resolver() {
		Modo modoAtual;
		ArrayList<Modo> filho;
		boolean flag;
		int cMov = 0;
		int cOpen = 0;
		
		while(!q.isEmpty()) {
			modoAtual = q.remove();
			this.passados.add(modoAtual);
			
			//teste
			cMov++;
			System.out.println("Movimento: " + cMov);
			modoAtual.imprimirModo();
			System.out.println();
			
			//comparação entre o estado atual e a solução
			if(modoAtual.equals(this.modoFinal)) {
				System.out.println("Parabens você conseguiu");
				modoAtual.imprimirModo();
				System.out.println("Abertos e Ambiguos" + (cOpen - cMov));
				return;
			}
			
			//geraçao de filhos e se houver diferença deles entre todas as passadas, adiciona em pq
			filho = modoAtual.gFilhos();
			for(Modo item : filho) {
				flag = true;
				for(Modo item2 : this.passados) {
					if(item.equals(item2)) {
						flag = false;
						break;
					}
				}
				if(flag) {					
					q.add(item);
					cOpen ++;	
				}
			}
		}
	}
}
