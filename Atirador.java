import java.util.*;
import javax.swing.*;
import java.awt.*;


public abstract class Atirador {
    public enum Lado { LUZ, SOMBRA }
    private String cacador;
    private double precisao;
    private int vida;
    private int atqBasico;
    private Lado lado;
    private int identidade;

public Atirador (String cacador, double precisao, int vida, int atqBasico, Lado lado, int identidade) {
    this.cacador = cacador;
    this.precisao = precisao;
    this.lado = lado;
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


      public void perderPV(int dano){
        this.vida -= dano;
        if (this.vida <= 0){
            if (this.lado == Lado.LUZ){
                System.out.println("Um Atirador da Luz foi derrotado");
            } else {
                System.out.println("Um Atirador da Sombra foi derrotado");
            }
        }
    }
/*
   teste de balanceamento double danoMedio = precisao * (atqBasico / precisao) + (1 - precisao) * atqBasico;
*/ 

}
class Atirador_Luz extends Atirador {
    public Atirador_Luz(int identidade){ 
    super("Cacador da Luz",0.3, 65, 30, Lado.LUZ, identidade);
    }  
}

class Atirador_Sombra extends Atirador {
    public Atirador_Sombra(int identidade){
    super("Cacador da Sombra",0.3, 65, 30, Lado.SOMBRA, identidade);
    }
}
