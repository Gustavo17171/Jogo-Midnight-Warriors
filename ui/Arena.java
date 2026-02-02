package ui;

import audio.MusicManager;
import javax.swing.*;
import java.net.URL;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;
import jogo.Combatentes;
import jogo.Jogo;

public class Arena extends JPanel {

    private JLabel fundo;
    private final TelaPrincipal frame;
    private Timer timerBatalha;
    private final Jogo equipeLuz;
    private final Jogo equipeSombra;
    private JLabel lblLuz;
    private JLabel lblSombra;

   // ================= LOG VISUAL =================
    private JTextArea logArea;

    public Arena(TelaPrincipal frame, int gL, int mL, int aL, int gS, int mS, int aS) {

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

    // ================= LOG =================
    private void criarLog() {
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setLineWrap(true);

        JScrollPane scroll = new JScrollPane(logArea);
        scroll.setBounds(50, 480, 900, 100);
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

    // ================= FUNDO =================
    private void criarFundo() {
        URL imgURL = getClass().getResource("/imagens/Tela_Arena.png");

        if (imgURL != null) {
            Image img = new ImageIcon(imgURL).getImage()
                    .getScaledInstance(1000, 600, Image.SCALE_SMOOTH);
            fundo = new JLabel(new ImageIcon(img));
        } else {
            fundo = new JLabel("Imagem não encontrada");
        }

        fundo.setBounds(0, 0, 1000, 600);
        add(fundo);
    }

    // ================= INTERFACE =================
    private void criarInterface() {
        JLabel titulo = new JLabel("ARENA DE COMBATE");
        titulo.setBounds(360, 20, 200, 30);
        add(titulo);

        lblLuz = new JLabel();
        lblLuz.setBounds(150, 80, 300, 30);
        add(lblLuz);

        lblSombra = new JLabel();
        lblSombra.setBounds(500, 80, 300, 30);
        add(lblSombra);

        atualizarHUD();
    }

    private void atualizarHUD() {
        lblLuz.setText("Luz: " + equipeLuz.getVivos().size() + " vivos");
        lblSombra.setText("Sombra: " + equipeSombra.getVivos().size() + " vivos");
    }

    // ================= BATALHA =================
    private void iniciarBatalha() {
        timerBatalha = new Timer(2200, e -> {

            if (equipeLuz.temSoldadosVivos() && equipeSombra.temSoldadosVivos()) {

                animarAtaque();

            } else {
                timerBatalha.stop();
                finalizarBatalha();
            }
        });

        timerBatalha.start();
    }

    // ================= ANIMAÇÃO =================
    private void animarAtaque() {

        Combatentes luz = equipeLuz.alvoAleatorio();
        Combatentes sombra = equipeSombra.alvoAleatorio();

        Timer anim = new Timer(20, null);
        anim.addActionListener(e -> {

            moverSuave(luz, 450, 300);
            moverSuave(sombra, 550, 300);

            if (Math.abs(luz.getX() - 450) < 5) {
                anim.stop();

                Jogo.executarRodada(equipeLuz, equipeSombra);

                luz.voltarPosicao();
                sombra.voltarPosicao();

                equipeLuz.removerMortos();
                equipeSombra.removerMortos();

                atualizarHUD();
                atualizarSprites();
            }
        });

        anim.start();
    }

    private void moverSuave(Combatentes c, int alvoX, int alvoY) {
        int dx = (alvoX - c.getX()) / 10;
        int dy = (alvoY - c.getY()) / 10;
        c.moverPara(c.getX() + dx, c.getY() + dy);
    }

    // ================= SPRITES =================
    private void desenharCombatentes() {
        for (Combatentes c : equipeLuz.getVivos()) add(c.getImagem());
        for (Combatentes c : equipeSombra.getVivos()) add(c.getImagem());
    }

    private void atualizarSprites() {
        removeAll();
        criarInterface();
        criarLog();
        desenharCombatentes();
        add(fundo);
        setComponentZOrder(fundo, getComponentCount() - 1);
        repaint();
    }
    // ================= FINALIZA A BATALHA =================
    private void finalizarBatalha() {
        MusicManager.stop();
        JOptionPane.showMessageDialog(this,
                "🏆 Vitória da " +
                (equipeLuz.temSoldadosVivos()
                        ? equipeLuz.getNomeFaccao()
                        : equipeSombra.getNomeFaccao()));
        frame.mostrarTelaDescanso();
    }
}
