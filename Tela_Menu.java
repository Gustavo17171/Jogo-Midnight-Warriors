package ui;

import audio.MusicManager;
import javax.swing.*;
import java.net.URL;

public class Tela_Menu extends JPanel {

    private final TelaPrincipal frame;

    private int mago = 0;
    private int tank = 0;
    private int atirador = 0;

    private final int LIMITE = 100;
    private JLabel lblTotal;

    public Tela_Menu(TelaPrincipal frame) {
        this.frame = frame;
        setLayout(null);

        MusicManager.playLoop("/audio/menu.wav");

        // Fundo primeiro
        JLabel fundo = criarFundo();
        add(fundo);

        criarTabela();
        criarRodape();
    }

    private JLabel criarFundo() {
        URL imgURL = getClass().getResource("/imagens/Tela_Menu.png");

        JLabel fundo;
        if (imgURL != null) {
            fundo = new JLabel(new ImageIcon(imgURL));
        } else {
            fundo = new JLabel("Imagem Tela_Menu não encontrada");
            System.out.println("ERRO: /imagens/Tela_Menu.png não encontrada");
        }

        fundo.setBounds(0, 0, 1000, 600);
        return fundo;
    }

    private void criarTabela() {
        lblTotal = new JLabel("Total Selecionado: 0 / 100");
        lblTotal.setBounds(350, 120, 250, 30);
        add(lblTotal);

        criarLinha("Mago", 180, () -> mago++);
        criarLinha("Tank", 230, () -> tank++);
        criarLinha("Atirador", 280, () -> atirador++);
    }

    private void criarLinha(String nome, int y, Runnable onAdd) {
        JLabel lblNome = new JLabel(nome);
        lblNome.setBounds(300, y, 100, 30);

        JLabel lblQtd = new JLabel("0");
        lblQtd.setBounds(430, y, 30, 30);

        JButton btnMais = new JButton("+");
        btnMais.setBounds(480, y, 50, 30);

        btnMais.addActionListener(e -> {
            if (getTotal() < LIMITE) {
                onAdd.run();
                lblQtd.setText(String.valueOf(getClasse(nome)));
                atualizarTotal();
            }
        });

        add(lblNome);
        add(lblQtd);
        add(btnMais);
    }

    private void criarRodape() {
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(200, 500, 120, 40);
        btnVoltar.addActionListener(e -> frame.mostrarTelaDescanso());

        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(550, 500, 120, 40);
        btnConfirmar.addActionListener(e -> iniciarArena());

        add(btnVoltar);
        add(btnConfirmar);
    }

    private void iniciarArena() {
        MusicManager.stop();

        frame.iniciarArena(
            tank, mago, atirador,
            tank, mago, atirador
        );
    }

    private int getTotal() {
        return mago + tank + atirador;
    }

    private int getClasse(String nome) {
        return switch (nome) {
            case "Mago" -> mago;
            case "Tank" -> tank;
            default -> atirador;
        };
    }

    private void atualizarTotal() {
        lblTotal.setText("Total Selecionado: " + getTotal() + " / 100");
    }
}
