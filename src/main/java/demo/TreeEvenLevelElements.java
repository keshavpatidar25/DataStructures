package demo;

import java.util.concurrent.atomic.AtomicInteger;

public class TreeEvenLevelElements {

    private int data;
    private TreeEvenLevelElements left;
    private TreeEvenLevelElements right;

    public TreeEvenLevelElements() {
    }

    public TreeEvenLevelElements(int data) {
        this.data = data;
    }

    public TreeEvenLevelElements(int data, TreeEvenLevelElements left, TreeEvenLevelElements right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public TreeEvenLevelElements getLeft() {
        return left;
    }

    public void setLeft(TreeEvenLevelElements left) {
        this.left = left;
    }

    public TreeEvenLevelElements getRight() {
        return right;
    }

    public void setRight(TreeEvenLevelElements right) {
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }


    public static void main(String[] args) {
        TreeEvenLevelElements l31Node = new TreeEvenLevelElements(1);
        TreeEvenLevelElements l32Node = new TreeEvenLevelElements(2);
        TreeEvenLevelElements l33Node = new TreeEvenLevelElements(3);
        TreeEvenLevelElements l34Node = new TreeEvenLevelElements(4);
        TreeEvenLevelElements l21Node = new TreeEvenLevelElements(5, l31Node, null);
        TreeEvenLevelElements l22Node = new TreeEvenLevelElements(6, null, l32Node);
        TreeEvenLevelElements l23Node = new TreeEvenLevelElements(7, null, l33Node);
        TreeEvenLevelElements l24Node = new TreeEvenLevelElements(8, l34Node, null);
        TreeEvenLevelElements l11Node = new TreeEvenLevelElements(9, l21Node, l22Node);
        TreeEvenLevelElements l12Node = new TreeEvenLevelElements(10, l23Node, l24Node);
        TreeEvenLevelElements root = new TreeEvenLevelElements(11, l11Node, l12Node);


        getLevelData(root, 0, 0, new AtomicInteger(0));
        System.out.println();
        getLevelData(root, 0, 1, new AtomicInteger(0));
        System.out.println();
        getLevelData(root, 0, 2, new AtomicInteger(0));
        System.out.println();
        getLevelData(root, 0, 3, new AtomicInteger(0));
        System.out.println();




    }

    private static void getLevelData(TreeEvenLevelElements node, int currentLevel, int targetLevel, AtomicInteger count) {
        if(node == null || currentLevel > targetLevel) {
            return;
        }
        if(currentLevel == targetLevel) {
            if(count.get()%2 == 0 && count.get() != 0) {
                System.out.println(node.getData());
            }
        } else {
            TreeEvenLevelElements leftNode = node.getLeft();
            TreeEvenLevelElements rightNode = node.getRight();
            if(leftNode != null) {
                if(currentLevel + 1 == targetLevel)  {
                    count.incrementAndGet();
                }
                getLevelData(leftNode, currentLevel+1, targetLevel, count);
            }
            if(rightNode != null) {
                if(currentLevel + 1 == targetLevel)  {
                    count.incrementAndGet();
                }
                getLevelData(rightNode, currentLevel+1, targetLevel, count);
            }
        }
    }

}
