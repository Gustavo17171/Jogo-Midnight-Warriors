import java.util.*;
import javax.swing.*;
import java.awt.*;


public abstract class Mago {
    private String Arcanista;
    private int mana;
    private int vida;
    private int atqBasico;
    private int magia;
    private int identidade;

    public Mago(String Arcanista, int mana, int vida, int atqB, int magia,int identidade) {
        this.Arcanista = Arcanista;
        this.mana = mana;
        this.vida = vida;
        this.atqBasico = atqB;
        this.magia = magia;
        this.identidade = identidade;
    }

    public int Ataque() {
        //Habilidade Unica
        if (mana >= 30) {
            mana -= 30;
            return magia;
        }
        else {
            mana += 20;
            return atqBasico;

        }
    }

}

class MagoLuz extends Mago {
    public MagoLuz(int identidade) {
        super("Arcanista da Luz", 100, 75, 15, 40, identidade);
    }
}

class MagoSombra extends Mago {
    public MagoSombra(int identidade) {
        super("Arcanista da Sombra", 100, 75, 15, 40, identidade);
    }
}