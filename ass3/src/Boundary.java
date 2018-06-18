/**
 * A Boundary class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class Boundary {

    private int upBound;
    private int downBound;
    private int leftBound;
    private int rightBound;

    /**
     * Construct a Boundary by given 4 coordinates.
     *
     * @param u
     *            the up boundary.
     * @param d
     *            the down boundary.
     * @param l
     *            the left boundary.
     * @param r
     *            the right boundary.
     */
    public Boundary(int u, int d, int l, int r) {
        this.upBound = u;
        this.downBound = d;
        this.leftBound = l;
        this.rightBound = r;
    }

    /**
     * @return the up boundary.
     */
    public int getUpBound() {
        return this.upBound;
    }

    /**
     * @return the down boundary.
     */
    public int getDownBound() {
        return this.downBound;
    }

    /**
     * @return the left boundary.
     */
    public int getLeftBound() {
        return this.leftBound;
    }

    /**
     * @return the right boundary.
     */
    public int getRightBound() {
        return this.rightBound;
    }

}
