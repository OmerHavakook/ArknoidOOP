import java.awt.Color;
import java.util.Random;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * A MultipleFramesBouncingBallsAnimation class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class MultipleFramesBouncingBallsAnimation {
    /**
     * Change an array of strings to numbers.
     *
     * @param numbers
     *            - array of strings.
     * @return an array of numbers.
     */
    public static int[] stringsToInts(String[] numbers) {
        int[] resArr = new int[numbers.length];
        for (int i = 0; i < resArr.length; i++) {
            resArr[i] = Integer.parseInt(numbers[i]);
        }
        return resArr;

    }

    /**
     * Set velocity of ball of by given radius.
     *
     * @param r
     *            radius of ball.
     * @param b
     *            ball to change.
     */
    public static void setVelocityBySize(int r, Ball b) {
        Random rand = new Random();
        if (r >= 50) {
            b.setVelocity(Velocity.fromAngleAndSpeed(rand.nextInt(360), 2));
        } else {
            b.setVelocity(Velocity.fromAngleAndSpeed(rand.nextInt(360), (10 * Math.PI) / r));
        }
    }

    /**
     *
     * @param args
     *            input from the user
     */
    public static void main(String[] args) {
        GUI gui = new GUI("title", 700, 700);
        biuoop.Sleeper sleeper = new Sleeper();
        int[] numbers = MultipleFramesBouncingBallsAnimation.stringsToInts(args);
        Ball[] balls = new Ball[numbers.length];
        DrawSurface d = gui.getDrawSurface();
        for (int i = 0; i < balls.length / 2; i++) {
            balls[i] = new Ball((int) (Math.random() * (500 - 50 + 2 * numbers[i]) + 50 - 2 * numbers[i]),
                    (int) (Math.random() * (500 - 50 + 2 * numbers[i]) + 50 - 2 * numbers[i]), numbers[i],
                    java.awt.Color.BLACK);
            balls[i].setBoundary(50, 500, 50, 500);
            MultipleFramesBouncingBallsAnimation.setVelocityBySize(numbers[i], balls[i]);
        }
        for (int i = balls.length / 2; i < balls.length; i++) {
            balls[i] = new Ball((int) (Math.random() * (600 - 450 + 2 * numbers[i]) + 450 - 2 * numbers[i]),
                    (int) (Math.random() * (600 - 450 + 2 * numbers[i]) + 450 - 2 * numbers[i]), numbers[i],
                    java.awt.Color.BLACK);
            balls[i].setBoundary(450, 600, 450, 600);
            MultipleFramesBouncingBallsAnimation.setVelocityBySize(numbers[i], balls[i]);
        }
        while (true) {
            d = gui.getDrawSurface();
            d.setColor(Color.GRAY);
            d.fillRectangle(50, 50, 450, 450);
            d.setColor(Color.YELLOW);
            d.fillRectangle(450, 450, 150, 150);
            for (int j = 0; j < numbers.length; j++) {
                balls[j].moveOneStep();
                balls[j].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);
        }
    }
}
