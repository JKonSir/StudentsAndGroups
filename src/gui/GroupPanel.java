package gui;

import DAO.AccessDataDAOGroupImpl;
import bean.GroupBean;
import table.CreateTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by gijoe on 7/3/2015.
 */
public class GroupPanel extends JFrame {

    private ParametersGroup parametersGroup;
    private JTable table;
    private JPanel jPanel;
    private JScrollPane jScrollPane;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private CreateTableModel tableModelGroup;
    private AccessDataDAOGroupImpl accessDataDAOGroup;

    public JPanel getGroupPanel() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        jPanel = new JPanel();
        table = new JTable();
        jScrollPane = new JScrollPane(table);
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");

        parametersGroup = new ParametersGroup();

        accessDataDAOGroup = new AccessDataDAOGroupImpl(jPanel);
        tableModelGroup = accessDataDAOGroup.getTableModelFull();
        table.setModel(tableModelGroup);

        jPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel.add(addButton, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel.add(updateButton, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanel.add(deleteButton, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel.add(jScrollPane, gridBagConstraints);

        addAction();

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        return jPanel;
    }

    private void addAction() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parametersGroup.showDialog();
                parametersGroup.setVisible(true);
                if (parametersGroup.getCloseValue() == parametersGroup.getOK()) {
                    GroupBean groupBean = new GroupBean();
                    groupBean.setId(parametersGroup.getIdGroup());
                    groupBean.setFacultyName(parametersGroup.getFacultyName());
                    accessDataDAOGroup.add(groupBean);
                    tableModelGroup = accessDataDAOGroup.getCreateTableModel();
                    table.setModel(tableModelGroup);
                    table.revalidate();
                    table.repaint();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowSelect = table.getSelectedRow();
                if (rowSelect != -1) {
                    Long idGroup = Long.parseLong((String) tableModelGroup.getValueAt(rowSelect, 0));
                    accessDataDAOGroup.delete(idGroup);
                    tableModelGroup = accessDataDAOGroup.getCreateTableModel();
                    table.setModel(tableModelGroup);
                    table.revalidate();
                    table.repaint();
                } else {
                    JOptionPane.showMessageDialog(jPanel, "Select row");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowSelect = table.getSelectedRow();
                if (rowSelect != -1) {
                    Long idGroup = Long.parseLong((String) tableModelGroup.getValueAt(rowSelect, 0));
                    parametersGroup.showDialog();
                    parametersGroup.setVisible(true);
                    if (parametersGroup.getCloseValue() == parametersGroup.getOK()) {
                        GroupBean groupBean = new GroupBean();
                        groupBean.setId(parametersGroup.getIdGroup());
                        groupBean.setFacultyName(parametersGroup.getFacultyName());
                        accessDataDAOGroup.update(groupBean, idGroup);
                        tableModelGroup = accessDataDAOGroup.getCreateTableModel();
                        table.setModel(tableModelGroup);
                        table.revalidate();
                        table.repaint();
                    }
                } else {
                    JOptionPane.showMessageDialog(jPanel, "Select row");
                }
            }
        });
    }

    public ParametersGroup getParametersGroup() {
        return parametersGroup;
    }
}
