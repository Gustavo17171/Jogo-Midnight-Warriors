import java.util.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.imageio.*;


public abstract class Combatente {
    protected int vida;
    protected String nome;
    protected Random random;

    public Combatente(int vida, String nome){
        this.vida = vida;
        this.nome = nome;
        this.random = new Random();
    }

    public abstract void atacar(Combatente alvo);

    public void receberDano(int dano){
        this.vida -= dano;
        if(this.vida < 0){
            this.vida = 0;
        }
        System.out.println(this.nome + "recebeu" + dano + "pontos de dano. Vida restante: " + this.vida);
        if(this.vida == 0  ){
            System.out.println(this.nome + "foi derrotado!");
        }
    }
    public boolean estaVivo(){
        return this.vida > 0;
    }

}
