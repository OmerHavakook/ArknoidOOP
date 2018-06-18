package gamefiles;

import java.util.ArrayList;
import java.util.List;

import collisionsystem.Collidable;
import collisionsystem.CollisionInfo;
import geometry.Line;
import geometry.Point;

/**
 * A GameEnvironment class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class GameEnvironment {

    private List<Collidable> listOfCollidable;

    /**
     * construct the GameEnvironment.
     */
    public GameEnvironment() {
        this.listOfCollidable = new ArrayList<Collidable>();
    }

    /**
     * add collidable to the list.
     *
     * @param c
     *            the collidable.
     */
    public void addCollidable(Collidable c) {
        this.listOfCollidable.add(c);
    }

    /**
     * calculate the closet collision point on the line.
     *
     * @param trajectory
     *            the move line of ball.
     * @return collision info.
     *
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        int j = -1;
        List<Point> points = new ArrayList<Point>();
        for (Collidable c : this.listOfCollidable) {
            points.add(trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle()));
        }

        double dis = Double.MAX_VALUE;

        for (int i = 0; i < points.size(); i++) {
            if (points.get(i) != null) {
                if (points.get(i).distance(trajectory.start()) < dis) {
                    dis = points.get(i).distance(trajectory.start());
                    j = i;
                }
            }

        }
        if (j != -1) {
            return new CollisionInfo(points.get(j), this.listOfCollidable.get(j));
        }
        return null;

    }

    /**
     * get the list of coliiadabels.
     * 
     * @return
     */
    public List<Collidable> getListOfCollidable() {
        return listOfCollidable;
    }

}
