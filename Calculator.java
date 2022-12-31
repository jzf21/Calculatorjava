import javax.swing.*;

public class Calculator extends JFrame {
    public Calculator() {
        super("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLookAndFeel();
        setSize(300, 200);
        setVisible(true);

    }

    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(
                "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"
            );
        } catch (Exception exc) {
            // ignore error
        }
    }

    public static void main(String[] arguments) {
        Calculator.setLookAndFeel();
        Calculator calc = new Calculator();
    }
}

class OperatorButtons extends JPanel {
    public OperatorButtons() {
        JButton add = new JButton("+");
        JButton sub = new JButton("-");
        JButton mul = new JButton("*");
        JButton div = new JButton("/");
        JButton equ = new JButton("=");
        add(add);
        add(sub);
        add(mul);
        add(div);
        add(equ);
    }
}
