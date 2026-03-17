package abc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Main2 {

    public static void main(String[] args) {
//        Input: s = "abcabcbb"
//        Output: 3;
//        Explanation: The answer is "abc", with the length of 3.
        String s = "pwwkew";
        Set<Character> mp = new HashSet<>();
        int j = 0;
        int i = 0;
        int n = s.length();
        int max = 0;
        while(i<n && j<n){
                char ch = s.charAt(i);
                mp.add(ch);
                if(mp.size()==j-i+1) {
                    max = Math.max(max, j - i + 1);
                    j++;
                }else{
                    i++;
                    j++;
                    mp.remove(s.charAt(i));
                }
        }
        System.out.println(max);
    }

}
