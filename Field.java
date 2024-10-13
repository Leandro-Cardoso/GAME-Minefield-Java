import java.util.Random;

public class Field {
    private int sizeX, sizeY, size, numBombs;
    private int[][] field;
    private Square[] bombs;

    public Field(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.size = sizeX * sizeY;
        this.numBombs = this.size / 10;
        this.field = new int[sizeY][sizeX];
        this.generateField();
    }

    private void addBomb(int x, int y) {
        Square bomb = new Bomb(x, y);
        this.field[y][x] = bomb;

        for (i = 0; i < this.bombs.length; i++) {
            if (this.bombs[i] == null) {
                this.bombs[i] = bomb;
            }
        }
    }

    private void generateBombs() {
        Random generator = new Random();
        int x, y;

        x = generator.nextInt(this.sizeX);
        y = generator.nextInt(this.sizeY);

        for (i = 0; i < this.numBombs; i++) {
            while (this.field[y][x].name == 'B') {
                x = generator.nextInt(this.sizeX);
                y = generator.nextInt(this.sizeY);
            }
            this.addBomb(x, y);
        }
    }

    private void generateField() {
        this.generateBombs();
    }
}
