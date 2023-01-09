import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa z uciekającym przyciskiem
 */
public class RunningButton extends JFrame implements ActionListener {

    //zmienne
    JButton bStart, bExit, button,bEasy,bMedium,bHard;
    JLabel lWin,lLvl;

    static int windowWidth=824;
    static int windowHeight=570;
    static int widthbuttons = 150;
    static int heightbuttons=100;
    int xbutton=windowWidth/2-widthbuttons/2, ybutton=windowHeight/2-heightbuttons/2;

    static int firstXButton=windowWidth/2-widthbuttons/2, firstYButton=windowHeight/2-heightbuttons/2;
    int xMouse =(int)MouseInfo.getPointerInfo().getLocation().getX();
    int yMouse =(int)MouseInfo.getPointerInfo().getLocation().getY();
    int lvlPosition;
    Color blue = new Color(50,150,200);
    Color darkBlue= new Color(60,100,150);
    JFrame info= new JFrame("Congratulations!"); ;

    /**
     * konstruktor tworzący okno aplikacji
     */
    RunningButton(){
        //parametry okna
        setSize(windowWidth,windowHeight);
        setTitle("Running button");
        setLayout(null);
        getContentPane().setBackground(darkBlue);

        //przycisk startu gry
        bStart = new JButton();
        bStart.setText("Start");
        bStart.setBounds(0,0,windowWidth/2,windowHeight/2);
        bStart.setBackground(blue);
        bStart.setFont(new Font("Dialog",Font.HANGING_BASELINE,30));
        add(bStart);
        bStart.addActionListener(this);

        //przycisk wyjscia z aplikacji
        bExit=new JButton("Exit");
        bExit.setBounds(windowWidth/2,windowHeight/2,windowWidth/2,windowHeight/2);
        bExit.setBackground(blue);
        bExit.setFont(new Font("Dialog",Font.HANGING_BASELINE,30));
        add(bExit);
        bExit.addActionListener(this);

        //label z napisem w przypadku wygranej


        //przycisk z poziomem łatwym
        bEasy =new JButton("Easy");
        bEasy.setBounds(windowWidth/2-widthbuttons/2,windowHeight/4-heightbuttons/2,150,50);
        bEasy.setBackground(blue);
        //bEasy.setForeground(Color.MAGENTA);
        bEasy.setFont(new Font("Dialog",Font.HANGING_BASELINE,20));
        bEasy.addActionListener(this);

        //przycisk z poziomem średnim
        bMedium =new JButton("Medium");
        bMedium.setBounds(windowWidth/2-widthbuttons/2,windowHeight/2-heightbuttons/2,150,50);
        bMedium.setBackground(blue);
        //bMedium.setForeground(Color.MAGENTA);
        bMedium.setFont(new Font("Dialog",Font.HANGING_BASELINE,20));
        bMedium.addActionListener(this);

        //przycisk z poziomem trudnym
        bHard =new JButton("Hard");
        bHard.setBounds(windowWidth/2-widthbuttons/2,windowHeight*3/4-heightbuttons/2,150,50);
        bHard.setBackground(blue);
        //bHard.setForeground(Color.MAGENTA);
        bHard.setFont(new Font("Dialog",Font.HANGING_BASELINE,20));
        bHard.addActionListener(this);

        //label z napisem wyobru poziomu
        lLvl= new JLabel("Choose a difficulty level:");
        lLvl.setBounds(230,1,700,50);
        lLvl.setFont(new Font("Dialog",Font.HANGING_BASELINE,28));
        //lLvl.setForeground(Color.);

    }

    /**
     * Metoda ustawiająca przycisk w początkowym położeniu
     */
    void buttonStartSetting(){
        button.setBounds(firstXButton,firstYButton,widthbuttons,heightbuttons);
    }

    /**
     * Metoda ustawiająca elementy w aplikacji na takie z możliwością wyboru poziomu trudnośći
     */
    void chooseLvl(){

        //remove(lWin);
        add(lLvl);
        add(bEasy);
        add(bMedium);
        add(bHard);
        setTitle("Choose level");
    }

    /**
     * Metoda usuwająca elementy na ekranie
     */
    void removing(){
        remove(lLvl);
        remove(bEasy);
        remove(bHard);
        remove(bMedium);
        //remove(lWin);
        getContentPane().setBackground(new Color(61,100,150));
    }

    /**
     * Metoda uruchamiająca właściwą grę z łapaniem przycisku
     */
    void start(){

        button=new JButton("Catch me if you can");
        buttonStartSetting();
        button.setBackground(Color.CYAN);
        add(button);
        button.addActionListener(this);


    }

    /**
     * Metoda ustawiająca poziom trudności
     * @param lvl wybrany poziom przez użytkownika
     */
    void lvlIsChosen(Object lvl){
        if(lvl==bEasy) lvlPosition=120;
        if(lvl==bMedium) lvlPosition=80;
        if(lvl==bHard) lvlPosition=40;

        removing();
        start();
    }

    /**
     * Metoda generująca losowe położenie przycisku
     * @return ArrayList ze współrzędnymi przycisku
     */
    List<Integer> setPosition(){
        List<Integer> xAndy = new ArrayList<>();
        xbutton=(int)(Math.random()*(windowWidth-widthbuttons));
        ybutton=(int)(Math.random()*(windowHeight-heightbuttons));
        xAndy.add(xbutton);
        xAndy.add(ybutton);


        return xAndy;
    }

    /**
     * Metoda sprawdzająca czy przycisk został złapany
     * @param xbutton współrzędna x przycisku
     * @param ybutton współrzędna y przycisku
     * @return boolean z odpowiedzią czy przycisk jest złapany
     */
    Boolean isButtonCatch(int xbutton, int ybutton){
        boolean yesOrNo;
        xMouse =(int)MouseInfo.getPointerInfo().getLocation().getX();
        yMouse =(int)MouseInfo.getPointerInfo().getLocation().getY();

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

    /**
     * Metoda ustawiająca parametry aplikacji po wygranej
     */
    void win(){


        remove(button);
        getContentPane().setBackground(new Color(60,100,151));
        bStart.setText("Try again");
        add(bStart);
        add(bExit);
        //add(lWin);
        congrtulations();

    }

    void congrtulations(){

        //info= new JFrame("Congratulations!");
        info.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        info.setVisible(true);
        info.setSize(350,100);
        info.setBackground(new Color(14, 41, 24));

        lWin= new JLabel("Congratulations, You catched a button!");
        lWin.setBounds(100,0,350,100);
        lWin.setBackground(new Color(14, 41, 24));
        lWin.setFont(new Font("Dialog",Font.HANGING_BASELINE,15));
        lWin.setForeground(Color.BLACK);


        info.add(lWin);
    }






    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source==bStart){
            remove(bStart);
            remove(bExit);
            getContentPane().setBackground(new Color(61,100,151));
            chooseLvl();
            info.dispose();
        }
        if(source==bExit){
            info.dispose();
            dispose();
            FirstWindow window = null;
            window = new FirstWindow();
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
