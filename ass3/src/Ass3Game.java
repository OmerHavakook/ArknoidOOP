

/**
 * A Ass3Game class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class Ass3Game {

    /**
    *
    * @param args
    *            input from the user
    */
    public static void main(String[] args) {
        Game game = new Game(800, 600);
        game.initialize();
        game.run();
    }
}
