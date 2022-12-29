import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Canvas extends JFrame implements ActionListener {
    //JColorChooser colorChooser = new JColorChooser();
    //JFrame jFrame = new JFrame();
    JButton bChangeColor;
    JRadioButton rbShape;
    JButton lCurrentColor;
    java.awt.Canvas canvas = new java.awt.Canvas();
    MouseListener mouseListener= new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Graphics g = getGraphics();
            if((300<=e.getX() && e.getX()<=1200) && (0<=e.getY() && e.getY()<=800)){

                g.setColor(getColor());
                g.drawOval(e.getX(),e.getY(),30,30);

            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };

    Graphics g = getGraphics();
    int xMouse;
    int yMouse ;

    JPanel pSettings, pCanva;
    Color currentColor;
    Canvas(){
        setSize(1200,800);
        setTitle("Canva");
        setLayout(null);
        getContentPane().setBackground(new Color(160,200,100));

        /*pSettings=new JPanel();
        pSettings.setBounds(0,0,300,800);
        pSettings.setBackground(Color.black);
        add(pSettings);*/

        lCurrentColor = new JButton();
        lCurrentColor.setBounds(50,25,200,100);
        lCurrentColor.setText("Current color");
        //lCurrentColor.setBackground(Color.BLUE);
        add(lCurrentColor);

        bChangeColor = new JButton("Choose a new color");
        bChangeColor.setBounds(50,150,200,100);
        bChangeColor.addActionListener(this);
        add(bChangeColor);

        pCanva =new JPanel();
        pCanva.setBounds(300,0,900,800);
        pCanva.setBackground(Color.white);
        add(pCanva);
        //pCanva.add(canvas);

        //canvas.setBounds(300,0,900,800);
        //canvas.setBackground(Color.GRAY);


        //add(canvas);
        addMouseListener(mouseListener);


    }

    /*void paintCircle(Graphics g,Color color){



        g.setColor(Color.black);
        g.drawOval(xMouse,yMouse,20,30);
    }*/

    Color getColor() {return currentColor;}
    public void drawCenteredCircle(Graphics g, int x, int y, int r) {

        x = x-(r/2);
        y = y-(r/2);
        g.fillOval(x,y,r,r);
    }
    Color changingColor(){
        JColorChooser colorChooser = new JColorChooser();
        currentColor = JColorChooser.showDialog(null, "Pick a color...I guess", Color.black);
        return currentColor;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        xMouse =(int)MouseInfo.getPointerInfo().getLocation().getX();
        yMouse =(int)MouseInfo.getPointerInfo().getLocation().getY();

        if(source==bChangeColor){
            lCurrentColor.setBackground(changingColor());
        }



    }

}
