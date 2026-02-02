package jogo;
import ui.Arena;
import jogo.Combatentes;
import java.util.*;
import personagens.*;
import javax.swing.JLabel;

public class Jogo {
    private String nomeFaccao; // "Aliança da Luz" ou "Horda das Sombras"
    private List<Combatentes> exercito; // Lista dinâmica para acomodar qualquer quantidade 
    
    // ================= CONSTRUTOR DE JOGO =================
    public Jogo(String nomeFaccao, int qtdGuardiao, int qtdMagos, int qtdArqueiros) {

        int total = qtdGuardiao +qtdMagos + qtdArqueiros;
        if(total >100){
            throw new IllegalArgumentException(   "Máximo de 100 tropas");
        }
       this.nomeFaccao = nomeFaccao;
       this.exercito = new ArrayList<>();
        
        recrutar("Guardiao", qtdGuardiao);
        recrutar("Arcanista", qtdMagos);
        recrutar("Cacador", qtdArqueiros);
    }
    public List<Combatentes> getVivos() {
        List<Combatentes> vivos = new ArrayList<>();
        for (Combatentes c : exercito) {
            if (c.estaVivo()) vivos.add(c);
        }
        return vivos;
    }

    // ================= METODOS AUXILIARES =================
    private void recrutar(String tipo, int qtd) {
        for (int i = 0; i < qtd; i++) {

            switch (tipo) {
                case "Guardiao" ->
                    exercito.add( nomeFaccao.contains("Luz")? new Guardiao.Guardiao_Luz(i, 0, 0) : new Guardiao.Guardiao_Sombra(i,500,200) );

                case "Arcanista" ->
                    exercito.add( nomeFaccao.contains("Luz")? new Arcanista.ArcanistaLuz(i, 0 , -0): new Arcanista.ArcanistaSombra(i,0,0) );

                case "Cacador" ->
                    exercito.add( nomeFaccao.contains("Luz") ? new Cacador.Cacador_Luz(i, 0 , 0) : new Cacador.Cacador_Sombra(i,0,0)  );
            }
        }
    }


   public String getNomeFaccao() {
        return nomeFaccao;
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

    public  void removerMortos() {
        exercito.removeIf(c -> !c.estaVivo());
        
    }

    // ================= RODA O JOGO =================
    public static void executarRodada(Jogo luz, Jogo sombra) {

    if (!luz.temSoldadosVivos() || !sombra.temSoldadosVivos()) return;

    Combatentes atacanteLuz = luz.alvoAleatorio();
    Combatentes atacanteSombra = sombra.alvoAleatorio();

    System.out.println(" Nova rodada!");
    System.out.println("Luz ataca primeiro!");

    atacanteLuz.atacar(atacanteSombra);

    if (atacanteSombra.estaVivo()) {
        System.out.println("Sombra revida!");
        atacanteSombra.atacar(atacanteLuz);
    } else {
        System.out.println("Um combatente da Sombra caiu!");
    }


    atacanteLuz.voltarPosicao();
    atacanteSombra.voltarPosicao();
    luz.removerMortos();
    sombra.removerMortos();
}

    
}