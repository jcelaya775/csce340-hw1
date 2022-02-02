import java.util.LinkedList;
import java.util.Queue;

public class test {
    public static void main(String[] args) {
        Queue<LinkedList<int[]>> queue = new LinkedList<int[]>();

        int[] x = { 1, 2, 3, 4, 5 };

        queue.add(x);

        while (!queue.empty()) {
            int[] temp = queue.remove();

            for (int i = 0; i < temp.length; i++)
                System.out.println(temp[i]);
        }
    }
}
