import java.util.Scanner;

/**
 * Cria uma tela para o terminal.
 */

public class Screen {
    private static int size = 65;
    private static char titleDecorator = '=';
    private static String error = "";

    public static Scanner scanner = new Scanner(System.in);

    /**
     * Exibe o titulo no terminal.
     * @param title
     */
    
    public static void renderTitle(String title) {
        int spaces = Screen.size - (title.length() + 2);

        System.out.print("\n" + Utils.multiplyChars(Screen.titleDecorator, spaces / 2) + " " + title + " ");

        if (spaces % 2 == 0) {
            System.out.print(Utils.multiplyChars(Screen.titleDecorator, spaces / 2));
        }
        else {
            System.out.print(Utils.multiplyChars(Screen.titleDecorator, spaces / 2 + 1));
        }

        System.out.print("\n");
    }

    /**
     * Exibe informacao dentro do tamanho da tela.
     * @param info -> Informacao a ser exibida
     */

    public static void renderInfo(String info) {
        System.out.print("\n ");

        for (int i = 0; i < info.length(); i++) {
            char c = info.charAt(i);
            System.out.print(c);
        }

        System.out.print("\n");
    }

    /**
     * Exibe o campo minado.
     * @param field -> Compo em forma de "String"
     */

    public static void renderField(String field) {
        System.out.print(field);
    }

    /**
     * Exibe uma mensagem de erro, caso exista erro para ser exibido.
     */
    
    public static void renderError() {
        if (Screen.error != "") {
            System.out.println("\n ERROR: " + Screen.error);
            Screen.error = "";
        }
    }
    
    /**
     * Retorna a entrada de um numero inteiro pelo usuario entre os valores "start" e "end", incluindo os mesmos, em caso de erro retorna -1.
     * @param msg -> Mensagem para a entrada
     * @param start -> Valor minimo
     * @param end -> Valor maximo
     * @return int option
     */
    
    public static int getIntIn(String msg, int start, int end) {
        int in;

        System.out.print("\n " + msg + ": ");
        in =  Screen.scanner.nextInt();

        if (in < start || in > end) {
            in = -1;
            Screen.error = "Valor de entrada invalido";
        }

        return in;
    }
}
