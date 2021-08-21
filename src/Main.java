import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

public class Main { // Citation -  I used this link as a reference:
    // https://www.javatpoint.com/example-to-connect-to-the-mysql-database
    public static void main(String[] args) {
        ArrayList<String> queryStrings = new ArrayList<String>(); // each string is a row from the table
        StringBuilder alertBoxMessage = new StringBuilder(); // all the rows will be appended here
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // establish a connection
            con = DriverManager.getConnection("jdbc:mysql://localhost/sample_db?characterEncoding=latin1","root","password");
            String query = "select * from sampletable"; // a string that contains a SQL query;
            Statement st = con.createStatement();
            ResultSet result = st.executeQuery(query); // this will include the results of executing the SQL query
            while (result.next()) { // loop through all the results
                String currentString = "";
                currentString += result.getInt("sampleId"); // append id
                currentString += " ";
                currentString += result.getString("sampleName"); // append name
                currentString += " ";
                currentString += result.getString("sampleDate"); // append date
                queryStrings.add(currentString); // push the stringified into the queryStrings arraylist
            }
            con.close(); // close the connection after getting all the info we need
            alertBoxMessage.append(" ID| NAME| DATE").append("\n"); // the columns on the alert box
            for (String queryString : queryStrings) {
                alertBoxMessage.append(queryString).append("\n"); // ensures there is a new line after each row
            }
            JOptionPane.showMessageDialog(null, alertBoxMessage); // this box shows all the rows from the table
        }
        catch(SQLException | ClassNotFoundException ex) { // handle the exception in a graceful manner
            System.out.println("Exception caught gracefully!");
            System.out.println(ex.getMessage());
        }
    }
}