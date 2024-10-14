import java.util.Random;

/**
 * Gera um campo minado de tamanho x colunas e y linhas.
 */

public class Field {
    private int sizeX, sizeY, size, numBombs;
    private Square[][] field;
    private Square[] bombs;
    private char invisibleSquare;

    public Field(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.size = sizeX * sizeY;
        this.numBombs = this.size / 10;
        this.field = new Square[sizeY][sizeX];
        this.bombs = new Square[numBombs];
        this.invisibleSquare = '?';

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

    private void activateBombs() {
        for (Square bomb : this.bombs) {
            bomb.setVisible();
        }
    }
    
    /**
     * Ativa um determinado quadrado em x coluna e y linha, se esse quadrado for 'B' (bomba) ativa todas as bombas, se esse quadrado for '0' (sem bomba no redor) ativa todos os espaços ao redor de forma recursiva.
     * @param x
     * @param y
     */
    
    public void activate(int x, int y) {
        if (this.field[y][x].getName() == 'B') {
            this.activateBombs();
        }
        else if (this.field[y][x].getName() == '0') {
            this.field[y][x].setVisible();

            for (int dy = -1; dy <= 1; dy++) {
                for (int dx = -1; dx <= 1; dx++) {
                    int nx = x + dx;
                    int ny = y + dy;
    
                    if (nx >= 0 && nx < this.sizeX && ny >= 0 && ny < this.sizeY && !this.field[ny][nx].isVisible()) {
                        this.activate(nx, ny);
                    }
                }
            }
        }
        else {
            this.field[y][x].setVisible();
        }
    }
    
    /**
     * Retorna uma "String" com o campo gerado.
     * @return String str
     */
    
    public String str() {
        String str = "";
        String RESET = "\u001B[0m";
        String BLUE = "\u001B[34m";
        String CYAN = "\u001B[36m";
        String GREEN = "\u001B[32m";
        String RED = "\u001B[31m";

        for (int y = 0; y < this.sizeY; y++){
            str += "\n";

            if (y == 0) {
                str += "   ";

                for (int x = 0; x < this.sizeX; x++) {
                    if ((x + 1) % 2 == 0) {
                        str += CYAN;
                    }
                    else {
                        str += BLUE;
                    }

                    if (x < 9) {
                        str += " ";
                    }
                    
                    str += " " + (x + 1);
                }

                str += RESET + "\n";
            }

            for (int x = 0; x < this.sizeX; x++) {
                str += "  ";

                if (x == 0) {
                    if ((y + 1) % 2 == 0) {
                        str += CYAN;
                    }
                    else {
                        str += BLUE;
                    }

                    if (y < 9) {
                        str += " ";
                    }
                    
                    str += (y + 1) + RESET + " ";
                }

                if (this.field[y][x].isVisible()) {
                    if (this.field[y][x].getName() == 'B') {
                        str += RED + this.field[y][x].getName() + RESET;
                    }
                    else {
                        str += (this.field[y][x].getName() == '0' ? '.' : this.field[y][x].getName());
                    }
                }
                else {
                    str += GREEN + this.invisibleSquare + RESET;
                }
            }
        }

        str += "\n";

        return str;
    }

    /**
     * Retorna o tamanho em colunas do campo.
     * @return int sizeX
     */
    
    public int getSizeX() {
        return this.sizeX;
    }

    /**
     * Retorna o tamanho em linhas do campo.
     * @return int sizeY
     */

    public int getSizeY() {
        return this.sizeY;
    }

    /**
     * Retorna se ouve uma vitoria ou nao.
     * @return boolean isVictory
     */
    
    public boolean isVictory() {
        int count = 0;

        for (Square[] line : this.field) {
            for (Square square : line) {
                if (square.isVisible()) {
                    count++;
                }
            }
        }

        return (count + this.bombs.length == this.size) ? true : false;
    }

    /**
     * Retorna se ouve uma derrota ou nao.
     * @return boolean isDefeat
     */
    
    public boolean isDefeat() {
        return (this.bombs[0].isVisible()) ? true : false;
    }
}
