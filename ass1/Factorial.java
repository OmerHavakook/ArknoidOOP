/**
 * @author Omer Havakook <darhavakook@gmail.com>
 * @version 1.6
 * @since 2010-03-31
* */
public class Factorial {
    /**
     * the function calculate factorial.
     * @param n - long positive number.
     * @return a long number.
     */
    public static long factorialIter(long n) {
        long f = 1;
        for (int i = 1; i <= n; i++) {
            f = f * i;
        }
        return f;
    }
    /**
     * the function calculate factorial recursive.
     * @param n - long positive number.
     * @return a long number.
     */
    public static long factorialRecursive(long n) {
        if (n == 1) {
            return 1;
        } else {
            return (n * factorialRecursive(n - 1));
        }
    }
    /**
     *
     * @param args input from the user
     */
    public static void main(String[] args) {
        int res = Integer.parseInt(args[0]);
        System.out.println("recursive: " + factorialRecursive(res));
        System.out.println("iterative: " + factorialIter(res));
    }
}
