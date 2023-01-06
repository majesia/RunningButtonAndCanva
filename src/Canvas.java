import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

/**
 * Klasa z kodem do rysowania kształtów na panelu, rozszerza klasę JFrame
 * i implementuje klasy ActionListener, KeyListener i MouseListener
 */
public class Canvas extends JFrame implements ActionListener, KeyListener, MouseListener {

    // wypisanie wszystkich zmiennych
    JButton bChangeColor, bCurrentColor, bClear, bExit;
    JRadioButton rbFill,rbEmpty;
    int size=50, number;
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

    /**
     * konstruktor tworzący okno z programem
     */

    Canvas(){
        //ustawianie głownego pola
        setSize(1600,900);
        setTitle("Canva");
        setFont(font);
        setLayout(null);
        getContentPane().setBackground(new Color(160,200,100));

        //JPanel z menu
        pButtons= new JPanel();
        pButtons.setLayout(null);
        pButtons.setBackground(new Color(53,85,68));
        pButtons.setBounds(0,0,299,900);
        pButtons.setFont(font);
        add(pButtons);

        //aktualny kolor
        bCurrentColor = new JButton();
        bCurrentColor.setBounds(50,25,200,100);
        bCurrentColor.setText("Current color");
        bCurrentColor.setFont(font);
        pButtons.add(bCurrentColor);

        //przycisk ze zmiana koloru ksztaltu
        bChangeColor = new JButton("Choose a new color");
        bChangeColor.setFont(font);
        bChangeColor.setBounds(50,150,200,100);
        bChangeColor.addActionListener(this);
        pButtons.add(bChangeColor);

        //JPanel z canva
        pCanva =new JPanel();
        pCanva.setBounds(300,0,1300,900);
        pCanva.setBackground(Color.white);
        add(pCanva);
        pCanva.addMouseListener(this);

        // przyciski z wyborem kształtu wypełnionego bądź nie
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

        //JSlider z rozmiarem kształtu
        sSize=new JSlider(0,0,100,50);
        sSize.setFont(font);
        sSize.setBounds(50,450,200,50);
        sSize.setMajorTickSpacing(20);
        sSize.setMinorTickSpacing(5);
        sSize.setPaintTicks(true);
        sSize.setPaintLabels(true);
        pButtons.add(sSize);
        sSize.addChangeListener(changeListener);

        //przycisk z czysczeniem canvy
        bClear=new JButton("Clear the canva");
        bClear.setBounds(50,550,200,100);
        bClear.addActionListener(this);
        bClear.setFont(font);
        pButtons.add(bClear);

        //przycisk wyjścia
        bExit=new JButton("Exit");
        bExit.setFont(font);
        bExit.addActionListener(this);
        bExit.setBounds(50,700,200,70);
        pButtons.add(bExit);


        addKeyListener(this);

        //JLabele z wyborem kształtu
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

    /**
     * Metoda zwracająca aktualny kolor
     * @return currentColor
     */
    Color getColor() {return currentColor;}

    /**
     * Metoda rysująca koło
     * @param x współrzędna x miejsca w którym koło ma być narysowane
     * @param y współrzędna y miejsca w którym koło ma być narysowane
     * @param r rozmiar kształtu
     */
    void drawCenteredCircle(int x, int y, int r) {
        Graphics g = pCanva.getGraphics();
        g.setColor(getColor());
        if(number==1) g.fillOval(x+r/2,y+r/2,r,r);
        if(number==2) g.drawOval(x+r/2,y+r/2,r,r);
    }

    /**
     * Metoda rysująca kwadrat
     * @param x współrzędna x miejsca w którym kwadrat ma być narysowany
     * @param y współrzędna y miejsca w którym kwadrat ma być narysowany
     * @param r rozmiar kształtu
     */
    void drawCenteredSquare(int x, int y, int r){
        Graphics g = pCanva.getGraphics();
        g.setColor(getColor());
        if(number==1) g.fillRect(x,y,r,r);
        if(number==2) g.drawRect(x,y,r,r);
    }

    /**
     * Metoda zmieniająca aktualnie wybrany kolor
     * @return nowy kolor
     */
    Color changingColor(){
        JColorChooser colorChooser = new JColorChooser();
        currentColor = colorChooser.showDialog(null, "Pick a color...I guess", Color.black);
        return currentColor;
    }

    /**
     * Metoda wybierają, który kształt ma narysować przez sprawdzenie jaki kształt jest aktualnie wybrany
     * @param x współrzędna x miejsca w którym kształt ma być narysowany
     * @param y współrzędna y miejsca w którym kształt ma być narysowany
     * @param r rozmiar kształtu
     */
    void drawFigure(int x, int y, int r){
        if(figure=='c') drawCenteredCircle(x-r,y-r,r);
        if(figure=='s') drawCenteredSquare(x-r/2,y-r/2,r);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source==bChangeColor){
            bCurrentColor.setBackground(changingColor());
        }

        if(source==rbFill) number=1;
        if(source==rbEmpty) number=2;

        //czyszczenie canvy
        if(source==bClear){
            getContentPane().setBackground(new Color(160,200,100));
            pCanva.getGraphics().clearRect(0,0,1300,900);
            pCanva.setBackground(Color.WHITE);
        }

        //zamykanie okna canvy i otwieranie okna z wyborem gry
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

    /**
     * Wybór rodzaju kształtu z klawiatury poprzez kliknięcie klawisza
     * z literą 'c' - wybór koła lub 's' - wybór kwadratu
     * @param e the event to be processed
     */
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

    /**
     * Metoda uruchamiająca metedy do rysownia figury poprzez kliknięcie myszą
     * @param e the event to be processed
     */
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
