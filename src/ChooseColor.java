import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseColor extends JFrame implements ActionListener {
    JButton btn = new JButton("Color");
    public ChooseColor()
    {
        setLayout(new FlowLayout());

        add(btn);
        btn.addActionListener(this);
        setSize(300, 300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e){
        Color init = Color.BLACK;
        Color colour = JColorChooser.showDialog(this, "Choose a color", init);
       // this.setBackground(colour);
        btn.setForeground(colour);
        btn.setBackground(colour);
    }
    public static void main(String[] args){
        ChooseColor cc = new ChooseColor();
    }
}
