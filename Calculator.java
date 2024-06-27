import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Calculator extends JFrame implements ActionListener {
    // Components
    JTextField tf1, tf2, tfResult;
    JButton btnAdd, btnSub, btnMul, btnDiv, btnClear;

    // Constructor to set up GUI components
    public Calculator() {
        // Frame setup
        setTitle("Simple Calculator");
        setSize(400, 300);
        setLayout(new GridBagLayout());  // Using GridBagLayout for flexible layout management
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating components
        tf1 = new JTextField(10);
        tf2 = new JTextField(10);
        tfResult = new JTextField(10);
        tfResult.setEditable(false);
        tfResult.setBackground(Color.LIGHT_GRAY);

        btnAdd = new JButton("+");
        btnSub = new JButton("-");
        btnMul = new JButton("*");
        btnDiv = new JButton("/");
        btnClear = new JButton("Clear");

        // Setting font and border
        Font font = new Font("Arial", Font.PLAIN, 18);
        tf1.setFont(font);
        tf2.setFont(font);
        tfResult.setFont(font);
        btnAdd.setFont(font);
        btnSub.setFont(font);
        btnMul.setFont(font);
        btnDiv.setFont(font);
        btnClear.setFont(font);

        // Adding components to the frame with GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        addComponent(new JLabel("Number 1:"), gbc, 0, 0);
        addComponent(tf1, gbc, 1, 0);
        addComponent(new JLabel("Number 2:"), gbc, 0, 1);
        addComponent(tf2, gbc, 1, 1);
        addComponent(new JLabel("Result:"), gbc, 0, 2);
        addComponent(tfResult, gbc, 1, 2);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 10, 0));
        buttonPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnSub);
        buttonPanel.add(btnMul);
        buttonPanel.add(btnDiv);
        buttonPanel.add(btnClear);

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(buttonPanel, gbc);

        // Adding action listeners
        btnAdd.addActionListener(this);
        btnSub.addActionListener(this);
        btnMul.addActionListener(this);
        btnDiv.addActionListener(this);
        btnClear.addActionListener(this);

        setVisible(true);
    }

    // Helper method to add components to the frame
    private void addComponent(Component comp, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        add(comp, gbc);
    }

    // Action event handler
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double num1 = Double.parseDouble(tf1.getText());
            double num2 = Double.parseDouble(tf2.getText());
            double result = 0;

            if (e.getSource() == btnAdd) {
                result = num1 + num2;
            } else if (e.getSource() == btnSub) {
                result = num1 - num2;
            } else if (e.getSource() == btnMul) {
                result = num1 * num2;
            } else if (e.getSource() == btnDiv) {
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    tfResult.setText("Error: Div by 0");
                    return;
                }
            } else if (e.getSource() == btnClear) {
                tf1.setText("");
                tf2.setText("");
                tfResult.setText("");
                return;
            }
            tfResult.setText(String.valueOf(result));
        } catch (NumberFormatException ex) {
            tfResult.setText("Invalid Input");
        }
    }

    public static void main(String[] args) {
        new Calculator();
}
}

