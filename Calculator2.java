import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator2 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);

        JLabel label = new JLabel("");
        label.setSize(700, 100);
        label.setVerticalAlignment(JLabel.TOP);
        frame.add(label);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(3, 3));

        // Create a list of buttons
        JButton[] buttons = {
                new JButton("1"),
                new JButton("2"),
                new JButton("3"),
                new JButton("4"),
                new JButton("5"),
                new JButton("6"),
                new JButton("7"),
                new JButton("8"),
                new JButton("9"),
                new JButton("0"),
                new JButton("+"),
                new JButton("-"),
                new JButton("*"),
                new JButton("/"),
                new JButton("="),
                new JButton("Backspace"),
                new JButton("Clear")
        };

        // Add the buttons to the frame
        for (JButton button : buttons) {
            frame.add(button);
        }

        // Create an ActionListener to handle button clicks
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton clickedButton = (JButton) e.getSource();
                String buttonText = clickedButton.getText();

                if (buttonText.equals("Backspace")) {
                    // Remove the last character from the label text
                    String labelText = label.getText();
                    labelText = labelText.substring(0, labelText.length() - 1);
                    label.setText(labelText);
                } else if (buttonText.equals("Clear")) {
                    // Clear the label text
                    label.setText("");
                }
                else if(buttonText.equals("=")){
                    // Calculate the result
                    String labelText = label.getText();
                    String[] parts = labelText.split("[+-/*]");
                    int num1 = Integer.parseInt(parts[0]);
                    int num2 = Integer.parseInt(parts[1]);
                    int result = 0;
                    if(labelText.contains("+")){
                        result = num1 + num2;
                    }
                    else if(labelText.contains("-")){
                        result = num1 - num2;
                    }
                    else if(labelText.contains("*")){
                        result = num1 * num2;
                    }
                    else if(labelText.contains("/")){
                        result = num1 / num2;
                    }
                    label.setText(result + "");
                } else {
                    // Add the button text to the label
                    label.setText(label.getText() + buttonText);
                }
            }
        };

        // Add the ActionListener to all the buttons
        for (JButton button : buttons) {
            button.addActionListener(listener);
        }
    }

}
