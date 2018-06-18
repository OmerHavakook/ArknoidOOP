package levels;

/**
 * LevelSet class.
 *
 */
public class LevelSet {
    private String name;
    private String path;

    /**
     * constructor.
     *
     * @param name
     *            string.
     * @param path
     *            string.
     */
    public LevelSet(String name, String path) {
        this.name = name;
        this.path = path;
    }

    /**
     * @return name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return path.
     */
    public String getPath() {
        return this.path;
    }
}
