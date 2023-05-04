package jdbc;

public class Passenger {
    private int passengerNo;
    private String name;
    private int grade;
    private int age;

    public Passenger(int no, String name, int grade, int age){
        this.passengerNo = no;
        this.name = name;
        this.grade = grade;
        this.age = age;
    }

    public int getPassengerNo(){
        return this.passengerNo;
    }

    public String getName(){
        return this.name;
    }

    public int getGrade(){
        return this.grade;
    }

    public int getAge(){
        return this.age;
    }
    
    @Override
    public String toString(){
        return this.passengerNo + " " + this.name + " " +this.grade + " " +this.age;
    }

}
