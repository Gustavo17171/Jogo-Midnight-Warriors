package ui;

import audio.MusicManager;
import javax.swing.*;
import java.net.URL;
import java.awt.Image;
import java.awt.Color;

public class Tela_Menu extends JPanel {

    private final TelaPrincipal frame;

    private int magoLuz = 0;
    private int tankLuz = 0;
    private int atiradorLuz = 0;
    private int magoSombra = 0;
    private int tankSombra = 0;
    private int atiradorSombra = 0;

    private final int LIMITE = 100;
    private JLabel lblTotalLuz;
    private JLabel lblTotalSombra;
    private URL url, imgURL;

    public Tela_Menu(TelaPrincipal frame) {
        this.frame = frame;
        setLayout(null);
        // settar Fundo primeiro
        JLabel fundo = criarFundo();
        fundo.setLayout(null);
        add(fundo);
        MusicManager.playLoop("../audio/menu.wav");
        criarTabelaLuz(fundo);
        criarTabelaSombra(fundo);
        criarRodape(fundo);

    }

    private JLabel criarFundo() {
        imgURL = getClass().getResource("../imagens/Tela_Menu.png");

        JLabel fundo;

        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image img = icon.getImage().getScaledInstance(1000, 600, java.awt.Image.SCALE_SMOOTH);
            fundo = new JLabel(new ImageIcon(img));

        } else {
            fundo = new JLabel("Imagem Tela_Menu não encontrada");
            System.out.println("ERRO: ../imagens/Tela_Menu.png não encontrada");
        }

        fundo.setBounds(0, 0, 1000, 600);
        fundo.setLayout(null); // necessário para adicionar botões sobre o fundo
        return fundo;
    }

    private void criarTabelaLuz(JLabel fundo) {

        // Fundo da tabela (a imagem grande com borda)
        JLabel fundoTabela;
        JLabel nomeTabelaL;
        URL imgURL = getClass().getResource("/imagens/tela_branca.png");

        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image img = icon.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
            fundoTabela = new JLabel(new ImageIcon(img));
            fundoTabela.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
            fundoTabela.setOpaque(true);
            fundoTabela.setBackground(new Color(255, 255, 255, 200)); // branco semi-transparente
        } else {
            fundoTabela = new JLabel("Imagem tabela não encontrada");
            System.out.println("ERRO: ../imagens/tela_branca.png não encontrada");
        }

        fundoTabela.setBounds(100, 70, 350, 350);
        fundoTabela.setLayout(null);
        fundo.add(fundoTabela);

        nomeTabelaL = new JLabel("Equipe da Luz");
        nomeTabelaL.setBounds(120, -5, 200, 50);
        fundoTabela.add(nomeTabelaL);

        // Label do total (topo da tabela)
        lblTotalLuz = new JLabel("Total Selecionado: 0 / 100");
        lblTotalLuz.setBounds(80, 20, 200, 30);
        fundoTabela.add(lblTotalLuz);

        // Linhas
        criarLinha(fundoTabela, "MagoLuz", 80, () -> magoLuz++, "/imagens/arcanista_luz.png");
        criarLinha(fundoTabela, "TankLuz", 140, () -> tankLuz++, "/imagens/guardiao_luz.png");
        criarLinha(fundoTabela, "AtiradorLuz", 200, () -> atiradorLuz++, "/imagens/cacador_luz.png");
    }

    private void criarTabelaSombra(JLabel fundo) {

        // Fundo da tabela (a imagem grande com borda)
        JLabel fundoTabela;
        JLabel nomeTabelaS;
        URL imgURL = getClass().getResource("/imagens/tela_branca.png");

        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image img = icon.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
            fundoTabela = new JLabel(new ImageIcon(img));
            fundoTabela.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
            fundoTabela.setOpaque(true);
            fundoTabela.setBackground(new Color(255, 255, 255, 200)); // branco semi-transparente
        } else {
            fundoTabela = new JLabel("Imagem tabela não encontrada");
            System.out.println("ERRO: ../imagens/tela_branca.png não encontrada");
        }

        fundoTabela.setBounds(600, 70, 350, 350);
        fundoTabela.setLayout(null);
        fundo.add(fundoTabela);

        nomeTabelaS = new JLabel("Equipe da Sombra");
        nomeTabelaS.setBounds(120, -5, 200, 50);
        fundoTabela.add(nomeTabelaS);

        // Label do total (topo da tabela)
        lblTotalSombra = new JLabel("Total Selecionado: 0 / 100");
        lblTotalSombra.setBounds(80, 20, 200, 30);
        fundoTabela.add(lblTotalSombra);

        // Linhas
        criarLinha(fundoTabela, "MagoSombra", 80, () -> magoSombra++, "/imagens/arcanista_sombra.png");
        criarLinha(fundoTabela, "TankSombra", 140, () -> tankSombra++, "/imagens/guardiao_sombra.png");
        criarLinha(fundoTabela, "AtiradorSombra", 200, () -> atiradorSombra++, "/imagens/cacador_sombra.png");
    }

    private void criarLinha(
            JLabel fundoTabela,
            String nome, int y, Runnable onAdd, String caminhoIcone) {

        // Nome da classe
        JLabel lblNome = new JLabel(nome);
        lblNome.setBounds(10, y - 5, 120, 30);
        fundoTabela.add(lblNome);

        // Campo de quantidade (visual)
        JLabel lblQtd = new JLabel("0", SwingConstants.CENTER);
        lblQtd.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lblQtd.setBounds(110, y - 5, 50, 30);
        fundoTabela.add(lblQtd);

        // Botão +
        JButton btnMais = new JButton("+");
        btnMais.setBounds(160, y - 5, 50, 30);
        fundoTabela.add(btnMais);
        //
        // Botão -
        JButton btnMenos = new JButton("-");
        btnMenos.setBounds(210, y - 5, 50, 30);
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

        lblIcone.setBounds(270, y - 10, 50, 50);
        fundoTabela.add(lblIcone);

        // Lógica do botão +
        btnMais.addActionListener(e -> {
            if (getTotalLuz() < LIMITE && (nome.endsWith("Luz"))
                    || getTotalSombra() < LIMITE && (nome.endsWith("Sombra"))) {
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
                    case "MagoLuz" -> magoLuz--;
                    case "TankLuz" -> tankLuz--;
                    case "AtiradorLuz" -> atiradorLuz--;
                    case "MagoSombra" -> magoSombra--;
                    case "TankSombra" -> tankSombra--;
                    case "AtiradorSombra" -> atiradorSombra--;
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
                tankLuz, magoLuz, atiradorLuz,
                tankSombra, magoSombra, atiradorSombra);
    }

    private int getTotalLuz() {
        return magoLuz + tankLuz + atiradorLuz;
    }

    private int getTotalSombra() {
        return magoSombra + tankSombra + atiradorSombra;
    }

    private int getClasse(String nome) {
        return switch (nome) {
            case "MagoLuz" -> magoLuz;
            case "TankLuz" -> tankLuz;
            case "AtiradorLuz" -> atiradorLuz;
            case "MagoSombra" -> magoSombra;
            case "TankSombra" -> tankSombra;
            default -> atiradorSombra;
        };
    }

    private void atualizarTotal() {
        lblTotalLuz.setText("Total Selecionado: " + getTotalLuz() + " / 100");
        lblTotalSombra.setText("Total Selecionado: " + getTotalSombra() + " / 100");
    }
}