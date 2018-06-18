package collisionsystem;

import biuoop.DrawSurface;
import gamefiles.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;
import sprites.Sprite;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A Block class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle rec;
    private Color color;
    private List<HitListener> listOfhitListener;
    private int hits;
    private Color strokeColor = null;
    private Map<Integer, Color> colorForBlock;
    private Map<Integer, Image> imageForBlock;
    public static final int FIXEDSIZE = 20;
    private static final Color DEFAULTCOLOR = new Color(224, 224, 224);

    /**
     * Construct a block given recteangle.
     *
     * @param rec
     *            the shape of the block
     */
    public Block(Rectangle rec) {
        this.rec = rec;
        this.listOfhitListener = new ArrayList<HitListener>();
        this.color = DEFAULTCOLOR;
    }

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
        this.listOfhitListener = new ArrayList<HitListener>();
    }

    /**
     * constructor with image and color.
     *
     * @param rectangle
     *            the shape of the block.
     * @param colorForBlock
     *            map.
     * @param imageForBlock
     *            map.
     * @param strokeColor
     *            color.
     * @param counterOfHit
     *            counter.
     */
    public Block(Rectangle rectangle, Map<Integer, Color> colorForBlock, Map<Integer, Image> imageForBlock,
            Color strokeColor, int counterOfHit) {
        this.rec = rectangle;
        this.colorForBlock = colorForBlock;
        this.imageForBlock = imageForBlock;
        this.strokeColor = strokeColor;
        this.listOfhitListener = new ArrayList<HitListener>();
        this.hits = counterOfHit;
    }

    /**
     * draw the block.
     *
     * @param d
     *            the draw surface.
     */
    public void drawOn(DrawSurface d) {
        int x = (int) this.getCollisionRectangle().getUpperLeft().getX();
        int y = (int) this.getCollisionRectangle().getUpperLeft().getY();
        int currentHitPoint = this.getHits();

        if (this.colorForBlock != null) {
            if (this.colorForBlock.containsKey(currentHitPoint)) {
                d.setColor(this.colorForBlock.get(currentHitPoint));
            } else {
                d.setColor(this.colorForBlock.get(0));
            }
            d.fillRectangle(x, y, (int) this.getCollisionRectangle().getWidth(),
                    (int) this.getCollisionRectangle().getHeight());

        } else if (this.imageForBlock == null && this.color != null) {
            d.setColor(this.color);
            d.fillRectangle(x, y, (int) this.getCollisionRectangle().getWidth(),
                    (int) this.getCollisionRectangle().getHeight());
        } else {
            if (this.imageForBlock.containsKey(currentHitPoint)) {
                d.drawImage(x, y, this.imageForBlock.get(currentHitPoint));
            } else {
                d.drawImage(x, y, this.imageForBlock.get(0));
            }
        }

        if (this.strokeColor != null) {
            d.setColor(this.strokeColor);
            d.drawRectangle(x, y, (int) this.getCollisionRectangle().getWidth(),
                    (int) this.getCollisionRectangle().getHeight());
        }
    }

    @Override
    public void timePassed(double dt) {
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
     * update the amount of hit.
     */
    public void hitInBlock() {
        if (this.hits > 0) {
            this.hits -= 1;
        }
    }

    /**
     * Calculate the new velocity after hit in the block.
     *
     * @param collisionPoint
     *            the collision point in the block.
     * @param currentVelocity
     *            the velocity of the ball.
     * @param hitter
     *            the hitter obj.
     * @return new velocity for ball.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
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
        this.hitInBlock();
        this.notifyHit(hitter);
        return newVelocity;
    }

    /**
     * add the block to the game.
     *
     * @param g
     *            the game parameter.
     */
    public void addToGame(GameLevel g) {
        g.addSprite((Sprite) this);
        g.addCollidable((Collidable) this);
    }

    /**
     * removes this block from game.
     *
     * @param game
     *            the game to remove from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * add Listener to this block.
     *
     * @param h
     *            the HitListener to add.
     */
    public void addHitListener(HitListener h) {
        this.listOfhitListener.add(h);
    }

    /**
     * removes listner from this.
     *
     * @param h
     *            the h to remove.
     */
    public void removeHitListener(HitListener h) {
        this.listOfhitListener.remove(h);
    }

    /**
     * notifies block that hit occured.
     *
     * @param hitter
     *            the hitting object.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.listOfhitListener);
        // Notify all listeners about a hit event:
        for (HitListener h : listeners) {
            h.hitEvent(this, hitter);
        }
    }

    /**
     * @return the hits.
     */
    public int getHits() {
        return this.hits;
    }

}
