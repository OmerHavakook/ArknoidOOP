package score;

import java.io.Serializable;

/**
 * ScoreInfo class.
 *
 */
public class ScoreInfo implements Serializable, Comparable {
    private int score;
    private String name;

    /**
     * constructor.
     *
     * @param name
     *            string.
     * @param score
     *            int.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * basic constructor.
     */
    public ScoreInfo() {
        this.name = null;
        this.score = 0;
    }

    @Override
    public String toString() {
        return this.name + " " + String.valueOf(this.score);
    }

    /**
     * @return name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return score.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * set name.
     *
     * @param name1
     *            string.
     */
    public void setName(String name1) {
        this.name = name1;
    }

    @Override
    public int compareTo(Object o) {
        return ((ScoreInfo) o).getScore() - this.score;
    }

    /**
     * reset score.
     */
    public void reset() {
        this.score = 0;
        this.name = null;
    }

}
