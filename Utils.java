import java.io.IOException;

/**
 * Metodos genericos.
 */

public class Utils {
    /**
     * Limpa a tela do terminal em diversos sistemas.
     */

    public static void clear() {
        try {
            String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        }
        catch (IOException | InterruptedException ex) {
            System.err.println("\n ERRO: " + ex.getMessage());
        }
    }

    /**
     * Retorna uma "String" com o caracter "c" repetido "n" vezes.
     * @param c -> caracter
     * @param n -> numero a ser repetido
     * @return String string
     */
    
    public static String multiplyChars(char c, int n) {
        String string = "";

        for (int i = 0; i < n; i++) {
            string += c;
        }

        return string;
    }
}
