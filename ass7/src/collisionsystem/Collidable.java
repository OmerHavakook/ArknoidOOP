package collisionsystem;

import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;

/**
 * A Collidable interface.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public interface Collidable {

    /**
     * return the rectangle of the collidable.
     *
     * @return Rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * @param collisionPoint
     *            the collision point on the collidable.
     * @param currentVelocity
     *            the velocity of ball.
     * @param hitter
     *            the hitter obj.
     * @return new velocity for ball.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

}
