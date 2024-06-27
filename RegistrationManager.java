import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrationManager extends Frame implements ActionListener {
    // UI Components
    TextField tfName, tfEmail, tfPhone;
    Choice genderChoice;
    List<Registrant> registrants = new ArrayList<>();
    List<String> registrationDetails = new ArrayList<>();

    // Buttons
    Button btnSave, btnDisplay, btnExport;

    // Constructor
    /**
     * 
     */
    public RegistrationManager() {
        // Frame setup
        setTitle("Registration Manager");
        setSize(500, 400);
        setLayout(new GridBagLayout());

        // Labels and Input Fields
        Label lblName = new Label("Name:");
        tfName = new TextField(20);

        Label lblEmail = new Label("Email:");
        tfEmail = new TextField(20);

        Label lblPhone = new Label("Phone:");
        tfPhone = new TextField(20);

        Label lblGender = new Label("Gender:");
        genderChoice = new Choice();
        genderChoice.add("Male");
        genderChoice.add("Female");
        genderChoice.add("Other");

        // Buttons
        btnSave = new Button("Save");
        btnDisplay = new Button("Display");
        btnExport = new Button("Export to File");

        // Add Action Listeners
        btnSave.addActionListener(this);
        btnDisplay.addActionListener(this);
        btnExport.addActionListener(this);

        // Layout setup using GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        addComponent(lblName, gbc, 0, 0);
        addComponent(tfName, gbc, 1, 0);

        addComponent(lblEmail, gbc, 0, 1);
        addComponent(tfEmail, gbc, 1, 1);

        addComponent(lblPhone, gbc, 0, 2);
        addComponent(tfPhone, gbc, 1, 2);

        addComponent(lblGender, gbc, 0, 3);
        addComponent(genderChoice, gbc, 1, 3);

        gbc.gridwidth = 2;
        gbc.gridx = 0;

        gbc.gridy = 4;
        add(btnSave, gbc);

        gbc.gridy = 5;
        add(btnDisplay, gbc);

        gbc.gridy = 6;
        add(btnExport, gbc);

        // Handle window close event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    // Helper method to add components to the frame
    private void addComponent(Component comp, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        add(comp, gbc);
    }

    // Handle button actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSave) {
            saveRegistrant();
        } else if (e.getSource() == btnDisplay) {
            displayRegistrants();
        } else if (e.getSource() == btnExport) {
            exportToFile();
        }
    }

    // Save registrant data
    private void saveRegistrant() {
        String name = tfName.getText();
        String email = tfEmail.getText();
        String phone = tfPhone.getText();
        String gender = genderChoice.getSelectedItem();

        Registrant registrant = new Registrant(name, email, phone, gender);
        registrants.add(registrant);

        tfName.setText("");
        tfEmail.setText("");
        tfPhone.setText("");

        showMessageDialog(this, "Data saved successfully!", "Save Success");
    }

    // Display registrant data
    private void displayRegistrants() {
        registrationDetails.clear();
        for (Registrant registrant : registrants) {
            registrationDetails.add(registrant.toString());
        }
        new DisplayDialog(this, registrationDetails);
    }

    // Export registrant data to a file
    private void exportToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("registrants.txt"))) {
            for (Registrant registrant : registrants) {
                writer.write(registrant.toString());
                writer.newLine();
            }
            showMessageDialog(this, "Data exported successfully!", "Export Success");
        } catch (IOException ex) {
            showMessageDialog(this, "Error exporting data.", "Export Error");
        }
    }

    // Show message dialog
    private void showMessageDialog(Component parent, String message, String title) {
        Dialog dialog = new Dialog((Frame) parent, title, true);
        dialog.setLayout(new FlowLayout());
        Label lblMessage = new Label(message);
        Button btnOk = new Button("OK");

        btnOk.addActionListener(ae -> dialog.setVisible(false));

        dialog.add(lblMessage);
        dialog.add(btnOk);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        new RegistrationManager();
    }
}

// Registrant class to store registrant data
class Registrant implements Serializable {
    private String name;
    private String email;
    private String phone;
    private String gender;

    public Registrant(String name, String email, String phone, String gender) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Email: %s, Phone: %s, Gender: %s", name, email, phone, gender);
    }
}

// Dialog to display registrant details
class DisplayDialog extends Dialog {
    public DisplayDialog(Frame parent, List<String> registrationDetails) {
        super(parent, "Registrant Details", true);
        setLayout(new GridLayout(registrationDetails.size() + 1, 1));
        for (String detail : registrationDetails) {
            add(new Label(detail));
        }
        Button btnClose = new Button("Close");
        btnClose.addActionListener(e -> setVisible(false));
        add(btnClose);
        setSize(400, registrationDetails.size() * 30 + 100);
        setLocationRelativeTo(parent);
        setVisible(true);
}
}
