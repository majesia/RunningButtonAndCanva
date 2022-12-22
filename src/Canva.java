import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Canva extends JFrame implements ActionListener {
    Canva(){
        setSize(800,500);
        setTitle("Canva");
        setLayout(null);
        getContentPane().setBackground(new Color(160,200,100));

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
