package sprites;

import biuoop.DrawSurface;
import levels.LevelInformation;

/**
 * A LevelNameIndicator class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class LevelNameIndicator implements Sprite {

    private LevelInformation name;

    /**
     * Basic Constructor.
     *
     * @param name
     *            name of level.
     */
    public LevelNameIndicator(LevelInformation name) {
        this.name = name;
    }

    /**
     * draws level name on give drawsurface.
     *
     * @param d
     *            the drawsurface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        String levelName = "Level Name:" + this.name.levelName();
        d.drawText(600, 12, levelName, 15);
    }

    /**
     * notify this obj that timepass.
     * 
     */
    @Override
    public void timePassed() {
        return;
    }

}
