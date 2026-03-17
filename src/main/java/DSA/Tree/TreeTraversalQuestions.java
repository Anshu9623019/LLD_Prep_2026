package DSA.Tree;


import java.util.*;

public class TreeTraversalQuestions {
    static int diameter[] = new int[2];
    static int maxSum[] = new int[2];


    public static void main(String[] args) {
        Node node = new Node(3);
        node.left = new Node(5);
        node.left.left = new Node(6);
        node.left.right = new Node(2);
        node.left.right.left = new Node(7);
        node.left.right.right = new Node(4);
        node.left.right.right.left = new Node(9);
        node.right = new Node(1);
        node.right.left = new Node(9);
        node.right.right = new Node(8);
        System.out.println("Max depth of BT :" + maxDepthBT(node));
        System.out.println("Check balanced Tree :" + checkBalancedBT(node));
        System.out.println("Diameter of BT :"+ diameter(node) );
        maxSumDia(node);
        System.out.println("Max Sum :" + maxSum[0]);
        System.out.println("zidzag traversal :" + zigZagTraversal(node));
        System.out.println("Boundary Traversal :" + boundaryTraversal(node));

        ArrayList<Integer> ansLeft = new ArrayList<>();
        maxLevelLeft = 0;
        leftViewRecursive(node, 1, ansLeft);

        ArrayList<Integer> ansRight = new ArrayList<>();
        maxLevelRight = 0;
        rightViewRecursive(node, 1, ansRight);


    }


    //Easy
    public static int  maxDepthBT(Node root){
        if(root==null){
            return 0;
        }
        int left = maxDepthBT(root.left);
        int right = maxDepthBT(root.right);
        return 1 + Math.max(left,right);
    }


    // o(N2) : Time Complexity  : Easy
    public static boolean  checkBalancedBT(Node root){
        if(root==null){
            return true;
        }
        int left = maxDepthBT(root.left);
        int right = maxDepthBT(root.right);
        if(Math.abs(left-right)>1){
            return false;
        }
        return checkBalancedBT(root.left) && checkBalancedBT(root.right);
    }

   // Bit Tricky (check this)
    public static boolean checkBalancedBT1(Node root){
        if(root==null){
            return true;
        }
        return isBalanced(root) !=-1;
    }

    public static int isBalanced(Node root){
        if(root==null){
            return 0;
        }
        int lh = isBalanced(root.left);
        int rh = isBalanced(root.right);
        if(lh==-1 || rh==-1){
            return -1;
        }
        if(Math.abs(lh-rh)>1){
            return -1;
        }
        return Math.max(lh,rh)+1;
    }


    //Diameter of BinaryTree
    public static int diameter(Node root){
        diameterSoln(root,diameter);
        return diameter[0];
    }


    public static int diameterSoln(Node root, int diameter[]){
        if(root==null) return 0;

        int l = diameterSoln(root.left,diameter);
        int r = diameterSoln(root.right,diameter);

        diameter[0] = Math.max(diameter[0],l+ r);
        return 1 + Math.max(l,r);
    }



    public static int maxSumDia(Node root){
        if(root==null){
            return 0;
        }
        int l = Math.max(0,maxSumDia(root.left)); // if path returning 0 sum
        int r = Math.max(0,maxSumDia(root.right)); // if path returning 0 sum
        maxSum[0] = Math.max(maxSum[0],root.data+l+r);
        return root.data + Math.max(l,r);
    }

    //Check if two tree are identical or not
    public static boolean checkEqual(Node root1,Node root2){
        if(root1==null || root2==null){
            return (root1==root2);
        }
        if(root1.data!=root2.data){
            return false;
        }
        boolean l = checkEqual(root1.left,root2.left);
        boolean r = checkEqual(root1.right,root2.right);
        return l && r;
    }

    // ZigZag traversal
    public static ArrayList<ArrayList<Integer>> zigZagTraversal(Node root){
        if(root==null){
            return null;
        }
        Queue<Node> qe = new LinkedList<>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        qe.add(root);
        boolean leftToRight = true;
        while(!qe.isEmpty()){
            int size = qe.size();
            ArrayList<Integer> ele = new ArrayList<>();
            for (int i=0;i<size;i++){
                Node temp = qe.poll();
                ele.add(temp.data);
                if(temp.left!=null){
                    qe.add(temp.left);
                }
                if (temp.right!=null){
                    qe.add(temp.right);
                }
            }
            if(!leftToRight){
                Collections.reverse(ele);
                ans.add(ele);
            }
            ans.add(ele);
            leftToRight = !leftToRight;

        }

        return ans;
    }


    //Boundary Traversal
    public static ArrayList<Integer> boundaryTraversal(Node root){
        ArrayList<Integer> ans = new ArrayList<>();
        if(root==null){
            return ans;
        }
        ans.add(root.data);
        ArrayList<Integer> left =  addLeft(root.left);
        System.out.println(left);
        addLeaf(root,left);
        System.out.println(left);
        ArrayList<Integer> right =  addRight(root.right);
        System.out.println(right);
        ans.addAll(left);
        ans.addAll(right);
        return ans;
    }

    public  static ArrayList<Integer> addLeft(Node root){
        ArrayList<Integer> left = new ArrayList<>();
        while (root!=null){
            if(root.left!=null || root.right!=null){
                left.add(root.data);
                if(root.left!=null){
                    root = root.left;
                }else {
                    root = root.right;
                }
            }else {
                root = root.left;
            }
        }
        return left;
    }

    public static void addLeaf(Node root, ArrayList<Integer> leaf){
        if(root==null){
            return;
        }
        if(root.left ==null && root.right==null){
            leaf.add(root.data);
        }
        addLeaf(root.left,leaf);
        addLeaf(root.right,leaf);
    }

    public static ArrayList<Integer> addRight(Node root){
        ArrayList<Integer> right = new ArrayList<>();

        while (root!=null){
            if(root.left!=null || root.right!=null){
                right.add(root.data);
                if(root.left!=null){
                    root = root.right;
                }else {
                    root = root.left;
                }
            }else {
                root = root.left;
            }
        }
        Collections.reverse(right);
        return right;
    }

    static class Tuple {
        Node node;
        int col;
        int row;

        Tuple(Node n, int c, int r){
            node = n;
            col = c;
            row = r;
        }
    }
    //Vertical Order Traversal
        public static ArrayList<ArrayList<Integer>> verticalTraversalSorted(Node root){

            TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map =
                    new TreeMap<>();

            Queue<Tuple> q = new LinkedList<>();
            q.offer(new Tuple(root, 0, 0));

            while(!q.isEmpty()){
                Tuple t = q.poll();

                map.putIfAbsent(t.col, new TreeMap<>());
                map.get(t.col).putIfAbsent(t.row, new PriorityQueue<>());
                map.get(t.col).get(t.row).offer(t.node.data);

                if(t.node.left != null)
                    q.offer(new Tuple(t.node.left, t.col - 1, t.row + 1));

                if(t.node.right != null)
                    q.offer(new Tuple(t.node.right, t.col + 1, t.row + 1));
            }

            ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

            for(TreeMap<Integer, PriorityQueue<Integer>> rows : map.values()){
                ArrayList<Integer> colList = new ArrayList<>();

                for(PriorityQueue<Integer> pq : rows.values()){
                    while(!pq.isEmpty()){
                        colList.add(pq.poll());
                    }
                }
                ans.add(colList);
            }
            return ans;
        }

    static class Pair {
        Node node;
        int hd;

        Pair(Node n, int h){
            node = n;
            hd = h;
        }
    }


   // Top View
    public static ArrayList<Integer> topView(Node root){
        ArrayList<Integer> ans = new ArrayList<>();
        if(root == null) return ans;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();

        q.offer(new Pair(root, 0));

        while(!q.isEmpty()){
            Pair p = q.poll();

            // ONLY insert if HD not seen before
            map.putIfAbsent(p.hd, p.node.data);

            if(p.node.left != null)
                q.offer(new Pair(p.node.left, p.hd - 1));

            if(p.node.right != null)
                q.offer(new Pair(p.node.right, p.hd + 1));
        }

        for(int val : map.values()){
            ans.add(val);
        }

        return ans;
    }

    //Bottom view
    public static ArrayList<Integer> bottomView(Node root){
        ArrayList<Integer> ans = new ArrayList<>();
        if(root == null) return ans;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();

        q.offer(new Pair(root, 0));

        while(!q.isEmpty()){
            Pair p = q.poll();

            // ALWAYS overwrite
            map.put(p.hd, p.node.data);

            if(p.node.left != null)
                q.offer(new Pair(p.node.left, p.hd - 1));

            if(p.node.right != null)
                q.offer(new Pair(p.node.right, p.hd + 1));
        }

        for(int val : map.values()){
            ans.add(val);
        }

        return ans;
    }

    // Left View/right View Iterative code

    // 1. Left

    public static ArrayList<Integer> leftViewIterative(Node root){
        ArrayList<Integer> ans = new ArrayList<>();
        if(root == null) return ans;

        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            int size = q.size();

            for(int i = 0; i < size; i++){
                Node curr = q.poll();

                // FIRST node of this level
                if(i == 0){
                    ans.add(curr.data);
                }

                if(curr.left != null) q.offer(curr.left);
                if(curr.right != null) q.offer(curr.right);
            }
        }

        return ans;
    }



    public static ArrayList<Integer> rightViewIterative(Node root){
        ArrayList<Integer> ans = new ArrayList<>();
        if(root == null) return ans;

        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            int size = q.size();

            for(int i = 0; i < size; i++){
                Node curr = q.poll();

                // LAST node of this level
                if(i == size - 1){
                    ans.add(curr.data);
                }

                if(curr.left != null) q.offer(curr.left);
                if(curr.right != null) q.offer(curr.right);
            }
        }

        return ans;
    }


    //Recursive
    // 1. right
    static int maxLevelRight = 0;

    public static void rightViewRecursive(Node root, int level, ArrayList<Integer> ans){
        if(root == null) return;

        if(level > maxLevelRight){
            ans.add(root.data);
            maxLevelRight = level;
        }

        rightViewRecursive(root.right, level + 1, ans);
        rightViewRecursive(root.left, level + 1, ans);
    }

    // Right

    static int maxLevelLeft = 0;

    public static void leftViewRecursive(Node root, int level, ArrayList<Integer> ans){
        if(root == null) return;

        if(level > maxLevelLeft){
            ans.add(root.data);
            maxLevelLeft = level;
        }

        leftViewRecursive(root.left, level + 1, ans);
        leftViewRecursive(root.right, level + 1, ans);
    }


    //Symmetry
    public static boolean isSymmetric(Node root){
        if(root == null) return true;
        return isMirror(root.left, root.right);
    }

    static boolean isMirror(Node t1, Node t2){
        if(t1 == null && t2 == null) return true;
        if(t1 == null || t2 == null) return false;

        if(t1.data != t2.data) return false;

        return isMirror(t1.left, t2.right) &&
                isMirror(t1.right, t2.left);
    }

    //Iterative
    public static boolean isSymmetricIterative(Node root){
        if(root == null) return true;

        Queue<Node> q = new LinkedList<>();
        q.offer(root.left);
        q.offer(root.right);

        while(!q.isEmpty()){
            Node t1 = q.poll();
            Node t2 = q.poll();

            if(t1 == null && t2 == null) continue;
            if(t1 == null || t2 == null) return false;

            if(t1.data != t2.data) return false;

            // mirror pushing
            q.offer(t1.left);
            q.offer(t2.right);

            q.offer(t1.right);
            q.offer(t2.left);
        }
        return true;
    }

    //Iterative Node, print the path from top to bottom
    public static boolean rootToNode(Node root, int target, ArrayList<Integer> path){

        if(root == null) return false;

        // Add current node
        path.add(root.data);

        // If target found
        if(root.data == target){
            return true;
        }

        // Search in left OR right subtree
        if(rootToNode(root.left, target, path) ||
                rootToNode(root.right, target, path)){
            return true;
        }

        // Backtrack: remove current node
        path.remove(path.size() - 1);
        return false;
    }



    // LCA Good question : (VVI)
    public static Node lowestCommonAncestor(Node root, Node p, Node q){

        if(root == null) return null;

        // If current node matches p or q
        if(root == p || root == q){
            return root;
        }

        Node left = lowestCommonAncestor(root.left, p, q);
        Node right = lowestCommonAncestor(root.right, p, q);

        // If both sides returned non-null, this is LCA
        if(left != null && right != null){
            return root;
        }

        // Else propagate non-null upward
        return left != null ? left : right;
    }


    // Lowest Common Ancestor
     static class Pair1{
        Node node;
        long index;
        Pair1(Node node,long index){
            this.node = node;
            this.index = index;
        }
    }


    public static int maxWidth(Node root){
        if(root == null) return 0;

        Queue<Pair1> q = new LinkedList<>();
        q.offer(new Pair1(root, 0));

        int ans = 0;

        while(!q.isEmpty()){
            int size = q.size();
            long min = q.peek().index;   // normalize

            long first = 0, last = 0;

            for(int i = 0; i < size; i++){
                Pair1 p = q.poll();
                long cur = p.index - min;

                if(i == 0) first = cur;
                if(i == size - 1) last = cur;

                if(p.node.left != null)
                    q.offer(new Pair1(p.node.left, 2*cur + 1));

                if(p.node.right != null)
                    q.offer(new Pair1(p.node.right, 2*cur + 2));
            }

            ans = Math.max(ans, (int)(last - first + 1));
        }

        return ans;
    }


    // Children Sum
    public static boolean childrenSum(Node root){

        if(root == null) return true;

        // Leaf node
        if(root.left == null && root.right == null)
            return true;

        int left = (root.left != null) ? root.left.data : 0;
        int right = (root.right != null) ? root.right.data : 0;

        return (root.data == left + right) &&
                childrenSum(root.left) &&
                childrenSum(root.right);
    }


    // Another question(VVI : Hard), Check Once
    public static void convertTree(Node root){

        if(root == null) return;

        int child = 0;

        if(root.left != null) child += root.left.data;
        if(root.right != null) child += root.right.data;

        // Push value DOWN if parent bigger
        if(child < root.data){
            if(root.left != null)
                root.left.data = root.data;
            else if(root.right != null)
                root.right.data = root.data;
        }
        // Recurse
        convertTree(root.left);
        convertTree(root.right);

        // Pull value UP after recursion
        int total = 0;
        if(root.left != null) total += root.left.data;
        if(root.right != null) total += root.right.data;

        if(root.left != null || root.right != null)
            root.data = total;
    }


    //Print All node at distance K

    // Step 1: Build parent map
    static void buildParentMap(Node root, Map<Node, Node> parent) {
        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            Node curr = q.poll();

            if (curr.left != null) {
                parent.put(curr.left, curr);
                q.offer(curr.left);
            }

            if (curr.right != null) {
                parent.put(curr.right, curr);
                q.offer(curr.right);
            }
        }
    }

    // Step 2: BFS from target
    static void printNodesAtDistanceK(Node root, Node target, int k) {

        Map<Node, Node> parent = new HashMap<>();
        buildParentMap(root, parent);

        Queue<Node> q = new LinkedList<>();
        Set<Node> visited = new HashSet<>();

        q.offer(target);
        visited.add(target);

        int distance = 0;

        while (!q.isEmpty()) {

            int size = q.size();

            if (distance == k) {
                while (!q.isEmpty()) {
                    System.out.print(q.poll().data + " ");
                }
                return;
            }

            for (int i = 0; i < size; i++) {
                Node curr = q.poll();

                // left
                if (curr.left != null && !visited.contains(curr.left)) {
                    visited.add(curr.left);
                    q.offer(curr.left);
                }

                // right
                if (curr.right != null && !visited.contains(curr.right)) {
                    visited.add(curr.right);
                    q.offer(curr.right);
                }

                // parent
                if (parent.containsKey(curr) && !visited.contains(parent.get(curr))) {
                    visited.add(parent.get(curr));
                    q.offer(parent.get(curr));
                }
            }
            distance++;
        }
    }


    // Build parent mapping
    static void buildParent(Node root, Map<Node, Node> parent) {
        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            Node curr = q.poll();

            if (curr.left != null) {
                parent.put(curr.left, curr);
                q.offer(curr.left);
            }

            if (curr.right != null) {
                parent.put(curr.right, curr);
                q.offer(curr.right);
            }
        }
    }

    static int minTime(Node root, Node target) {

        Map<Node, Node> parent = new HashMap<>();
        buildParent(root, parent);

        Queue<Node> q = new LinkedList<>();
        Set<Node> visited = new HashSet<>();

        q.offer(target);
        visited.add(target);

        int time = 0;

        while (!q.isEmpty()) {

            int size = q.size();
            boolean burned = false;

            for (int i = 0; i < size; i++) {
                Node curr = q.poll();

                // left
                if (curr.left != null && !visited.contains(curr.left)) {
                    visited.add(curr.left);
                    q.offer(curr.left);
                    burned = true;
                }

                // right
                if (curr.right != null && !visited.contains(curr.right)) {
                    visited.add(curr.right);
                    q.offer(curr.right);
                    burned = true;
                }

                // parent
                if (parent.containsKey(curr) && !visited.contains(parent.get(curr))) {
                    visited.add(parent.get(curr));
                    q.offer(parent.get(curr));
                    burned = true;
                }
            }

            if (burned) time++;
        }

        return time;
    }

    static int postIndex;

    static Node buildTree(int[] inorder, int[] postorder) {

        postIndex = postorder.length - 1;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return construct(postorder, 0, inorder.length - 1, map);
    }

    static Node construct(int[] post, int inStart, int inEnd,
                          Map<Integer, Integer> map) {

        if (inStart > inEnd) return null;

        int val = post[postIndex--];
        Node root = new Node(val);

        int index = map.get(val);

        // IMPORTANT: build right first
        root.right = construct(post, index + 1, inEnd, map);
        root.left  = construct(post, inStart, index - 1, map);

        return root;
    }

    static int preIndex;

    static Node buildTree1(int[] preorder, int[] inorder) {

        preIndex = 0;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return construct1(preorder, 0, inorder.length - 1, map);
    }

    static Node construct1(int[] pre, int inStart, int inEnd,
                          Map<Integer, Integer> map) {

        if (inStart > inEnd) return null;

        int val = pre[preIndex++];
        Node root = new Node(val);

        int index = map.get(val);

        // Build LEFT first
        root.left  = construct1(pre, inStart, index - 1, map);
        root.right = construct1(pre, index + 1, inEnd, map);

        return root;
    }


    // Serialize
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(Node node, StringBuilder sb) {
        if (node == null) {
            sb.append("null,");
            return;
        }

        sb.append(node.data).append(",");
        buildString(node.left, sb);
        buildString(node.right, sb);
    }

    // Deserialize
    public Node deserialize(String data) {
        Queue<String> queue =
                new LinkedList<>(Arrays.asList(data.split(",")));
        return buildTree(queue);
    }

    private Node buildTree(Queue<String> queue) {
        String val = queue.poll();

        if (val.equals("null")) return null;

        Node node = new Node(Integer.parseInt(val));
        node.left = buildTree(queue);
        node.right = buildTree(queue);

        return node;
    }

    //BFS : iterative
    public String serializeBFS(Node root) {

        if (root == null) return "";

        StringBuilder sb = new StringBuilder();
        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            Node curr = q.poll();

            if (curr == null) {
                sb.append("null,");
                continue;
            }

            sb.append(curr.data).append(",");
            q.offer(curr.left);
            q.offer(curr.right);
        }

        return sb.toString();
    }

    public Node deserializeBFS(String data) {

        if (data.isEmpty()) return null;

        String[] values = data.split(",");
        Node root = new Node(Integer.parseInt(values[0]));

        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        int i = 1;

        while (!q.isEmpty()) {
            Node curr = q.poll();

            if (!values[i].equals("null")) {
                curr.left = new Node(Integer.parseInt(values[i]));
                q.offer(curr.left);
            }
            i++;

            if (!values[i].equals("null")) {
                curr.right = new Node(Integer.parseInt(values[i]));
                q.offer(curr.right);
            }
            i++;
        }

        return root;
    }


}
