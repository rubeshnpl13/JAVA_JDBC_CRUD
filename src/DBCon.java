import java.sql.*;
public class DBCon {
    public Connection con;


    public DBCon() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/studentdb","root","");

        } catch (Exception ex) {
            System.out.println("Error:" + ex);
        }


    }
    public static void main(String[] args)
    {
        new DBCon();
    }

}
