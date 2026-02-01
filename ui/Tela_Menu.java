package ui;

import audio.MusicManager;
import javax.swing.*;
import java.net.URL;
import java.awt.Image;
import java.awt.Color;

public class Tela_Menu extends JPanel {

    private final TelaPrincipal frame;

    private int mago = 0;
    private int tank = 0;
    private int atirador = 0;

    private final int LIMITE = 100;
    private JLabel lblTotal;
    private URL url,imgURL;
    
    public Tela_Menu(TelaPrincipal frame) {
        this.frame=frame;
        setLayout(null);
        // settar Fundo primeiro
        JLabel fundo = criarFundo();
        fundo.setLayout(null);
        add(fundo);
        MusicManager.playLoop("/audio/menu.wav");
        criarTabela(fundo);
        criarRodape(fundo);
        
    }

    private JLabel criarFundo() {
        imgURL = getClass().getResource("/imagens/Tela_Menu.png");

        JLabel fundo;

        if (imgURL != null) {
         ImageIcon icon = new ImageIcon(imgURL);
         Image img = icon.getImage().getScaledInstance(1000, 600, java.awt.Image.SCALE_SMOOTH);
         fundo = new JLabel(new ImageIcon(img));

        } 
        else {
            fundo = new JLabel("Imagem Tela_Menu não encontrada");
            System.out.println("ERRO: /imagens/Tela_Menu.png não encontrada");
        }

        fundo.setBounds(0, 0, 1000, 600);
        fundo.setLayout(null); // necessário para adicionar botões sobre o fundo
        return fundo;
    }

   private void criarTabela(JLabel fundo) {

    // Fundo da tabela (a imagem grande com borda)
    JLabel fundoTabela;
    URL imgURL = getClass().getResource("/imagens/tela_branca.png");

    if (imgURL != null) {
        ImageIcon icon = new ImageIcon(imgURL);
        Image img = icon.getImage().getScaledInstance(600, 300, Image.SCALE_SMOOTH);
        fundoTabela = new JLabel(new ImageIcon(img));
        fundoTabela.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        fundoTabela.setOpaque(true);
        fundoTabela.setBackground(new Color(255, 255, 255, 200)); // branco semi-transparente
    } else {
        fundoTabela = new JLabel("Imagem tabela não encontrada");
        System.out.println("ERRO: /imagens/tela_branca.png não encontrada");
    }

    fundoTabela.setBounds(200, 140, 600, 300);
    fundoTabela.setLayout(null);
    fundo.add(fundoTabela);

    // Label do total (topo da tabela)
    lblTotal = new JLabel("Total Selecionado: 0 / 100");
    lblTotal.setBounds(180, 20, 300, 30);
    fundoTabela.add(lblTotal);

    // Linhas
    criarLinha(fundoTabela, "Mago",     80,  () -> mago++,     "/imagens/arcanista_luz.png");
    criarLinha(fundoTabela, "Tank",     140, () -> tank++,     "/imagens/guardiao_luz.png");
    criarLinha(fundoTabela, "Atirador", 200, () -> atirador++, "/imagens/cacador_luz.png");
}

    private void criarLinha(
            JLabel fundoTabela,
String nome, int y, Runnable onAdd, String caminhoIcone) {
    
        // Nome da classe
        JLabel lblNome = new JLabel(nome);
        lblNome.setBounds(70, y - 5, 120, 30);
        fundoTabela.add(lblNome);

        // Campo de quantidade (visual)
        JLabel lblQtd = new JLabel("0", SwingConstants.CENTER);
        lblQtd.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lblQtd.setBounds(210, y - 5, 50, 30);
        fundoTabela.add(lblQtd);

        // Botão +
        JButton btnMais = new JButton("+");
        btnMais.setBounds(280, y - 5, 50, 30);
        fundoTabela.add(btnMais);
        //
        // Botão -
        JButton btnMenos = new JButton("-");
        btnMenos.setBounds(340, y - 5, 50, 30);
        fundoTabela.add(btnMenos);

        // Ícone da classe (direita)
        JLabel lblIcone;
        URL url = getClass().getResource(caminhoIcone);

        if (url != null) {
            ImageIcon original = new ImageIcon(url);
            Image img = original.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            lblIcone = new JLabel(new ImageIcon(img));
        } else {
            
            lblIcone = new JLabel("Imagem nao encontrada");
            System.out.println("Erro: Imagen não encontrada " + caminhoIcone);
        }

        lblIcone.setBounds(400, y - 10, 50, 50);
        fundoTabela.add(lblIcone);

        // Lógica do botão +
        btnMais.addActionListener(e -> {
            if (getTotal() < LIMITE) {
                onAdd.run();
                lblQtd.setText(String.valueOf(getClasse(nome)));
                atualizarTotal();
            }
        });
        // Lógica do botão -
        btnMenos.addActionListener(e -> {
            int qtdAtual = getClasse(nome);
            if (qtdAtual > 0) {
                switch (nome) {
                    case "Mago" -> mago--;
                    case "Tank" -> tank--;
                    case "Atirador" -> atirador--;
                }
                lblQtd.setText(String.valueOf(getClasse(nome)));
                atualizarTotal();
            }
        });



}

    private void criarRodape(JLabel fundo) {
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(200, 500, 120, 40);
        btnVoltar.addActionListener(e -> this.frame.mostrarTelaDescanso());

        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(680, 500, 120, 40);
        btnConfirmar.addActionListener(e -> iniciarArena());

        fundo.add(btnVoltar);
        fundo.add(btnConfirmar);
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
