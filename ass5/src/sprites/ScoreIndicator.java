package sprites;

import java.awt.Color;

import biuoop.DrawSurface;
import collisionsystem.Counter;
import gamefiles.GameLevel;

/**
 * A ScoreIndicator class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Basic Constructor.
     *
     * @param score
     *            the Current Score.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * draws the indicator on given drawsurface.
     *
     * @param d
     *            the drawsurface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        String scorestring;
        scorestring = "Score: " + String.valueOf(this.score.getValue());
        d.drawText(GameLevel.WIDTH / 2, 12, scorestring, 15);

    }

    /**
     * notify the sprite that time has passed.
     * 
     * @param dt
     *            the speed change dt.
     */
    @Override
    public void timePassed() {
    }

}
