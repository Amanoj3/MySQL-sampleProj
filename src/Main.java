import java.sql.*;
import javax.swing.*;

public class Main { // Citation -  I used this link as a reference:
    // https://www.javatpoint.com/example-to-connect-to-the-mysql-database
    public static void main(String[] args) {
        System.out.println("hey");
        Connection con = null;
        try {
            System.out.println("heeee"); // works up until this point
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/sample_db?characterEncoding=latin1","root","password");
            String query = "select * from sampletable";
            Statement st = con.createStatement();
            ResultSet result = st.executeQuery(query);
            int index = 1;
            while (result.next()) {
                String currentString = "";
                currentString += result.getInt("sampleId");
                currentString += " ";
                currentString += result.getString("sampleName");
                currentString += " ";
                currentString += result.getString("sampleDate");
                System.out.println(currentString);
            }
            con.close();
        }
        catch(SQLException | ClassNotFoundException ex) {
            System.out.println("Exception caught gracefully!");
            System.out.println(ex.getMessage());
        }
    }
}
