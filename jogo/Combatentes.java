package jogo;
import java.util.Random;
import javax.swing.JLabel;

public abstract class Combatentes {
    protected int vida;
    protected String nome;
    protected Random random;
    protected int x;
    protected int y;
    private final String caminho;
    protected JLabel imagem;
    protected int xInicial;
    protected int yInicial;
    // ================= CONTRUTOR COMBATENTES  =================
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

    // ===== MÉTODOS DE COMBATE =====   
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
        this.x = x;
        this.y = y;
        imagem.setLocation(x, y);
    }

    public void setPosicaoOriginal(int x, int y) {
        this.xInicial = x;
        this.yInicial = y;
    }

    public void voltarPosicao() {
        moverPara(xInicial, yInicial);
    }

    public JLabel getImagem() {
        return imagem;
    }
    // gets de posicao da animação
    public int getX() { return imagem.getX(); }
    public int getY() { return imagem.getY(); }
}
