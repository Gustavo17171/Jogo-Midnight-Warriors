package ui;

import audio.MusicManager;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import javax.swing.*;
import jogo.Combatentes;
import jogo.Jogo;

public class Arena extends JPanel {

    private JLabel fundo;
    private final TelaPrincipal frame;
    private Timer timerBatalha;

    private final Jogo equipeLuz;
    private final Jogo equipeSombra;

    // HUD
    private JLabel lblLuz;
    private JLabel lblSombra;
    private JLabel titulo;

    // LOG
    private JTextArea logArea;

    public Arena(TelaPrincipal frame,
                 int gL, int mL, int aL,
                 int gS, int mS, int aS) {

        this.frame = frame;
        equipeLuz = new Jogo("Aliança da Luz", gL, mL, aL);
        equipeSombra = new Jogo("Horda das Sombras", gS, mS, aS);

        setLayout(null);

        criarFundo();
        criarInterface();
        criarLog();
        desenharCombatentes();

        MusicManager.playLoop("/audio/arena.wav");
        redirecionarConsole();
        iniciarBatalha();

        setComponentZOrder(fundo, getComponentCount() - 1);
    }

    // ================= FUNDO =================
    private void criarFundo() {
        URL imgURL = getClass().getResource("/imagens/Tela_Arena.png");

        if (imgURL != null) {
            Image img = new ImageIcon(imgURL)
                    .getImage()
                    .getScaledInstance(1000, 600, Image.SCALE_SMOOTH);
            fundo = new JLabel(new ImageIcon(img));
        } else {
            fundo = new JLabel("Imagem não encontrada");
        }

        fundo.setBounds(0, 0, 1000, 600);
        add(fundo);
    }

    // ================= HUD =================
    private void criarInterface() {

        titulo = new JLabel("ARENA DE COMBATE");
        titulo.setBounds(360, 20, 320, 30);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setForeground(Color.WHITE);
        add(titulo);

        lblLuz = new JLabel();
        lblLuz.setBounds(120, 80, 350, 30);
        lblLuz.setFont(new Font("Arial", Font.BOLD, 28));
        lblLuz.setForeground(Color.WHITE);
        add(lblLuz);

        lblSombra = new JLabel();
        lblSombra.setBounds(580, 80, 350, 30);
        lblSombra.setFont(new Font("Arial", Font.BOLD, 28));
        lblSombra.setForeground(Color.WHITE);
        add(lblSombra);

        atualizarHUD();
    }

    private void atualizarHUD() {
        SwingUtilities.invokeLater(() -> {
            lblLuz.setText("Luz: " + equipeLuz.getVivos().size() + " vivos");
            lblSombra.setText("Sombra: " + equipeSombra.getVivos().size() + " vivos");
        });
    }

    // ================= LOG =================
    private void criarLog() {
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(logArea);
        scroll.setBounds(50, 470, 900, 110);
        add(scroll);
    }

    private void redirecionarConsole() {
        PrintStream ps = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                SwingUtilities.invokeLater(() ->
                        logArea.append(String.valueOf((char) b))
                );
            }
        });
        System.setOut(ps);
    }

    // ================= BATALHA =================
    private void iniciarBatalha() {
        timerBatalha = new Timer(2200, e -> {

            if (equipeLuz.temSoldadosVivos()
                    && equipeSombra.temSoldadosVivos()) {

                Jogo.executarRodada(equipeLuz, equipeSombra);

                equipeLuz.removerMortos();
                equipeSombra.removerMortos();

                atualizarHUD();
                atualizarSprites();

            } else {
                timerBatalha.stop();
                finalizarBatalha();
            }
        });

        timerBatalha.start();
    }

    // ================= SPRITES =================
    private void desenharCombatentes() {

        int yLuz = 170;
        for (Combatentes c : equipeLuz.getVivos()) {
            c.moverPara(180, yLuz);   // ESQUERDA
            add(c.getImagem());
            yLuz += 80;
        }

        int ySombra = 170;
        for (Combatentes c : equipeSombra.getVivos()) {
            c.moverPara(760, ySombra); // DIREITA
            add(c.getImagem());
            ySombra += 80;
        }
    }

    private void atualizarSprites() {
        removeAll();

        add(titulo);
        add(lblLuz);
        add(lblSombra);

        criarLog();
        desenharCombatentes();

        add(fundo);
        setComponentZOrder(fundo, getComponentCount() - 1);

        repaint();
    }

    // ================= FIM =================
    private void finalizarBatalha() {
        MusicManager.stop();

        JOptionPane.showMessageDialog(
                this,
                "🏆 Vitória da " +
                        (equipeLuz.temSoldadosVivos()
                                ? equipeLuz.getNomeFaccao()
                                : equipeSombra.getNomeFaccao())
        );

        frame.mostrarTelaDescanso();
    }
}
