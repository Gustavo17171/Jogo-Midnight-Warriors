import java.util.*;
import javax.swing.*;
import java.awt.*;


public abstract class Atirador {
    private String cacador;
    private double precisao;
    private int vida;
    private int atqBasico;
    private int identidade;

public Atirador (String cacador, double precisao, int vida, int atqBasico, int identidade) {
    this.cacador = cacador;
    this.precisao = precisao;
    this.vida = vida;
    this.atqBasico = atqBasico;
    this.identidade = identidade;
}

public int ataque(){  
    // habilidade unica: acerto critico     
        if (Math.random() < precisao){
            return (atqBasico * 2) ;
        }
        else{
        return atqBasico;
        }
    }

/*
   teste de balanceamento double danoMedio = precisao * (atqBasico / precisao) + (1 - precisao) * atqBasico;
*/ 

}
class Atirador_Luz extends Atirador {
    public Atirador_Luz(int identidade){ 
    super("Cacador da Luz",0.3, 65, 30 , identidade);
    }  
}

class Atirador_Sombra extends Atirador {
    public Atirador_Sombra(int identidade){
    super("Cacador da Sombra",0.3, 65, 30, identidade);
    }
}
