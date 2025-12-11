import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class ControlPanel extends JFrame {

    // Main panels and components
    private JPanel colorPanel, textPanel, extraPanel;
    private JLabel statusLabel, previewLabel;
    private JTextField titleField;
    private JPanel centerPanel;

    // Theme
    private String currentTheme = "Light";

    private Color[] colorCycle = {
            new Color(255, 230, 230),
            new Color(255, 243, 207),
            new Color(207, 226, 255)
    };
    private int colorIndex = 0;

    public ControlPanel() {
        setTitle("Swing Listeners Practice");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        createTopBar();
        createCenterPanels();
        createStatusBar();
        createMenuBar();

        setVisible(true);
    }

    // Top bar
    private void createTopBar() {
        JPanel topBar = new JPanel();
        JButton showBtn = new JButton("Show All");
        JButton hideBtn = new JButton("Hide All");
        JButton resetBtn = new JButton("Reset");
        JComboBox<String> themeCombo = new JComboBox<>(new String[]{"Light", "Ocean", "Warm"});

        showBtn.addActionListener(e -> setAllVisible(true));
        hideBtn.addActionListener(e -> setAllVisible(false));
        resetBtn.addActionListener(e -> resetAll());

        themeCombo.addActionListener(e -> {
            currentTheme = (String) themeCombo.getSelectedItem();
            applyTheme();
        });

        topBar.add(showBtn);
        topBar.add(hideBtn);
        topBar.add(resetBtn);
        topBar.add(themeCombo);

        add(topBar, BorderLayout.NORTH);
    }

    private void setAllVisible(boolean visible) {
        colorPanel.setVisible(visible);
        textPanel.setVisible(visible);
        extraPanel.setVisible(visible);
        statusLabel.setVisible(visible);
    }

    private void resetAll() {
        setAllVisible(true);
        currentTheme = "Light";
        applyTheme();
        titleField.setText("");
        previewLabel.setText("Preview");
        previewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        colorPanel.setBackground(colorCycle[0]);
    }

    private void applyTheme() {
        Color bg, fg;
        switch (currentTheme) {
            case "Light":
                bg = new Color(255, 255, 255);
                fg = new Color(60, 60, 60);
                break;
            case "Ocean":
                bg = new Color(204, 229, 255); 
                fg = new Color(30, 30, 60);
                break;
            case "Warm":
                bg = new Color(255, 224, 204); 
                fg = new Color(80, 40, 20);
                break;
            default:
                bg = Color.WHITE;
                fg = Color.BLACK;
        }
        colorPanel.setBackground(bg);
        textPanel.setBackground(bg);
        extraPanel.setBackground(bg);
        previewLabel.setForeground(fg);
        statusLabel.setForeground(fg);
    }

    // Center panels
    private void createCenterPanels() {
        centerPanel = new JPanel(new GridLayout(1,3));

        createColorPanel();
        createTextPanel();
        createExtraPanel();

        centerPanel.add(colorPanel);
        centerPanel.add(textPanel);
        centerPanel.add(extraPanel);

        add(centerPanel, BorderLayout.CENTER);
    }

    // Color panel
    private void createColorPanel() {
        colorPanel = new JPanel();
        colorPanel.setBorder(new TitledBorder("Colors & Visibility"));
        colorPanel.setLayout(new FlowLayout());

        JCheckBox cbText = new JCheckBox("Text Panel", true);
        JCheckBox cbExtra = new JCheckBox("Extra Panel", true);
        JCheckBox cbStatus = new JCheckBox("Status Bar", true);
        JButton colorBtn = new JButton("Change Color");

        cbText.addActionListener(e -> textPanel.setVisible(cbText.isSelected()));
        cbExtra.addActionListener(e -> extraPanel.setVisible(cbExtra.isSelected()));
        cbStatus.addActionListener(e -> statusLabel.setVisible(cbStatus.isSelected()));

        colorBtn.addActionListener(e -> {
            colorIndex = (colorIndex + 1) % colorCycle.length;
            colorPanel.setBackground(colorCycle[colorIndex]);
        });

        colorPanel.add(cbText);
        colorPanel.add(cbExtra);
        colorPanel.add(cbStatus);
        colorPanel.add(colorBtn);
    }

    // Text panel
    private void createTextPanel() {
        textPanel = new JPanel();
        textPanel.setBorder(new TitledBorder("Text"));
        textPanel.setLayout(new BorderLayout());

        titleField = new JTextField();
        previewLabel = new JLabel("Preview", JLabel.CENTER);
        previewLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        titleField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                titleField.setBackground(new Color(255, 255, 204)); 
            }
            public void focusLost(FocusEvent e) {
                if (titleField.getText().trim().isEmpty()) {
                    titleField.setBackground(new Color(255, 204, 204)); 
                    statusLabel.setText("Title is required");
                    previewLabel.setText("Preview");
                } else {
                    titleField.setBackground(Color.WHITE);
                    previewLabel.setText(titleField.getText());
                    statusLabel.setText("");
                }
            }
        });

        titleField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    previewLabel.setText(titleField.getText());
                } else if (e.getKeyChar() == '+') {
                    Font f = previewLabel.getFont();
                    previewLabel.setFont(f.deriveFont(f.getSize()+2f));
                } else if (e.getKeyChar() == '-') {
                    Font f = previewLabel.getFont();
                    previewLabel.setFont(f.deriveFont(f.getSize()-2f));
                }
            }
        });

        textPanel.add(titleField, BorderLayout.NORTH);
        textPanel.add(previewLabel, BorderLayout.CENTER);
    }

    // Extra panel
    private void createExtraPanel() {
        extraPanel = new JPanel();
        extraPanel.setBorder(new TitledBorder("Mouse Area"));
        extraPanel.setLayout(new BorderLayout());

        JLabel mouseLabel = new JLabel("Move the mouse here", JLabel.CENTER);
        mouseLabel.setFont(new Font("Arial", Font.BOLD, 16));

        mouseLabel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                mouseLabel.setText("Hello Mouse");
                mouseLabel.setForeground(new Color(102, 153, 102));
            }
            public void mouseExited(MouseEvent e) {
                mouseLabel.setText("Move the mouse here");
                mouseLabel.setForeground(new Color(60, 60, 60));
            }
            public void mousePressed(MouseEvent e) { mouseLabel.setText("Pressed"); }
            public void mouseReleased(MouseEvent e) { mouseLabel.setText("Released"); }
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2)
                    JOptionPane.showMessageDialog(extraPanel, "Double click detected");
            }
        });

        extraPanel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                statusLabel.setText("Mouse moved: " + e.getX() + ", " + e.getY());
            }
            public void mouseDragged(MouseEvent e) {
                extraPanel.setBackground(new Color(204, 255, 255));
                statusLabel.setText("Dragging...");
            }
        });

        extraPanel.add(mouseLabel, BorderLayout.CENTER);
    }

    // Status bar
    private void createStatusBar() {
        statusLabel = new JLabel("Status");
        statusLabel.setBorder(BorderFactory.createEtchedBorder());
        add(statusLabel, BorderLayout.SOUTH);
    }

    // Menu bar
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu viewMenu = new JMenu("View");
        JMenuItem miText = new JMenuItem("Text Panel");
        JMenuItem miExtra = new JMenuItem("Extra Panel");
        JMenuItem miStatus = new JMenuItem("Status Bar");

        miText.addActionListener(e -> textPanel.setVisible(!textPanel.isVisible()));
        miExtra.addActionListener(e -> extraPanel.setVisible(!extraPanel.isVisible()));
        miStatus.addActionListener(e -> statusLabel.setVisible(!statusLabel.isVisible()));

        viewMenu.add(miText);
        viewMenu.add(miExtra);
        viewMenu.add(miStatus);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem miAbout = new JMenuItem("About...");
        miAbout.addActionListener(e -> JOptionPane.showMessageDialog(this, "Swing Listeners Practice\nAuthor: Ly"));

        helpMenu.add(miAbout);

        menuBar.add(viewMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ControlPanel::new);
    }
}



