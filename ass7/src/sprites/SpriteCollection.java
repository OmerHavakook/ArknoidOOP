package sprites;

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * A SpriteCollection class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class SpriteCollection {

    private ArrayList<Sprite> listOfSprites = new ArrayList<Sprite>();

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
     *
     * @param dt
     *            double.
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < this.listOfSprites.size(); i++) {
            this.listOfSprites.get(i).timePassed(dt);
        }
    }

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

    /**
     * get list of sprites.
     *
     * @return listOfSprites.
     */
    public List<Sprite> getListOfSprites() {
        return listOfSprites;
    }

}
