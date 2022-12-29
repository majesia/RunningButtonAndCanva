import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RunningButton extends JFrame implements ActionListener {

    JButton bStart, bExit, button,bEasy,bMedium,bHard;
    JLabel lWin,lLvl;

    static int windowWidth=800;
    static int windowHeight=500;
    static int widthbuttons = 150;
    static int heightbuttons=100;
    int xbutton=windowWidth/2-widthbuttons/2, ybutton=windowHeight/2-heightbuttons/2;

    static int firstXButton=windowWidth/2-widthbuttons/2, firstYButton=windowHeight/2-heightbuttons/2;
    int xMouse =(int)MouseInfo.getPointerInfo().getLocation().getX();
    int yMouse =(int)MouseInfo.getPointerInfo().getLocation().getY();
    int easy=0,medium=1,hard=2;
    int lvlPosition;
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
        lWin.setBounds(150,10,700,100);
        lWin.setFont(new Font("Dialog",Font.HANGING_BASELINE,28));
        lWin.setForeground(Color.MAGENTA);
        lWin.setBackground(new Color(14, 41, 24));

        bEasy =new JButton("Easy");
        bEasy.setBounds(windowWidth/2-widthbuttons/2,windowHeight/4-heightbuttons/2,150,50);
        bEasy.setBackground(Color.GRAY);
        bEasy.setForeground(Color.MAGENTA);
        bEasy.setFont(new Font("Dialog",Font.HANGING_BASELINE,20));
        bEasy.addActionListener(this);

        bMedium =new JButton("Medium");
        bMedium.setBounds(windowWidth/2-widthbuttons/2,windowHeight/2-heightbuttons/2,150,50);
        bMedium.setBackground(Color.GRAY);
        bMedium.setForeground(Color.MAGENTA);
        bMedium.setFont(new Font("Dialog",Font.HANGING_BASELINE,20));
        bMedium.addActionListener(this);

        bHard =new JButton("Hard");
        bHard.setBounds(windowWidth/2-widthbuttons/2,windowHeight*3/4-heightbuttons/2,150,50);
        bHard.setBackground(Color.GRAY);
        bHard.setForeground(Color.MAGENTA);
        bHard.setFont(new Font("Dialog",Font.HANGING_BASELINE,20));
        bHard.addActionListener(this);

        lLvl= new JLabel("Choose a difficulty level:");
        lLvl.setBounds(150,10,700,100);
        lLvl.setFont(new Font("Dialog",Font.HANGING_BASELINE,28));
        lLvl.setForeground(Color.MAGENTA);
        //lLvl.setBackground(new Color(14, 41, 24));
    }

    void buttonStartSetting(){
        button.setBounds(firstXButton,firstYButton,widthbuttons,heightbuttons);
    }
    void chooseLvl(){

        remove(lWin);
        add(lLvl);
        add(bEasy);
        add(bMedium);
        add(bHard);
        setTitle("Choose level");
    }
    void removing(){
        remove(lLvl);
        remove(bEasy);
        remove(bHard);
        remove(bMedium);
        remove(lWin);
        getContentPane().setBackground(new Color(14, 41, 241));
    }

    void start(){

        setTitle("Catch me if you can");

        button=new JButton();
       // button.setBounds(xbutton,ybutton,widthbuttons,heightbuttons);
        buttonStartSetting();
        button.setBackground(Color.CYAN);
        add(button);
        button.addActionListener(this);


    }
    void lvlIsChosen(Object lvl){
        if(lvl==bEasy) lvlPosition=120;
        if(lvl==bMedium) lvlPosition=80;
        if(lvl==bHard) lvlPosition=40;

        removing();
        start();
    }
    List<Integer> setPosition(){
        List<Integer> xAndy = new ArrayList<>();
        xbutton=(int)(Math.random()*(windowWidth-widthbuttons));
        ybutton=(int)(Math.random()*(windowHeight-heightbuttons));
        xAndy.add(xbutton);
        xAndy.add(ybutton);


        return xAndy;
    }

    Boolean isButtonCatch(int xbutton, int ybutton){
        boolean yesOrNo;
        xMouse =(int)MouseInfo.getPointerInfo().getLocation().getX();
        yMouse =(int)MouseInfo.getPointerInfo().getLocation().getY();
        //System.out.println(xmouse+" "+ymouse);

        int minRightX = xbutton;
        int maxRightX = xbutton +lvlPosition;


        int minRightY = ybutton;
        int maxRightY = ybutton+lvlPosition;
        System.out.println("x:" + minRightX + " " + maxRightX);
        System.out.println("y:" + minRightY + " " + maxRightY);
        if((minRightX<=xMouse)&&(xMouse<=maxRightX) && (minRightY<=yMouse)&&(yMouse<=maxRightY)) yesOrNo=true;
        else yesOrNo=false;

        return yesOrNo;
    }

    void win(){
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
            chooseLvl();
        }
        if(source==bExit){
            dispose();
            FirstWindow window = new FirstWindow();
            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            window.setVisible(true);
        }

        if(source==bEasy ||source==bMedium ||source==bHard) lvlIsChosen(source);

        if(source==button){

            if(isButtonCatch(button.getX(),button.getY())){
                win();
            }
            else{
                button.setBounds(setPosition().get(0), setPosition().get(1), widthbuttons, heightbuttons);
            }

        }
    }
}
