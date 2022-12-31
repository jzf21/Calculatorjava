import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Calculator extends JFrame {

    Container container;
    StringField stringField;

    public Calculator() {
        super("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLookAndFeel();
        setSize(300, 300);
        setVisible(true);

        container = getContentPane();
        stringField = new StringField();
        container.setLayout(new FlowLayout());

        stringField.setPreferredSize(new Dimension(280, 30));

        container.add(stringField);
        container.add(new NumberButtons(stringField));
        container.add(new OperatorButtons(stringField));

    }

    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception exc) {
            // ignore error
        }
    }

    public static void main(String[] arguments) {
        Calculator.setLookAndFeel();
        Calculator calc = new Calculator();
    }
}

class StringField extends JTextField implements ActionListener {

    public void actionPerformed(ActionEvent event) {
        String text = event.getActionCommand();
        setText(getText() + text);
    }

}

class NumberButton extends JButton {
    public NumberButton(String label, ActionListener listener) {
        super(label);
        addActionListener(listener);
    }

}

class NumberButtons extends JPanel {
    public NumberButtons(ActionListener listener) {

        setLayout(new GridLayout(4, 3));
        NumberButton one = new NumberButton("1", listener);
        NumberButton two = new NumberButton("2", listener);
        NumberButton three = new NumberButton("3", listener);
        NumberButton four = new NumberButton("4", listener);
        NumberButton five = new NumberButton("5", listener);
        NumberButton six = new NumberButton("6", listener);
        NumberButton seven = new NumberButton("7", listener);
        NumberButton eight = new NumberButton("8", listener);
        NumberButton nine = new NumberButton("9", listener);
        NumberButton zero = new NumberButton("0", listener);

        add(one);
        add(two);
        add(three);
        add(four);
        add(five);
        add(six);
        add(seven);
        add(eight);
        add(nine);
        add(zero);
    }
}

class OperatorButton extends JButton {
    public OperatorButton(String label, ActionListener listener) {
        super(label);
        addActionListener(listener);
    }

}

class OperatorButtons extends JPanel {
    public OperatorButtons(ActionListener listener) {
        setLayout(new GridLayout(5, 1));
        OperatorButton add = new OperatorButton("+", listener);
        OperatorButton sub = new OperatorButton("-", listener);
        OperatorButton mul = new OperatorButton("*", listener);
        OperatorButton div = new OperatorButton("/", listener);
        OperatorButton equ = new OperatorButton("=", listener);
        add(add);
        add(sub);
        add(mul);
        add(div);
        add(equ);
    }
}
