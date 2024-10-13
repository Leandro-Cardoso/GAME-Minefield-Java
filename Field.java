import java.util.Random;

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

    private void addBomb(int x, int y) {
        Square bomb = new Bomb(x, y);
        this.field[y][x] = bomb;

        for (int i = 0; i < this.bombs.length; i++) {
            if (this.bombs[i] != bomb) {
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

                if (this.field[y][x] == null) {
                    break;
                }
            }

            this.addBomb(x, y);
        }
    }

    private void generateField() {
        this.generateBombs();
    }

    /**
     * Retorna uma "String" com o campo.
     * @return String str
     */
    
    public String str() {
        String str = "";

        for (int y = 0; y < this.sizeY; y++){
            str += "\n";
            for (int x = 0; x < this.sizeX; x++) {
                str += " " + (this.field[y][x] == null ? " " : this.field[y][x].getName());
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
