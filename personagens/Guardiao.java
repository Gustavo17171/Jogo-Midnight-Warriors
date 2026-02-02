package personagens;
import java.net.URL;
import java.awt.Image;
import javax.swing.*;

import jogo.Combatentes;

// TANK FRACO MAGOS NO TOPO DO REINO!!!!!!!!!
public  abstract class Guardiao extends Combatentes {

    private int vigor;
    private final int ataque;
    private final int id;
    JLabel GuardiaoImagem;
    URL url;

   public Guardiao(String nome, int vida, int ataque, int id, int x, int y, String caminho) {
        super(vida, nome, x, y, caminho);
        this.id = id;
        this.vigor = 100;
        this.ataque = ataque;

        url = getClass().getResource(caminho);

        if(url != null){
            ImageIcon icon = new ImageIcon(url);
            GuardiaoImagem = new JLabel(icon);
        }
        else{
            System.out.println("Imagem nao encontrada: " + caminho);
            GuardiaoImagem = new JLabel("Imagem nao encontrada");
        }
        GuardiaoImagem.setName(nome);
        GuardiaoImagem.setBounds(x, y, 200, 200);

    }

    public void Bencao() {
        if(Math.random() <= 0.05) {
            System.out.println(this.nome + id + " recebeu uma bênção e recuperou vida!");
            
            if(this.vida < 120){
                this.vida = 120;
            }
            else {
                this.vida += 20;
            }
        }
    }

    public void Defender(int dano) {
        if(Math.random() <= 0.4){
            if (this.vigor >= 100){
            this.vigor = 0;
                dano = 0;
                this.vida += 10;
            System.out.println(this.nome + id +  " utilizou todo seu vigor para bloquear o ataque e ganhou mais resistência!" + " Vida restante: " + this.vida);
            }
            else if (this.vigor > 0){
            
                if(this.vigor >= dano){
                    this.vigor -= dano;
                    dano = 0;
                    System.out.println(this.nome + id + "Utilizou seu Vigor para bloquear o ataque!" + " Vida restante: " + this.vida);
                }
                else { 
                    dano -= this.vigor;
                    this.vigor = 0;
                    
                    System.out.println(this.nome + id + "utilizou seu vigor para bloquear parte do ataque!" + " Vida restante: " + this.vida);
                    this.vida -= dano;
                }

            }
        }
        else {
            System.out.println(this.nome + id + " não conseguiu usar seu vigor para defender o ataque.");
            this.vida -= dano;
        }

        if(this.vida < 0){
                    this.vida = 0;
                }

        System.out.println(this.nome + id + " recebeu " + dano + " pontos de dano. Vida restante: " + this.vida);
                
        if(this.vida == 0  ){
            System.out.println(this.nome + id + " foi derrotado!");
        }

    }    
    @Override
    public void receberDano(int dano){
        // habilidade unica: vigor
        Bencao();
        
        Defender(dano);
            
    }

        
    @Override
    public void atacar(Combatentes alvo){  
        this.vigor += 40;
        System.out.println(this.nome + id + " atacou e causou " + this.ataque + " de dano.");
        alvo.receberDano(this.ataque);
    }

     // ===== VARIAÇÕES =====
    public static class Guardiao_Luz extends Guardiao {
        public Guardiao_Luz(int id, int x, int y) {
            super("Guardião da Luz", 120, 20,id, x, y, "../imagens/guardiao_luz.png");
            GuardiaoImagem.putClientProperty("id", "Guardiao_Luz");
        }
    }

    public static class Guardiao_Sombra extends Guardiao {
        public Guardiao_Sombra(int  id, int x, int y) {
            super("Guardião da Sombra", 120, 20, id, x, y, "../imagens/guardiao_sombra.png");
            GuardiaoImagem.putClientProperty("id", "Guardiao_Sombra");
        }
    }
}

