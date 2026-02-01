package jogo;
import java.util.Random;

import javax.swing.JLabel;

import javax.swing.JLabel;


public abstract class Combatentes {
    protected int vida;
    protected String nome;
    protected Random random;
    private int x;
    private int y;
    private final String caminho;
    protected JLabel imagem;

    public Combatentes(int vida, String nome,int x, int y, String caminho){
         this.x = x;
         this.y = y;
         this.caminho = caminho;
        this.vida = vida;
        this.nome = nome;
        this.random = new Random();
    }
     public JLabel getImagem() {
        return imagem;
    }

    public abstract void atacar(Combatentes alvo);

    public void receberDano(int dano){
        this.vida -= dano;
        if(this.vida < 0) vida = 0;
        
       System.out.println(
            nome + " recebeu " + dano +
            " de dano | Vida: " + vida
        );

        if(this.vida == 0  ){
            System.out.println(this.nome + "foi derrotado!");
        }
    }
    public boolean estaVivo(){
        return this.vida > 0;
    }

    public String getNome(){
        return nome;
    }
}
