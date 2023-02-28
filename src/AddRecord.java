import javax.swing.*;
import java.awt.event.*;
import java.sql.PreparedStatement;

public class AddRecord extends JFrame implements ActionListener{
    JLabel lblroll,lblname,lbllev,lblfacu,lblsem;
    JTextField txtroll,txtname;
    JComboBox cblevel,cbfacu,cbsem;
    JButton btnsave;
    PreparedStatement pstmt;
    DBCon dbc;

    public AddRecord()
    {
        dbc=new DBCon();

       lblroll=new JLabel("Roll No");
       lblname=new JLabel("Name");
       lbllev=new JLabel("Level");
       lblfacu=new JLabel("Faculty");
       lblsem=new JLabel("Semester");

       txtroll=new JTextField();
       txtname=new JTextField();

       cblevel=new JComboBox();
       cblevel.addItem("+2");
       cblevel.addItem("Bachelors");
       cblevel.addItem("Masters");

       cbfacu=new JComboBox();
       cbfacu.addItem("Science");
       cbfacu.addItem("Management");
       cbfacu.addItem("Humanities");
       cbfacu.addItem("BBA");
       cbfacu.addItem("BHM");
       cbfacu.addItem("Bsc.CSIT");
       cbfacu.addItem("MBA");
       cbfacu.addItem("Msc.CSIT");
       cbfacu.addItem("MSC");





        cbsem=new JComboBox();
        cbsem.addItem("I");
        cbsem.addItem("II");
        cbsem.addItem("III");
        cbsem.addItem("IV");
        cbsem.addItem("V");
        cbsem.addItem("VI");
        cbsem.addItem("VII");
        cbsem.addItem("VII");


        btnsave=new JButton("Save");

        setLayout(null);
        add(lblroll);
        lblroll.setBounds(50,50,100,25);
        add(txtroll);
        txtroll.setBounds(150,50,100,25);

        add(lblname);
        lblname.setBounds(50,100,100,25);
        add(txtname);
        txtname.setBounds(150,100,100,25);

        add(lbllev);
        lbllev.setBounds(50,150,100,25);
        add(cblevel);
        cblevel.addActionListener(this);
        cblevel.setBounds(150,150,100,25);

        add(lblfacu);
        lblfacu.setBounds(50,200,100,25);
        add(cbfacu);
        cbfacu.setBounds(150,200,100,25);

        add(lblsem);
        lblsem.setBounds(50,250,100,25);
        add(cbsem);
        cbsem.setBounds(150,250,100,25);

        add(btnsave);
        btnsave.addActionListener(this);
        btnsave.setBounds(100,300,75,25);




        setVisible(true);
        setSize(500,500);

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
        if(e.getSource()==btnsave) {
            try {
                pstmt = dbc.con.prepareStatement("insert into studenttable (Rollno,Fullname,Levels,Faculty,Semester) values(?,?,?,?,?)");
                pstmt.setString(1,txtroll.getText());
                pstmt.setString(2,txtname.getText());
                pstmt.setString(3,cblevel.getSelectedItem().toString());
                pstmt.setString(4,cbfacu.getSelectedItem().toString());
                pstmt.setString(5,cbsem.getSelectedItem().toString());

                int result=pstmt.executeUpdate();
                if(result>0)
                {
                    JOptionPane.showMessageDialog(null,"Record Saved Successfully");
                }else {
                    JOptionPane.showMessageDialog(null,"Unable to Save");
                }
            } catch (Exception e2) {
                e2.printStackTrace();

            }
        }
    }
}



