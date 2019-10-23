package demo.datastructures;

import java.util.*;

public class Trie {
    private Node root = new Node('\0');

    public void addString(String str) {
        if(str == null)
            return;
        char[] chars = str.toCharArray();
        Node parent = root;
        for(char c : chars) {
            parent = addString(c, parent);
        }
        parent.isValid = true;
    }

    private Node addString(char c, Node parent) {
        if(!parent.next.containsKey(c)) {
            parent.next.put(c, new Node(c));
        }
        return  parent.next.get(c);
    }

    public boolean isValidString(String str) {
        if(str == null)
            return false;
        char[] chars = str.toCharArray();
        Node parent = root;
        for(char c : chars) {
            if(parent.next.containsKey(c)) {
                parent = parent.next.get(c);
            } else {
                return false;
            }
        }
        return parent.isValid;
    }

    private void printAllValidStrings() {
        System.out.println("Valid Strings : ");
        this.printAllValidStrings(root, "");
    }

    public void printAllValidStrings(Node node, String str) {
        node.next.values().forEach(next -> {
            String nextStr = str +  next.character;
            if(next.isValid)
                System.out.println(nextStr);
            printAllValidStrings(next, nextStr);

        });
    }




    private static class Node {

        private char character;
        private boolean isValid;
        private Map<Character, Node> next = new HashMap<>();

        private Node(char character) {
            this.character = character;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "character=" + character +
                    ", isValid=" + isValid +
                    ", next=" + next +
                    '}';
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.addString("Hello");
        trie.addString("Hello World");
        trie.addString("Howard");
        trie.addString("How");
        trie.addString("Cat");
        trie.addString("Cow");
        trie.addString("Cattle");
        trie.addString(null);

        System.out.println(trie.isValidString("Hello"));
        System.out.println(trie.isValidString("Hello Wo"));
        System.out.println(trie.isValidString("Hello World"));
        System.out.println(trie.isValidString("Ho"));
        System.out.println(trie.isValidString("Howard"));
        System.out.println(trie.isValidString("Ca"));
        System.out.println(trie.isValidString("Abc"));
        System.out.println(trie.isValidString(""));
        System.out.println(trie.isValidString("Cat"));
        System.out.println(trie.isValidString("Cow"));
        System.out.println(trie.isValidString("Cattle"));
        System.out.println(trie.isValidString(null));

        System.out.println();

        trie.printAllValidStrings();


    }

}
