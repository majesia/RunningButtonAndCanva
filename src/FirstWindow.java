import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstWindow extends JFrame implements ActionListener {
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
        bCanva.addActionListener(this);

        bRunningButton = new JButton("Running button");
        bRunningButton.setBounds(400,0,400,300);
        bRunningButton.setBackground(new Color(50,100,70));
        bRunningButton.setFont(new Font("Dialog",Font.HANGING_BASELINE,20));
        add(bRunningButton);
        bRunningButton.addActionListener(this);




    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source==bCanva){
            SwingUtilities.invokeLater(() -> {

                    Canvas canva = new Canvas();
                    canva.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    canva.setVisible(true);
                    dispose();

            });

        }

        if(source==bRunningButton){
            SwingUtilities.invokeLater(() -> {

                RunningButton runningButton = new RunningButton();
                runningButton.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                runningButton.setVisible(true);
                dispose();

            });

        }

    }
}
