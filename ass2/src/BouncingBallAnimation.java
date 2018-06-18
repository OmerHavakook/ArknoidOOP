import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * A BouncingBallAnimation class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class BouncingBallAnimation {
    /**
     *
     * @param args
     *            input from the user
     */
    public static void main(String[] args) {

        GUI gui = new GUI("title", 400, 400);
        biuoop.Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(120, 30, 30, java.awt.Color.BLACK);
        ball.setVelocity(2, 2);
        ball.setBoundary(0, 400, 0, 400);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50); // wait for 50 milliseconds.
        }
    }
}
