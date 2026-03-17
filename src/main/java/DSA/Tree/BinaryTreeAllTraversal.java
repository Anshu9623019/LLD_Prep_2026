package DSA.Tree;

import java.util.*;

class Node{
    int data;
    Node left;
    Node right;
    Node(int data){
        left = null;
        right = null;
        this.data = data;
    }
}
public class BinaryTreeAllTraversal {
    class Pair{
        Node node;
        int state;
        Pair(Node node,int state){
            this.node = node;
            this.state = state;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        BinaryTreeAllTraversal bt = new BinaryTreeAllTraversal();

        System.out.println();
        bt.IterativePostOrder1(root);
        bt.postOrder(root);
        System.out.println();
        bt.preOrder(root);
        System.out.println();
        bt.levelOrder(root);
        System.out.println();
        System.out.println(bt.IterativePostOrder(root).reversed());
        System.out.println("PreOrder");
        bt.IterativePreOrder(root);
        System.out.println("InOrder");
        bt.IterativeInOrder(root);
        System.out.println();
        System.out.println("Single Traversal preInPost order"+bt.preInPost(root));
        System.out.println("Morris Inorder"+bt.InorderMorris(root));
        System.out.println("Morris PreOrder"+bt.preOrderMorris(root));
        System.out.println("Morris PostOrder"+bt.postMorrisTraversal(root));
         Node curr = bt.flattenBT(root);
         while (curr!=null){
             System.out.println(curr.data);
             curr = curr.right;
         }
    }

    public  void postOrder(Node root){
        if(root==null){
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data);
    }

    public void preOrder(Node root){
        if(root==null){
            return;
        }
        System.out.print(root.data);
        preOrder(root.left);
        preOrder(root.right);
    }

    public void inOrder(Node root){
        if(root==null){
            return;
        }
        inOrder(root.left);
        System.out.print(root.data);
        inOrder(root.right);
    }

    public void levelOrder(Node root){
        Queue<Node> pq = new LinkedList<>();
        pq.add(root);
        while (!pq.isEmpty()){
            Node temp = pq.poll();
            if(temp.left!=null ){
                pq.add(temp.left);
            }
            if(temp.right!=null){
                pq.add(temp.right);
            }
            System.out.println(temp.data);
        }
    }
    public  void PostOrder(Node root){
        if(root==null){
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data);
    }

    public void PreOrder(Node root){
        if(root==null){
            return;
        }
        System.out.print(root.data);
        preOrder(root.left);
        preOrder(root.right);
    }

    public void InOrder(Node root){
        if(root==null){
            return;
        }
        inOrder(root.left);
        System.out.print(root.data);
        inOrder(root.right);
    }

    public void IterativeLevelOrder(Node root){
        Queue<Node> pq = new LinkedList<>();
        pq.add(root);
        while (!pq.isEmpty()){
            Node temp = pq.poll();
            if(temp.left!=null ){
                pq.add(temp.left);
            }
            if(temp.right!=null){
                pq.add(temp.right);
            }
            System.out.println(temp.data);
        }
    }

    public  ArrayList<Integer> IterativePostOrder(Node root){
        if(root==null){
            return null;
        }
        Stack<Node> st1 = new Stack<>();
        Stack<Node> st2 = new Stack<>();
        ArrayList<Integer> li = new ArrayList<>();
        st1.add(root);
        while(!st1.isEmpty()){
            Node temp = st1.pop();
            st2.add(temp);
            if(temp.left!=null){
                st1.add(temp.left);
            }
            if(temp.right!=null){
                st1.add(temp.right);
            }
            while(!st2.isEmpty()){
                    int ans = st2.pop().data;
                    li.add(ans);
            }
        }
        return li;
    }

    public  ArrayList<Integer> IterativePostOrder1(Node root){
        ArrayList<Integer> ans = new ArrayList<>();
        if(root==null){
            return ans;
        }
        Stack<Node> st = new Stack<>();
        Node curr = root;
        Node lastVisited = null;
        while(curr!=null && !st.isEmpty()){
            while (curr!=null){
                st.add(curr);
                curr = curr.left;
            }
            Node peekElement = st.pop();
            if(peekElement.right!=null && peekElement!=lastVisited){
                curr = peekElement.right;
            }else {
                ans.add(curr.data);
                lastVisited = curr;
            }
        }
         return  ans;

//        Interview Explanation (Say This)
//
//        We simulate recursion using one stack.
//        We move left pushing nodes.
//        When no left remains, we peek stack.
//        If right subtree exists and not processed, we move right.
//        Otherwise we process node.
//        lastVisited helps track completed right subtree.
    }

    public void IterativePreOrder(Node root){

        if(root==null){
            return;
        }
        Stack<Node> st = new Stack<>();
        st.add(root);

        while(!st.isEmpty()){
            Node temp = st.peek();
            st.pop();
            System.out.println(temp.data);
            if(temp.right!=null){
                st.add(temp.right);
            }if(temp.left!=null){
                st.add(temp.left);
            }

        }

    }


    public ArrayList<Integer> IterativeInOrder(Node root){
            ArrayList<Integer> li = new ArrayList<>();
            if(root==null){
                return li;
            }
            Stack<Node> st = new Stack<>();
            while(true){
                if(root!=null){
                    st.add(root);
                    root = root.left;
                }else {
                    if (st.isEmpty()) {
                        break;
                    }
                    Node temp = st.pop();
                    li.add(temp.data);
                    root = temp.right;
                }
            }
            return li;
    }

    public ArrayList<ArrayList<Integer>> preInPost(Node root){
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> preOrder = new ArrayList<>();
        ArrayList<Integer> inOrder = new ArrayList<>();
        ArrayList<Integer> postOrder = new ArrayList<>();
        Stack<Pair> st = new Stack<>();
        st.add(new Pair(root,1));
        while (!st.isEmpty()){
            Pair temp = st.pop();
            if(temp.state==1){
                preOrder.add(temp.node.data);
                temp.state++;
                st.add(temp);
                if(temp.node.left!=null){
                    st.add(new Pair(temp.node.left, 1));
                }
            }else if(temp.state==2){
                inOrder.add(temp.node.data);
                temp.state++;
                st.add(temp);
                if(temp.node.right!=null){
                    st.add(new Pair(temp.node.right, 1));
                }
            }else {
                postOrder.add(temp.node.data);
            }
        }
        ans.add(preOrder);
        ans.add(inOrder);
        ans.add(postOrder);
        return ans;
    }


    // Morris Traversal .... LNR
    public ArrayList<Integer> InorderMorris(Node root){
        ArrayList<Integer> ans = new ArrayList<>();
        if(root==null){
            return ans;
        }
        while(root!=null){
            //if left doesn't exist
            if(root.left==null){
                ans.add(root.data);
                root = root.right;
            }else{
                //if left exist
                Node curr = root.left;
                //check left is traverse or not
                while (curr.right!=null && curr.right!=root){
                    curr = curr.right;
                }
                //if not traverse
                if(curr.right==null){
                    curr.right = root;
                    root = root.left;
                }else{
                    // if already traverse
                    ans.add(root.data);
                    curr.right = null;
                    root = root.right;
                }
            }

        }
        return ans;
    }


    // NLR
    public ArrayList<Integer> preOrderMorris(Node root){
        ArrayList<Integer> ans = new ArrayList<>();
        if(root==null){
            return ans;
        }
        while(root!=null){
            //if left doesn't  exist
            if(root.left==null){
                ans.add(root.data);
                root = root.right;
            }else {
                // if exist
                Node curr = root.left;
                //check left is traversed or not
                while (curr.right!=null && curr.right!=root){
                    curr = curr.right;
                }
                // if not traverse
                if (curr.right==null){
                    ans.add(root.data);
                    curr.right = root;
                    root = root.left;
                }else{
                    //if traversed
                    curr.right = null;
                    root = root.right;
                }
            }

        }
        return ans;
    }

    public List<Integer> postMorrisTraversal(Node root){
        ArrayList<Integer> ans = new ArrayList<>();
        if (root==null){
            return ans;
        }
        while (root!=null){
            //if right doesn't exist
            if(root.right==null){
                ans.add(root.data);
                root = root.left;
            }else{
                //if right exist
                Node curr = root.right;
                while (curr.left!=null && curr.left!=root){
                    curr = curr.left;
                }
                if(curr.left==null){
                    ans.add(root.data);
                    curr.left = root;
                    root = root.right;
                }else {
                    curr.left = null;
                    root = root.left;
                }
            }
        }
        return ans.reversed();
    }

    //Flatten Binary tree to linkedList
   public Node flattenBT(Node root){
        ArrayList<Integer> ans = new ArrayList<>();
        Node temp = root;
        if (root==null){
            return null;
        }
        while (root!=null){
            if (root.left==null){
                root = root.right;
            }else{
                Node curr = root.left;
                while (curr.right!=null){
                    curr = curr.right;
                }
                curr.right = root.right;
                root.right = root.left;
                root.left = null;
                root = root.right;
            }
        }
        return temp;
    }
}
