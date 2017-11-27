package JavaTest.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by admin on 2017/7/14.
 */

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode() {
    }

    public TreeNode(TreeNode left, TreeNode right, int val) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


public class FindNodeValue {

    static int counter = 0;//定义一个静态计数变量

    public static void main(String[] args) {
        int[] a = {1, 2, 3};//, 0, 0, 4, 0, 0, 5, 0, 0
        FindNodeValue findNodeValue = new FindNodeValue();
        TreeNode root = findNodeValue.createBiTree(new TreeNode(),a,0);
       // ArrayList<Integer> intList =  findNodeValue.PrintFromTopToBottom(root);
        ArrayList<Integer> intList =  findNodeValue.PrintFromTopToBottom2(root);
        for (int in:intList ) {
            System.out.print(in+" ");
        }

    }

    /**
     * 构造二叉树
     *
     * @param root根节点
     * @param a数据源
     * @param i计数器
     * @return 根节点
     */
    public   TreeNode createBiTree(TreeNode root, int[] a, int i) {
        if (i < a.length) {
//            if (a[i] == 0) {
//                root = null;
//            } else {
                TreeNode lchild = new TreeNode();
                TreeNode rchild = new TreeNode();
                root.val = a[i];
                ++counter;
                root.left = createBiTree(lchild, a,  counter);
                root.right = createBiTree(rchild, a,  counter);

  //          }
        }
        return root;
    }

    ArrayList<Integer> intList = new ArrayList<>();

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        if (root != null) {
            intList.add(root.val);
            if (root.left != null) {
                int left_Value = root.left.val;
                intList.add(left_Value);
                PrintFromTopToBottom(root.left);

            }
            if (root.right != null) {
                int right_Value = root.right.val;
                intList.add(right_Value);
                PrintFromTopToBottom(root.right);

            }
        }

        return intList;
    }

    //从左到右层级打印
    public ArrayList<Integer> PrintFromTopToBottom2(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(root == null) return list;
        Deque<TreeNode> deque = new LinkedList<TreeNode>();

        deque.add(root);
        while(!deque.isEmpty()){
            TreeNode t = deque.pop();
            list.add(t.val);
            if(t.left != null) deque.add(t.left);
            if(t.right != null) deque.add(t.right);
        }
        return list;
    }

    //从左至右依次打印
    public ArrayList<Integer> PrintFromTopToBottom3(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(root == null) return list;
        Deque<TreeNode> deque = new LinkedList<TreeNode>();

        deque.add(root);
        while(!deque.isEmpty()){
            TreeNode t = deque.pop();
            list.add(t.val);
            if(t.left != null) deque.add(t.left);
            if(t.right != null) deque.add(t.right);
        }
        return list;
    }
}





