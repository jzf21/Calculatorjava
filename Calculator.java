
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);

        JLabel label = new JLabel("");
        label.setSize(700, 100);
        label.setVerticalAlignment(JLabel.TOP);
        frame.add(label);

        frame.setLayout(new GridLayout(3, 3));
        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        JButton button5 = new JButton("5");
        JButton button6 = new JButton("6");
        JButton button7 = new JButton("7");
        JButton button8 = new JButton("8");
        JButton button9 = new JButton("9");
        JButton button0 = new JButton("0");

        JButton buttonPlus = new JButton("+");
        JButton buttonMinus = new JButton("-");
        JButton buttonMultiply = new JButton("*");
        JButton buttonDivide = new JButton("/");
        JButton buttonEquals = new JButton("=");
        JButton backspace = new JButton("Backspace");
        JButton clear = new JButton("Clear");

        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(button5);
        frame.add(button6);
        frame.add(button7);
        frame.add(button8);
        frame.add(button9);
        frame.add(button0);
        frame.add(buttonPlus);
        frame.add(buttonMinus);
        frame.add(buttonMultiply);
        frame.add(buttonDivide);
        frame.add(buttonEquals);
        frame.add(clear);
        frame.add(backspace);

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText(label.getText() + "1");
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText(label.getText() + "2");
            }
        });
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText(label.getText() + "3");
            }
        });
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText(label.getText() + "4");
            }
        });
        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText(label.getText() + "5");
            }
        });
        button6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText(label.getText() + "6");
            }
        });
        button7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText(label.getText() + "7");
            }
        });
        button8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText(label.getText() + "8");
            }
        });
        button9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText(label.getText() + "9");
            }
        });
        button0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText(label.getText() + "0");
            }
        });
        buttonPlus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText(label.getText() + "+");
            }
        });
        buttonMinus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText(label.getText() + "-");
            }
        });
        buttonMultiply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText(label.getText() + "*");
            }
        });
        buttonDivide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText(label.getText() + "/");
            }
        });
        buttonEquals.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] numbers = label.getText().split("[+-/*]");
                String[] operators = label.getText().split("[0-9]+");
                int result = Integer.parseInt(numbers[0]);
                for (int i = 1; i < numbers.length; i++) {
                    if (operators[i].equals("+")) {
                        result += Integer.parseInt(numbers[i]);
                    } else if (operators[i].equals("-")) {
                        result -= Integer.parseInt(numbers[i]);
                    } else if (operators[i].equals("*")) {
                        result *= Integer.parseInt(numbers[i]);
                    } else if (operators[i].equals("/")) {
                        result /= Integer.parseInt(numbers[i]);
                    }
                }
                label.setText(Integer.toString(result));
            }
        });
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText("");
            }
        });
        backspace.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = label.getText();
                if (text.length() > 0) {
                    label.setText(text.substring(0, text.length() - 1));
                }
            }
        });

        frame.setVisible(true);
    }

}
