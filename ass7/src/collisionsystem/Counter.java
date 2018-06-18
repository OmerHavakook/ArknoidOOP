package collisionsystem;

/**
 * A Counter class.
 *
 * @author Omer Havakook <darhavakook@gmail.com>
 */
public class Counter {

    private int count;

    /**
     * Default Constructor.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * Basic counstructor.
     *
     * @param count
     *            the current count.
     */
    public Counter(int count) {
        this.count = count;
    }

    /**
     * add number to current count.
     *
     * @param number
     *            the number to add.
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number
     *            the number to subtract.
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * get current count.
     *
     * @return count.
     */
    public int getValue() {
        return this.count;
    }
}
