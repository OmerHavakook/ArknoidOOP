import biuoop.DrawSurface;
import java.awt.Color;

/**
 * A Block class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class Block implements Collidable, Sprite {

    private Rectangle rec;
    private Color color;
    private int hits;

    /**
     * Construct a block given recteangle color and hits.
     *
     * @param rec
     *            the shape of the block.
     * @param color
     *            the color of the block.
     * @param hits
     *            the number of hits on the block.
     */
    public Block(Rectangle rec, java.awt.Color color, int hits) {
        this.rec = rec;
        this.color = color;
        this.hits = hits;
    }

    /**
     * draw the block.
     *
     * @param surface the draw surface.
     */
    public void drawOn(DrawSurface surface) {
        String s;
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
        surface.setColor(Color.BLACK);
        if (this.hits > 0) {
            s = Integer.toString(this.hits);
        } else {
            s = "x";
        }
        surface.drawText((int) (this.rec.getUpperLeft().getX() + (-2) + this.rec.getWidth() / 2),
                (int) (this.rec.getUpperLeft().getY() + 2 + this.rec.getHeight() / 2), s, 15);
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());

    }

    /**
     * time pass method for block. no implimention for now.
     */
    public void timePassed() {
    }

    /**
     * @return color of block.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * @return rectangle of block.
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    /**
     * Calculate the new velocity after hit in the block.
     *
     * @param collisionPoint
     *            the collision point in the block.
     * @param currentVelocity
     *            the velocity of the ball.
     * @return new velocity for ball.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        this.hits--;
        Velocity newVelocity = currentVelocity;
        double startX = this.rec.getUpperLeft().getX();
        double startY = this.rec.getUpperLeft().getY();
        double x = collisionPoint.getX();
        double y = collisionPoint.getY();
        if (collisionPoint.equals(this.rec.getUpperLeft()) || collisionPoint.equals(this.rec.getUpperRight())
                || collisionPoint.equals(this.rec.getBottomLeft())
                || collisionPoint.equals(this.rec.getBottomRight())) {
            newVelocity.setDx(currentVelocity.getDX() * (-1));
            newVelocity.setDy(currentVelocity.getDY() * (-1));
        } else if ((x > startX) && (x < startX + this.rec.getWidth())) {
            newVelocity.setDy((currentVelocity.getDY()) * (-1));
        } else if ((y > startY) && (y < startY + this.rec.getHeight())) {
            newVelocity.setDx(currentVelocity.getDX() * (-1));
        }
        return newVelocity;
    }

    /**
     * add the block to the game.
     * @param g the game parameter.
     */
    public void addToGame(Game g) {
        g.addSprite((Sprite) this);
        g.addCollidable((Collidable) this);
    }

}
