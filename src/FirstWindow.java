import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa z oknem wyboru gry
 */
public class FirstWindow extends JFrame implements ActionListener {
    JButton bCanva, bRunningButton;
    public FirstWindow(){

        setSize(415,435);
        setTitle("Running button / Canva");
        setLayout(null);
        getContentPane().setBackground(new Color(160,100,100));

        bCanva = new JButton("Canva");
        bCanva.setBounds(0,0,200,200);
        bCanva.setBackground(new Color(250,0,90));
        bCanva.setFont(new Font("Dialog",Font.HANGING_BASELINE,20));
        add(bCanva);
        bCanva.addActionListener(this);

        bRunningButton = new JButton("Running button");
        bRunningButton.setBounds(200,200,200,200);
        bRunningButton.setBackground(new Color(250,0,90));
        bRunningButton.setFont(new Font("Dialog",Font.HANGING_BASELINE,20));
        add(bRunningButton);
        bRunningButton.addActionListener(this);




    }

    private void setBounds(int outTop) {
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
