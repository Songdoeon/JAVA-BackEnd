import java.sql.*;

public class SecondConnection {
    private static final String diverName = "com.mysql.cj.jdbc.Driver";
    private static final String databaseURL = "jdbc:mysql://localhost:/Module06";
    private static final String userName = "root";
    private static final String password = "@ehdjs78";

    Connection myConnection =null;
    PreparedStatement statement = null;
    ResultSet result = null;

    public void connect(){
        try{
            Class.forName(diverName);
            myConnection = DriverManager.getConnection(databaseURL, userName, password);
            String Query = "SELECT PassengerName, Grade, Age From Passenger WHERE PassengerNo = ?";
            statement =  myConnection.prepareStatement(Query);
            statement.setString(1,"2");
            
            result =statement.executeQuery();
            while(result.next()){
                System.out.println(result.getString(1)+" ");
                System.out.println(result.getString(2)+" ");
                System.out.println(result.getString(3)+" ");
                // System.out.println(result.getString(4));
                
                // System.out.println(result.getInt(1));
                // System.out.println(result.getInt(1));
                // System.out.println(result.getInt(1));
            }
            result.close();
            statement.close();
            myConnection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        SecondConnection first = new SecondConnection();
        first.connect();

    }

}
