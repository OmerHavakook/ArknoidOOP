/**
 * A CollisionInfo class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class CollisionInfo {

    private Point collisionPoint;

    private Collidable collisionObject;

    /**
     * Construct the collision info.
     *
     * @param collisionPoint2
     *            collision point.
     * @param collisionObject2
     *            collidable object.
     */
    public CollisionInfo(Point collisionPoint2, Collidable collisionObject2) {
        this.collisionPoint = collisionPoint2;
        this.collisionObject = collisionObject2;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }

}
