package jogo;

import java.util.*;

import jogo.Combatentes;
import personagens.*;

public class Jogo {
    private String nomeFaccao; // "Aliança da Luz" ou "Horda das Sombras"
    private List<Combatentes> exercito; // Lista dinâmica para acomodar qualquer quantidade 
    
    // Construtor: Define a facção e recruta as tropas iniciais
    public Jogo(String nomeFaccao, int qtdGuardiao, int qtdMagos, int qtdArqueiros) {

        int total = qtdGuardiao +qtdMagos + qtdArqueiros;
        if(total >100){
            throw new IllegalArgumentException(
                "Máximo de 100 tropas"
            );
        }
       this.nomeFaccao = nomeFaccao;
       this.exercito = new ArrayList<>();
        // Recruta as unidades baseadas nos parâmetros passados
        recrutar("Guardião", qtdGuardiao);
        recrutar("Arcanista", qtdMagos);
        recrutar("Caçador", qtdArqueiros);
    }

    // Método auxiliar para criar as instâncias (Fábrica simples)
    private void recrutar(String tipo, int qtd) {
        for (int i = 0; i < qtd; i++) {

            switch (tipo) {
                case "Guardiao" ->
                    exercito.add(
                        nomeFaccao.contains("Luz")
                        ? new Guardiao.Guardiao_Luz()
                        : new Guardiao.Guardiao_Sombra()
                    );

                case "Arcanista" ->
                    exercito.add(
                        nomeFaccao.contains("Luz")
                        ? new Arcanista.ArcanistaLuz()
                        : new Arcanista.ArcanistaSombra()
                    );

                case "Cacador" ->
                    exercito.add(
                        nomeFaccao.contains("Luz")
                        ? new Cacador.Cacador_Luz()
                        : new Cacador.Cacador_Sombra()
                    );
            }
        }
    }
   public String getNomeFaccao() {
        return nomeFaccao;
    }

    public List<Combatentes> getVivos() {
        List<Combatentes> vivos = new ArrayList<>();
        for (Combatentes c : exercito) {
            if (c.estaVivo()) vivos.add(c);
        }
        return vivos;
    }

    public boolean temSoldadosVivos() {
        return !getVivos().isEmpty();
    }

    public boolean pertence(Combatentes c) {
        return exercito.contains(c);
    }

    public Combatentes alvoAleatorio() {
        List<Combatentes> vivos = getVivos();
        if (vivos.isEmpty()) return null;
        return vivos.get(new Random().nextInt(vivos.size()));
    }

    public void removerMortos() {
        exercito.removeIf(c -> !c.estaVivo());
    }

    public static void executarRodada(Jogo luz, Jogo sombra) {

        List<Combatentes> ordem = new ArrayList<>();
        ordem.addAll(luz.getVivos());
        ordem.addAll(sombra.getVivos());
        Collections.shuffle(ordem);

        for (Combatentes atacante : ordem) {

            if (!atacante.estaVivo()) continue;
            if (!luz.temSoldadosVivos() || !sombra.temSoldadosVivos()) break;

            Jogo inimigo = luz.pertence(atacante) ? sombra : luz;
            Combatentes alvo = inimigo.alvoAleatorio();

            if (alvo != null) {
                atacante.atacar(alvo);
                if (!alvo.estaVivo()) {
                    inimigo.removerMortos();
                }
            }
        }
    }
}