package jogo;
import java.util.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.imageio.*;


import java.util.Random;


public abstract class Combatentes {
    protected int vida;
    protected String nome;
    protected Random random;
   

    public Combatentes(int vida, String nome){
        this.vida = vida;
        this.nome = nome;
        this.random = new Random();
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
