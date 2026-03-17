package org.example.OppsByKunalKushwaha.Access;

public class ObjectDemo {
    int num;
    float gpa;
    public ObjectDemo(int num,float gpa) {
        super();
        this.num = num;
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "ObjectDemo{}";
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

     // Learn more in Hashmap lecture
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public static void main(String[] args) {
        ObjectDemo obj = new ObjectDemo(10,31.1f);
        ObjectDemo obj1 = new ObjectDemo(10,12.4f);

        if(obj1==obj){
            System.out.println("obj1 is equal to obj2");
        }

        if(obj.equals(obj1)){
            System.out.println("obj1 is equal to obj2");
        }

    }
}
