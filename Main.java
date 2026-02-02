import javax.swing.SwingUtilities;
import ui.TelaPrincipal;
import java.awt.Image;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipal());
    }
}

// ================= COMANDOS PARA OPERAR AS PASTAS =================
/*

Remove-Item -Recurse -Force bin\audio
Remove-Item -Recurse -Force bin\imagens
Copy-Item -Recurse audio bin\
Copy-Item -Recurse imagens bin\


javac -encoding UTF-8 -d bin Main.java ui\*.java jogo\*.java personagens\*.java audio\*.java
java -cp bin Main

*/