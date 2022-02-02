import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class HW {
    public static int getMax(int[] arr) {
        if (arr.length < 1)
            return -1;

        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
        }

        return max;
    }

    public static int findLIS(int[] sequence) {
        int n = sequence.length;
        int[] subLens = new int[n]; // contains lengths of subsequences
        int numParents = 0;

        if (n >= 1)
            subLens[0] = 1;

        for (int i = 1; i < n; i++) {
            subLens[i] = 1;
            for (int j = 0; j < i; j++) {
                if (sequence[i] > sequence[j] && subLens[i] < subLens[j] + 1)
                    subLens[i] = subLens[j] + 1;
                numParents++;
            }
        }

        int maxSubLen = getMax(subLens);

        int subLen = maxSubLen;
        Queue lis = new LinkedList<int[]>();

        // initalize lis -> find max starting points
        for (int i = 0; i < n; i++) {
            if (subLens[i] == maxSubLen) {
                int[] parent = new int[maxSubLen];
                parent[subLen - 1] = sequence[i];
                lis.add(parent);
            }
        }

        subLen--;

        while (subLen > 0) {
            while (numParents > 0) {
                int[] parent = new int[maxSubLen];
                if (!lis.empty())
                    parent = lis.peek(); // current parent

                for (int i = 0; i < n; i++) {
                    if (subLens[i] == subLen && sequence[i] < parent[subLen - 1]) {
                        // child 'inherits' parents elements
                        int[] child = new int[maxSubLen];
                        for (int j = 0; j < maxSubLen; j++) {
                            child[j] = parent[j];
                        }

                        child[subLen - 1] = sequence[i]; // fill in new value
                        lis.add(child); // future parent
                        numParents++;
                    }
                }

                lis.remove(); // remove current parent
                numParents--;
            }

            subLen--;
        }

        // print out all lis
        // while (!lis.empty())
        // {
        // vector<int> sub = lis.peek();

        // for (int i=n-1; i>=0; i++)
        // cout << sub[i] << " ";
        // cout << endl;

        // lis.remove();
        // }

        return maxSubLen;
    }

    int main() {
        // int sequence[] = {68, 49, 31, 25, 22, 48, 38, 19,
        // 1, 80, 8, 83, 55, 33, 98, 29};
        // const int n = sizeof(sequence) / sizeof(sequence[0]);

        int sequence[] = { 10, 9, 2, 5, 3, 101, 7, 18 };
        System.out.println(findLIS(sequence));

        return 0;
    }
}
