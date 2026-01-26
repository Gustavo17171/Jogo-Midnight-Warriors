import java.util.*;
import javax.swing.*;
import java.awt.*;


public abstract class jogador{
    private int turn = 1;
    private int qnt;

    int escolhidoLuz = (int)(Math.random()) * qnt;
    int escolhidoSombra = (int)(Math.random()) * qnt;
    
    protected int turno(){
        if (turn = 1){
            ataque(escolhidoLuz);
            turn = 0;
            turno();
            }
        else{
            ataque(escolhidoSombra);
            turn = 1;
            turno();
            }
    }
}