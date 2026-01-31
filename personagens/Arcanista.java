package personagens;

import jogo.Combatentes;

public abstract class Arcanista extends Combatentes {

    protected int mana;
    protected int ataque;
    protected int magia;

    public Arcanista(String nome, int vida, int ataque, int magia) {
        super(vida, nome);
        this.mana = 100;
        this.ataque = ataque;
        this.magia = magia;
    }

    @Override
    public void atacar(Combatentes alvo) {

        if (mana >= 30) {
            mana -= 30;
            System.out.println(nome + " lançou magia!");
            alvo.receberDano(magia);
        } else {
            mana += 20;
            alvo.receberDano(ataque);
        }
    }

    // ===== VARIAÇÕES =====
    public static class ArcanistaLuz extends Arcanista {
        public ArcanistaLuz() {
            super("Arcanista da Luz", 80, 15, 40);
        }
    }

    public static class ArcanistaSombra extends Arcanista {
        public ArcanistaSombra() {
            super("Arcanista da Sombra", 80, 15, 40);
        }
    }
}
