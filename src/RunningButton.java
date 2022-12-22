import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RunningButton extends JFrame implements ActionListener {
    RunningButton(){
        setSize(800,500);
        setTitle("Running button");
        setLayout(null);
        getContentPane().setBackground(new Color(60,100,150));
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
