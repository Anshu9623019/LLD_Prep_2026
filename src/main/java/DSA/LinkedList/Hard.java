package DSA.LinkedList;

public class Hard {

    class ListNode{
        ListNode next;
        int val;

        ListNode(int n){
            this.val = val;
        }
    }

    // Find Middle element of linkedList
    public ListNode middleNode(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

//    //Reverse a LinkedList
//    three method to reverse linkedlist.
//    Using Stack :- Tc - o(n) and s.c = o(n)
//    Inplace reversel , using privios and next pointer :- T.c :- o(n) Sc = o(1).
//    Using Recursion (if (head == null || head.next == null) {
//        return head;
//    }
//
//    // Recursive step:
//    // Reverse the linked list starting
//    // from the second node (head.next).
//    Node newHead = reverseLinkedList(head.next);
//
//    // Save a reference to the node following
//    // the current 'head' node.
//    Node front = head.next;
//
//    // Make the 'front' node point to the current
//    // 'head' node in the reversed order.
//    front.next = head;
//
//    // Break the link from the current 'head' node
//    // to the 'front' node to avoid cycles.
//    head.next = null;
//
//        return newHead;

    public ListNode reverseList(ListNode head) {

        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {

            // 1. Save remaining list
            ListNode next = curr.next;

            // 2. Reverse current link
            curr.next = prev;

            // 3. Move prev forward
            prev = curr;

            // 4. Move curr forward
            curr = next;
        }

        return prev;
    }


    //Detect Cycle

    public boolean hasCycle(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    // Find the starting point in LL

    public ListNode detectCycle(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        // Step 1: Detect cycle
        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                break;
            }
        }

        // No cycle
        if (fast == null || fast.next == null) {
            return null;
        }

        // Step 2: Find cycle start
        slow = head;

        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }


    // Length of Loop of cycle

    public int lengthOfLoop(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        // Phase 1: Detect cycle
        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {

                // Phase 2: Count cycle length
                int count = 1;
                ListNode curr = slow.next;

                while (curr != slow) {
                    count++;
                    curr = curr.next;
                }

                return count;
            }
        }

        // No cycle
        return 0;
    }


    //Check if LL is palindrome or not
    public boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null) {
            return true;
        }

        // 1. Find middle
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. Reverse second half
        ListNode prev = null;
        ListNode curr = slow;

        while (curr != null) {
            ListNode next = curr.next;

            curr.next = prev;

            prev = curr;
            curr = next;
        }

        // 3. Compare first half and reversed second half
        ListNode left = head;
        ListNode right = prev;

        while (right != null) {

            if (left.val != right.val) {
                return false;
            }

            left = left.next;
            right = right.next;
        }

        return true;
    }

    // Segregate odd and even nodes in Linked List
    public ListNode oddEvenList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode odd = head;
        ListNode even = head.next;

        // Remember beginning of even list
        ListNode evenHead = even;

        while (even != null && even.next != null) {

            // Connect odd nodes
            odd.next = even.next;
            odd = odd.next;

            // Connect even nodes
            even.next = odd.next;
            even = even.next;
        }

        // Attach even list after odd list
        odd.next = evenHead;

        return head;
    }


    //Remove Nth Node From the End of Linked List

    public ListNode removeNthFromEnd(ListNode head, int n) {

        // Dummy node handles deletion of head
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode slow = dummy;
        ListNode fast = dummy;

        // Keep fast n+1 nodes ahead of slow
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Move both pointers
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Remove nth node from end
        slow.next = slow.next.next;

        return dummy.next;
    }

    // Delete the Middle Node of a Linked List
    public ListNode deleteMiddle(ListNode head) {

        // Only one node
        if (head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head.next.next;

        // Find node just before middle
        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;
        }

        // Delete middle
        slow.next = slow.next.next;

        return head;
    }

    // Sort LL

    public ListNode sortList(ListNode head) {

        // Base case
        if (head == null || head.next == null) {
            return head;
        }

        // 1. Find middle
        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. Split into two halves
        ListNode right = slow.next;
        slow.next = null;

        // 3. Recursively sort both halves
        ListNode leftSorted = sortList(head);
        ListNode rightSorted = sortList(right);

        // 4. Merge sorted halves
        return merge(leftSorted, rightSorted);
    }

    private ListNode merge(ListNode left, ListNode right) {

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (left != null && right != null) {

            if (left.val <= right.val) {
                curr.next = left;
                left = left.next;
            } else {
                curr.next = right;
                right = right.next;
            }

            curr = curr.next;
        }

        // Attach remaining nodes
        if (left != null) {
            curr.next = left;
        } else {
            curr.next = right;
        }

        return dummy.next;
    }


    // Sort 0,1,2
    public ListNode segregate(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        // Dummy heads
        ListNode zeroD = new ListNode(-1);
        ListNode oneD = new ListNode(-1);
        ListNode twoD = new ListNode(-1);

        // Tails
        ListNode zero = zeroD;
        ListNode one = oneD;
        ListNode two = twoD;

        ListNode curr = head;

        while (curr != null) {

            ListNode next = curr.next;

            // Detach current node
            curr.next = null;

            if (curr.val == 0) {

                zero.next = curr;
                zero = curr;

            } else if (curr.val == 1) {

                one.next = curr;
                one = curr;

            } else {

                two.next = curr;
                two = curr;
            }

            curr = next;
        }

        // Connect 0-list → 1-list → 2-list

        zero.next = (oneD.next != null)
                ? oneD.next
                : twoD.next;

        one.next = twoD.next;

        return zeroD.next;
    }

    // Find the intersection point of Y LL

    public ListNode getIntersectionNode(
            ListNode headA,
            ListNode headB) {

        ListNode a = headA;
        ListNode b = headB;

        while (a != b) {

            a = (a == null) ? headB : a.next;
            b = (b == null) ? headA : b.next;
        }

        return a;
    }



    // Add one to a number represented by LL
    public ListNode addOne(ListNode head) {

        // Step 1: Reverse
        head = reverse(head);

        // Step 2: Add 1
        ListNode curr = head;
        int carry = 1;

        while (curr != null && carry != 0) {

            int sum = curr.val + carry;

            curr.val = sum % 10;
            carry = sum / 10;

            curr = curr.next;
        }

        // If carry remains, create new node
        if (carry != 0) {

            ListNode newNode = new ListNode(carry);
            newNode.next = head;
            head = newNode;
        }

        // Step 3: Reverse back
        return reverse(head);
    }

    private ListNode reverse(ListNode head) {

        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {

            ListNode next = curr.next;

            curr.next = prev;

            prev = curr;
            curr = next;
        }

        return prev;
    }

    //Add two numbers in Linked List

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // Reverse both lists
        l1 = reverse(l1);
        l2 = reverse(l2);

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {

            int sum = carry;

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            carry = sum / 10;
        }

        // Reverse result
        return reverse(dummy.next);
    }

    //reverse kth node

    public ListNode reverse2(ListNode head){
        if(head==null || head.next==null){
            return head;
        }
        ListNode prev = head;
        ListNode curr = head.next;
        head.next = null;
        while(curr!=null){
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    public ListNode kthNode(ListNode head,int k){

        while(k>1 && head!=null){
            head = head.next;
            k--;
        }
        return head;
    }
    public ListNode reverseKGroup(ListNode head, int k) {

        if(head==null || head.next==null || k==1){
            return head;
        }

        ListNode temp = head; ListNode prev = null;
        ListNode next = null; ListNode kth = null;
        while(temp!=null){
            kth = kthNode(temp,k);
            if(kth==null){
                if(prev!=null){
                    prev.next = temp;
                }
                break;
            }else{
                next = kth.next;
                kth.next = null;
                kth = reverse(temp);
                if(temp==head){
                    head = kth;
                }else{
                    prev.next = kth;
                }
                prev = temp;
                temp = next;
            }
        }

        return head;
    }


    //Rotate a LL

    public ListNode rotateRight(ListNode head, int k) {

        // Edge cases
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // Step 1: Find length and tail
        int n = 1;
        ListNode tail = head;

        while (tail.next != null) {
            tail = tail.next;
            n++;
        }

        // Step 2: Reduce unnecessary rotations
        k = k % n;

        if (k == 0) {
            return head;
        }

        // Step 3: Make circular
        tail.next = head;

        // Step 4: Find new tail
        int steps = n - k;

        ListNode newTail = head;

        for (int i = 1; i < steps; i++) {
            newTail = newTail.next;
        }

        // Step 5: New head
        ListNode newHead = newTail.next;

        // Step 6: Break circle
        newTail.next = null;

        return newHead;
    }

    // Flattening of LL
    public Node flatten(Node head) {

        // Empty or single node
        if (head == null || head.next == null) {
            return head;
        }

        // Flatten the remaining list
        head.next = flatten(head.next);

        // Merge current child list with flattened next list
        head = merge(head, head.next);

        return head;
    }

    private Node merge(Node a, Node b) {

        if (a == null) return b;
        if (b == null) return a;

        Node result;

        if (a.data <= b.data) {
            result = a;
            result.child = merge(a.child, b);
        } else {
            result = b;
            result.child = merge(a, b.child);
        }

        return result;
    }

    // Clone a Linked List with Random and Next Pointer,
    // Two approach... 1). HashMap : 2). Keep copy node bet two original node

    public Node copyRandomList(Node head) {

        if (head == null) {
            return null;
        }

        Map<Node, Node> map = new HashMap<>();

        // Step 1: Create clone nodes
        Node curr = head;

        while (curr != null) {

            map.put(curr, new Node(curr.val));

            curr = curr.next;
        }

        // Step 2: Connect next and random
        curr = head;

        while (curr != null) {

            Node clone = map.get(curr);

            clone.next = map.get(curr.next);
            clone.random = map.get(curr.random);

            curr = curr.next;
        }

        return map.get(head);
    }


    public Node copyRandomList(Node head) {

        if (head == null) {
            return null;
        }

        // ------------------------------------------------
        // Step 1: Insert clone after every original node
        // ------------------------------------------------

        Node curr = head;

        while (curr != null) {

            Node copy = new Node(curr.val);

            copy.next = curr.next;
            curr.next = copy;

            curr = copy.next;
        }


        // ------------------------------------------------
        // Step 2: Assign random pointers
        // ------------------------------------------------

        curr = head;

        while (curr != null) {

            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }

            curr = curr.next.next;
        }


        // ------------------------------------------------
        // Step 3: Separate original and cloned lists
        // ------------------------------------------------

        Node dummy = new Node(0);
        Node copyCurr = dummy;

        curr = head;

        while (curr != null) {

            Node copy = curr.next;

            // Restore original list
            curr.next = copy.next;

            // Build cloned list
            copyCurr.next = copy;
            copyCurr = copy;

            curr = curr.next;
        }

        return dummy.next;
    }




















}
