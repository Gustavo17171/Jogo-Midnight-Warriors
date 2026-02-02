package jogo;
import java.util.Random;

import javax.swing.JLabel;


public abstract class Combatentes {
    protected int vida;
    protected String nome;
    protected Random random;
    private int x;
    private int y;
    private final String caminho;
    protected JLabel imagem;
    protected int xInicial;
    protected int yInicial;

    public Combatentes(int vida, String nome,int x, int y, String caminho){
        this.x = x;
        this.y = y;
        this.caminho = caminho;
        this.vida = vida;
        this.nome = nome;
        this.random = new Random();
        this.xInicial = x;
        this.yInicial = y;

    }

    
    public abstract void atacar(Combatentes alvo);
    public void receberDano(int dano) {
        vida -= dano;
        if (vida < 0) vida = 0;
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    // ===== POSIÇÃO =====
    public void moverPara(int x, int y) {
        imagem.setLocation(x, y);
    }
    public void setPosicaoOriginal(int x, int y) {
        this.xInicial = x;
        this.yInicial = y;
    }

    public void voltarPosicao() {
        imagem.setLocation(xInicial, yInicial);
    }

    public JLabel getImagem() {
        return imagem;
    }

    
    }
