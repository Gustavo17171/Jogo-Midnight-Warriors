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
    private int id;

    JLabel GuardiaoImagem;
    private URL url;

    public Guardiao (String nome, String caminho, int vigor, int vida,int id, int atqBasico, int x, int y){
        super(vida, nome,x, y, caminho);
        this.vigor = vigor;
        this.id = id;
        this.atqBasico = atqBasico;
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

    public void Bencao() {
        if(Math.random() < 0.05) {
            if(this.vida < 120){
                this.vida = 120;
            }
            else {
                this.vida += 20;
            }
        }
    }

    public void Defender(int dano) {
        if(Math.random() < 0.4){
            if (this.vigor >= 100){
            this.vigor = 0;
                dano = 0;
                this.vida += 10;
            System.out.println(this.nome + " utilizou todo seu vigor para bloquear o ataque e ganhou mais resistência!");
            }
            else if (this.vigor > 0){
            
                if(this.vigor >= dano){
                    this.vigor -= dano;
                        dano = 0;
                    System.out.println(this.nome + "Utilizou seu Vigor para bloquear o ataque!");
                }
                else { 
                    dano -= this.vigor;
                    this.vigor = 0;
                    System.out.println(this.nome + "utilizou seu vigor para bloquear parte do ataque!");
                }

            }
        }
        else {
            System.out.println(this.nome + " não conseguiu usar seu vigor para defender o ataque.");
        }
    }    
    @Override
    public void receberDano(int dano){
        // habilidade unica: vigor
        Bencao();
        
        Defender(dano);
        
        this.vida -= dano;
            
                
        if(this.vida < 0){
            this.vida = 0;
        }
            System.out.println(this.nome + " recebeu " + dano + " pontos de dano. Vida restante: " + this.vida);
        
        if(this.vida == 0  ){
            System.out.println(this.nome + " foi derrotado!");
        }
            
    }

        
    @Override
    public void atacar(Combatente alvo){  
        this.vigor += 40;
        alvo.receberDano(this.atqBasico);
    }

    public static class Guardiao_Luz extends Guardiao {
        public Guardiao_Luz(int id, int x, int y){
        super("Guardiao da Luz", "/imagens/Guardiao_Luz.png", 100, 120,id, 20, x, y);
        GuardiaoImagem.putClientProperty("id", "Guardiao_Luz");
        }    
    }

    public static class Guardiao_Sombra extends Guardiao {
        public Guardiao_Sombra(int id, int x, int y){    
        super("Guardiao da Sombra", "/imagens/Guardiao_Sombra.png", 100, 120, id, 20, x, y);
        GuardiaoImagem.putClientProperty("id", "Guardiao_Sombra");    
        }
    }

}
