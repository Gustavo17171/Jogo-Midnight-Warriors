import java.util.*;
import javax.swing.*;
import java.awt.*;


public abstract class Tank {
    private String Guardiao_de_ferro;
    private int vigor;
    private int vida;
    private int atqBasico;
    private int Bloqueio;
    private int identidade;

public Tank (String Guardiao_de_ferro, int vigor, int vida, int atqBasico, int Bloqueio,int identidade) {
    this.Guardião_de_ferro = Guardiao_de_ferro;
    this.vigor = vigor;
    this.vida = vida;
    this.atqBasico = atqBasico;
    this.Bloqueio = Bloqueio;
    this.identidade = identidade;
}
    
public int ataque() {

    if (vigor <= 100) { 
    vigor -= 100;  
    return Bloqueio;
    }
    else { vigor += 50;            
    return atqBasico;
}

class Tank_Luz extends Tank {
    public Tank_Luz(int identidade){
    super("Guardiao de ferro da Luz", 100, 120, 20, 300,identidade);
    }    
}

class Tank_Sombra extends Tank {
    public Tank_Sombra(int identidade){    
    super("Guardiao de ferro da Sombra",100, 120, 20, 300,identidade);
    }
}
