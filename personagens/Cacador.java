package personagens;

import jogo.Combatentes;

public abstract class Cacador extends Combatentes {

    protected double precisao;
    protected int ataque;

    public Cacador(String nome, int vida, int ataque, double precisao) {
        super(vida, nome);
        this.ataque = ataque;
        this.precisao = precisao;
    }

    @Override
    public void atacar(Combatentes alvo) {
        if (Math.random() < precisao) {
            System.out.println(nome + " acertou um CRÍTICO!");
            alvo.receberDano(ataque * 2);
        } else {
            alvo.receberDano(ataque);
        }
    }

    // ===== VARIAÇÕES =====
    public static class Cacador_Luz extends Cacador {
        public Cacador_Luz() {
            super("Caçador da Luz", 70, 30, 0.3);
        }
    }

    public static class Cacador_Sombra extends Cacador {
        public Cacador_Sombra() {
            super("Caçador da Sombra", 70, 30, 0.3);
        }
    }
}
