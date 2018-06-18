
package sprites;

import java.awt.Image;

import biuoop.DrawSurface;

/**
 * A Background class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class Background implements Sprite {

    private SpriteCollection background;
    private Image image;

    /**
     * Basic Constructor.
     */
    public Background() {
        this.background = new SpriteCollection();
        this.image = null;
    }

    /**
     * Image Background constructor.
     *
     * @param image
     *            the image for background.
     */
    public Background(Image image) {
        this.image = image;
        this.background = null;
    }

    @Override
    public void drawOn(DrawSurface d) {
        if (image != null) {
            d.drawImage(0, 0, image);
        }

        if (background != null) {
            background.drawAllOn(d);
        }
    }

    @Override
    public void timePassed(double dt) {
        return;
    }

    /**
     * add new sprite.
     *
     * @param s
     *            sprite.
     */
    public void addSprite(Sprite s) {
        this.background.addSprite(s);
    }

}
