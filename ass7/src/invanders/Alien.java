package invanders;

import java.awt.Image;

import biuoop.DrawSurface;
import collisionsystem.Block;
import geometry.Point;
import geometry.Rectangle;
/**
 * A Alien class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class Alien extends Block {
    private Image image;
    private double xStart;
    private double yStart;
    private Rectangle start;

    /**
     * this is the constructor for Enemy.
     *
     * @param x
     *            - as the x coordinate
     * @param y
     *            - as the y coordinate
     * @param image
     *            - as the image of the enemy
     */
    public Alien(int x, int y, Image image) {
        super(x, y);
        this.xStart = x;
        this.yStart = y;
        this.image = image;
        this.start = this.getCollisionRectangle();
    }

    /**
     * this method moves the enemy.
     *
     * @param dx
     *            - as the dx movmenent
     * @param dt
     *            - as a double
     */
    public void moveBy(double dx, double dt) {
        double dxChange = (dx * dt);
        double di = getCollisionRectangle().getUpperLeft().getX() + dxChange;
        this.xStart = di;
        this.yStart = getCollisionRectangle().getUpperLeft().getY();
        double width = getCollisionRectangle().getWidth();
        double height = getCollisionRectangle().getHeight();
        this.setRec(new Rectangle(new Point(xStart, yStart), width, height));
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.drawImage((int) xStart, (int) yStart, image);
    }

    /**
     * this method returns the start rectangle.
     *
     * @return the start rectangle
     */
    public Rectangle returnRect() {
        return this.start;
    }

    /**
     * this method set the location of the rectangle.
     *
     * @param rect
     *            - as a recatngle object
     */
    public void setRectangle(Rectangle rect) {
        this.xStart = (int) rect.getUpperLeft().getX();
        this.yStart = (int) rect.getUpperLeft().getY();
        this.setRec(new Rectangle(rect.getUpperLeft(), rect.getWidth(), rect.getHeight()));
    }

}
