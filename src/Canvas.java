import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Canvas extends JFrame implements ActionListener {
    JButton bChangeColor, lCurrentColor;
    JRadioButton rbCircle, rbTriangle,rbSquare;
    int xMouse,yMouse, size;
    JPanel  pCanva;
    Color currentColor;
    JSlider sSize;
    MouseListener mouseListener= new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {

            if((300<=e.getX() && e.getX()<=1200) && (0<=e.getY() && e.getY()<=800)){

                drawFigure(e.getX(),e.getY(),size);

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

    ChangeListener changeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            Object source = e.getSource();

            if(source==sSize) {
                size=sSize.getValue();
            }

        }
    };


    Canvas(){
        setSize(1200,800);
        setTitle("Canva");
        setLayout(null);
        getContentPane().setBackground(new Color(160,200,100));

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


        sSize=new JSlider(0,0,50,5);
        sSize.setBounds(50,500,200,50);
        add(sSize);
        sSize.addChangeListener(changeListener);


    }

    /*void paintCircle(Graphics g,Color color){



        g.setColor(Color.black);
        g.drawOval(xMouse,yMouse,20,30);
    }*/
    int number;
    Color getColor() {return currentColor;}
    void drawCenteredCircle(int x, int y, int r) {
        Graphics g = getGraphics();
        g.setColor(getColor());
        g.fillOval(x+r/2,y+r/2,r,r);
    }

    void drawCenteredSquare(int x, int y, int r){
        Graphics g = getGraphics();
        g.setColor(getColor());
        g.fillRect(x,y,r,r);
    }
    void drawTriangle(int x,int y,int h){
        Graphics g = getGraphics();
        g.setColor(getColor());
        g.fillArc(x,y,h,h,0,60 );
    }
    Color changingColor(){
        JColorChooser colorChooser = new JColorChooser();
        currentColor = JColorChooser.showDialog(null, "Pick a color...I guess", Color.black);
        return currentColor;
    }

    void drawFigure(int x, int y, int r){
        if(number==1) drawCenteredCircle(x-r,y-r,r);
        if(number==2) drawCenteredSquare(x-r/2,y-r/2,r);
        if(number==3) drawTriangle(x,y,r);
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
