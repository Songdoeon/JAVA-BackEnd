package jdbc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PassengerList {
    List<Passenger> passengerList = new ArrayList<>();
    private static final String driverName = "com.mysql.cj.jdbc.Driver";
    private static final String databaseURL = "jdbc:mysql://localhost:/Module06";
    private static final String userName = "root";
    private static final String password = "@ehdjs78";


    public void loadDriver(String driverName){
        try{
            Class.forName(driverName);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<Passenger> getDate(){
        loadDriver(driverName);
        Connection myConnection =null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try{
            myConnection = DriverManager.getConnection(databaseURL, userName, password);
            String Query = "SELECT PassengerNo,PassengerName, Grade, Age From Passenger";
            statement =  myConnection.prepareStatement(Query);

            result = statement.executeQuery(Query);
            while(result.next()){
                this.passengerList.add(new Passenger(result.getInt(1),
                result.getString(2),
                result.getInt(3),
                result.getInt(4)));
            }
            result.close();
            statement.close();
            myConnection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return passengerList;
    }
    public static void main(String[] args){
        PassengerList list = new PassengerList();
        List<Passenger> passnegers = list.getDate();
        
        for(Passenger p: passnegers){
            System.out.println(p);
        }

    }
}
class Passenger {
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
