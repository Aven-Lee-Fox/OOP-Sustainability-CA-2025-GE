/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package innovationhub;

/**
 *
 * @author AHMED MAHAT - 25131923
 */


import javax.swing.*;
import java.awt.*;

public class AddIdeaFormFrame extends JFrame {

    private JFrame previousMenu;
    private InnovationHubManager hubManager;

    private JTextField txtId;
    private JTextField txtTitle;
    private JTextField txtCategory;
    private JTextField txtCost;
    private JTextField txtFeasibility;
    private JTextField txtImpact;
    private JTextField txtInnovation;
    private JComboBox<String> cmbStatus;

    private JButton btnSave;
    private JButton btnCancel;

    public AddIdeaFormFrame(JFrame previousMenu, InnovationHubManager hubManager) {
        this.previousMenu = previousMenu;
        this.hubManager = hubManager;
        initComponents();
    }

    private void initComponents() {
        setTitle("Add Innovation Idea");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        JLabel lblId = new JLabel("ID:");
        JLabel lblTitle = new JLabel("Title:");
        JLabel lblCategory = new JLabel("Category:");
        JLabel lblCost = new JLabel("Estimated Cost:");
        JLabel lblFeasibility = new JLabel("Feasibility (1-10):");
        JLabel lblImpact = new JLabel("Impact (1-10):");
        JLabel lblInnovation = new JLabel("Innovation (1-10):");
        JLabel lblStatus = new JLabel("Status:");

        txtId = new JTextField();
        txtTitle = new JTextField();
        txtCategory = new JTextField();
        txtCost = new JTextField();
        txtFeasibility = new JTextField();
        txtImpact = new JTextField();
        txtInnovation = new JTextField();

        cmbStatus = new JComboBox<>(new String[]{"Submitted", "Shortlisted", "In Progress", "Completed"});

        btnSave = new JButton("Save");
        btnCancel = new JButton("Back");

        btnSave.addActionListener(e -> saveIdea());
        btnCancel.addActionListener(e -> goBack());

        JPanel formPanel = new JPanel(new GridLayout(8, 2, 5, 5));
        formPanel.add(lblId);
        formPanel.add(txtId);
        formPanel.add(lblTitle);
        formPanel.add(txtTitle);
        formPanel.add(lblCategory);
        formPanel.add(txtCategory);
        formPanel.add(lblCost);
        formPanel.add(txtCost);
        formPanel.add(lblFeasibility);
        formPanel.add(txtFeasibility);
        formPanel.add(lblImpact);
        formPanel.add(txtImpact);
        formPanel.add(lblInnovation);
        formPanel.add(txtInnovation);
        formPanel.add(lblStatus);
        formPanel.add(cmbStatus);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().add(formPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void saveIdea() {
        try {
            String id = txtId.getText().trim();
            String title = txtTitle.getText().trim();
            String category = txtCategory.getText().trim();
            double cost = Double.parseDouble(txtCost.getText().trim());
            double feasibility = Double.parseDouble(txtFeasibility.getText().trim());
            double impact = Double.parseDouble(txtImpact.getText().trim());
            double innovation = Double.parseDouble(txtInnovation.getText().trim());
            String status = (String) cmbStatus.getSelectedItem();

            if (id.isEmpty() || title.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID and Title are required.");
                return;
            }

            InnovationIdea idea = new InnovationIdea(
                    id, title, "", category, cost,
                    feasibility, impact, innovation, status
            );
            hubManager.addIdea(idea);

            JOptionPane.showMessageDialog(this, "Idea added successfully.");
            clearForm();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numbers for cost and scores.",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        txtId.setText("");
        txtTitle.setText("");
        txtCategory.setText("");
        txtCost.setText("");
        txtFeasibility.setText("");
        txtImpact.setText("");
        txtInnovation.setText("");
        cmbStatus.setSelectedIndex(0);
    }

    private void goBack() {
        if (previousMenu != null) {
            previousMenu.setVisible(true);
        }
        dispose();
    }
}

