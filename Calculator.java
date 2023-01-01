import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Calculator extends JFrame {

    Container container;
    StringField stringField;
    JButton equalsButton;

    public Calculator() {
        super("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setVisible(true);

        container = getContentPane();
        stringField = new StringField();

        container.setBounds(0, 0, 100, 150);
        container.setLayout(new GridLayout(4, 1));

        stringField.setPreferredSize(new Dimension(40, 30));
        stringField.setFont(new Font("Arial", Font.PLAIN, 20));
        JButton equalsButton = new JButton("=");
        equalsButton.addActionListener(new ResultFinder(stringField));

        container.add(stringField);
        container.add(new CalcButtons(stringField)); // string field is a listener
        container.add(new FunctionalButtons(stringField));
        container.setVisible(true);
    }

    public static void main(String[] arguments) {
        Calculator calc = new Calculator();
    }
}

class StringField extends JTextField implements ActionListener {

    public void actionPerformed(ActionEvent event) {
        String text = event.getActionCommand();
        setText(getText() + text);
    }

}

class FunctionalButtons extends JPanel {

    public FunctionalButtons(JTextField stringField) {
        setLayout(new GridLayout(3, 1));
        JButton clearButton = new JButton("C");
        JButton equalsButton = new JButton("=");
        JButton deleteButton = new JButton("Del");

        equalsButton.addActionListener(new ResultFinder(stringField));

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                stringField.setText("");
            }

        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String text = stringField.getText();
                if (text.length() > 0) {
                    stringField.setText(text.substring(0, text.length() - 1));
                }
            }
        });
        add(clearButton);
        add(deleteButton);
        add(equalsButton);
    }

}

class CalcButton extends JButton {
    public CalcButton(String label, ActionListener listener) {
        super(label);
        addActionListener(listener);
    }

}

class CalcButtons extends JPanel {
    public CalcButtons(ActionListener stringField) {

        setLayout(new GridLayout(4, 3));
        // paddding
        CalcButton[] numberButtons = new CalcButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new CalcButton(Integer.toString((i + 1) % 10), stringField);
            add(numberButtons[i]);
        }
        char Operators[] = { '+', '-', '*', '/', '(', ')' };
        for (int i = 0; i < Operators.length; i++) {
            CalcButton operatorButton = new CalcButton(Character.toString(Operators[i]), stringField);
            add(operatorButton);
        }

    }
}

class ResultFinder implements ActionListener {

    JTextField stringField;

    public ResultFinder(JTextField stringField) {
        this.stringField = stringField;
    }

    public void actionPerformed(ActionEvent event) {
        String expression = stringField.getText();
        try {
            stringField.setText(stringField.getText() + " = " + Double.toString(evaluate(expression)));
        } catch (Exception e) {
            stringField.setText("Error");
        }
    }

    public static double evaluate(String expression) {
        char[] tokens = expression.toCharArray();
        Stack<Double> values = new Stack<Double>();        // 

        Stack<Character> ops = new Stack<Character>();     // 

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ')
                continue;

            
            if (tokens[i] >= '0' && tokens[i] <= '9') {
                StringBuffer sbuf = new StringBuffer();
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
                    sbuf.append(tokens[i++]);
                i--; 
                values.push(Double.parseDouble(sbuf.toString()));
            }

            
            else if (tokens[i] == '(')
                ops.push(tokens[i]);

            
            else if (tokens[i] == ')') {
                while (ops.peek() != '(')
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }

            
            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));

                
                ops.push(tokens[i]);
            }
        }

        
        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));

        return values.pop();
    }

    
    public static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

   
    public static double applyOp(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }

}