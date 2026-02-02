package ui;

import audio.MusicManager;
import javax.swing.*;
import java.net.URL;
import personagens.Guardiao;
import personagens.Cacador;
import personagens.Guardiao;
import jogo.Combatentes;
import jogo.Jogo;


import java.awt.*;
public class Arena extends JPanel {
    private JLabel fundo;
    private final TelaPrincipal frame;
    private Timer timerBatalha;
    private final Jogo equipeLuz;
    private final Jogo equipeSombra;
    private JLabel lblLuz;
    private JLabel lblSombra;

    public Arena( TelaPrincipal frame, int gL, int mL, int aL, int gS, int mS, int aS ) {

        this.frame = frame;

        equipeLuz = new Jogo("Aliança da Luz", gL, mL, aL);
        equipeSombra = new Jogo("Horda das Sombras", gS, mS, aS);

        setLayout(null);

        criarFundo();       // Fundo primeiro
        criarInterface();   // Interface depois
        desenharCombatentes();
        MusicManager.playLoop("/audio/arena.wav");
        iniciarBatalha();
        setComponentZOrder(fundo, getComponentCount() - 1);
    }
    
    private void desenharCombatentes() {
        // --- PREPARAÇÃO: ORDENAR AS LISTAS ---
        // Define a ordem de prioridade: Guardião (1) -> Caçador (2) -> Arcanista (3)
        java.util.Comparator<Combatentes> ordemVisual = (c1, c2) -> {
            int p1 = (c1 instanceof Guardiao) ? 1 : (c1 instanceof Cacador) ? 2 : 3;
            int p2 = (c2 instanceof Guardiao) ? 1 : (c2 instanceof Cacador) ? 2 : 3;
            return Integer.compare(p1, p2);
        };

        // Cria listas temporárias ordenadas para não bagunçar a lógica do jogo
        java.util.List<Combatentes> vivosLuz = new java.util.ArrayList<>(equipeLuz.getVivos());
        vivosLuz.sort(ordemVisual);

        java.util.List<Combatentes> vivosSombra = new java.util.ArrayList<>(equipeSombra.getVivos());
        vivosSombra.sort(ordemVisual);

        // --- LUZ (Lado Esquerdo) ---
        // Início em 100, mas vamos preencher de TRÁS PRA FRENTE visualmente
        // para que o Tank (índice 0) fique na frente de batalha (maior X)
        int inicioX_Luz = 100; 
        int inicioY_Luz = 200; 
        int col = 0;
        int linha = 0;

        for (Combatentes c : vivosLuz) {
            JLabel lbl = c.getImagem();
            
            // TRUQUE: Invertemos a coluna para a Luz. 
            // Coluna 0 (Tank) vai para X mais longe (Frente). Coluna 2 (Mago) fica no X inicial (Fundo).
            // (2 - col) faz: 2->0, 1->1, 0->2
            int x = inicioX_Luz + ((2 - col) * 60); 
            int y = inicioY_Luz + (linha * 90); 

            lbl.setBounds(x, y, 100, 100);
            c.setPosicaoOriginal(x, y); 
            add(lbl);

            col++;
            if (col > 2) { col = 0; linha++; }
        }

        // --- SOMBRA (Lado Direito) ---
        // Aqui a lógica padrão funciona: Indice 0 (Tank) fica no menor X (Frente da Sombra)
        int inicioX_Sombra = 700; 
        int inicioY_Sombra = 200; 
        col = 0;
        linha = 0;

        for (Combatentes c : vivosSombra) {
            JLabel lbl = c.getImagem();
            
            int x = inicioX_Sombra + (col * 60); 
            int y = inicioY_Sombra + (linha * 90);

            lbl.setBounds(x, y, 100, 100);
            c.setPosicaoOriginal(x, y);
            add(lbl);

            col++;
            if (col > 2) { col = 0; linha++; }
        }
    }


    
    private void criarFundo() {
        URL imgURL = getClass().getResource("/imagens/Tela_Arena.png");
       
        if (imgURL != null) {
         ImageIcon icon = new ImageIcon(imgURL);
         Image img = icon.getImage().getScaledInstance(1000, 600, java.awt.Image.SCALE_SMOOTH);
         fundo = new JLabel(new ImageIcon(img));
        } 
        else {
            fundo = new JLabel("Imagem da arena não encontrada");
            System.out.println("ERRO: /imagens/Tela_Arena.png não encontrada");
        }

        fundo.setBounds(0, 0, 1000, 600);
        add(fundo);
    }

    private void criarInterface() {
        JLabel titulo = new JLabel("ARENA DE COMBATE");
        titulo.setBounds(360, 20, 320, 30);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setForeground(Color.WHITE);
        add(titulo);

        lblLuz = new JLabel();
        lblLuz.setBounds(150, 80, 300, 30);
        add(lblLuz);

        lblSombra = new JLabel();
        lblSombra.setBounds(600, 80, 300, 30);
        add(lblSombra);

        atualizarHUD();
    }


    // Atualiza a arena após mortes
private void atualizarSprites() {
        // 1. Limpa a tela
        removeAll();

        // 2. Adiciona a Interface (Textos)
        add(lblLuz);
        add(lblSombra);
        JLabel titulo = new JLabel("ARENA DE COMBATE");
        titulo.setBounds(360, 20, 320, 30);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setForeground(Color.WHITE);
        add(titulo);


        // 3. Adiciona os Bonecos (Vivos)
        desenharCombatentes();

        // 4. Adiciona o Fundo
        add(fundo);

       
        if (getComponentCount() > 0) {
            setComponentZOrder(fundo, getComponentCount() - 1);
        }

        revalidate();
        repaint();
    }

    private void atualizarHUD() {
        SwingUtilities.invokeLater(() -> {
            lblLuz.setText("Luz: " + equipeLuz.getVivos().size() + " vivos");
            lblLuz.setForeground(Color.WHITE);
            lblLuz.setFont(new Font("Arial", Font.BOLD, 30));
            
            lblSombra.setText("Sombra: " + equipeSombra.getVivos().size() + " vivos");
            lblSombra.setForeground(Color.WHITE);
            lblSombra.setFont(new Font("Arial", Font.BOLD, 30));            
        });
    }

    private void iniciarBatalha() {
    timerBatalha = new Timer(2000, e -> { 
        if (equipeLuz.temSoldadosVivos() && equipeSombra.temSoldadosVivos()) { 
            
            Jogo.executarRodada(equipeLuz, equipeSombra); 
            
            equipeLuz.removerMortos();
            equipeSombra.removerMortos(); // Atualiza os sprites após remover os mortos
            

            atualizarHUD();

            //atualização visual dos combatentes
            atualizarSprites();



        } else { 
            timerBatalha.stop();
            finalizarBatalha();
        }
    });

    timerBatalha.start();
}
    private void finalizarBatalha() {
        MusicManager.stop();
        SwingUtilities.invokeLater(() -> { String vencedor = equipeLuz.temSoldadosVivos() ? equipeLuz.getNomeFaccao() : equipeSombra.getNomeFaccao();
        JOptionPane.showMessageDialog( this, "🏆 Vitória da " + vencedor );
        frame.mostrarTelaDescanso();        });
    }

}
