package DSA.Heap.Operation;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

 class HashMapFinal<K,V>{
    ArrayList<LinkedList<Entity<K,V>>> list;

    private int size = 0;
    private float lf = 0.5f;

    public HashMapFinal(){
        list = new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(new LinkedList<>());
        }
    }

    public  void put(K key,V value){
        int hash = Math.abs(key.hashCode()%list.size());

        LinkedList<Entity<K,V>> entities = list.get(hash);

        for(Entity entity : entities){
            if(entity.key.equals(key)){
                entity.value = value;
                return;
            }
        }
        if((float)(size)/list.size()>lf){
            reHash();
        }
        entities.add(new Entity<>(key,value));
    }

    private void reHash(){
        System.out.println("We are now rehashing");
        ArrayList<LinkedList<Entity<K,V>>> old = list;
        list = new ArrayList<>();
        size = 0;
        for (int i=0;i<old.size()*2;i++){
            list.add(new LinkedList<>());
        }
        for (LinkedList<Entity<K,V>> entities  : old){
            for(Entity<K,V> entity : entities ){
                put(entity.key, entity.value);
            }
        }
    }


    public V get(K key){
        int hash = Math.abs(key.hashCode()%list.size());
        LinkedList<Entity<K,V>> entities = list.get(hash);

        for(Entity<K,V> entity : entities){
            if(entity.key.equals(key)){
                return entity.value;
            }
        }
        return null;
    }

    public  void remove(K key){
        int hash = Math.abs(key.hashCode()%list.size());
        LinkedList<Entity<K,V>> entities = list.get(hash);
        Entity<K,V> target = null;


        for(Entity<K,V> entity : entities){
            if(entity.key.equals(key)){
                target =  entity;
                break;
            }
        }
        entities.remove(target);
        size--;
    }

    public  boolean containsKey(K key){
        return get(key)!=null;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        for (LinkedList<Entity<K,V>> entities : list){

            for (Entity<K,V> entity : entities){
                builder.append(entity.key);
                builder.append("=");
                builder.append(entity.value);
                builder.append(", ");
            }

        }
        builder.append("}");
        return builder.toString();
    }
    private class Entity<K,V>{
        K key;
        V value;
        Entity(K key,V val){
            this.key = key;
            this.value = val;
        }
    }
}

public class HashMapLinkedList {
    public static void main(String[] args) {
        HashMapFinal<String,Integer> hp = new HashMapFinal<>();
        hp.put("Ram",20);
        hp.put("Kumar",21);
        hp.put("Kum111ar",21);
        hp.put("Kuma2r",21);
        hp.put("Kumar1",21);
        hp.put("Kumar111",21);
//      hp.put("Ram",22);
        System.out.println(hp);
    }
}
