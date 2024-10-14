/**
 * Classe principal do programa.
 */

public class Main {
    /**
     * Encerra o programa corretamente.
     */
    
    public static void exit() {
        Screen.scanner.close();
        System.exit(0);
    }

    /**
     * Tela de vitoria.
     */

    public static void victory(Field field) {
        int in, back;

        back = 0;

        while (true) {
            Utils.clear();
            Screen.renderTitle("Vitoria !!!");
            Screen.renderInfo("Parabéns !!! Voce venceu !!!\n Digite '0' para voltar.");
            Screen.renderField(field.str());
            Screen.renderError();

            in = Screen.getIntIn("Voltar", back, back);

            if (in == back) {
                break;
            }
        }
    }

    /**
     * Tela de derrota.
     */
    
    public static void defeat(Field field) {
        int in, back;

        back = 0;

        while (true) {
            Utils.clear();
            Screen.renderTitle("Derrota !!!");
            Screen.renderInfo("Que pena... Voce explodiu, tente novamente.\n Digite '0' para voltar.");
            Screen.renderField(field.str());
            Screen.renderError();

            in = Screen.getIntIn("Voltar", back, back);

            if (in == back) {
                break;
            }
        }
    }

    /**
     * Gera um novo campo minado.
     * @return Field field
     */
    
    public static Field generateField() {
        Field field = new Field(20, 15);

        return field;
    }
    
    /**
     * Metodo principal do programa e tela inicial.
     * @param args
     */
    
    public static void main(String[] args) {
        Field field = Main.generateField();
        int x, y, exit;

        exit = 0;

        while (true) {
            Utils.clear();
            Screen.renderTitle("Campo Minado");
            Screen.renderInfo("Digite '0' para sair.");
            Screen.renderField(field.str());
            Screen.renderError();

            y = Screen.getIntIn("Linha", exit, field.getSizeY());

            if (y == exit) {
                Main.exit();
            }
            else if (y == -1) {
                continue;
            }

            x = Screen.getIntIn("Coluna", exit, field.getSizeX());

            if (x == exit) {
                Main.exit();
            }
            else if (x == -1) {
                continue;
            }

            field.activate(x - 1, y - 1);

            if (field.isDefeat()) {
                Main.defeat(field);
                field = Main.generateField();
            }
            else if (field.isVictory()) {
                Main.victory(field);
                field = Main.generateField();
            }
        }
    }
}
