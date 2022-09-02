package breakout;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
    public int[][] map;
    public int brickWidth;
    public int brickHeight;

    public MapGenerator(int row, int column) {
        map = new int[row][column];
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                map[y][x] = 1;
            }
        }
        brickWidth = (540 / column);
        brickHeight = (150 / row);
        return;
    }

    public void drawMap(Graphics2D gfx) {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (map[y][x] > 0) {
                    int brickX = ((x * brickWidth) + 80);
                    int brickY = ((y * brickHeight) + 50);
                    gfx.setColor(Color.white);
                    gfx.fillRect(brickX, brickY, brickWidth, brickHeight);
                    // To show an outline around the brick:
                    gfx.setStroke(new BasicStroke(3));
                    gfx.setColor(Color.black);
                    gfx.drawRect(brickX, brickY, brickWidth, brickHeight);
                }
            }
        }
        return;
    }

    public void setBrickValue(int value, int row, int column) {
        map[row][column] = value;
        return;
    }
}
