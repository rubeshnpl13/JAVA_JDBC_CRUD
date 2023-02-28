import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {
    JLabel lbluser,lblpass;
    JTextField txtuser;
    JPasswordField txtpass;
    JButton btnLogin,btnReset;
    JButton btnnew;
    DBCon dbc;
    PreparedStatement pstmt;
    ResultSet rs;
    public Login()
    {
        dbc=new DBCon();
        //setLayout(new FlowLayout());
        setLayout(null);//Customization by ourselves
        lbluser=new JLabel("Username");
        lblpass=new JLabel("Password");
        txtuser = new JTextField();
        txtpass=new JPasswordField();
        btnLogin=new JButton("Login");
        btnReset=new JButton("Reset");
        btnnew=new JButton("New User");
        add(lbluser);
        lbluser.setBounds(50,50,100,25);
        add(txtuser);
        txtuser.setBounds(150,50,100,25);
        add(lblpass);
        lblpass.setBounds(50,100,100,25);
        add(txtpass);
        txtpass.setBounds(150,100,100,25);
        add(btnLogin);
        btnLogin.addActionListener(this);
        btnLogin.setBounds(100,150,75,25);
        add(btnnew);
        btnnew.addActionListener(this);
        btnnew.setBounds(170,150,75,25);

        add(btnReset);
        btnReset.setBounds(170,150,75,25);

        setSize(300,220);
        setVisible(true);
        setResizable(false);//cannot change the frame size i.e. maximization of screen is avoided
        setTitle("Student Login Page");//set the title
        //setLocation(500,300);//frame locate according to screen resolution
        setLocationRelativeTo(null);//frame locate according to screen resolution
        setDefaultCloseOperation(EXIT_ON_CLOSE);//exit the frame

    }
    public static void main(String args[])
    {
        setDefaultLookAndFeelDecorated(true);
        //Login L =
       new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if(e.getSource()== btnLogin)
        {
            try {
                pstmt = dbc.con.prepareStatement("select * from Login where name=? and password=?");
                pstmt.setString(1,txtuser.getText());
                pstmt.setString(2,txtpass.getText());
                rs=pstmt.executeQuery();
                if(rs.next())
                {
                    new MainForm();

                }else {
                    JOptionPane.showMessageDialog(null,"Login Unsuccess");
                }

            }catch (Exception ex)
            {
                ex.printStackTrace();
            }

        }
        if(e.getSource()==btnnew) {
            try {
                pstmt = dbc.con.prepareStatement("insert into Login (name,password) values(?,?)");
                pstmt.setString(1,txtuser.getText());
                pstmt.setString(2,txtpass.getText());
                int result=pstmt.executeUpdate();
                if(result > 0)
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
