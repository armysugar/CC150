package ch3;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.LinkedList;

/**
 * Created by Jun on 11/26/2014.
 * 3.7 of CC150
 * A queue holding two types of objects, can either dequeue the oldest of all or oldest of one type
 */
public class TowFoldQueue {
//    public final enum Animals {CAT", "DOG"};
    private LinkedList<Animal> ls = new LinkedList<Animal>();

    public static void main(String[] args) {
        TowFoldQueue shelter = new TowFoldQueue();
        shelter.enqueue(new Animal("DOG", 0));
        shelter.enqueue(new Animal("CAT", 0));
        shelter.enqueue(new Animal("CAT", 1));
        shelter.enqueue(new Animal("DOG", 1));
        System.out.println(shelter.dequeueCat());
        System.out.println(shelter.dequeueAll());
        System.out.println(shelter);
    }

    public boolean enqueue(Animal a){
        return ls.add(a);
    }

    public boolean enqueue(String type){
        return ls.add(new Animal(type));
    }

    public Animal dequeueAll(){
        if(!ls.isEmpty()){
            return ls.remove(0);
        }
        return null;
    }

    public Animal dequeueCat(){
        if(getFirstCatIndex() > -1){
            return ls.remove(getFirstCatIndex());
        }else {
            return null;
        }
    }

    public Animal dequeueDog(){
        if(getFirstDogIndex() > -1){
            return ls.remove(getFirstDogIndex());
        }else {
            return null;
        }
    }

    public int getFirstCatIndex(){
        return ls.indexOf(new Animal("CAT"));
    }

    public int getFirstDogIndex(){
        return ls.indexOf(new Animal("DOG"));
    }

    public String toString(){
        return ls.toString();
    }

}

class Animal{
//    public enum Animal {CAT, DOG}
    private final String type;

    private int rank;
//    private Animal next;
    public Animal(String type){
        if(type.equals("CAT") || type.equals("DOG")){
            this.type = type;
            rank = 0;
        }else {
            this.type = "UNKNOWN ANIMAL";
            rank = 0;
        }
//        next = null;
    }
    public Animal(String type, int rank){
        if(type.equals("CAT") || type.equals("DOG")){
            this.type = type;
            this.rank = rank;
        }else {
            this.type = "UNKNOWN ANIMAL";
            this.rank = rank;
        }
//        next = null;
    }
//    public Animal getNext() {
//        return next;
//    }
//
//    public Animal setNext(Animal next){
//        this.next = next;
//        return this;
//    }

    public String getType() {
        return type;
    }

    public boolean isCat(){
        return type.equals("CAT");
    }

    public boolean isDog(){
        return type.equals("DOG");
    }

    public boolean equals(Object a){
        if(a instanceof Animal){
            if(((Animal) a).type.equals(this.type)) {
                return true;
            }
        }
        return false;
    }

    public String toString(){
        return type + " " + rank;
    }

}