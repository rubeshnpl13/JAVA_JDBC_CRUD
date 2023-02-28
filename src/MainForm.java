import javax.swing.*;
import java.awt.event.*;

public class MainForm extends JFrame implements ActionListener {
    JMenuBar mb;//Menu bar
    JMenu mnuro,mnure,mnuhlp;//menus
    JMenuItem miaadd,miedit,midel,miexit;//menu item
    JMenuItem miall,miindv;
    JMenuItem miabt;
    //Creating Menu Bar



    public MainForm()
    {
        mb=new JMenuBar();

        mnuro = new JMenu("Record Operation");
        mnure =new JMenu("Report");
        mnuhlp = new JMenu("Help");

        miaadd=new JMenuItem("Add");
        miedit=new JMenuItem("Edit");
        midel =new JMenuItem("Delete");
        miexit=new JMenuItem("Exit");

        miall=new JMenuItem("All");
        miindv=new JMenuItem("Indv");

        miabt=new JMenuItem("About");

        setJMenuBar(mb);//Set Menubar to Frame

        //Add menus to Menubar
        mb.add(mnuro);
        mb.add(mnure);
        mb.add(mnuhlp);

        //Record Operation
        mnuro.add(miaadd);
        miaadd.addActionListener(this);
        mnuro.add(miedit);
        miedit.addActionListener(this);
        mnuro.add(midel);
        midel.addActionListener(this);

        mnuro.add(miexit);

        //Report
        mnure.add(miall);
        miall.addActionListener(this);
        mnure.add(miindv);
        miindv.addActionListener(this);


        //help
        mnuhlp.add(miabt);

        setVisible(true);
        setSize(500,500);

        setTitle("Menu Bar");//set the title
        //setLocation(500,300);//frame locate according to screen resolution
        setLocationRelativeTo(null);//frame locate according to screen resolution

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==miaadd)
        {
            new AddRecord();
        }else if(e.getSource()==miedit)
        {
            new Modify();
        }
        else if(e.getSource()==midel)
        {
            new Delete();
        }
        else if(e.getSource()==miall)
        {
            new AllRecordTable();
        }
        else if(e.getSource()==miindv)
        {
            new IndvRecord();
        }
    }
}



