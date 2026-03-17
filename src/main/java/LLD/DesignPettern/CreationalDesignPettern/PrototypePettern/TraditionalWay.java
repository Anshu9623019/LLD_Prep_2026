package LLD.DesignPettern.CreationalDesignPettern.PrototypePettern;

class Character implements Cloneable{
     String name ;
     int health;
     int attackPower;
     int level;

    public Character(String name,int health,int attackPower,int level){
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.level = level;
    }
    @Override
    public Character clone() throws CloneNotSupportedException{
        return (Character) super.clone();
    }
    public void  showCharacterInfo(){
        System.out.println("Character name="+name+", Health="+health+", AttackPower"+attackPower+", and level="+level);
    }
}

class CharacterFactory{
    private  Character characterPrototype;

    CharacterFactory(){
        characterPrototype = new Character("DefaultName",100,50,1);
    }

    public Character createCharacterWithName(String name) throws CloneNotSupportedException{
       Character cloneCharacter = characterPrototype.clone();
       cloneCharacter = new Character(name,cloneCharacter.health,cloneCharacter.attackPower,cloneCharacter.level);
       return cloneCharacter;
    }
    public Character createCharacterWithHealth(int health) throws CloneNotSupportedException{
        Character cloneCharacter = characterPrototype.clone();
        cloneCharacter = new Character(cloneCharacter.name,health,cloneCharacter.attackPower,cloneCharacter.level);
        return cloneCharacter;
    }

    public Character createCharacterWithPower(int attackPower) throws CloneNotSupportedException{
        Character cloneCharacter = characterPrototype.clone();
        cloneCharacter = new Character(characterPrototype.name,cloneCharacter.health,attackPower,cloneCharacter.level);
        return cloneCharacter;
    }

    public Character createCharacterWithLevel(int level) throws CloneNotSupportedException{
        Character cloneCharacter = characterPrototype.clone();
        cloneCharacter = new Character(characterPrototype.name,cloneCharacter.health,cloneCharacter.attackPower,level);
        return cloneCharacter;
    }
}


// Step 1: Prototype class
class Employee implements Cloneable {
    private int id;
    private String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void show() {
        System.out.println("ID: " + id + ", Name: " + name);
    }

    // Step 2: Clone method
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // shallow copy
    }
}

public class TraditionalWay {

    public static void main(String[] args) {
        CharacterFactory characterFactory = new CharacterFactory();
        try {
            Character warrior = characterFactory.createCharacterWithName("Warrior");
            Character level = characterFactory.createCharacterWithLevel(100);
            level.showCharacterInfo();
            warrior.showCharacterInfo();

            //2nd example
            Employee emp1 = new Employee(101, "John");
            Employee emp2 = (Employee) emp1.clone(); // cloning

            emp1.show();
            emp2.show();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
    }
}


