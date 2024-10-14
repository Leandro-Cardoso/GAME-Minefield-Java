import java.util.Random;

/**
 * Gera um campo minado de tamanho x colunas e y linhas.
 */

public class Field {
    private int sizeX, sizeY, size, numBombs;
    private Square[][] field;
    private Square[] bombs;

    public Field(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.size = sizeX * sizeY;
        this.numBombs = this.size / 10;
        this.field = new Square[sizeY][sizeX];
        this.bombs = new Square[numBombs];

        this.generateField();
    }

    private void createSquares() {
        for (int y = 0; y < this.sizeY; y++) {
            for (int x = 0; x < this.sizeX; x++) {
                Square square = new Square(y, x);

                this.field[y][x] = square;
            }
        }
    }

    private void addBomb(int x, int y) {
        Square bomb = new Bomb(x, y);
        this.field[y][x] = bomb;

        for (int i = 0; i < this.bombs.length; i++) {
            if (this.bombs[i] == null) {
                this.bombs[i] = bomb;
                break;
            }
        }
    }

    private void generateBombs() {
        Random generator = new Random();
        int x, y;

        for (int i = 0; i < this.numBombs; i++) {
            while (true) {
                x = generator.nextInt(this.sizeX);
                y = generator.nextInt(this.sizeY);

                if (this.field[y][x].getName() != 'B') {
                    break;
                }
            }

            this.addBomb(x, y);
        }
    }

    private void sumBomb(int x, int y) {
        if (this.field[y][x].getName() != 'B') {
            char newName = this.field[y][x].getName();
            newName++;
            this.field[y][x].setName(newName);
        }
    }
    
    private void sumBombs() {
        for (Square bomb : this.bombs) {
            int x = bomb.getX();
            int y = bomb.getY();

            if (y - 1 > -1) {
                if (x - 1 > -1) {
                    this.sumBomb(x - 1, y - 1);
                }
                this.sumBomb(x, y - 1);
                if (x + 1 < this.sizeX) {
                    this.sumBomb(x + 1, y - 1);
                }
            }
            if (x - 1 > -1) {
                this.sumBomb(x - 1, y);
            }
            if (x + 1 < this.sizeX) {
                this.sumBomb(x + 1, y);
            }
            if (y + 1 < this.sizeY) {
                if (x - 1 > -1) {
                    this.sumBomb(x - 1, y + 1);
                }
                this.sumBomb(x, y + 1);
                if (x + 1 < this.sizeX) {
                    this.sumBomb(x + 1, y + 1);
                }
            }
        }
    }

    private void generateField() {
        this.createSquares();
        this.generateBombs();
        this.sumBombs();
    }

    /**
     * Retorna uma "String" com o campo gerado.
     * @return String str
     */
    
    public String str() {
        String str = "";

        for (int y = 0; y < this.sizeY; y++){
            str += "\n";
            for (int x = 0; x < this.sizeX; x++) {
                str += " " + (this.field[y][x].getName() == '0' ? ' ' : this.field[y][x].getName());
            }
        }
        str += "\n";

        return str;
    }

    public static void main(String[] args) {
        Field field = new Field(20, 10);
        System.out.println(field.str());
    }
}
