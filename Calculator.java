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
        setSize(300, 300);
        setVisible(true);

        container = getContentPane();
        stringField = new StringField();

        container.setLayout(new FlowLayout());

        stringField.setPreferredSize(new Dimension(280, 30));
        JButton equalsButton = new JButton("=");
        equalsButton.addActionListener(new ResultFinder(stringField));

        container.add(stringField);
        container.add(new CalcButtons(stringField)); // string field is a listener
        container.add(equalsButton);

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

class CalcButton extends JButton {
    public CalcButton(String label, ActionListener listener) {
        super(label);
        addActionListener(listener);
    }

}

class CalcButtons extends JPanel {
    public CalcButtons(ActionListener stringField) {

        setLayout(new GridLayout(4, 3));
        CalcButton[] numberButtons = new CalcButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new CalcButton(Integer.toString(i), stringField);
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

        // Stack for numbers: 'values'
        Stack<Double> values = new Stack<Double>();

        // Stack for Operators: 'ops'
        Stack<Character> ops = new Stack<Character>();

        for (int i = 0; i < tokens.length; i++) {
            // Current token is a whitespace, skip it
            if (tokens[i] == ' ')
                continue;

            // Current token is a number, push it to stack for numbers
            if (tokens[i] >= '0' && tokens[i] <= '9') {
                StringBuffer sbuf = new StringBuffer();
                // There may be more than one digits in number
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
                    sbuf.append(tokens[i++]);
                i--; // to compensate for the i++ in the for loop
                values.push(Double.parseDouble(sbuf.toString()));
            }

            // Current token is an opening brace, push it to 'ops'
            else if (tokens[i] == '(')
                ops.push(tokens[i]);

            // Closing brace encountered, solve entire brace
            else if (tokens[i] == ')') {
                while (ops.peek() != '(')
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }

            // Current token is an operator.
            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                // While top of 'ops' has same or greater precedence to current
                // token, which is an operator. Apply operator on top of 'ops'
                // to top two elements in values stack
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));

                // Push current token to 'ops'.
                ops.push(tokens[i]);
            }
        }

        // Entire expression has been parsed at this point, apply remaining
        // ops to remaining values
        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));

        // Top of 'values' contains result, return
        return values.pop();
    }

    // Returns true if 'op2' has higher or same precedence as 'op1',
    // otherwise returns false.
    public static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    // A utility method to apply an operator 'op' on operands 'a'
    // and 'b'. Return the result.
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