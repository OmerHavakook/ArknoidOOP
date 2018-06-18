/**
 * @author Omer Havakook <darhavakook@gmail.com>
 * @version 1.6
 * @since 2010-03-31
* */
public class DescribeNumbers {
    /**
     * the function change an array of strings 
     * to numbers.
     * @param numbers - array of strings.
     * @return an array of numbers.
     */
    public static int[] stringsToInts(String[] numbers) {
        int[] resArr = new int[numbers.length];
        for (int i = 0; i < resArr.length; i++) {
            resArr[i] = Integer.parseInt(numbers[i]);
        }
        return resArr;

    }
    /**
     * the function find the min number
     * in array.
     * @param numbers - array of numbers.
     * @return int number.
     */
    public static int min(int[] numbers) {
        int min = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        return min;
    }
    /**
     * the function find the max number
     * in array.
     * @param numbers - array of numbers.
     * @return int number.
     */
    public static int max(int[] numbers) {
        int max = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        return max;

    }
    /**
     * the function find the averge in array.
     * @param numbers - array of numbers.
     * @return float number.
     */
    public static float avg(int[] numbers) {
        float sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        float average = sum / numbers.length;
        return average;
    }
    /**
     * 
     * @param args input from the user
     */
    public static void main(String[] args) {
        int[] arr = stringsToInts(args);
        System.out.println("min: " + min(arr));
        System.out.println("max: " + max(arr));
        System.out.println("avg: " + avg(arr));

    }

}
