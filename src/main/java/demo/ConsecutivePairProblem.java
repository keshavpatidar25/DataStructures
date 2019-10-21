package demo;

import java.util.*;

public class ConsecutivePairProblem {

    public static void main(String[] args) {

        Set<Pair> pairs = new TreeSet<>();
        pairs.add(new Pair(9,4));
        pairs.add(new Pair(11,9));
        pairs.add(new Pair(5,1));
        pairs.add(new Pair(4,5));


        LinkedList<Pair> list = new LinkedList<>(pairs);
        while(true) {
            Pair first = list.getFirst();
            Pair last = list.getLast();
            if (first.getLeft() == last.getRight()) {
                list.addFirst(last);
                list.removeLast();
            } else
                break;
        }
        System.out.println(list);

    }

    public static class Pair implements Comparable<Pair> {
        private int left, right;

        public Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }



        public int getLeft() {
            return left;
        }

        public int getRight() {
            return right;
        }


        @Override
        public int compareTo(Pair p) {
            if (this.getLeft() == p.getRight())
                return 1;
            return -1;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
