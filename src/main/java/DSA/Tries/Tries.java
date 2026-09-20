//package DSA.Tries;
//
//class TrieNode {
//    TrieNode[] children;  // 26 letters a-z
//    boolean isEnd;        // marks end of word
//
//    public TrieNode() {
//        children = new TrieNode[26];
//        isEnd    = false;
//    }
//}
//
//
//class Trie {
//    private TrieNode root;
//
//    public Trie() {
//        root = new TrieNode();
//    }
//
//    // ─────────────────────────────
//    // INSERT word into trie
//    // ─────────────────────────────
//    public void insert(String word) {
//        TrieNode node = root;
//
//        for (char ch : word.toCharArray()) {
//            int idx = ch - 'a';
//
//            // create node if doesn't exist
//            if (node.children[idx] == null)
//                node.children[idx] = new TrieNode();
//
//            node = node.children[idx];  // move down
//        }
//
//        node.isEnd = true;   // mark end of word
//    }
//
//    // ─────────────────────────────
//    // SEARCH exact word in trie
//    // ─────────────────────────────
//    public boolean search(String word) {
//        TrieNode node = root;
//
//        for (char ch : word.toCharArray()) {
//            int idx = ch - 'a';
//
//            if (node.children[idx] == null)
//                return false;   // path doesn't exist
//
//            node = node.children[idx];
//        }
//
//        return node.isEnd;   // must be end of word
//    }
//
//    // ─────────────────────────────
//    // STARTSWITH prefix check
//    // ─────────────────────────────
//    public boolean startsWith(String prefix) {
//        TrieNode node = root;
//
//        for (char ch : prefix.toCharArray()) {
//            int idx = ch - 'a';
//
//            if (node.children[idx] == null)
//                return false;
//
//            node = node.children[idx];
//        }
//
//        return true;   // prefix exists (don't check isEnd)
//    }
//}
////```
////
////        ---
////
////        ### Dry Run — Insert & Search
////```
////Insert: "apple"
////
////root → a → p → p → l → e*
////        ↑
////isEnd=true
////
////Insert: "app"
////
////root → a → p → p* → l → e*
////        ↑
////isEnd=true (marked)
////
////Search: "app"  → follow a→p→p → isEnd=true  ✅
////Search: "ap"   → follow a→p   → isEnd=false ❌
////Search: "appl" → follow a→p→p→l → isEnd=false ❌
////
////startsWith("app") → follow a→p→p → exists → true  ✅
////startsWith("app") → true  ✅
////startsWith("b")   → no 'b' child → false  ✅
//
//
//
////Problem 2
////
////Problem 2 — Implement Trie II
////
////        Problem
////
////Implement Trie with:
////
////insert(word)
////countWordsEqualTo(word) — how many times word inserted
////countWordsStartingWith(prefix) — how many words have this prefix
////erase(word) — delete one occurrence
//
//
//
// TrieNode {
//TrieNode[] children;
//int countEndsWith;    // words ending here
//int countPrefix;      // words passing through
//
//public TrieNode() {
//    children     = new TrieNode[26];
//    countEndsWith = 0;
//    countPrefix   = 0;
//}
//}
//
//
//Trie {
//private TrieNode root;
//
//public Trie() {
//    root = new TrieNode();
//}
//
//// INSERT
//public void insert(String word) {
//    TrieNode node = root;
//    for (char ch : word.toCharArray()) {
//        int idx = ch - 'a';
//        if (node.children[idx] == null)
//            node.children[idx] = new TrieNode();
//        node = node.children[idx];
//        node.countPrefix++;    // increment prefix count
//    }
//    node.countEndsWith++;      // increment word count
//}
//
//// COUNT WORDS EQUAL TO word
//public int countWordsEqualTo(String word) {
//    TrieNode node = root;
//    for (char ch : word.toCharArray()) {
//        int idx = ch - 'a';
//        if (node.children[idx] == null) return 0;
//        node = node.children[idx];
//    }
//    return node.countEndsWith;
//}
//
//// COUNT WORDS STARTING WITH prefix
//public int countWordsStartingWith(String prefix) {
//    TrieNode node = root;
//    for (char ch : prefix.toCharArray()) {
//        int idx = ch - 'a';
//        if (node.children[idx] == null) return 0;
//        node = node.children[idx];
//    }
//    return node.countPrefix;
//}
//
//// ERASE one occurrence of word
//public void erase(String word) {
//    TrieNode node = root;
//    for (char ch : word.toCharArray()) {
//        int idx = ch - 'a';
//        node = node.children[idx];
//        node.countPrefix--;    // decrement prefix count
//    }
//    node.countEndsWith--;      // decrement word count
//}
//}
////        ```
////
////        ---
////
////        ### Dry Run — Trie II
////```
////insert("apple")  insert("apple")  insert("app")
////
////After inserts:
////root
////   └─a (prefix=3)
////     └─p (prefix=3)
////       └─p (prefix=3, ends=1)  ← "app" ends here
////         └─l (prefix=2)
////           └─e (prefix=2, ends=2) ← "apple" ends here twice
////
////countWordsEqualTo("apple")     → 2  ✅
////countWordsEqualTo("app")       → 1  ✅
////countWordsStartingWith("app")  → 3  ✅
////countWordsStartingWith("ap")   → 3  ✅
////
////erase("apple"):
////decrement prefix along a→p→p→l→e
////decrement ends at 'e'
////
////After erase:
////countWordsEqualTo("apple")    → 1  ✅
////countWordsStartingWith("app") → 2  ✅
////        ```
//
//
//
//class Solution {
//    public String longestString(String[] words) {
//        Trie trie = new Trie();
//
//        // insert all words
//        for (String word : words) trie.insert(word);
//
//        String result = "";
//
//        for (String word : words) {
//            // check if all prefixes exist
//            if (trie.allPrefixExist(word)) {
//                // take longest, or lexicographically smaller if same length
//                if (word.length() > result.length() ||
//                        (word.length() == result.length() && word.compareTo(result) < 0))
//                    result = word;
//            }
//        }
//
//        return result;
//    }
//}
//
//class Trie {
//    TrieNode root = new TrieNode();
//
//    void insert(String word) {
//        TrieNode node = root;
//        for (char ch : word.toCharArray()) {
//            int idx = ch - 'a';
//            if (node.children[idx] == null)
//                node.children[idx] = new TrieNode();
//            node = node.children[idx];
//        }
//        node.isEnd = true;
//    }
//
//    // check if ALL prefixes of word exist in trie
//    boolean allPrefixExist(String word) {
//        TrieNode node = root;
//        for (char ch : word.toCharArray()) {
//            int idx = ch - 'a';
//            node = node.children[idx];
//            if (!node.isEnd) return false;  // prefix not a complete word
//        }
//        return true;
//    }
//}
////```
////
////        ---
////
////        ## Problem 4 — Number of Distinct Substrings
////
////---
////
////        ### Problem
////> Given a string `s`, return the **number of distinct substrings** (including empty string).
////        ```
////Input:  s = "abc"
////Output: 7
////Reason: "", "a", "b", "c", "ab", "bc", "abc" → 7
////        ```
////
////        ---
////
////        ### Key Insight
////```
////Insert every suffix of s into trie:
////s = "abc"
////suffixes: "abc", "bc", "c"
////
////Each NEW node created = NEW distinct substring
////
////Count all nodes + 1 (for empty string)
//
//
//class Solution {
//    public int countDistinctSubstrings(String s) {
//        int n     = 0;
//        int count = 0;   // count of trie nodes = distinct substrings
//
//        // TrieNode without class — use array of maps
//        // Simple array-based trie
//        int[][] trie = new int[s.length() * s.length() + 1][26];
//        int nodeCount = 1;   // root = node 0
//
//        // insert every suffix
//        for (int i = 0; i < s.length(); i++) {
//            int node = 0;   // start from root
//
//            for (int j = i; j < s.length(); j++) {
//                int idx = s.charAt(j) - 'a';
//
//                if (trie[node][idx] == 0) {
//                    trie[node][idx] = nodeCount++;
//                    count++;   // new node = new distinct substring
//                }
//
//                node = trie[node][idx];
//            }
//        }
//
//        return count + 1;   // +1 for empty string
//    }
//}
//```
//
//        ---
//
//        ## Problem 5 — Maximum XOR of Two Numbers (LC 421)
//
//---
//
//        ### Problem
//> Given integer array `nums`, find the **maximum XOR** of any two elements.
//        ```
//Input:  nums = [3, 10, 5, 25, 2, 8]
//Output: 28
//Reason: 5 XOR 25 = 00101 XOR 11001 = 11100 = 28
//        ```
//
//        ---
//
//        ### Key Insight
//```
//XOR is maximized when bits DIFFER:
//        0 XOR 1 = 1  ← different → good!
//        1 XOR 0 = 1  ← different → good!
//        0 XOR 0 = 0  ← same      → bad
//  1 XOR 1 = 0  ← same      → bad
//
//Strategy:
//Insert all numbers in BINARY TRIE (MSB first)
//For each number, greedily find its complement
//        (try opposite bit at each level)
//
