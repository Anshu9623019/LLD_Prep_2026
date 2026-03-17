package org.example;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class FailFastMap {

    public static void main(String[] args){



        // FailFast Map
        Map<Integer,String> map= new ConcurrentHashMap<>();
        map.put(1,"one");
        map.put(2,"two");
        map.put(3,"three");
        Iterator<Integer> it = map.keySet().iterator();
        for(Integer key : map.keySet()){
            System.out.println(key);
            System.out.println(map.get(key));
        }
        while(it.hasNext()){
            System.out.println(it.next());
            map.put(4,"four");
        }
        // FailFast List
        List<String> li = new CopyOnWriteArrayList<>();
        li.add("a");
        li.add("b");

        Iterator<String> ite = li.iterator();
        while(ite.hasNext()){
            System.out.println(ite.next());
            li.add("c");
        }


        // How can I write custom ArrayList without duplicate element.


    }
}
