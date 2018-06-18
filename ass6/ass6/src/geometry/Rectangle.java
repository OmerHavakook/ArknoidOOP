package geometry;

import java.util.List;
import java.util.ArrayList;

/**
 * A Rectangle class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class Rectangle {

    private Point upperLeft, upperRight, bottomLeft, bottomRight;;
    private double width;
    private double height;
    private Line up, down, left, right;

    /**
     * @return the upper right point.
     */
    public Point getUpperRight() {
        return upperRight;
    }

    /**
     * @return the upper bottom left point.
     */
    public Point getBottomLeft() {
        return bottomLeft;
    }

    /**
     * @return the bottom right point.
     */
    public Point getBottomRight() {
        return bottomRight;
    }

    /**
     * creates a new rectangle.
     *
     * @param upperLeft
     *            the upperLeft point of the rectangle
     * @param width
     *            of the rectangle
     * @param height
     *            of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {

        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.setAllRectangleLines();
    }

    /**
     * set all the lines of the rectangle.
     */
    private void setAllRectangleLines() {
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.bottomLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.bottomRight = new Point(upperRight.getX(), bottomLeft.getY());
        this.up = new Line(this.upperLeft, this.upperRight);
        this.down = new Line(this.bottomLeft, this.bottomRight);
        this.right = new Line(this.upperRight, this.bottomRight);
        this.left = new Line(this.upperLeft, this.bottomLeft);
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified
     * line.
     *
     * @param line
     *            the line we check intersection with.
     * @return list with intersection points if exist.
     */
    public List<Point> intersectionPoints(Line line) {

        Line[] rectangleLines = {this.up, this.down, this.right, this.left };
        boolean[] intersectingLines = line.whichLinesIntersect(rectangleLines);
        List<Point> intersectionList = new ArrayList<Point>();
        for (int i = 0; i < 4; i++) {
            if (intersectingLines[i]) {
                intersectionList.add(line.intersectionWith(rectangleLines[i]));
            }
        }

        return intersectionList;
    }

    /**
     * set the x of the location.
     *
     * @param x
     *            the x value.
     */
    public void setX(double x) {
        upperLeft.setX(x);
        this.setAllRectangleLines();
    }

    /**
     * @return the width.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @return the up line.
     */
    public Line getUp() {
        return up;
    }

    /**
     * @return the down line.
     */
    public Line getDown() {
        return down;
    }

    /**
     * @return left line.
     */
    public Line getLeft() {
        return left;
    }

    /**
     * @return the right line.
     */
    public Line getRight() {
        return right;
    }

    /**
     * set the point upper left of rectangle.
     *
     * @param point
     *            point to change.
     */
    public void setUpperLeft(Point point) {
        this.upperLeft.setX(point.getX());
        this.upperRight.setY(point.getY());

    }
}
