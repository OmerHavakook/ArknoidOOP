package gamefiles;

/**
 * Task interface.
 *
 * @param <T>
 *            generic.
 */
public interface Task<T> {
    /**
     * run the task.
     *
     * @return T.
     */
    T run();

}
