import java.util.Arrays;

public class HW1 {
    public static void main(String[] args) {
        int[] arr1 = { 10, 9, 2, 5, 3, 1010, 7, 18 };
        int[] arr2 = { 1, 3, 2, 5, 8, 7, 9 };
        int[] arr3 = { 4, 43, 5, 7, 2, 6 };
        int[] arr4 = { 3, 5, 2, 5, 5, 3, 4, 3, 2, 9, 4, 5, 5, 2, 2, 6, 7, 45, 6, 45, 23, 5, 6, 7, 34, 3, 24, 6, 3, 34,
                5, 34, 8, 65, 3, 34, 23, 5, 7, 6, 34, 6 };

        System.out.println(longestIncreasingSubsequence(arr1));
        // System.out.println(longestIncreasingSubsequence(arr2));
        // System.out.println(longestIncreasingSubsequence(arr3));
        // System.out.println(longestIncreasingSubsequence(arr4));
    }

    /**
     * Return a a list of the longest increasing subsequences
     **/
    public static int longestIncreasingSubsequence(int[] arr) {
        if (arr.length == 1)
            return arr[0]; // UPDATE TO RETURN AN ARRAY
        else if (arr.length == 0)
            return 0; // UPDATE TO RETURN AN ARRAY

        int[] maxSubsequenceLengths = new int[arr.length];
        Arrays.fill(maxSubsequenceLengths, 1);

        // stores indexes of all previous largest subsequence indexes
        int[][] prevMaxIndexes = new int[arr.length][];
        prevMaxIndexes[0] = new int[0];

        /*
         * prevMaxIndexes = [
         * [],
         * [-1],
         * [-1, -1],
         * [-1, -1, -1],
         * [-1, -1, -1, -1]
         * ]
         */

        // [10, 9, 2, 5, 3, 1010, 7, 18]
        for (int i = 1; i < arr.length; i++) {
            int maxSubsequenceLength = -1; // max length of all subsequence up to index i
            prevMaxIndexes[i] = new int[i]; // index(es) of last element(s) of all max subsequence(s)
            Arrays.fill(prevMaxIndexes[i], -1); // fill prevMaxIndexes w/ sentinel values

            // get max length of subsequence(s) up to i
            for (int j = 0; j < i; j++) {
                int prevNum = arr[j];
                int currentNum = arr[i];
                int prevSubsequenceLength = maxSubsequenceLengths[j];

                if (currentNum > prevNum && prevSubsequenceLength > maxSubsequenceLength) {
                    maxSubsequenceLengths[i] = prevSubsequenceLength + 1;
                    maxSubsequenceLength = prevSubsequenceLength;
                }
            }

            // store last index(es) of preceding max subsequences
            for (int j = 0; j < i; j++) {
                int prevNum = arr[j];
                int currentNum = arr[i];

                if (currentNum > prevNum && maxSubsequenceLengths[j] >= maxSubsequenceLength)
                    prevMaxIndexes[i][j] = j;
            }
        }

        // for (int i = 0; i < prevMaxIndexes.length; i++) {
        // System.out.println(i + ": " + Arrays.toString(prevMaxIndexes[i]));
        // }

        return 0;
    }

    public static int[][] backtrack(int[] arr, int[][] indexes) {

        return new int[0][];
    }
}