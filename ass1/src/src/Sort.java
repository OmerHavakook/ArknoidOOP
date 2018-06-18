/**
 * @author Omer Havakook <darhavakook@gmail.com>
 * @version 1.6
 * @since 2010-03-31
* */
public class Sort {
    /**
     * the function change an array of strings 
     * to numbers from the 2nd place.
     * @param numbers - array of strings.
     * @return an array of numbers.
     */
    public static int[] stringsToInts(String[] numbers) {
        int[] resArr = new int[numbers.length - 1];
        for (int i = 0; i < resArr.length; i++) {
            resArr[i] = Integer.parseInt(numbers[i + 1]);
        }
        return resArr;

    }
    /**
     * the function sorted ascending order
     * an array of numbers.
     * @param numbers - array of numbers.
     * @return an array of numbers.
     */
    public static int[] bubbleSort(int[] numbers) {
        int n = numbers.length;
        int swap;
        for (int c = 0; c < (n - 1); c++) {
            for (int d = 0; d < n - c - 1; d++) {
                if (numbers[d] > numbers[d + 1]) {
                    swap = numbers[d];
                    numbers[d] = numbers[d + 1];
                    numbers[d + 1] = swap;
                }
            }
        }
        return numbers;
    }
    /**
     * 
     * @param args input from the user
     */
    public static void main(String[] args) {
        int[] arr = stringsToInts(args);
        if (args[0].equals("asc")) {
            bubbleSort(arr);
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }

        } else if (args[0].equals("desc")) {
            bubbleSort(arr);
            for (int i = (arr.length - 1); i >= 0; i--) {
                System.out.print(arr[i] + " ");
            }
        }

    }
}
