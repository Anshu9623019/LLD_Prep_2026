package DSA.Tree;

//                 8
//                / \
//                3   10
//                / \    \
//                1   6     14
//                / \    /
//                4   7  13


import com.sun.source.tree.BreakTree;

import java.util.Stack;

public class BinarySearchTree {

    public static void main(String[] args) {

        Node root = new Node(8);
        root.left = new Node(3);
        root.left.right = new Node(6);
        root.left.left = new Node(1);
        root.left.left.left = new Node(4);
        root.right = new Node(10);
        root.right.right = new Node(14);
        root.right.right.left = new Node(13);
        checkBST(root);
        System.out.println("check BST "+ checkBST(root));
        System.out.println("LCA of 6,7 "+LCA_BST(root,6,7).data);

    }

    //recursive
    public static Node search(Node root,int target) {
        if (root != null && root.data == target) {
            return root;
        }
        if (root.data > target) {
            search(root.left,target);
        } else{
            search(root.right,target);
        }
        return null;
    }

    //iterative method
    public static Node search1(Node root,int target) {

        while (root!=null && root.data!=target){
            if(root.data>target){
                root = root.left;
            }else{
                root = root.right;
            }
        }
        return root;
    }

    //insert a node in BST
    static Node insert(Node root, int key) {

        Node newNode = new Node(key);

        if (root == null) {
            return newNode;
        }

        Node curr = root;

        while (true) {

            if (key < curr.data) {

                if (curr.left == null) {
                    curr.left = newNode;
                    break;
                } else {
                    curr = curr.left;
                }

            } else if (key > curr.data) {

                if (curr.right == null) {
                    curr.right = newNode;
                    break;
                } else {
                    curr = curr.right;
                }
            }

            // If duplicates not allowed
            else {
                break;
            }
        }

        return root;
    }



    // VVI (Morris order traversal)
    public Node DeleteNode(Node root, int key) {
        if(root == null) return null;

        if(root.data == key){
            if(root.left != null && root.right != null){
                var temp = root.left;
                while(temp.right != null){
                    temp = temp.right;
                }
                temp.right = root.right;
                root.right = null;
                return root.left;
            }
            else{
                if(root.left != null) return root.left;
                if(root.right != null) return root.right;
                return null;
            }
        }
        if(root.data > key)
            root.left = DeleteNode(root.left, key);
        else
            root.right = DeleteNode(root.right, key);

        return root;
    }


    // Floor
    public static Integer findFloor(Node root, int key) {
        Integer floor = null;

        while (root != null) {
            if (root.data == key) {
                return root.data;
            }

            if (key > root.data) {
                floor = root.data;   // possible floor
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return floor;
    }

    // Ceil
    public static Integer findCeil(Node root, int key) {
        Integer ceil = null;

        while (root != null) {
            if (root.data == key) {
                return root.data;
            }

            if (key < root.data) {
                ceil = root.data;   // possible ceil
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return ceil;
    }

    //check BST
    public static boolean checkBST(Node root){
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        return isBST(root,min,max);
    }
     static boolean isBST(Node root,int min,int max){
        if(root==null) return true;
        if(root.data>=max || root.data<=min){
            return false;
        }
        return isBST(root.left,min, root.data) && isBST(root.right,root.data,max);
     }
    public static Node LCA_BST(Node root, int k1, int k2) {
        if (root == null) return null;

        // If both keys are smaller → go left
        if (k1 < root.data && k2 < root.data) {
            return LCA_BST(root.left, k1, k2);
        }

        // If both keys are greater → go right
        if (k1 > root.data && k2 > root.data) {
            return LCA_BST(root.right, k1, k2);
        }

        // Otherwise, this is the split point
        return root;
    }

    static int index = 0;

    public static Node buildBST(int[] preorder) {
        index = 0;
        return construct(preorder, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static Node construct(int[] preorder, long min, long max) {

        if (index >= preorder.length) return null;

        int value = preorder[index];

        // If value doesn't fit range → return null
        if (value <= min || value >= max) {
            return null;
        }

        // Create node
        Node root = new Node(value);
        index++;

        // Build left subtree
        root.left = construct(preorder, min, value);

        // Build right subtree
        root.right = construct(preorder, value, max);

        return root;
    }

    public static Node inorderSuccessor(Node root, int key) {
        Node successor = null;

        while (root != null) {
            if (key < root.data) {
                successor = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }

        return successor;
    }

    public static Node inorderPredecessor(Node root, int key) {
        Node predecessor = null;

        while (root != null) {
            if (key > root.data) {
                predecessor = root;
                root = root.right;
            } else {
                root = root.left;
            }
        }

        return predecessor;
    }


    /// Inorder Traversal OF BST
    class BSTIterator {

        private Stack<Node> stack = new Stack<>();

        public BSTIterator(Node root) {
            pushLeft(root);
        }

        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public int next() {
            Node node = stack.pop();
            if (node.right != null) {
                pushLeft(node.right);
            }
            return node.data;
        }
    }

    class BSTIterator1 {

        private Stack<Node> stack = new Stack<>();
        private boolean reverse;

        public BSTIterator1(Node root, boolean reverse) {
            this.reverse = reverse;
            pushAll(root);
        }

        private void pushAll(Node node) {
            while (node != null) {
                stack.push(node);
                if (reverse)
                    node = node.right;
                else
                    node = node.left;
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public int next() {
            Node node = stack.pop();

            if (!reverse)
                pushAll(node.right);
            else
                pushAll(node.left);

            return node.data;
        }
    }

    public boolean findTarget(Node root, int k) {

        if (root == null) return false;

        BSTIterator1 left = new BSTIterator1(root, false);  // smallest
        BSTIterator1 right = new BSTIterator1(root, true);  // largest

        int i = left.next();
        int j = right.next();

        while (i < j) {

            int sum = i + j;

            if (sum == k) return true;

            else if (sum < k) {
                if (left.hasNext())
                    i = left.next();
                else
                    return false;
            }

            else {
                if (right.hasNext())
                    j = right.next();
                else
                    return false;
            }
        }

        return false;
    }

    //Swap Node : Recover BST
    class Solution {

        Node first = null;
        Node second = null;
        Node prev = null;

        public void recoverTree(Node root) {
            inorder(root);

            // swap values
            int temp = first.data;
            first.data = second.data;
            second.data = temp;
        }

        private void inorder(Node root) {
            if (root == null) return;

            inorder(root.left);

            if (prev != null && prev.data > root.data) {
                if (first == null) {
                    first = prev;
                }
                second = root;
            }

            prev = root;

            inorder(root.right);
        }
    }
    class Info {
        boolean isBST;
        int size;
        int min;
        int max;

        Info(boolean isBST, int size, int min, int max) {
            this.isBST = isBST;
            this.size = size;
            this.min = min;
            this.max = max;
        }
    }

    class Solution1 {

        static int maxSize = 0;

        public  int largestBST(Node root) {
            solve(root);
            return maxSize;
        }

        private Info solve(Node root) {

            if (root == null) {
                return new Info(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
            }

            Info left = solve(root.left);
            Info right = solve(root.right);

            if (left.isBST && right.isBST &&
                    root.data > left.max &&
                    root.data < right.min) {

                int size = left.size + right.size + 1;
                maxSize = Math.max(maxSize, size);

                int min = Math.min(root.data, left.min);
                int max = Math.max(root.data, right.max);

                return new Info(true, size, min, max);
            }

            return new Info(false,
                    Math.max(left.size, right.size),
                    0, 0);
        }
    }

}
