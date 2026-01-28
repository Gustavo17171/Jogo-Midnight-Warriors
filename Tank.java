import java.util.*;
import javax.swing.*;
import java.awt.*;


public abstract class Tank {
    public enum Lado { LUZ, SOMBRA }

    private String Guardiao_de_ferro;
    private int vigor;
    private int vida;
    private int atqBasico;
    private int Bloqueio;
    private int identidade;
    private Lado lado;

    public Tank (String Guardiao_de_ferro, int vigor, int vida, int atqBasico, int Bloqueio, Lado lado, int identidade) {
        this.Guardiao_de_ferro = Guardiao_de_ferro;
        this.vigor = vigor;
        this.vida = vida;
        this.atqBasico = atqBasico;
        this.Bloqueio = Bloqueio;
        this.lado = lado;
        this.identidade = identidade;
    }

    public int ataque() {
        if (vigor <= 100) {
            vigor -= 100;
            return Bloqueio;
        } else {
            vigor += 50;
            return atqBasico;
        }
    }

    public void perderPV(int dano){
        this.vida -= dano;
        if (this.vida <= 0){
            if (this.lado == Lado.LUZ){
                System.out.println("Um Tank da Luz foi derrotado");
            } else {
                System.out.println("Um Tank da Sombra foi derrotado");
            }
        }
    }
}

class Tank_Luz extends Tank {
    public Tank_Luz(int identidade){
        super("Guardiao de ferro da Luz", 100, 120, 20, 300, Lado.LUZ, identidade);
    }
}

class Tank_Sombra extends Tank {
    public Tank_Sombra(int identidade){
        super("Guardiao de ferro da Sombra",100, 120, 20, 300, Lado.SOMBRA, identidade);
    }
}

