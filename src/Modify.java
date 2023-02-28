import javax.swing.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.sql.Statement;


public class Modify extends JFrame implements ActionListener {
    JLabel lblroll, lblname, lbllev, lblfacu, lblsem;
    JTextField txtname;
    JComboBox cbroll;
    JComboBox cblevel, cbfacu, cbsem;
    JButton btnupdate;
    Statement stmt;
    PreparedStatement pstmt;
    ResultSet rs;
    DBCon dbc;

    public Modify() {
        dbc = new DBCon();

        lblroll = new JLabel("Roll No");
        lblname = new JLabel("Name");
        lbllev = new JLabel("Level");
        lblfacu = new JLabel("Faculty");
        lblsem = new JLabel("Semester");

        //txtroll=new JTextField();
        txtname = new JTextField();
        cbroll = new JComboBox();


        cblevel = new JComboBox();
        cblevel.addItem("+2");
        cblevel.addItem("Bachelors");
        cblevel.addItem("Masters");

        cbfacu = new JComboBox();
        cbfacu.addItem("Science");
        cbfacu.addItem("Management");
        cbfacu.addItem("Humanities");
        cbfacu.addItem("Bsc.CSIT");
        cbfacu.addItem("BBA");
        cbfacu.addItem("MBA");


        cbsem = new JComboBox();
        cbsem.addItem("I");
        cbsem.addItem("II");
        cbsem.addItem("III");
        cbsem.addItem("IV");
        cbsem.addItem("V");
        cbsem.addItem("VI");
        cbsem.addItem("VII");
        cbsem.addItem("VII");


        btnupdate = new JButton("Update");

        setLayout(null);
        add(lblroll);
        lblroll.setBounds(50, 50, 100, 25);
        add(cbroll);
        cbroll.setBounds(150, 50, 100, 25);

        add(lblname);
        lblname.setBounds(50, 100, 100, 25);
        add(txtname);
        txtname.setBounds(150, 100, 100, 25);

        add(lbllev);
        lbllev.setBounds(50, 150, 100, 25);
        add(cblevel);
        cblevel.addActionListener(this);
        cblevel.setBounds(150, 150, 100, 25);

        add(lblfacu);
        lblfacu.setBounds(50, 200, 100, 25);
        add(cbfacu);
        cbfacu.setBounds(150, 200, 100, 25);

        add(lblsem);
        lblsem.setBounds(50, 250, 100, 25);
        add(cbsem);
        cbsem.setBounds(150, 250, 100, 25);

        add(btnupdate);
        btnupdate.addActionListener(this);
        btnupdate.setBounds(100, 300, 75, 25);
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

        setTitle("Add Record");//set the title
        //setLocation(500,300);//frame locate according to screen resolution
        setLocationRelativeTo(null);//frame locate according to screen resolution

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cblevel) {
            String selected = (String)cblevel.getSelectedItem();
            if (selected.equals("Bachelors")) {
                cbfacu.removeAllItems();
                cbfacu.addItem("BBA");
                cbfacu.addItem("BHM");
                cbfacu.addItem("Bsc.CSIT");
            } else if (selected.equals("Masters")) {
                cbfacu.removeAllItems();
                cbfacu.addItem("MBA");
                cbfacu.addItem("Msc.CSIT");
                cbfacu.addItem("MSC");
            }else{
                cbfacu.removeAllItems();
                cbfacu.addItem("Science");
                cbfacu.addItem("Management");
                cbfacu.addItem("Humanities");
            }
        }
        if(e.getSource()==cblevel) {
            String selected = (String)cblevel.getSelectedItem();
            if (selected.equals("Bachelors")) {
                cbsem.removeAllItems();
                cbsem.addItem("I");
                cbsem.addItem("II");
                cbsem.addItem("III");
                cbsem.addItem("IV");
                cbsem.addItem("V");
                cbsem.addItem("VI");
                cbsem.addItem("VII");
                cbsem.addItem("VII");
            } else if (selected.equals("Masters")) {
                cbsem.removeAllItems();
                cbsem.addItem("I");
                cbsem.addItem("II");
                cbsem.addItem("III");
                cbsem.addItem("IV");
            }else{
                cbsem.removeAllItems();
                cbsem.addItem("11");
                cbsem.addItem("12");
            }
        }
        if(e.getSource()==cbroll) {
            try {
                pstmt = dbc.con.prepareStatement("select * from studenttable where Rollno=?");
                pstmt.setString(1,cbroll.getSelectedItem().toString());
                rs=pstmt.executeQuery();
                if(rs.next())
                {
                   txtname.setText(rs.getString(2));
                   cblevel.setSelectedItem(rs.getString(3));
                   cbfacu.setSelectedItem(rs.getString(4));
                   cbsem.setSelectedItem(rs.getString(5));

                }

            } catch (Exception e2) {
                e2.printStackTrace();

            }
        }
        if(e.getSource()==btnupdate)
        {
            try {
                pstmt = dbc.con.prepareStatement("update studenttable set Fullname=?,Levels=?,Faculty=?,Semester=? where Rollno=?");
                pstmt.setString(1,txtname.getText());
                pstmt.setString(2,cblevel.getSelectedItem().toString());
                pstmt.setString(3,cbfacu.getSelectedItem().toString());
                pstmt.setString(4,cbsem.getSelectedItem().toString());
                pstmt.setString(5,cbroll.getSelectedItem().toString());



                int result=pstmt.executeUpdate();
                if(result>0)
                {
                    JOptionPane.showMessageDialog(null,"Record Updated Successfully");
                }else {
                    JOptionPane.showMessageDialog(null,"Record is not Updated");
                }
            } catch (Exception ex) {
                    ex.printStackTrace();
            }

        }


    }


 }




