package main.java.com.eugeniojava.model;

public class Customer {

    private int id;
    private String name;
    private int age;
    private String segment;

    public Customer() {
    }

    public Customer(String name, int age, String segment) {
        this.name = name;
        this.age = age;
        this.segment = segment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    @Override
    public String toString() {
        return "Id: " + id + " | Name: " + name + " | Age: " + age + " | Segment: " + segment;
    }
}
