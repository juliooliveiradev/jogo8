package jogo8;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Estrela {
	Modo modoInicial;
	Modo modoFinal;
	PriorityQueue<Modo> pq;
	Queue<Modo> passados;
	
	// e recebido uma lista atual e final para o calculo de custo
	
	public Estrela(ArrayList start, ArrayList fim) {
		modoInicial = new Modo(start,fim);
		modoFinal = new Modo(fim,fim);
		pq = new PriorityQueue<>();
		pq.add(modoInicial);	
	}
	
	//tratar a instancia da situacao
	public void resolver() {
		Modo modoAtual;
		ArrayList<Modo> filho;
		boolean flag;
		int cMov = 0;
		int cOpen = 0;
		
		while(!pq.isEmpty()) {
			modoAtual = pq.poll();
			this.passados.add(modoAtual);
			
			//teste
			cMov++;
			System.out.println("Movimento: " + cMov);                   
			modoAtual.imprimirModo();                          
			System.out.println("Valor: " + modoAtual.getValor());
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
					Modo aux  = new Modo(item.n, modoFinal.n);
					pq.add(aux);
					cOpen ++;	
				}
			}
		}
	}
}
