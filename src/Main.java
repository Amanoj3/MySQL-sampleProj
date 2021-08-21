import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

public class Main { // Citation -  I used this link as a reference:
    // https://www.javatpoint.com/example-to-connect-to-the-mysql-database
    public static void main(String[] args) {
        ArrayList<String> queryStrings = new ArrayList<String>();
        StringBuilder alertBoxMessage = new StringBuilder();
        Connection con = null;
        try {
            System.out.println("heeee"); // works up until this point
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/sample_db?characterEncoding=latin1","root","password");
            String query = "select * from sampletable";
            Statement st = con.createStatement();
            ResultSet result = st.executeQuery(query);
            while (result.next()) {
                String currentString = "";
                currentString += result.getInt("sampleId");
                currentString += " ";
                currentString += result.getString("sampleName");
                currentString += " ";
                currentString += result.getString("sampleDate");
                queryStrings.add(currentString);
                System.out.println(currentString);
            }
            con.close();
            alertBoxMessage.append(" ID| NAME| DATE").append("\n");
            for (String queryString : queryStrings) {
                alertBoxMessage.append(queryString).append("\n");
            }
            JOptionPane.showMessageDialog(null, alertBoxMessage);
        }
        catch(SQLException | ClassNotFoundException ex) {
            System.out.println("Exception caught gracefully!");
            System.out.println(ex.getMessage());
        }
    }
}
