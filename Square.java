/**
 * O "Square" representa cada quadrado do campo, que pode ser uma area vazia ou uma bomba.
 */

public class Square {
    private char name;
    private int x, y;
    private boolean isVisible;

    public Square(int x, int y) {
        this.name = ' ';
        this.x = x;
        this.y = y;
        this.isVisible = false;
    }

    /**
     * Retorna a posicao X (coluna) da matriz do campo.
     * @return int x
     */

    public int getX() {
        return this.x;
    }

    /**
     * Retorna a posicao Y (linha) da matriz do campo.
     * @return int y
     */

    public int getY() {
        return this.y;
    }

    /**
     * Definir nome para o quadrado.
     */

    public void setName(char name) {
        this.name = name;
    }

    /**
     * Ativa o quadrado.
     */
    
    public void activate() {
        this.isVisible = true;
    }
}
