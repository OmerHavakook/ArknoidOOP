package geometry;

import java.util.List;

/**
 * A Line class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class Line {

    private Point start;
    private Point end;

    private double m, b;

    /**
     * Construct a line given two points.
     *
     * @param start
     *            the start point.
     * @param end
     *            the end point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.m = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
        this.b = this.start.getY() - (this.m * this.start.getX());

    }

    /**
     * Construct a line given 4 coordinates.
     *
     * @param x1
     *            the x coordinate of start point.
     * @param x2
     *            the x coordinate of end point.
     * @param y1
     *            the y coordinate of start point.
     * @param y2
     *            the y coordinate of end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point s = new Point(x1, y1);
        Point e = new Point(x2, y2);
        this.start = s;
        this.end = e;
        this.m = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
        this.b = this.start.getY() - (this.m * this.start.getX());

    }

    /**
     *
     * @return the length to the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     *
     * @return the point middle of the line.
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     *
     * @return the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     *
     * @return the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * @param p
     *            point to check if it on line.
     * @return true if the point on line and false otherwise.
     */
    public boolean isOnLine(Point p) {

        if (p.getX() <= Math.max(this.start.getX(), this.end.getX())
                && p.getX() >= Math.min(this.start.getX(), this.end.getX())
                && p.getY() <= Math.max(this.start.getY(), this.end.getY())
                && p.getY() >= Math.min(this.start.getY(), this.end.getY())) {
            return true;
        }
        return false;
    }

    /**
     * @param other
     *            line to check if intersecting.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {

        if (this.m == other.m) {
            return false;
        }

        if (this.intersectionWith(other) == null) {
            return false;
        }
        return true;
    }

    /**
     * @param other
     *            line to check if intersecting.
     * @return the intersection point if the lines intersect, and null
     *         otherwise.
     */
    public Point intersectionWith(Line other) {
        double x, y;
        if (this.m == other.m) {
            return null;
        } else if (this.start.getX() != this.end.getX() && other.start.getX() != other.end.getX()) {
            x = (other.b - this.b) / (this.m - other.m);
            y = (this.m * x) + this.b;
        } else if (this.start.getX() == this.end.getX()) {
            x = this.start.getX();
            y = (other.m * x) + other.b;
        } else {
            x = other.start.getX();
            y = (this.m * x) + this.b;
        }
        Point p = new Point(x, y);
        if (this.isOnLine(p) && other.isOnLine(p)) {
            return p;
        }
        return null;

    }

    /**
     * check which line is intersect.
     *
     * @param lines
     *            an array of lines to check.
     * @return a boolean array of which line is intersect.
     */
    public final boolean[] whichLinesIntersect(final Line[] lines) {
        boolean[] intersectingLines = new boolean[lines.length];
        for (int i = 0; i < lines.length; i++) {
            intersectingLines[i] = this.isIntersecting(lines[i]);
        }
        return intersectingLines;
    }

    /**
     * @param other
     *            line to check if equals.
     * @return true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        boolean res1 = this.isIntersecting(other);
        Point res2 = this.intersectionWith(other);
        if (!res1) {
            if (res2 != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * calculate the closest intersection point to to the start point from
     * rectangle.
     *
     * @param rect
     *            the rectangle that intersect.
     * @return intersection point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {

        List<Point> points = rect.intersectionPoints(new Line(this.start, this.end));
        Point retP = null;
        if (points.isEmpty()) {
            return null;
        } else {
            double dis = Double.MAX_VALUE;
            for (Point p : points) {
                if (p != null) {
                    if (p.distance(this.start) < dis) {
                        retP = p;
                        dis = p.distance(this.start);
                    }
                }
            }
        }
        return retP;
    }

}
