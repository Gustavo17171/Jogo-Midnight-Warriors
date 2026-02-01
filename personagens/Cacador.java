package personagens;
import java.net.URL;
import java.awt.Image;
import javax.swing.*;


import jogo.Combatentes;

public abstract class Cacador extends Combatentes {

    private final double precisao;
    private final int ataque;
    private final int id;
    JLabel CacadorImagem;
    URL url;
    
    public Cacador (String nome, int vida,double precisao, int ataque,int id, int x, int y, String caminho){ 
        super(vida, nome, x, y, caminho);
        this.precisao = precisao;
        this.ataque = ataque;
        this.id = id;
        url = getClass().getResource(caminho);

        if(url != null){
            ImageIcon icon = new ImageIcon(url);
            CacadorImagem = new JLabel(icon);
        }
        else{
            System.out.println("Imagem nao encontrada: " + caminho);
            CacadorImagem = new JLabel("Imagem nao encontrada");
        }
        CacadorImagem.setName(nome);
        CacadorImagem.setBounds(x, y, 200, 200);
    }


    public boolean Furtividade() {
        if(Math.random() <= 0.1) {
            System.out.println(this.nome + id + " entrou em furtividade e evitou o proximo ataque!");
            return true;
        }
        return false;
    }

    @Override
    public void receberDano(int dano) {
        if (!Furtividade()) {
            this.vida -= dano;
        }
        else{
            dano = 0;
        }
    }



    @Override
    public void atacar(Combatentes alvo){  
        // habilidade unica: acerto critico     
            if (Math.random() <= precisao){
                System.out.println(this.nome + id + " acertou um Tiro Crítico!");
                alvo.receberDano(ataque * 2);    
            }
            else{
            alvo.receberDano(ataque);
            }

        }

    /*
    // ===== VARIAÇÕES =====
    */ 

        public static class Cacador_Luz extends Cacador {
            public Cacador_Luz(int id, int x, int y) {
                super("Caçador da Luz", 70, 0.3, 30, id, x, y, "/imagens/cacador_luz.png");
                CacadorImagem.putClientProperty( "id", "Cacador da Luz");
            }
        }

        public static class Cacador_Sombra extends Cacador {
            public Cacador_Sombra(int id, int x, int y) {
                super("Caçador da Sombra", 70, 0.3, 30, id, x, y, "/imagens/cacador_sombra.png");
                CacadorImagem.putClientProperty( "id", "Cacador da Sombra");
            }
        }
}
