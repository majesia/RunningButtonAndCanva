import javax.swing.*;
import java.awt.*;

/**
 * Klasa z metodą main uruchamiająca cały program
 */

public class ApplicationStart extends JFrame {
    public static void main(String[] args){

        SwingUtilities.invokeLater(() -> {
            try {
                FirstWindow window = new FirstWindow();
                window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                window.setVisible(true);
            }
            catch (Exception e) {
                e.printStackTrace(System.err);
            }
        });

    }
}
