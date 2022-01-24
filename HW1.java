import java.util.ArrayList;

public class HW1 {
    public static void main(String[] args) {
        int[] arr1 = { 10, 9, 2, 5, 3, 1010, 7, 18 };
        int[] arr2 = { 1, 3, 2, 5, 8, 7, 9 };
        int[] arr3 = { 4, 43, 5, 7, 2, 6 };
        int[] arr4 = { 3, 5, 2, 5, 5, 3, 4, 3, 2, 9, 4, 5, 5, 2, 2, 6, 7, 45, 6, 45, 23, 5, 6, 7, 34, 3, 24, 6, 3, 34,
                5, 34, 8, 65, 3, 34, 23, 5, 7, 6, 34, 6 };

        System.out.println(longestIncreasingSubsequenceBruteForce(arr1));
        System.out.println(longestIncreasingSubsequenceBruteForce(arr2));
        System.out.println(longestIncreasingSubsequenceBruteForce(arr3));
        System.out.println(longestIncreasingSubsequenceBruteForce(arr4));
    }

    // public static int longestIncreasingSubsequence(int[] arr) {
    // // list of all increasing subsequences
    // ArrayList<ArrayList<Integer>> increasingSubsequences = new
    // ArrayList<ArrayList<Integer>>();

    // // fill each subsequence with the corresponding number in the array
    // for (int i = 0; i < arr.length; ++i) {
    // // each subsequence is an initial increasing subsequence
    // ArrayList<Integer> increasingSubsequence = new ArrayList<>();
    // increasingSubsequence.add(arr[i]);
    // increasingSubsequences.add(increasingSubsequence);
    // }

    // // for (int i=1; i<arr.length; ++i) {
    // // for (int j=0; j<i)
    // // }

    // return 0;
    // }

    public static ArrayList<ArrayList<Integer>> longestIncreasingSubsequenceBruteForce(int[] arr) {
        ArrayList<ArrayList<Integer>> subsets = getSubsets(arr); // all possible subarrays of arr
        ArrayList<ArrayList<Integer>> increasingSubsequences = new ArrayList<ArrayList<Integer>>();

        /* Get all increasing subsequences */
        for (int i = 0; i < subsets.size(); i++) {
            ArrayList<Integer> subset = subsets.get(i);

            // if size <= 1 -> subset is increasing by definition
            if (subset.size() <= 1) {
                increasingSubsequences.add(subset);
                continue;
            }

            boolean isIncreasing = true;
            // check if current subset is increasing
            for (int j = 1; j < subsets.get(i).size(); j++) {
                // if (next element) <= (previous element) -> not increasing
                if (subset.get(j) <= subset.get(j - 1)) {
                    isIncreasing = false;
                    break;
                }
            }

            if (isIncreasing)
                increasingSubsequences.add(subset);
        }

        /* Find length of longest increasing subsequence */
        int maxLen = increasingSubsequences.get(0).size();

        for (int i = 1; i < increasingSubsequences.size(); i++) {
            maxLen = Math.max(increasingSubsequences.get(i).size(), maxLen);
        }

        /* get all increasing subsequences with max lengths */
        ArrayList<ArrayList<Integer>> rv = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < increasingSubsequences.size(); i++) {
            if (increasingSubsequences.get(i).size() == maxLen)
                rv.add(increasingSubsequences.get(i));
        }

        return rv;
    }

    public static ArrayList<ArrayList<Integer>> getSubsets(int[] set) {
        int n = set.length;
        ArrayList<ArrayList<Integer>> subsets = new ArrayList<ArrayList<Integer>>();

        // Run a loop for printing all 2^n
        // subsets one by one
        for (int i = 0; i < (1 << n); i++) {
            // current subset
            ArrayList<Integer> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                // (1<<j) is a number with jth bit 1
                // so when we 'and' them with the
                // subset number we get which numbers
                // are present in the subset and which
                // are not
                if ((i & (1 << j)) > 0)
                    subset.add(set[j]);
            }
            // add subset to list of subsets
            subsets.add(subset);
        }

        return subsets;
    }
}