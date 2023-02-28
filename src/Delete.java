import javax.swing.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.sql.Statement;


public class Delete extends JFrame implements ActionListener {
    JLabel lblroll, lblname;
    JTextField txtname;
    JComboBox cbroll;

    JButton btndelete;
    Statement stmt;
    PreparedStatement pstmt;
    ResultSet rs;
    DBCon dbc;

    public Delete() {
        dbc = new DBCon();

        lblroll = new JLabel("Roll No");
        lblname = new JLabel("Name");


        //txtroll=new JTextField();
        txtname = new JTextField();
        cbroll = new JComboBox();




        btndelete = new JButton("Delete");

        setLayout(null);
        add(lblroll);
        lblroll.setBounds(50, 50, 100, 25);
        add(cbroll);
        cbroll.setBounds(150, 50, 100, 25);

        add(lblname);
        lblname.setBounds(50, 100, 100, 25);
        add(txtname);
        txtname.setBounds(150, 100, 100, 25);



        add(btndelete);
        btndelete.addActionListener(this);
        btndelete.setBounds(150, 150, 75, 25);
        try{
            stmt = dbc.con.createStatement();
            rs=stmt.executeQuery("select * from studenttable");
            while(rs.next())
            {
                cbroll.addItem(rs.getString(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {

        }
        cbroll.addActionListener(this);

        setVisible(true);
        setSize(500, 500);

        setTitle("Delete Record");//set the title
        //setLocation(500,300);//frame locate according to screen resolution
        setLocationRelativeTo(null);//frame locate according to screen resolution

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cbroll) {
            try {
                pstmt = dbc.con.prepareStatement("select * from studenttable where Rollno=?");
                pstmt.setString(1,cbroll.getSelectedItem().toString());
                rs=pstmt.executeQuery();
                if(rs.next())
                {
                    txtname.setText(rs.getString(2));

                }

            } catch (Exception e2) {
                e2.printStackTrace();

            }
        }
        if(e.getSource()==btndelete)
        {
            try {
                pstmt = dbc.con.prepareStatement("Delete FROM  studenttable where Rollno=?");
                pstmt.setString(1,cbroll.getSelectedItem().toString());
                int result=pstmt.executeUpdate();
                if(result>0)
                {
                    JOptionPane.showMessageDialog(null,"Record Deleted Successfully");
                }else {
                    JOptionPane.showMessageDialog(null,"Record is not Deleted");
                }
            } catch (Exception ex) {

            }

        }


    }


}




