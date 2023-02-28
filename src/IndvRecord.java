import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class IndvRecord extends JFrame implements ActionListener {
    JLabel label;
    JComboBox cbsearch;
    JTextField txtfield;
    JButton btnsearch;

    static JTable table;

    DBCon dbConnection;
    PreparedStatement preparedStatement;
    ResultSet rs;
    static DefaultTableModel model;


    public IndvRecord() {

        setLayout(null);
        setSize(600, 600);
        setVisible(true);
        setTitle("Search");

        label = new JLabel("Search From");
        cbsearch = new JComboBox();
        txtfield = new JTextField();
        btnsearch = new JButton("Search");

        add(label);
        label.setBounds(20, 10, 80, 30);

        add(cbsearch);
        cbsearch.setBounds(100, 10, 80, 30);
        cbsearch.addItem("Roll");
        cbsearch.addItem("Name");
        cbsearch.addItem("Level");
        cbsearch.addItem("Faculty");
        cbsearch.addItem("Semester");

        add(txtfield);
        txtfield.setBounds(180, 10, 80, 30);

        add(btnsearch);
        btnsearch.setBounds(260, 10, 80, 30);
        btnsearch.addActionListener(this);
    }
    static void table() {
        model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("Roll No");
        model.addColumn("Full Name");
        model.addColumn("Level");
        model.addColumn("Faculty");
        model.addColumn("Semester");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnsearch) {
            try {
                dbConnection = new DBCon();

                String selected = (String) cbsearch.getSelectedItem();

                if (selected.equals("Roll")) {
                    preparedStatement = dbConnection.con.prepareStatement("SELECT * FROM studenttable WHERE Rollno = ?");
                    preparedStatement.setString(1, txtfield.getText());
                } else if (selected.equals("Name")) {
                    preparedStatement = dbConnection.con.prepareStatement("SELECT * FROM studenttable WHERE Fullname = ?");
                    preparedStatement.setString(1, txtfield.getText());

                } else if (selected.equals("Level")) {
                    preparedStatement = dbConnection.con.prepareStatement("SELECT * FROM studenttable WHERE Levels = ?");
                    preparedStatement.setString(1, txtfield.getText());

                } else if (selected.equals("Faculty")) {
                    preparedStatement = dbConnection.con.prepareStatement("SELECT * FROM studenttable WHERE Faculty = ?");
                    preparedStatement.setString(1, txtfield.getText());

                } else if (selected.equals("Semester")) {
                    preparedStatement = dbConnection.con.prepareStatement("SELECT * FROM studenttable WHERE Semester = ?");
                    preparedStatement.setString(1, txtfield.getText());

                }
                rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    table();
                    do {
                        model.addRow(new Object[] {
                                rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)
                        });
                    } while (rs.next());

                } else {
                    JOptionPane.showMessageDialog(null, "No data found");
                }
                int vertical_scroll = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
                int horizontal_scroll = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;

                JScrollPane scrollPane = new JScrollPane((table), vertical_scroll, horizontal_scroll);

                add(scrollPane);
                scrollPane.setBounds(10, 170, 500, 200);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        new IndvRecord();
    }
}