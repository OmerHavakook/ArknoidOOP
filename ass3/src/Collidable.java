
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
     * @return new velocity for ball.
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);

}
