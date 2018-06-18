package score;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * HighScoresTable class.
 *
 */
public class HighScoresTable implements Serializable {
    private List<ScoreInfo> scores;
    private int size;

    /**
     * constructor.
     */
    public HighScoresTable() {
        this.scores = new ArrayList<ScoreInfo>();
        this.size = 0;
    }

    /**
     * constructor by size.
     *
     * @param size
     *            int.
     */
    public HighScoresTable(int size) {
        this.scores = new ArrayList<ScoreInfo>(size);
        this.size = size;
    }

    /**
     * add score.
     *
     * @param score
     *            score info.
     */
    public void add(ScoreInfo score) {
        scores.add(score);
        this.sortHighScore();
        if (scores.size() > this.size) {
            this.scores.remove(this.size);
        }

    }

    /**
     * sort high score.
     */
    private void sortHighScore() {
        Collections.sort(this.scores);
    }

    /**
     * @return size.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * @return hish score list.
     */
    public List<ScoreInfo> getHighScores() {
        return this.scores;
    }

    /**
     * return rank of score.
     *
     * @param score
     *            score info.
     * @return int.
     */
    public int getRank(int score) {
        for (int i = 0; i < this.scores.size(); i++) {
            if (this.scores.get(i).getScore() <= score) {
                return i + 1;
            }
        }
        return this.scores.size();
    }

    /**
     * clear table.
     */
    public void clear() {
        this.scores.clear();
    }

    /**
     * load table from file.
     *
     * @param filename
     *            file.
     * @throws IOException
     *             io exp.
     */
    public void load(File filename) throws IOException {
        this.clear();
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(filename));
            HighScoresTable loadedTable = (HighScoresTable) ois.readObject();
            this.scores = loadedTable.scores;
            this.size = loadedTable.size;

        } catch (IOException ioexe) {
            throw ioexe;
        } catch (ClassNotFoundException e) {
            System.out.print("not good!: ClassNotFoundException");
        } finally {

            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ioex) {
                throw ioex;
            }
        }
    }

    /**
     * save table.
     *
     * @param filename
     *            file.
     * @throws IOException
     *             io exp.
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(this);
        } catch (IOException ioexe) {
            throw ioexe;
        } finally {

            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ioex) {
                throw ioex;
            }
        }
    }

    /**
     * load from file.
     *
     * @param filename
     *            file.
     * @return High score table.
     */
    public static HighScoresTable loadFromFile(File filename) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(filename));
            return (HighScoresTable) ois.readObject();
        } catch (IOException ioexe) {
            return new HighScoresTable();
        } catch (ClassNotFoundException e) {
            System.out.print("ClassNotFoundException");
            return new HighScoresTable();
        } finally {

            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ioex) {
                return new HighScoresTable();
            }
        }
    }

}
