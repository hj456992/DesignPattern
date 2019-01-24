package Test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Created by houjue on 2018/11/20.
 */
public class QueueTest {

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        Random random = new Random();
        for (int i=0; i< 10;i ++) {
            int k = random.nextInt(100);
            queue.add(k);
            System.out.println(k);
        }

        System.out.println();
        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }
    }

}
