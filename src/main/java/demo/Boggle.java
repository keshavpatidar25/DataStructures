package demo;

import demo.datastructures.Trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Boggle {


    public static void main(String[] args) {
        char[][] a = new char[][] {
                {'C', 'A','N'},
                {'H', 'T','X'},
                {'E', 'L','D'},
                {'F', 'L','O'},
                {'F', 'C','W'}
        };

        int rows = a.length;
        int cols = a[0].length;

        Trie trie = new Trie();
        trie.addString("HELLO");
        trie.addString("HELLO WORLD");
        trie.addString("HOWARD");
        trie.addString("HOW");
        trie.addString("CAT");
        trie.addString("COW");
        trie.addString("CATTLE");

        Trie.Node root = trie.getRoot();
        Set<String> validStrings = new HashSet<>();

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                boolean[][] visited = new boolean[rows][cols];
                visited[i][j] = true;
                findValidStrings(a, i, j, visited, root, "", validStrings);
            }
        }

        System.out.println("Result : " + validStrings);
    }

    private static class Pair {
        private int row, col;
        private Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static List<Pair>  getValidIndexes(int row, int col, int maxRow, int maxCol, boolean[][] visited) {
        int[][] indexDelta = new int[][] {
                {1,0},
                {0,1},
                {1,1},
                {-1,-1},
                {-1,0},
                {0,-1},
                {1, -1},
                {-1, 1}

        };
        List<Pair> pairs = new ArrayList<>();
        for (int[] delta : indexDelta) {
            int r = row + delta[0];
            int c = col + delta[1];
            if (r >= 0 && r < maxRow && c >= 0 && c < maxCol && !visited[r][c])
                pairs.add(new Pair(r, c));
        }
        return pairs;
    }

    private static boolean findValidStrings(char[][] a, int row, int col, boolean[][] visited, Trie.Node node, String s, Set<String> validStrings) {
        char c = a[row][col];
        if(node == null || !node.getNext().containsKey(c)) {
            return false;
        }
        Trie.Node next = node.getNext().get(c);
        boolean isValid = false;
        if(next.isValid()) {
            isValid = true;
            validStrings.add(s + c);
            System.out.println(s+c);
        }
        List<Pair> pairs = getValidIndexes(row, col, a.length, a[0].length, visited);
        for(Pair pair : pairs) {
            visited[pair.row][pair.col] = findValidStrings(a, pair.row, pair.col, visited, next, s+c, validStrings);
            isValid = isValid || visited[pair.row][pair.col];
        }
        return isValid;
    }
}
