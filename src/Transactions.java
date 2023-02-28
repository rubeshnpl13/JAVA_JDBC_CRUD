import java.sql.SQLException;
import java.sql.Statement;

public class Transactions {
    public static void main(String[] args) throws SQLException {
//        class.forName('com.mysql.cz.jdbc.Driver');
//        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","user", "");
//        con.setAutoCommit(false);
       DBCon dbc = new DBCon();

        dbc.con.setAutoCommit(false);
        Statement stmt = dbc.con.createStatement();
        stmt.executeUpdate("insert into Login values('raty', '12344')");
        stmt.executeUpdate("insert into Login values('masy', '12e4')");
        stmt.executeUpdate("insert into Login values('eaty', '1ss44')");
        dbc.con.commit();
        dbc.con.close();
    }
}
