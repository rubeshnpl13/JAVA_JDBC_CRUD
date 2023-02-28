import java.sql.*;
public class FetchRecords {
    public static void main(String[] args) throws SQLException {
//        class.forName('com.mysql.cz.jdbc.Driver');
//        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","user", "");
//        con.setAutoCommit(false);
       DBCon dbc = new DBCon();

        dbc.con.setAutoCommit(false);
        Statement stmt = dbc.con.createStatement();
        stmt.addBatch("insert into Login values('riyes', 'Nepal')");
        stmt.addBatch("insert into Login value('besh', 'epal')");
        stmt.executeBatch();
        dbc.con.commit();
        dbc.con.close();
    }
}
