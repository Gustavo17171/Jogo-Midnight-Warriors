package ui;

import javax.swing.*;
import jogo.Jogo;

public class TelaPrincipal extends JFrame {

    private Jogo jogoAtual;

    public TelaPrincipal() {
    configurarJanela();
    setVisible(true);
    mostrarTelaDescanso();
}

private final void configurarJanela() {
    setTitle("Midnight Warriors");
    setSize(1000, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);
}
 public final void mostrarTelaDescanso() {
        setContentPane(new Tela_Descanso(this));
        revalidate();
        repaint();
    }

public final void mostrarTelaMenu() {
        setContentPane(new Tela_Menu(this));
        revalidate();
        repaint();
    }

public final void iniciarArena(
        int gL, int mL, int aL,
        int gS, int mS, int aS
    ) {
        setContentPane(new Arena(
            this, gL, mL, aL, gS, mS, aS
        ));
        revalidate();
        repaint();
    }
}
