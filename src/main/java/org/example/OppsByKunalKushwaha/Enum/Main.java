package org.example.OppsByKunalKushwaha.Enum;

public class Main {

    public static void main(String[] args) {
        /*Common functions which is used
            -values(),
            -ordinal(),
            -valueOf(),
            -name()
         */

        //1. usages of Values() and ordinal()
        for(EnumExample sample : EnumExample.values()){
            System.out.println(sample.ordinal());
        }


        //Usages of valueOf() and name()
        EnumExample sample1 = EnumExample.valueOf("MONDAY");
        System.out.println(sample1.name());

        EnumCustomValues enumCustomValues = EnumCustomValues.getEnumWithCustomValue(101);
        EnumCustomValues enumCustomValues1 = EnumCustomValues.TUESDAY;
        System.out.println(enumCustomValues.getValue());
        System.out.println(enumCustomValues.getComment());
        System.out.println(EnumCustomValues.MONDAY.getComment());
        System.out.println(EnumCustomValues.WEDNESDAY.getValue());

        //Override the method in enum
        EnumMethodOvverrideBYConstant enumMethodOvverrideBYConstant = EnumMethodOvverrideBYConstant.MONDAY;
        enumMethodOvverrideBYConstant.dummyMethod();
        EnumMethodOvverrideBYConstant enumMethodOvverrideBYConstant1 = EnumMethodOvverrideBYConstant.TUESDAY;
        enumMethodOvverrideBYConstant1.dummyMethod();


        // Override the method with abstract method of enum
        EnumWithAbstractMethod enumWithAbstractMethod = EnumWithAbstractMethod.TUESDAY;
        enumWithAbstractMethod.dummyMethod();

        //implement the interface on enum classes
        EnumWithAbstractMethod enumWithAbstractMethod1 = EnumWithAbstractMethod.MONDAY;
        enumWithAbstractMethod1.toLowerCase();

        //
    }
}
