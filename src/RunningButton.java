import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RunningButton extends JFrame implements ActionListener {

    JButton bStart, bExit, button;
    JLabel lWin;

    int windowWidth=800, windowHeight=500;
    int widthbuttons = 150, heightbuttons=100;
    int xbutton=windowWidth/2-widthbuttons/2, ybutton=windowHeight/2-heightbuttons/2;
    int lastXButton, lastYButton;
    RunningButton(){
        setSize(windowWidth,windowHeight);
        setTitle("Running button");
        setLayout(null);
        getContentPane().setBackground(new Color(60,100,150));

        bStart = new JButton();
        bStart.setText("Start");
        bStart.setBounds(windowWidth/2-widthbuttons/2,windowHeight/3-heightbuttons/2,150,100);
        bStart.setBackground(Color.GRAY);
        bStart.setForeground(Color.MAGENTA);
        bStart.setFont(new Font("Dialog",Font.HANGING_BASELINE,20));
        add(bStart);
        bStart.addActionListener(this);

        bExit=new JButton("Exit");
        bExit.setBounds(windowWidth/2-widthbuttons/2,windowHeight*2/3-heightbuttons/2,150,100);
        bExit.setBackground(Color.GRAY);
        bExit.setForeground(Color.MAGENTA);
        bExit.setFont(new Font("Dialog",Font.HANGING_BASELINE,20));
        add(bExit);
        bExit.addActionListener(this);

        lWin= new JLabel("Congratulations, You catched a button!");
        lWin.setBounds(100,10,700,100);
        lWin.setFont(new Font("Dialog",Font.HANGING_BASELINE,28));
        lWin.setForeground(Color.MAGENTA);
        lWin.setBackground(new Color(14, 41, 24));
    }



    void Start(){

        setTitle("Catch me if you can");
        button=new JButton();
        button.setBounds(xbutton,ybutton,widthbuttons,heightbuttons);
        button.setBackground(Color.CYAN);
        add(button);
        button.addActionListener(this);


    }
    List<Integer> SetPosition(){
        List<Integer> xAndy = new ArrayList<>();
        xbutton=(int)(Math.random()*(windowWidth-widthbuttons));
        ybutton=(int)(Math.random()*(windowHeight-heightbuttons));
        xAndy.add(xbutton);
        xAndy.add(ybutton);


        return xAndy;
    }

    Boolean isButtonCatch(int xmouse, int ymouse, int xbutton, int ybutton){
        boolean yesOrNo=false;
        System.out.println(xmouse+" "+ymouse);

        int minRightX = xbutton;
        int maxRightX = xbutton +100;


        int minRightY = ybutton;
        int maxRightY = ybutton+100;
        System.out.println("x:" + minRightX + " " + maxRightX);
        System.out.println("y:" + minRightY + " " + maxRightY);
        if((minRightX<=xmouse)&&(xmouse<=maxRightX) && (minRightY<=ymouse)&&(ymouse<=maxRightY)) yesOrNo=true;
        else yesOrNo=false;

        return yesOrNo;
    }

    void Win(){
        remove(button);
        getContentPane().setBackground(new Color(14, 41, 24));
        bStart.setText("Try again");
        add(bStart);
        add(bExit);
        add(lWin);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source==bStart){
            remove(bStart);
            remove(bExit);
            getContentPane().setBackground(new Color(154, 241, 241));
            Start();
        }
        if(source==bExit){
            dispose();
            FirstWindow window = new FirstWindow();
            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            window.setVisible(true);
        }

        if(source==button){

            int xMouse =(int)MouseInfo.getPointerInfo().getLocation().getX();
            int yMouse =(int)MouseInfo.getPointerInfo().getLocation().getY();

            if(isButtonCatch(xMouse,yMouse,lastXButton,lastYButton)){
                Win();
            }
            else{
                button.setBounds(SetPosition().get(0), SetPosition().get(1), widthbuttons, heightbuttons);
            }

            lastXButton=button.getX();
            lastYButton=button.getY();
        }
    }
}
