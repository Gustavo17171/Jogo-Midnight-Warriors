import java.util.*;
import javax.swing.*;
import java.awt.*;


public abstract class Tank {
    private String Guardião_de_ferro;
    private int vigor;
    private int vida;
    private int atqBasico;
    private int Bloqueio;
    private int identidade;

public Tank (String Guardião_de_ferro, int vigor, int vida, int atqBasico, int Bloqueio,int identidade) {
    this.Guardião_de_ferro = Guardião_de_ferro;
    this.vigor = vigor;
    this.vida = vida;
    this.atqBasico = atqBasico;
    this.Bloqueio = Bloqueio;
    this.identidade = identidade;
}

public int Ataque(){  
        vigor += 25;
        return atqBasico;
        }
    
public int Habilidade_Unica() {
    if (vigor >= 30) { 
        vigor -= 30;  
        return Bloqueio;
    }
    else { vigor += 25;            
        return atqBasico;
    }
}

}

class Tank_Luz extends Tank {
    public Tank_Luz(int identidade){
    super("Guardiao de ferro da Luz", 100, 200, 20, 300,identidade);
    }    
}

class Tank_Sombra extends Tank {
    public Tank_Sombra(int identidade){    
    super("Guardiao de ferro da Sombra",100, 200, 20, 300,identidade);
    }
}