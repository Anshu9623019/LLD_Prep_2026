package abc;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> li = Arrays.asList("ABC","DEF","ABC","DEF","GEF",null);


        Map<String,Integer> mp = new HashMap<>();

        for(String s : li){
            mp.put(s,mp.getOrDefault(s,0)+1);
        }

        System.out.println(mp);

       //Map<String,Integer> mp = li.stream().collect(Collectors.groupingBy(s->s.length(),C));

        List<Integer> li1 = Arrays.asList(111,221,123,234,null);

        List<Integer> ans =  li1.stream().filter(s ->String.valueOf(s).startsWith("1")).collect(Collectors.toList());

        System.out.println(ans);

    }
}
