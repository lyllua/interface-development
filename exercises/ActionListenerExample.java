import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Logger;

public class ActionListenerExample extends JFrame {

    private static final Logger logger = Logger.getLogger(ActionListenerExample.class.getName());
    private int counter;

    public ActionListenerExample() {
        setTitle("ActionListener Example");
        setSize(600, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Listener examples
        exampleButton();
        exampleText();
        exampleList();
        exampleCombo();
        exampleRadioButton();
        exampleMenu();
        exampleCheck();
    }

    private void exampleButton() {
        JButton button = new JButton("Click me");
        JLabel labelButton = new JLabel("Clicks: 0");
        add(button);
        add(labelButton);

        button.addActionListener(e -> {
            counter++;
            labelButton.setText("Clicks: " + counter);
        });
    }

    private void exampleText() {
        JTextField textField = new JTextField(15);
        JLabel labelText = new JLabel("Type something and press Enter");
        add(textField);
        add(labelText);

        textField.addActionListener(e -> {
            labelText.setText("You typed: " + textField.getText());
            textField.setText("");
        });
    }

    private void exampleList() {
        String[] languages = {"Java", "Python", "C#", "JavaScript"};
        JList<String> list = new JList<>(languages);
        list.setVisibleRowCount(4);
        JScrollPane scroll = new JScrollPane(list);
        JButton listButton = new JButton("Show selection");
        JLabel labelList = new JLabel("Select a language");

        add(scroll);
        add(listButton);
        add(labelList);

        listButton.addActionListener(e -> {
            String selected = list.getSelectedValue();
            if (selected != null)
                labelList.setText("You chose: " + selected);
            else
                labelList.setText("Nothing selected");
        });
    }

    private void exampleCombo() {
        String[] colors = {"Red", "Green", "Blue"};
        JComboBox<String> combo = new JComboBox<>(colors);
        JLabel labelCombo = new JLabel("Select a color");
        add(combo);
        add(labelCombo);

        combo.addActionListener(e -> {
            String color = (String) combo.getSelectedItem();
            labelCombo.setText("You selected: " + color);
        });
    }

    private void exampleRadioButton() {
        JRadioButton r1 = new JRadioButton("Option A");
        JRadioButton r2 = new JRadioButton("Option B");
        JRadioButton r3 = new JRadioButton("Option C");
        JLabel labelRB = new JLabel("Select an option");
        ButtonGroup group = new ButtonGroup();
        group.add(r1);
        group.add(r2);
        group.add(r3);

        add(r1);
        add(r2);
        add(r3);
        add(labelRB);

        ActionListener listener = e -> {
            JRadioButton source = (JRadioButton) e.getSource();
            labelRB.setText("You selected: " + source.getText());
        };

        r1.addActionListener(listener);
        r2.addActionListener(listener);
        r3.addActionListener(listener);
    }

    private void exampleMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem exitItem = new JMenuItem("Exit");
        JLabel labelMenu = new JLabel("Choose a menu option", SwingConstants.CENTER);

        add(labelMenu);

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        ActionListener listener = e -> {
            String command = e.getActionCommand();
            labelMenu.setText("You selected: " + command);
            if (command.equals("Exit")) {
                System.exit(0);
            }
        };

        newItem.addActionListener(listener);
        openItem.addActionListener(listener);
        exitItem.addActionListener(listener);
    }

    private void exampleCheck() {
        JCheckBox checkBox = new JCheckBox("Enable notifications");
        JLabel labelCheck = new JLabel("Notifications disabled");

        add(checkBox);
        add(labelCheck);

        checkBox.addActionListener(e -> {
            if (checkBox.isSelected())
                labelCheck.setText("Notifications enabled");
            else
                labelCheck.setText("Notifications disabled");
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ActionListenerExample().setVisible(true));
    }
}
