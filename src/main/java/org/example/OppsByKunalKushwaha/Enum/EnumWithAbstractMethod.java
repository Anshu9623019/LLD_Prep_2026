package org.example.OppsByKunalKushwaha.Enum;

public enum EnumWithAbstractMethod implements MyInterface{
    MONDAY{
        @Override
        public void dummyMethod(){
            System.out.println("Monday dummy method");
        }
    },
    TUESDAY{
        @Override
        public void dummyMethod(){
            System.out.println("Monday dummy method");
        }
    },
    WEDNESDAY{
        @Override
        public void dummyMethod(){
            System.out.println("Wednesday dummy method");
        }
    },
    THURSDAY{
        @Override
        public void dummyMethod(){
            System.out.println("Thursday dummy method");
        }
    };

    public abstract void dummyMethod();

    @Override
    public String toLowerCase(){
        return this.name().toLowerCase();
    }
}
