package byog.Core;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Random;

/**
 * This is the class with actual implementations.
 */
public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    private Random rand;
    private Position hallWayP;
    private int hallWayW;
    private int hallWayH;
    private static int smaller;
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * This method will draw a single rectangle based on the position and size specified.
     *
     * @param world the world to be drawn on
     * @param p the position of the rectangle
     * @oaram w the width of the rectangle
     * @param h the height of the rectangle
     * @param first whether this is the first rectangle to be drawn on world
     * @return the world with the new rectangle
     */
    private TETile[][] drawOneRectangle(TETile[][] world, Position p, int w, int h, boolean first) {
        // check whether the rectangle is inside the window created
        if (p.x + w > world.length) {
            w = world.length - p.x;
        }
        if (p.y + h > world[0].length) {
            h = world[0].length - p.y;
        }
        if (p.x < 0 || p.y < 0 || h < 3 || w < 3) {
            throw new IllegalArgumentException("negative arguments");
        }
        TETile[][] copyWorld = TETile.copyOf(world);
        boolean flag = false;
        // draw the rectangle
        for (int i = p.x; i < p.x + w; i++) {
            for (int j = p.y; j < p.y + h; j++) {
                boolean edges = (i == p.x || i == p.x + w - 1 || j == p.y || j == p.y + h - 1);
                if (edges && world[i][j] != Tileset.FLOOR) {
                    copyWorld[i][j] = Tileset.WALL;
                } else if (world[i][j] == Tileset.FLOOR) {
                    flag = true;
                } else {
                    copyWorld[i][j] = Tileset.FLOOR;
                }
            }
        }
        if (flag || first) {
            return copyWorld;
        }
        throw new IllegalArgumentException("rectangle is not added");
    }

    /**
     * This method will draw one hallway on the direction specified
     *
     * @param world the world to be drawn on
     * @param d the direction of the hallway
     * @return the world with the new hallway
     * @throws IllegalArgumentException if fails to add the hallway
     */
    private TETile[][] drawOneHallWay(TETile[][] world, int d) throws IllegalArgumentException {
        Position newP = new Position(0, 0);
        int newW, newH;
        switch (d) {
            case 0: // above the original one
                newW = 3;
                newH = 3 + rand.nextInt(HEIGHT / 2);
                newP.x = hallWayP.x + rand.nextInt(hallWayW) - 1;
                newP.y = hallWayP.y + hallWayH - 3;
                break;
            case 1: // right side of the original one
                newW = 3 + rand.nextInt(WIDTH / 6);
                newH = 3;
                newP.x = hallWayP.x + hallWayW - 3;
                newP.y = hallWayP.y + rand.nextInt(hallWayH) - 1;
                break;
            case 2: // below the original one
                newW = 3;
                newH = 3 + rand.nextInt(HEIGHT / 2);
                newP.x = hallWayP.x + rand.nextInt(hallWayW) - 1;
                newP.y = hallWayP.y - newH + 3;
                break;
            default: // left side of the original one
                newW = 3 + rand.nextInt(WIDTH / 6);
                newH = 3;
                newP.x = hallWayP.x - newW + 3;
                newP.y = hallWayP.y + rand.nextInt(hallWayH) - 1;
        }
        world = drawOneRectangle(world, newP, newW, newH, false);
        hallWayP = newP;
        hallWayW = newW;
        hallWayH = newH;
        return world;
    }

    /**
     * This method will draw a group of connected hallways
     *
     * @param world the world to be drawn on
     * @param num the number of hallways to draw
     * @return the world with hallways added
     */
    private TETile[][] drawRandomHallways(TETile[][] world, int num) {
        hallWayP = new Position(rand.nextInt(WIDTH / 2), rand.nextInt(HEIGHT / 2));
        switch (rand.nextInt(2)) {
            case 0:
                hallWayW = 3;
                hallWayH = rand.nextInt(smaller);
                break;
            default:
                hallWayW = rand.nextInt(smaller);
                hallWayH = 3;
        }
        world = drawOneRectangle(world, hallWayP, hallWayW, hallWayH, true);
        while (num != 0) {
            try {
                int d = RandomUtils.discrete(rand, new int[]{1, 3, 1, 3});
                world = drawOneHallWay(world, d);
                --num;
            } catch (IllegalArgumentException e) {
                continue;
            }
        }
        return world;
    }

    /**
     * This method will draw a group of rooms
     *
     * @param world the world to be drawn on
     * @param num the desired number of rooms
     * @return the world with rooms added
     */
    private TETile[][] drawRandomRooms(TETile[][] world, int num) {
        while (num != 0) {
            int w = 4 + rand.nextInt(8);
            int h = 4 + rand.nextInt(8);
            Position p = new Position(rand.nextInt(WIDTH), rand.nextInt(HEIGHT));
            try {
                world = drawOneRectangle(world, p, w, h, false);
                --num;
            } catch (IllegalArgumentException e) {
                continue;
            }
        }
        return world;
    }

    /**
     * This method will return the world with random hallways and rooms
     *
     * @param world the world to be drawn on
     * @return the world with hallways and rooms
     */
    private TETile[][] drawRandomMap(TETile[][] world) {
        int num = HEIGHT * WIDTH / 25;
        if (HEIGHT > WIDTH) {
            smaller = WIDTH;
        } else{
            smaller = HEIGHT;
        }
        world = drawRandomHallways(world, num);
        world = drawRandomRooms(world, num / 10);
        return world;
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        String seed = input.substring(input.indexOf('N') + 1, input.indexOf('S'));
        rand = new Random(Integer.parseInt(seed));
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                finalWorldFrame[i][j] = Tileset.NOTHING;
            }
        }
        finalWorldFrame = drawRandomMap(finalWorldFrame);
        ter.renderFrame(finalWorldFrame);
        return finalWorldFrame;
    }
}
