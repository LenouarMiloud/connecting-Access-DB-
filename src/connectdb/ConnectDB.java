package connectdb;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ANONYME
 */
public class ConnectDB {
    public static void main(String[] args) {

        // variables
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        // load Oracle JDBC Driver
        try {

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }
        catch(ClassNotFoundException cnfex) {

            System.out.println("Problem in loading "
                    + " MS Access JDBC driver");
            cnfex.printStackTrace();
        }

        // open the database connection
        try {

            String msAccDB = "D:/Users_DB.mdb";
            String dbURL = "jdbc:ucanaccess://" + msAccDB; 

            // Create coonection By DriverManager
            connection = DriverManager.getConnection(dbURL); 

            // Create Statement object
            statement = connection.createStatement();

            // right the query for retrieving data from database
            String sql ="SELECT * FROM users";
            
            // Execute the query and put the result into result set object
            resultSet = statement.executeQuery(sql);

            // print the data in the console 
            while(resultSet.next()) {
                int id = resultSet.getInt(1);
                String nom = resultSet.getString(2);
                String prenom = resultSet.getString(3);
                System.out.println(id + "\t" + 
                        nom + "\t" + 
                        prenom);
            }
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {

            // Close the data base connection
            try {
                if(null != connection) {

                    resultSet.close();
                    statement.close();

                    connection.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }
    }
}
