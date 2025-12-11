import javax.swing.*;
import java.awt.*;

public class UserRegistration extends JFrame {

    private JTextField txtName, txtEmail;
    private JPasswordField txtPassword;
    private JCheckBox chkTerms;
    private JRadioButton rbMale, rbFemale, rbOther;
    private ButtonGroup genderGroup;
    private JComboBox<String> comboCountry;
    private JButton btnRegister, btnClear, btnExit;

    public UserRegistration() {
        setTitle("User Registration");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null); // Center the window

        // Main layout
        setLayout(new BorderLayout());

        // Center panel
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name
        gbc.gridx = 0; gbc.gridy = 0;
        centerPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        txtName = new JTextField(20);
        centerPanel.add(txtName, gbc);

        // Email
        gbc.gridx = 0; gbc.gridy = 1;
        centerPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        txtEmail = new JTextField(20);
        centerPanel.add(txtEmail, gbc);

        // Password
        gbc.gridx = 0; gbc.gridy = 2;
        centerPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        txtPassword = new JPasswordField(20);
        centerPanel.add(txtPassword, gbc);

        // Gender (radio buttons)
        gbc.gridx = 0; gbc.gridy = 3;
        centerPanel.add(new JLabel("Gender:"), gbc);
        gbc.gridx = 1;
        rbMale = new JRadioButton("Male");
        rbFemale = new JRadioButton("Female");
        rbOther = new JRadioButton("Other");
        genderGroup = new ButtonGroup();
        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);
        genderGroup.add(rbOther);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.add(rbMale);
        genderPanel.add(rbFemale);
        genderPanel.add(rbOther);

        centerPanel.add(genderPanel, gbc);

        // Country (ComboBox)
        gbc.gridx = 0; gbc.gridy = 4;
        centerPanel.add(new JLabel("Country:"), gbc);
        gbc.gridx = 1;
        comboCountry = new JComboBox<>(new String[] {"Spain", "France", "Italy", "Germany"});
        centerPanel.add(comboCountry, gbc);

        // Terms and conditions
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        chkTerms = new JCheckBox("I accept the terms of use");
        centerPanel.add(chkTerms, gbc);

        add(centerPanel, BorderLayout.CENTER);

        // SOUTH PANEL (buttons with FlowLayout)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnRegister = new JButton("Register");
        btnClear = new JButton("Clear");
        btnExit = new JButton("Exit");
        buttonPanel.add(btnRegister);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnExit);

        add(buttonPanel, BorderLayout.SOUTH);


        // Register
        btnRegister.addActionListener(e -> {
            String name = txtName.getText().trim();
            String email = txtEmail.getText().trim();
            String password = new String(txtPassword.getPassword());

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || !chkTerms.isSelected()) {
                JOptionPane.showMessageDialog(this,
                        "You must complete all required fields and accept the terms.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "User " + name + " registered successfully.",
                        "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Clear
        btnClear.addActionListener(e -> {
            txtName.setText("");
            txtEmail.setText("");
            txtPassword.setText("");
            chkTerms.setSelected(false);
            genderGroup.clearSelection();
            comboCountry.setSelectedIndex(0);
        });

        // Exit
        btnExit.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to exit?",
                    "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UserRegistration().setVisible(true);
        });
    }
}
