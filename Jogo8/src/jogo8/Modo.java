package jogo8;

import java.util.ArrayList;
import java.util.Objects;


public class Modo {
	public ArrayList<Integer> n;
    public int valor;
         
    
    //construtor para BFS
    public Modo (ArrayList v){
        n = new ArrayList<>(v);
    }
    
    //construtor para AStar
    public Modo(ArrayList modoIni, ArrayList modoFim){
        n = new ArrayList<>(modoIni);
        this.valor = calcularCusto(modoFim);     //já calcula o custo do estado com relação ao final
    }
    
        
    //calcula o custo em relação ao estado final com base em uma tabela de custos pre-calculada
    public final int calcularCusto(ArrayList<Integer> modoFinal){
        int custoTotal = 0;
        int auxFinalIndex;
        int[][] tabelaCusto = {
                {0,1,2,1,2,3,2,3,4},
                {1,0,1,2,1,2,3,2,3},
                {2,1,0,3,2,1,4,3,2},
                {1,2,3,0,1,2,1,2,3},
                {2,1,2,1,0,1,2,1,2},
                {3,2,1,2,1,0,3,2,1},
                {2,3,4,1,2,3,0,1,2},
                {3,2,3,2,1,2,1,0,1},
                {4,3,2,3,2,1,2,1,0}
        };
        for(int i=0; i<this.n.size();i++){
            auxFinalIndex = modoFinal.indexOf(this.n.get(i));
            custoTotal =custoTotal + tabelaCusto[i][auxFinalIndex];
        }
        return custoTotal;
    }
    
    
    public int getValor(){
        return valor;
    }
    
    
    
    //imprime o estado em 3x3
    public void imprimirModo(){
        for (int i=0; i<n.size();i++){
            System.out.print(n.get(i)+ "  ");
            if (i%3==2){
                System.out.println();
            }
        }
    }
    
    
    //Encontra em qual posição está o Zero
    public int encontrarZero(){
        return n.indexOf(0);
    }
    
    ////move a casa que contem zero para CIMA se for possível, caso contrario retorna null
    public Modo moveUp(){
        Modo m = new Modo(this.n);
        int zeroPos = m.encontrarZero();
        if((zeroPos==0) || (zeroPos==1) || (zeroPos==2)){
            m = null;
        }else{
            m.n.set(zeroPos, m.n.get(zeroPos-3));
            m.n.set(zeroPos-3, 0);
        }
        return m;
    }
    
    ////move a casa que contem zero para BAIXO se for possível, caso contrario retorna null
    public Modo moveDown(){
        Modo m = new Modo(this.n);
        int zeroPos = m.encontrarZero();
        if((zeroPos==6) || (zeroPos==7) || (zeroPos==8)){
            m = null;
        }else{
            m.n.set(zeroPos, m.n.get(zeroPos+3));
            m.n.set(zeroPos+3, 0);
        }
        return m;
    }
    
    //move a casa que contem zero para a ESQUERDA se for possível, caso contrario retorna null
    public Modo moveLeft(){
    	Modo m = new Modo(this.n);
        int zeroPos = m.encontrarZero();
        if((zeroPos==0) || (zeroPos==3) || (zeroPos==6)){
            m = null;
        }else{
            m.n.set(zeroPos, m.n.get(zeroPos-1));
            m.n.set(zeroPos-1, 0);
        }
        return m;
    }
    
    //move a casa que contem zero para a DIREITA se for possível, caso contrario retorna null
    public Modo moveRight(){
        Modo m = new Modo(this.n);
        int zeroPos = m.encontrarZero();
        if((zeroPos==2) || (zeroPos==5) || (zeroPos==8)){
            m = null;
        }else{
            m.n.set(zeroPos, m.n.get(zeroPos+1));
            m.n.set(zeroPos+1, 0);
        }
        return m;
    }
    
    //metodo para gerar possíveis filhos e retornar lista de filhos
    public ArrayList<Modo> gFilhos(){
        ArrayList<Modo> filhos = new ArrayList<>();
        Modo m;
        m = this.moveUp();
        if (m != null){
            filhos.add(m);
        }
        m = this.moveDown();
        if (m != null){
            filhos.add(m);
        }
        m = this.moveLeft();
        if (m != null){
            filhos.add(m);
        }
        m = this.moveRight();
        if (m != null){
            filhos.add(m);
        }
        return filhos;
    }
    
    //executa n movimentos para embaralhar o tabuleiro
    public void randomize(int n){
        int zeroPos;
        int rand;
        
        for (int i=0; i<n;i++){
            zeroPos = this.encontrarZero();
            rand = (int)Math.floor(Math.random()*(4-1+1)+1);
            switch(rand){
                case 1 -> { //move up
                    if((zeroPos==0) || (zeroPos==1) || (zeroPos==2)){
                        
                    }else{
                        this.n.set(zeroPos, this.n.get(zeroPos-3));
                        this.n.set(zeroPos-3, 0);
                    }
                }
                case 2 -> { //move down
                    if((zeroPos==6) || (zeroPos==7) || (zeroPos==8)){
                        
                    }else{
                        this.n.set(zeroPos, this.n.get(zeroPos+3));
                        this.n.set(zeroPos+3, 0);
                    }
                }
                case 3 -> { //move left
                    if((zeroPos==0) || (zeroPos==3) || (zeroPos==6)){
                        
                    }else{ 
                        this.n.set(zeroPos, this.n.get(zeroPos-1));
                        this.n.set(zeroPos-1, 0);
                    }
                }
                
                case 4 -> {//move right
                    if((zeroPos==2) || (zeroPos==5) || (zeroPos==8)){
                        
                    }else{
                        this.n.set(zeroPos, this.n.get(zeroPos+1));
                        this.n.set(zeroPos+1, 0);
                    }
                }
            }
        }
    }
    
    /**
     * compara o objeto com outro recebido
     * 
     * @param o => recebe como parametro outro objeto tipo State para comparação de seus ArrayList
     * @return retorna true caso os dois Objetos tenham seus ArrayList com os mesmos valores
     */
    @Override
    public boolean equals(Object o){
        if (o == null) {
            return false;
        }
        if (o.getClass()!= this.getClass()){
            return false;
        }
        boolean result = false;
        Modo s = (Modo) o;
        for (int i=0; i<n.size(); i++){
            if (this.n.get(i).equals(s.n.get(i)) ){
                result = true;
            }else {
                return false;
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.n);
        return hash;
    }
    
    //Utilizado para a PriorityQueue
    public int compareTo(Modo e){
        return (this.getValor()-e.getValor());
    }

}
