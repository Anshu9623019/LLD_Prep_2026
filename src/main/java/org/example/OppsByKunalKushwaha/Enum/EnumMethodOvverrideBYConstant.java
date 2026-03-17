package org.example.OppsByKunalKushwaha.Enum;

public enum EnumMethodOvverrideBYConstant {

    MONDAY{
        @Override
        public void dummyMethod(){
            System.out.println("Monday dummy method");
        }
    },
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    public void dummyMethod(){
        System.out.println("Default dummy method");
    }


}
