import java.util.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.imageio.*;

// TANK FRACO MAGOS NO TOPO DO REINO!!!!!!!!!
public  abstract class Guardiao extends Combatente {
    private int vigor;
    private int atqBasico;
    private int Bloqueio;
    private int id;
    private int x;
    private int y;
    private String caminho;
    JLabel GuardiaoImagem;
    private URL url;

public Guardiao (String nome, String caminho, int vigor, int vida,int id, int atqBasico, int Bloqueio, int x, int y){
    super(vida, nome);
    this.vigor = vigor;
    this.id = id;
    this.Bloqueio = Bloqueio;
    this.atqBasico = atqBasico;
    this.x = x;
    this.y = y;
    this.caminho = caminho;
    url = Guardiao.class.getResource(caminho);

    if(url != null){
        ImageIcon icon = new ImageIcon(url);
        GuardiaoImagem = new JLabel(icon);
    }
    else{
        System.out.println("Imagem nao encontrada: " + caminho);
        GuardiaoImagem = new JLabel("Imagem nao encontrada");
    }
    GuardiaoImagem.setName(nome);
    GuardiaoImagem.setBounds(x, y, 200, 200);
}

public void Turno(){}

@Override
public void receberDano(int dano){
    // habilidade unica: vigor
    if(Math.random() < 0.4){
        if (this.vigor >= 100){
         this.vigor = 0;
         System.out.println(this.nome + " utilizou seu vigor para bloquear totalmente o ataque!");
        }
        else if (this.vigor > 0){
           
            if(this.vigor < dano){
                this.vigor = 0;
            }
            else { 
                this.vigor -= dano;
            }
            this.vida -= (dano - this.vigor);
            System.out.println(this.nome + "utilizou seu vigor para bloquear parte do ataque!");
            
         if(this.vida < 0){
            this.vida = 0;
            }
            System.out.println(this.nome + " recebeu " + dano + " pontos de dano. Vida restante: " + this.vida);
            if(this.vida == 0  ){
             System.out.println(this.nome + " foi derrotado!");
            }

              }
    }
    else {
        this.vida -= dano;
        if(this.vida < 0){
            this.vida = 0;
        }
        System.out.println(this.nome + " recebeu " + dano + " pontos de dano. Vida restante: " + this.vida);
        if(this.vida == 0  ){
            System.out.println(this.nome + " foi derrotado!");
        }
    }
}
@Override
public void atacar(Combatente alvo){  
    this.vigor += 40;
    alvo.receberDano(this.atqBasico);
}

public static class Guardiao_Luz extends Guardiao {
    public Guardiao_Luz(int id, int x, int y){
    super("Guardiao da Luz", "/imagens/Guardiao_Luz.png", 100, 120,id, 0, 20, x, y);
    GuardiaoImagem.putClientProperty("id", "Guardiao_Luz");
    }    
}

public static class Guardiao_Sombra extends Guardiao {
    public Guardiao_Sombra(int id, int x, int y){    
    super("Guardiao da Sombra", "/imagens/Guardiao_Sombra.png", 100, 120, id, 20, 0, x, y);
    GuardiaoImagem.putClientProperty("id", "Guardiao_Sombra");    
    }
}

}
