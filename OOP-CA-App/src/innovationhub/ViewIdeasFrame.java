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
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ViewIdeasFrame extends JFrame {

    private JFrame previousMenu;
    private InnovationHubManager hubManager;

    private JTable tblIdeas;
    private DefaultTableModel tableModel;
    private JButton btnRefresh;
    private JButton btnDelete;
    private JButton btnBack;
    private JButton btnSort;

    public ViewIdeasFrame(JFrame previousMenu, InnovationHubManager hubManager) {
        this.previousMenu = previousMenu;
        this.hubManager = hubManager;
        initComponents();
        refreshTable();
    }

    private void initComponents() {
        setTitle("View Innovation Ideas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        String[] columnNames = {
                "ID", "Title", "Category", "Cost",
                "Feasibility", "Impact", "Innovation",
                "Total Score", "Status"
        };
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // table is read-only
            }
        };

        tblIdeas = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblIdeas);

        btnRefresh = new JButton("Refresh");
        btnDelete = new JButton("Delete Selected");
        btnBack = new JButton("Back");
        btnSort = new JButton("Sort by Score");

        btnRefresh.addActionListener(e -> refreshTable());
        btnDelete.addActionListener(e -> deleteSelected());
        btnBack.addActionListener(e -> goBack());
        btnSort.addActionListener(e -> sortByScore());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnSort);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnBack);

        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void refreshTable() {
        tableModel.setRowCount(0); // clear
        ArrayList<InnovationIdea> ideas = hubManager.getAllIdeas();
        for (InnovationIdea idea : ideas) {
            Object[] row = new Object[]{
                    idea.getId(),
                    idea.getTitle(),
                    idea.getCategory(),
                    idea.getEstimatedCost(),
                    idea.getFeasibilityScore(),
                    idea.getImpactScore(),
                    idea.getInnovationScore(),
                    idea.getTotalScore(),
                    idea.getStatus()
            };
            tableModel.addRow(row);
        }
    }

    private void deleteSelected() {
        int selectedRow = tblIdeas.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            return;
        }
        String id = (String) tableModel.getValueAt(selectedRow, 0);
        boolean removed = hubManager.deleteIdea(id);
        if (removed) {
            JOptionPane.showMessageDialog(this, "Idea deleted.");
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Could not delete idea.");
        }
    }

    private void sortByScore() {
        hubManager.sortByScoreDescending();
        refreshTable();
    }

    private void goBack() {
        if (previousMenu != null) {
            previousMenu.setVisible(true);
        }
        dispose();
    }
}

