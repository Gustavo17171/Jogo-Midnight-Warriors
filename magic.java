import java.util.*;
import javax.swing.*;
import java.awt.*;
public abstract class jogador{
    private int turn = 1;
    private int qnt;
    int escolhidoL = (int)(Math.random()) * qnt;
    int escolhidoS = (int)(Math.random()) * qnt;
    
    protected int turno(){
        if (turn = 1){
            ataque(escolhidoL);
            turn = 0;
            turno();
            }
            else{
                ataque(escolhidoS);
                turn = 1;
                 turno();
        }
    }
}
