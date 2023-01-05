import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class Canvas extends JFrame implements ActionListener, KeyListener, MouseListener {
    JButton bChangeColor, lCurrentColor, bClear, bExit;
    JRadioButton rbFill,rbEmpty;
    int xMouse,yMouse, size=50, number;
    JPanel  pCanva, pButtons;
    JLabel tFigure1, tFigure2, tFigureName;
    Color currentColor;
    JSlider sSize;
    char figure = 'c';
    Font font = new Font("Serif", Font.ITALIC, 15);


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
        setSize(1600,900);
        setTitle("Canva");
        setFont(font);
        setLayout(null);
        getContentPane().setBackground(new Color(160,200,100));

        pButtons= new JPanel();
        pButtons.setLayout(null);
        pButtons.setBackground(new Color(53,85,68));
        pButtons.setBounds(0,0,299,900);
        pButtons.setFont(font);
        add(pButtons);
        lCurrentColor = new JButton();
        lCurrentColor.setBounds(50,25,200,100);
        lCurrentColor.setText("Current color");
        lCurrentColor.setFont(font);
        pButtons.add(lCurrentColor);

        bChangeColor = new JButton("Choose a new color");
        bChangeColor.setFont(font);
        bChangeColor.setBounds(50,150,200,100);
        bChangeColor.addActionListener(this);
        pButtons.add(bChangeColor);

        pCanva =new JPanel();
        pCanva.setBounds(300,0,1300,900);
        pCanva.setBackground(Color.white);
        add(pCanva);
        pCanva.addMouseListener(this);


        rbFill=new JRadioButton("Fill");
        rbEmpty=new JRadioButton("Empty");
        rbFill.setFont(font);
        rbEmpty.setFont(font);
        pButtons.add(rbFill).setBounds(50,300,80,50);
        pButtons.add(rbEmpty).setBounds(50,350,80,50);
        rbFill.addActionListener(this);
        rbEmpty.addActionListener(this);

        ButtonGroup group= new ButtonGroup();
        group.add(rbFill);
        group.add(rbEmpty);

        sSize=new JSlider(0,0,100,50);
        sSize.setFont(font);
        sSize.setBounds(50,450,200,50);
        sSize.setMajorTickSpacing(20);
        sSize.setMinorTickSpacing(5);
        sSize.setPaintTicks(true);
        sSize.setPaintLabels(true);
        pButtons.add(sSize);
        sSize.addChangeListener(changeListener);

        bClear=new JButton("Clear the canva");
        bClear.setBounds(50,550,200,100);
        bClear.addActionListener(this);
        bClear.setFont(font);
        pButtons.add(bClear);

        bExit=new JButton("Exit");
        bExit.setFont(font);
        bExit.addActionListener(this);
        bExit.setBounds(50,700,200,70);
        pButtons.add(bExit);


        addKeyListener(this);

        tFigure1 = new JLabel("Chosen figure:");
        tFigure1.setFont(font);
        tFigure1.setForeground(Color.WHITE);
        tFigure1.setBounds(150,300,100,50);
        pButtons.add(tFigure1);

        tFigure2 = new JLabel("(click 'c' or 's' on keyboard)");
        tFigure2.setFont(new Font("Serif", Font.ITALIC, 12));
        tFigure2.setForeground(Color.WHITE);
        tFigure2.setBounds(150,320,140,50);
        pButtons.add(tFigure2);

        tFigureName = new JLabel();
        tFigureName.setForeground(Color.WHITE);
        tFigureName.setFont(font);
        tFigureName.setBounds(150,350,100,50);
        tFigureName.setText("Circle");
        pButtons.add(tFigureName);


    }

    Color getColor() {return currentColor;}

    void drawCenteredCircle(int x, int y, int r) {
        Graphics g = pCanva.getGraphics();
        g.setColor(getColor());
        if(number==1) g.fillOval(x+r/2,y+r/2,r,r);
        if(number==2) g.drawOval(x+r/2,y+r/2,r,r);
    }

    void drawCenteredSquare(int x, int y, int r){
        Graphics g = pCanva.getGraphics();
        g.setColor(getColor());
        if(number==1) g.fillRect(x,y,r,r);
        if(number==2) g.drawRect(x,y,r,r);
    }

    Color changingColor(){
        JColorChooser colorChooser = new JColorChooser();
        currentColor = colorChooser.showDialog(null, "Pick a color...I guess", Color.black);
        return currentColor;
    }

    void drawFigure(int x, int y, int r){
        if(figure=='c') drawCenteredCircle(x-r,y-r,r);
        if(figure=='s') drawCenteredSquare(x-r/2,y-r/2,r);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        //xMouse =(int)MouseInfo.getPointerInfo().getLocation().getX();
        //yMouse =(int)MouseInfo.getPointerInfo().getLocation().getY();

        if(source==bChangeColor){
            lCurrentColor.setBackground(changingColor());
        }

        if(source==rbFill) number=1;
        if(source==rbEmpty) number=2;

        if(source==bClear){
            getContentPane().setBackground(new Color(160,200,100));
            pCanva.getGraphics().clearRect(0,0,1300,900);
            pCanva.setBackground(Color.WHITE);
        }

        if(source==bExit){
            dispose();
            FirstWindow window = new FirstWindow();
            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            window.setVisible(true);

        }

        requestFocus();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        char source = e.getKeyChar();
        if(source=='c'){
            figure = 'c';
            System.out.println("klikniete c");
            tFigureName.setText("Circle");
        }
        if(source=='s'){
            figure = 's';
            System.out.println("klikniete s");
            tFigureName.setText("Square");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        drawFigure(e.getX(), e.getY(), size);
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
