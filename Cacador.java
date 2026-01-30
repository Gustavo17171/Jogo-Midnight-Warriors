import java.util.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.imageio.*;

public abstract class Cacador extends Combatente {
    private double precisao;
    private int atqBasico;
    private int id;
    private int x;
    private int y;
    private String caminho;
    JLabel CacadorImagem;
    private URL url;

public Cacador (String nome,String caminho, double precisao, int vida, int atqBasico, int id, int x, int y){ 
    super(vida, nome);
    this.precisao = precisao;
    this.atqBasico = atqBasico;
    this.id = id;
    this.x = x;
    this.y = y;
    this.caminho = caminho;
    url = Cacador.class.getResource(caminho);

    if(url != null){
        ImageIcon icon = new ImageIcon(url);
        CacadorImagem = new JLabel(icon);
    }
    else{
        System.out.println("Imagem nao encontrada: " + caminho);
        CacadorImagem = new JLabel("Imagem nao encontrada");
    }
    CacadorImagem.setName(nome);
    CacadorImagem.setBounds(x, y, 200, 200);
}

@Override
public void atacar(Combatente alvo){  
    // habilidade unica: acerto critico     
        if (Math.random() < precisao){
            System.out.println(this.nome + " acertou um Tiro Crítico!");
            alvo.receberDano(atqBasico * 2);
        }
        else{
        alvo.receberDano(atqBasico);
        }
    }

/*
   teste de balanceamento double danoMedio = precisao * (atqBasico / precisao) + (1 - precisao) * atqBasico;
*/ 

public static class Cacador_Luz extends Cacador {
    public Cacador_Luz(int id, int x, int y){ 
        super("Cacador da Luz", "../imagens/AtiradorLuz.png", 0.3, 65, 30 , id, x, y);
        CacadorImagem.putClientProperty( "id", "Cacador da Luz");
    }  
}

public static class Cacador_Sombra extends Cacador {
    public Cacador_Sombra(int id, int x, int y){
        super("Cacador da Sombra", "../imagens/AtiradorSombra.png", 0.3, 65, 30, id, x, y);
    CacadorImagem.putClientProperty( "id", "Cacador_Sombra");   
    }
}

}

