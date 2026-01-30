import java.util.*;

public class Jogador {
    private String nomeFaccao; // "Aliança da Luz" ou "Horda das Sombras"
    private List<Combatente> exercito; // Lista dinâmica para acomodar qualquer quantidade 
    private enum nomeFaccao {LUZ, SOMBRAS};
    
    // Construtor: Define a facção e recruta as tropas iniciais
    public Jogador(String nomeFaccao, int qtdGuerreiros, int qtdMagos, int qtdArqueiros) {
        this.nomeFaccao = nomeFaccao;
        this.exercito = new ArrayList<>();
        
        int total = qtdGuerreiros +qtdMagos + qtdArqueiros;
        if(total >100){
            System.out.println("Numero maximo de tropas atingido, o maximo e 100 tropas por jogador");
            return;
        }
        // Recruta as unidades baseadas nos parâmetros passados
        recrutarUnidades("Guardião", qtdGuerreiros);
        recrutarUnidades("Arcanista", qtdMagos);
        recrutarUnidades("Caçador", qtdArqueiros);
    }

    // Método auxiliar para criar as instâncias (Fábrica simples)
    private void recrutarUnidades(String tipo, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            if (tipo.equals("Guardião")) {
                // Adiciona um Tanque (Vigor, Bloqueio) [cite: 32]
                exercito.add(new Guardiao(nomeFaccao + " Guardião " + (i+1))); 
            } else if (tipo.equals("Arcanista")) {
                // Adiciona um Mago (Mana, Magia/Físico) [cite: 36]
                exercito.add(new Arcanista(nomeFaccao + " Arcanista " + (i+1)));
            } else if (tipo.equals("Caçador")) {
                // Adiciona um Atirador (Crítico, Passiva) [cite: 40]
                exercito.add(new Cacador(nomeFaccao + " Caçador " + (i+1)));
            }
        }
    }
    public void realizarTurno(Jogador inimigo){
        System.out.println("\n--- Turno da " + this.nomeFaccao + "---");
        for(Combatente soldado : this.exercito){
            if(soldado.estaVivo() && inimigo.temSoldadosVivos()){
                Combatente alvo = inimigo.getSoldadoAleatorio();
                soldado.atacar(alvo);
            }
        }

    }
    public boolean temSoldadosVivos() {
        return !exercito.isEmpty();
    }

    public void removerMortos() {
        exercito.removeIf(soldado -> !soldado.estaVivo());
    }

    public Combatente getSoldadoAleatorio() {
        if (exercito.isEmpty()) return null;
        Random random = new Random();
        return exercito.get(random.nextInt(exercito.size()));
    }
    public String getNomeFaccao() {
        return nomeFaccao;
    }
}