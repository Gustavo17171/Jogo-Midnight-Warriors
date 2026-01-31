package personagens;

import jogo.Combatentes;

public abstract class Guardiao extends Combatentes {

    protected int vigor;
    protected int ataque;

    public Guardiao(String nome, int vida, int ataque) {
        super(vida, nome);
        this.vigor = 100;
        this.ataque = ataque;
    }

    @Override
    public void receberDano(int dano) {

        // habilidade: bloquear com vigor
        if (vigor > 0) {
            int bloqueio = Math.min(vigor, dano);
            vigor -= bloqueio;
            dano -= bloqueio;
            System.out.println(nome + " bloqueou " + bloqueio + " com vigor");
        }

        super.receberDano(dano);
    }

    @Override
    public void atacar(Combatentes alvo) {
        vigor += 20;
        alvo.receberDano(ataque);
    }

    // ===== VARIAÇÕES =====
    public static class Guardiao_Luz extends Guardiao {
        public Guardiao_Luz() {
            super("Guardião da Luz", 120, 20);
        }
    }

    public static class Guardiao_Sombra extends Guardiao {
        public Guardiao_Sombra() {
            super("Guardião da Sombra", 120, 20);
        }
    }
}
