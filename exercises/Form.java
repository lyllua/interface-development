import javax.swing.*;
import java.awt.*;

public class Form {
    public static void main(String[] args) {
        // Create window
        JFrame frame = new JFrame("Simple Form");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(163, 211, 216, 255)); // light grey background

        // Name label
        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(50, 50, 80, 25);
        lblName.setFont(new Font("Arial", Font.BOLD, 14));
        lblName.setForeground(new Color(60, 60, 60));
        frame.add(lblName);

        // Name text field
        JTextField txtName = new JTextField();
        txtName.setBounds(140, 50, 200, 25);
        frame.add(txtName);

        // Email label
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(50, 90, 80, 25);
        lblEmail.setFont(new Font("Arial", Font.BOLD, 14));
        lblEmail.setForeground(new Color(60, 60, 60));
        frame.add(lblEmail);

        // Email text field
        JTextField txtEmail = new JTextField();
        txtEmail.setBounds(140, 90, 200, 25);
        frame.add(txtEmail);

        // Submit button
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(140, 140, 100, 30);
        btnSubmit.setBackground(new Color(70, 130, 180)); // steel blue
        btnSubmit.setForeground(Color.WHITE);
        frame.add(btnSubmit);

        // Button action
        btnSubmit.addActionListener(e ->
                JOptionPane.showMessageDialog(frame,
                        "Hello, " + txtName.getText() + "! Your email is " + txtEmail.getText())
        );

        frame.setVisible(true);
    }
}
