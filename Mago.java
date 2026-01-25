import java.util.*;
import javax.swing.*;
import java.awt.*;


public abstract class Mago {
    private String mage;
    private int mana;
    private int vida;
    private int atqBasico;
    private int magia;
    private int identidade;

    public Mago(String mage, int ident, int mana, int vida, int atqB, int magia) {
        this.mage = mage;
        this.mana = mana;
        this.vida = vida;
        this.atqBasico = atqB;
        this.magia = magia;
        this.identidade = ident;
    }
    public int Ataque() {
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
    public MagoLuz(int ident) {
        super("Mago da Luz", ident, 100, 75, 15, 40);
    }
}

class MagoSombra extends Mago {
    public MagoSombra(int ident) {
        super("Mago da Sombra", ident, 100, 75, 15, 40);
    }
}
