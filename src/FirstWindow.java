import javax.swing.*;
import java.awt.*;

public class FirstWindow extends JFrame {
    JButton bCanva, bRunningButton;
    public FirstWindow(){
        setSize(800,300);
        setTitle("Running button / Canva");
        setLayout(null);
        getContentPane().setBackground(new Color(160,100,100));

        bCanva = new JButton("Canva");
        bCanva.setBounds(0,0,400,300);
        bCanva.setBackground(new Color(100,50,40));
        bCanva.setFont(new Font("Dialog",Font.HANGING_BASELINE,20));
        add(bCanva);

        bRunningButton = new JButton("Running button");
        bRunningButton.setBounds(400,0,400,300);
        bRunningButton.setBackground(new Color(50,100,70));
        bRunningButton.setFont(new Font("Dialog",Font.HANGING_BASELINE,20));
        add(bRunningButton);




    }
}
