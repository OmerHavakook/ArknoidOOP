/**
 * A Point class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class Point {

    private double x;
    private double y;

    /**
     * Construct a point given x and y coordinates.
     *
     * @param x
     *            the x coordinate.
     * @param y
     *            the y coordinate.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param other
     *            point to measure the distance to.
     * @return the distance to the other point.
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * @param other
     *            point to equal between two points.
     * @return true if equals and false otherwise.
     */
    public boolean equals(Point other) {
        if (this.x == other.x && this.y == other.y) {
            return true;
        }
        return false;
    }

    /**
     * @return the x coordinate.
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y coordinate.
     */
    public double getY() {
        return this.y;
    }
    /**
     * set x member.
     * @param x the x value.
     */
    public void setX(double x) {
        this.x = x;
    }
    /**
     * set y member.
     * @param y the y member.
     */
    public void setY(double y) {
        this.y = y;
    }
}
