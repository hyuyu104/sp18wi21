package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    /**
     * Adds a hexagon to the world.
     *
     * @param world the world to draw on
     * @param p the bottom left coordinate of the hexagon
     * @param s the size of the hexagon
     * @param t the tile to draw
     */
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        int widthHex = s * 3 - 2;
        int heightHex = s * 2;
        int halfRowIdx = p.y + s;
        if (s < 2) throw new IllegalArgumentException("size must be greater than or equal to 2");
        if ((p.x + widthHex > world.length) || (p.x < 0))
            throw new IllegalArgumentException("hexagon's x position is out of the window");
        if ((p.y + heightHex> world[0].length) || (p.y < 0))
            throw new IllegalArgumentException("hexagon's y position is out of the window");
        for (int i = p.y; i < halfRowIdx; i++) {
            int expectedStarLength = s + 2 * (i - p.y);
            int startColIdx = p.x + (widthHex - expectedStarLength)/2;
            for (int j = startColIdx; j < startColIdx + expectedStarLength; j++) {
                world[j][i] = t;
            }
        }
        for (int i = halfRowIdx; i < p.y + heightHex; i++) {
            int expectedStarLength = widthHex - 2 * (i - halfRowIdx);
            int startColIdx = p.x + (widthHex - expectedStarLength)/2;
            for (int j = startColIdx; j < startColIdx + expectedStarLength; j++) {
                world[j][i] = t;
            }
        }
    }

    /**
     * Generate a random tile using RANDOM.
     *
     * @return TETile object
     */
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(6);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.FLOOR;
            case 3: return Tileset.GRASS;
            case 4: return Tileset.WATER;
            case 5: return Tileset.SAND;
            default: return Tileset.TREE;
        }
    }

    /**
     * Draw a column of hexagons with specified number and start position.
     *
     * @param world the world to draw in
     * @param p the left bottom position of the column of hexagons to draw
     * @param s the size of the hexagons
     * @param num total number of hexagons in a column
     */
    public static void drawColumn(TETile[][] world, Position p, int s, int num) {
        for (int i = 0; i < num; i++) {
            Position currp = new Position(p.x, p.y + i * s * 2);
            addHexagon(world, currp, s, randomTile());
        }
    }

    /**
     * Draw a tesselation of hexagons with random tile types.
     *
     * @param world the world to draw in
     * @param p the left bottom position of the map
     * @param s the size of the hexagons
     */
    public static void tesselateHexagon(TETile[][] world, Position p, int s) {
        Position[] positionArray = {new Position(p.x, p.y + s * 2),
            new Position(p.x + s * 2 - 1, p.y + s), new Position(p.x + s * 4 - 2, p.y),
            new Position(p.x + s * 6 - 3, p.y + s), new Position(p.x + s * 8 - 4, p.y + s * 2)};
        int[] numArray = {3, 4, 5, 4, 3};
        for (int i = 0; i < 5; i++)
            drawColumn(world, positionArray[i], s, numArray[i]);
    }

    public static void main(String[] args) {
        final int WIDTH = 60;
        final int HEIGHT = 30;
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        tesselateHexagon(world, new Position(0, 0), 3);
        ter.renderFrame(world);
    }
}
