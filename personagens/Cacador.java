package personagens;
import java.net.URL;
import java.awt.Image;
import javax.swing.*;


import jogo.Combatentes;

public abstract class Cacador extends Combatentes {

    private final double precisao;
    private final int ataque;
    private final int id;
    
    private URL url;
    
    public Cacador (String nome, int vida,double precisao, int ataque,int id, int x, int y, String caminho){ 
        super(vida, nome, x, y, caminho);
        this.precisao = precisao;
        this.ataque = ataque;
        this.id = id;
         imagem = new JLabel(new ImageIcon(getClass().getResource(caminho)));
       imagem.setBounds(x, y, 200, 200);
    }

    public JLabel getImagem() {
        return imagem;
    }
    
    public boolean Furtividade() {
        if(Math.random() <= 0.1) {
            System.out.println(this.nome + id + " entrou em furtividade e evitou o proximo ataque!" + " Vida restante: " + this.vida);
            return true;
        }
        return false;
    }

    @Override
    public void receberDano(int dano) {
        if (!Furtividade()) {
            this.vida -= dano;
            if(this.vida < 0) {
                this.vida = 0;
            }
            System.out.println(this.nome + id + " recebeu " + dano + " de dano. Vida restante: " + this.vida);
        }
        else{
            dano = 0;
        }
    }



    @Override
    public void atacar(Combatentes alvo){  
        // habilidade unica: acerto critico     
            if (Math.random() <= precisao){
                System.out.println(this.nome + id + " acertou um Tiro Crítico e causou " + (ataque * 2) + " de dano!");
                alvo.receberDano(ataque * 2);    
            }
            else{ 
                System.out.println(this.nome + id + " atacou e causou " + ataque + " de dano.");
                alvo.receberDano(ataque);
            }

        }

    /*
    // ===== VARIAÇÕES =====
    */ 

        public static class Cacador_Luz extends Cacador {
            public Cacador_Luz(int id, int x, int y) {
                super("Caçador da Luz", 70, 0.3, 30, id, x, y, "/imagens/cacador_luz.png");
                imagem.putClientProperty( "id", "Cacador da Luz");
            }
        }

        public static class Cacador_Sombra extends Cacador {
            public Cacador_Sombra(int id, int x, int y) {
                super("Caçador da Sombra", 70, 0.3, 30, id, x, y, "/imagens/cacador_sombra.png");
                imagem.putClientProperty( "id", "Cacador da Sombra");
            }
        }
}
