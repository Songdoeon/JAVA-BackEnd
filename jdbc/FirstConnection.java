import java.sql.*;

public class FirstConnection {
    private static final String diverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String databaseURL = "jdbc:sqlserver://localhost;encrypt=true;trustServerCertificate=true;databaseName=<Module06>";
    private static final String userName = "sa";
    private static final String password = "kogpsd";

    Connection connection =null;
    Statement statement = null;
    ResultSet result = null;

    public void connect(){
        try{
            Class.forName(diverName);
            connection = DriverManager.getConnection(databaseURL, userName, password);
            statement = connection.createStatement();
            String sqlquery = "select * from users";
            result = statement.executeQuery(sqlquery);

            while(result.next()){
                System.out.println(result.getString(1));
                // System.out.println(result.getInt(1));
                // System.out.println(result.getInt(1));
                // System.out.println(result.getInt(1));
            }
            result.close();
            statement.close();
            connection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        FirstConnection first = new FirstConnection();
        first.connect();

    }


}
