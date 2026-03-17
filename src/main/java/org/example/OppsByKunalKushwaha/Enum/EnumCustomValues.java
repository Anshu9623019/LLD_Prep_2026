package org.example.OppsByKunalKushwaha.Enum;

public enum EnumCustomValues {
    MONDAY(101,"1st day of week"),
    TUESDAY(101,"1st day of week"),
    WEDNESDAY(101,"1st day of week"),
    THURSDAY(101,"1st day of week"),
    FRIDAY(101,"1st day of week"),
    SATURDAY(101,"1st day of week"),
    SUNDAY(101,"1st day of week");

    private int value;
    private String comment;

    EnumCustomValues(int value,String comment){
        this.value = value;
        this.comment = comment;
    }
    public int getValue(){
        return value;
    }
    public void setValue(){
        this.value = value;
    }
    public String getComment(){
        return comment;
    }
    public void setComment(){
        this.comment = comment;
    }

    public  static EnumCustomValues getEnumWithCustomValue(int value){
        for (EnumCustomValues customValues : EnumCustomValues.values()){
            if(customValues.value==value){
                return customValues;
            }
        }
        return  null;
    }

}
