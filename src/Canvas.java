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
    JRadioButton rbCircle, rbTriangle,rbSquare;
    MouseListener mouseListener= new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {

            if((300<=e.getX() && e.getX()<=1200) && (0<=e.getY() && e.getY()<=800)){

                drawFigure(e.getX(),e.getY(),30);

            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    };

    Graphics g = getGraphics();
    int xMouse;
    int yMouse ;

    JPanel pSettings, pCanva;
    Color currentColor;
    Object shape;
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

        rbCircle=new JRadioButton("Circle");
        rbSquare=new JRadioButton("Square");
        rbTriangle= new JRadioButton("Triangle");
        rbCircle.setBounds(50,300,100,50);
        rbSquare.setBounds(50,350,100,50);
        rbTriangle.setBounds(50,400,100,50);
        add(rbCircle);
        add(rbTriangle);
        add(rbSquare);
        rbTriangle.addActionListener(this);
        rbCircle.addActionListener(this);
        rbSquare.addActionListener(this);

        ButtonGroup group= new ButtonGroup();
        group.add(rbCircle);
        group.add(rbSquare);
        group.add(rbTriangle);




    }

    /*void paintCircle(Graphics g,Color color){



        g.setColor(Color.black);
        g.drawOval(xMouse,yMouse,20,30);
    }*/
    int number;
    Color getColor() {return currentColor;}
    public void drawCenteredCircle(int x, int y, int r) {
        Graphics g = getGraphics();
        g.setColor(getColor());
        g.drawOval(x,y,r,r);
    }

    public void drawCenteredSquare(int x, int y, int r){
        Graphics g = getGraphics();
        g.setColor(getColor());
        g.drawRect(x,y,r,r);
    }
    Color changingColor(){
        JColorChooser colorChooser = new JColorChooser();
        currentColor = JColorChooser.showDialog(null, "Pick a color...I guess", Color.black);
        return currentColor;
    }

    void drawFigure(int x, int y, int r){
        if(number==1) drawCenteredCircle(x-r,y-r,r);
        if(number==2) drawCenteredSquare(x-r/2,y-r/2,r);
        //if(figure==rbTriangle) number=3;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        xMouse =(int)MouseInfo.getPointerInfo().getLocation().getX();
        yMouse =(int)MouseInfo.getPointerInfo().getLocation().getY();

        if(source==bChangeColor){
            lCurrentColor.setBackground(changingColor());
        }

        if(source==rbCircle) number=1;
        if(source==rbSquare) number=2;
        if(source==rbTriangle) number=3;

    }

}
