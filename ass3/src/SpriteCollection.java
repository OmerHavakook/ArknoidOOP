import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * A SpriteCollection class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class SpriteCollection {

    private List<Sprite> listOfSprites = new ArrayList<Sprite>();

    /**
     * add sprite to the collection.
     *
     * @param s
     *            the sprite.
     */
    public void addSprite(Sprite s) {
        this.listOfSprites.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        if (!(this.listOfSprites.isEmpty())) {
            for (Sprite s : this.listOfSprites) {
                s.timePassed();
            }
        }
    }

    // call drawOn(d) on all sprites.
    /**
     * call drawOn(d) on all sprites.
     *
     * @param d
     *            the draw surface.
     */
    public void drawAllOn(DrawSurface d) {
        if (!(this.listOfSprites.isEmpty())) {
            for (Sprite s : this.listOfSprites) {
                s.drawOn(d);
            }
        }
    }

}
