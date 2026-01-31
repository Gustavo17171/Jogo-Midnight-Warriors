import java.util.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.imageio.*;

// ABAIXO OS MAGOS TANKS NO TOPO!!!!!
public abstract class Arcanista extends Combatente {
    private int mana;
    private int atqBasico;
    private int magia;
    private int id;
    JLabel ArcanistaImagem;
    private URL url;

    public Arcanista(String nome,String caminho, int mana, int vida, int atqB, int magia,int id, int x, int y) {
     super(vida, nome, x, y, caminho);
        this.mana = mana;
        this.atqBasico = atqB;
        this.magia = magia;
        this.id = id;
        url = Arcanista.class.getResource(caminho);

        if(url != null){
            ImageIcon icon = new ImageIcon(url);
            ArcanistaImagem = new JLabel(icon);
        }
        else{
            System.out.println("Imagem nao encontrada: " + caminho);
            ArcanistaImagem = new JLabel("Imagem nao encontrada");
        }
    }

    public void Ultimate(Combatente alvo) {
        if(Math.random() < 0.05) {
            mana = 0;
            alvo.receberDano(200);
            System.out.println(this.nome + "Recebeu um poder oculto e causou 200 de dano no alvo!");
            

        }
    }
    
    @Override
    public void atacar(Combatente alvo){ 
        //Habilidade Unica
        if (mana >= 30) {
            mana -= 30;
            alvo.receberDano(this.magia);
        }
        else {
            mana += 20;
            alvo.receberDano(this.atqBasico);
        }
    }

    public static class ArcanistaLuz extends Arcanista {
    public ArcanistaLuz(int id, int x, int y) {
        super("Arcanista da Luz", "../imagens/ArcanistaLuz.png", 100, 90, 15, 40, id, x, y);
        ArcanistaImagem.putClientProperty("id", "Arcanista_Luz");
    }
}

public static class ArcanistaSombra extends Arcanista {
    public ArcanistaSombra(int id, int x, int y) {
        super("Arcanista da Sombra", "../imagens/ArcanistaSombra.png", 100, 90, 15, 40, id, x, y);
        ArcanistaImagem.putClientProperty("id", "Arcanista_Sombra");
    }   
}

}

