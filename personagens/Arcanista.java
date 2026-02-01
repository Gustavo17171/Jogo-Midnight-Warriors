package personagens;
import java.net.URL;
import java.awt.Image;
import javax.swing.*;
import jogo.Combatentes;

// ABAIXO OS MAGOS TANKS NO TOPO!!!!!
public abstract class Arcanista extends Combatentes {

    protected int mana;
    protected final int ataque;
    protected final int magia;
    private final int id;
    JLabel ArcanistaImagem;
    URL url;

    public Arcanista(String nome, int vida, int ataque, int magia, int id, int x, int y, String caminho) {
        super(vida, nome, x, y, caminho);

        this.id = id;
        this.mana = 100;
        this.ataque = ataque;
        this.magia = magia;
        
        url = getClass().getResource(caminho);

        if(url != null){
            ImageIcon icon = new ImageIcon(url);
            ArcanistaImagem = new JLabel(icon);
        }
        
        else{
            System.out.println("Imagem nao encontrada: " + caminho);
            ArcanistaImagem = new JLabel("Imagem nao encontrada");
        }
        
        ArcanistaImagem.setName(nome);
        ArcanistaImagem.setBounds(x, y, 200, 200);
    }


    public void Ultimate(Combatentes alvo) {
        if(Math.random() <= 0.05) {
            mana = 0;
            alvo.receberDano(200);
            System.out.println(this.nome + id + "  Recebeu um poder oculto e causou 200 de dano no alvo!");
            

        }
    }
    
    @Override
    public void atacar(Combatentes alvo){ 
        //Habilidade Unica
  
        if (mana >= 30) {
            mana -= 30;
            System.out.println(nome + id + " lançou magia!");
            alvo.receberDano(magia);
        } else {
            mana += 20;
            alvo.receberDano(ataque);
        }
    }

    // ===== VARIAÇÕES =====
    public static class ArcanistaLuz extends Arcanista {
        
        public ArcanistaLuz(int id, int x, int y) {
            super("Arcanista da Luz", 80, 15, 40, id, x, y, "/imagens/arcanista_luz.png");
            ArcanistaImagem.putClientProperty("id", "Arcanista_Luz");
        }
    }

    public static class ArcanistaSombra extends Arcanista {
        public ArcanistaSombra(int id, int x, int y) {
            super("Arcanista da Sombra", 80, 15, 40, id, x, y, "/imagens/arcanista_sombra.png");
            ArcanistaImagem.putClientProperty("id", "Arcanista_Sombra");
        }
    }

}
