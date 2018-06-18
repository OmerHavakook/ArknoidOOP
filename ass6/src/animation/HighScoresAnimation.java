package animation;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import score.HighScoresTable;
import score.ScoreInfo;

/**
 * A HighScoresAnimation class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class HighScoresAnimation implements Animation {

    private HighScoresTable scores;
    private boolean running;
    private KeyboardSensor keyboardSensor;
    private Image image1 = null;
    private Image image2 = null;
    private Image image3 = null;

    /**
     * Basic constructor.
     *
     * @param scores
     *            high score table.
     * @param keyboardSensor
     *            key board.
     */
    public HighScoresAnimation(HighScoresTable scores, KeyboardSensor keyboardSensor) {
        this.scores = scores;
        this.running = false;
        this.keyboardSensor = keyboardSensor;
        try {
            image1 = ImageIO.read(
                    ClassLoader.getSystemClassLoader().getResourceAsStream("background_images/startbackground.jpg"));
            image2 = ImageIO
                    .read(ClassLoader.getSystemClassLoader().getResourceAsStream("background_images/high1.png"));
            image3 = ImageIO
                    .read(ClassLoader.getSystemClassLoader().getResourceAsStream("background_images/high2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawImage(0, 0, image1);
        d.drawImage(d.getWidth() / 3 - 30, d.getHeight() / 6 - 50, image2);
        d.drawImage(d.getWidth() / 3 - 30, d.getHeight() / 6, image3);
        if (!scores.getHighScores().isEmpty()) {
            int y = 0;
            for (ScoreInfo score : this.scores.getHighScores()) {
                String scoreString = score.getName() + "     " + String.valueOf(score.getScore());
                d.drawText(d.getWidth() / 3, d.getHeight() / 6 + 70 + y, scoreString, 30);
                y += 75;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.running;
    }
}
