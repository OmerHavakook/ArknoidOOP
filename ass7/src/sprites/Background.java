
package sprites;

import java.awt.Color;
import java.awt.Image;

import biuoop.DrawSurface;

/**
 * A Background class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class Background implements Sprite {

    private Image image;

    /**
     * this is the constructor for BackroundBlack.
     *
     * @param image
     *            - as an image to draw at the backround
     */
    public Background(Image image) {
        this.image = image;
    }

    @Override
    public void drawOn(DrawSurface d) {
        if (this.image == null) {
            d.setColor(Color.BLACK);
            d.fillRectangle(0, 0, 800, 600);
        }
        d.drawImage(0, 0, this.image);

    }

    @Override
    public void timePassed(double dt) {
        return;
    }

}
