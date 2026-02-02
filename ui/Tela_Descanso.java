package ui;

import audio.MusicManager;
import javax.swing.*;
import java.net.URL;
import java.awt.Image;

// ================= CONSTRUDOR DE TELA DE DESCANSO =================
public class Tela_Descanso extends JPanel {

    private final TelaPrincipal frame;
    public Tela_Descanso(TelaPrincipal frame) {
        this.frame = frame;
        setLayout(null);

        // Música do menu
        MusicManager.playLoop("/audio/menu.wav");

        // Fundo
        JLabel fundo = criarFundo();
        add(fundo);

        // Botão Start
        JButton btnStart = new JButton("Start");
        btnStart.setBounds(380, 450, 200, 100);
        btnStart.addActionListener(e -> this.frame.mostrarTelaMenu());

        // Adiciona botão sobre o fundo
        fundo.add(btnStart);
    }

        // ================= FUNDO =================
        private JLabel criarFundo() {
        URL imgURL = getClass().getResource("/imagens/Tela_Descanso.png");

        JLabel fundo;

        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image img = icon.getImage()
                .getScaledInstance(1000, 600, Image.SCALE_SMOOTH);

            fundo = new JLabel(new ImageIcon(img));
        } else {
            fundo = new JLabel("Imagem não encontrada");
            System.out.println("Erro: /imagens/Tela_Descanso.png não foi encontrado");
        }

        fundo.setBounds(0, 0, 1000, 600);
        fundo.setLayout(null);
        return fundo;
    }


}
