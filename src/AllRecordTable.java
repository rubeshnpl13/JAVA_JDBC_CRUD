import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.Statement;

public class AllRecordTable extends JFrame {
    JTable table;
    DefaultTableModel model;

    public AllRecordTable()
    {
        model=new DefaultTableModel();
        table =new JTable(model);

        model.addColumn("Roll No");
        model.addColumn("Full Name");
        model.addColumn("Level");
        model.addColumn("Faculty");
        model.addColumn("Semester");

        try{
            DBCon dbc = new DBCon();
            Statement stmt=dbc.con.createStatement();
            ResultSet rs =stmt.executeQuery("select * from studenttable");
            while (rs.next())
            {
                model.addRow(new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)});

            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();

        }
        int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
        int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
        JScrollPane scrollPane = new JScrollPane((table),v,h);
        add(scrollPane);
        scrollPane.setBounds(20,20,600,600);
        setVisible(true);
        setSize(600,500);
    }
    public static void main(String[] args)
    {
        new AllRecordTable();
    }

}

