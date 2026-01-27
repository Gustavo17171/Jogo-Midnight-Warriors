import java.util.*;
import javax.swing.*;
import java.awt.*;


public abstract class jogador{
    private int turn = 1;
    private int qnt;

    public jogador(int turn, int qnt){
        this.turn = turn;
        this.qnt = qnt;
    }
    int escolhidoLuz = (int)(Math.random()) * qnt;
    int escolhidoSombra = (int)(Math.random()) * qnt;
    protected abstract void ataque(int x);
    protected void turno(){
        if (turn != 0){
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