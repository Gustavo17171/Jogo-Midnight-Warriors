package ui;

import audio.MusicManager;
import javax.swing.*;
import java.net.URL;
import jogo.Jogo;
import java.awt.Image;
import javax.swing.Timer;
import javax.swing.SwingUtilities;
public class Arena extends JPanel {

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

        MusicManager.playLoop("/audio/arena.wav");
        iniciarBatalha();
    }

    private void criarFundo() {
        URL imgURL = getClass().getResource("/imagens/Tela_Arena.png");

        JLabel fundo;
       
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
        SwingUtilities.invokeLater(() -> {
            lblLuz.setText("Luz: " + equipeLuz.getVivos().size() + " vivos");            
            lblSombra.setText("Sombra: " + equipeSombra.getVivos().size() + " vivos");
        });
    }

    private void iniciarBatalha() {
        timerBatalha = new Timer(900, e -> {
            if (equipeLuz.temSoldadosVivos() && equipeSombra.temSoldadosVivos()) { Jogo.executarRodada(equipeLuz, equipeSombra); atualizarHUD();
                } 
            else{ 
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
