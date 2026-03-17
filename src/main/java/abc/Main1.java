package abc;

import  java.util.*;

public class Main1 {

    public static int longestSubstring(String s, int k) {

        Map<Character,Integer> mp = new HashMap<>();
        int j = 0;
        int max = 0;
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            mp.put(ch,mp.getOrDefault(ch,0)+1);
            if(mp.get(ch)>k){
                while(mp.get(ch)>k){
                    char ch1 = s.charAt(j);
                    if(mp.containsKey(ch1)){
                        if(mp.get(ch1)>1){
                            mp.put(ch1,mp.get(ch1)-1);
                        }
                        if(mp.get(ch1)==0){
                            mp.remove(ch1);
                        }
                    }
                   j++;
                }
            }
            if(max<i-j+1){
                max = i-j+1;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String str = "ppppqqqrrsssaabbcdefggghh";
        System.out.println(longestSubstring(str,2));
    }
}
