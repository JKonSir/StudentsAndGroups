package gui;

import DAO.AccessDataDAOStudentImpl;
import bean.StudentBean;
import table.CreateTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by gijoe on 6/29/2015.
 */
public class StudentPanel extends JFrame {

    private JPanel jPanel;
    private JTable table;
    private JScrollPane jScrollPane;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTextField groupNumber;
    private JTextField studentName;
    private JLabel groupNumberLabel;
    private JLabel studentNameLabel;
    private CreateTableModel tableModelStudent;
    private ParametersStudent parametersStudent;
    private AccessDataDAOStudentImpl accessDataDAOStudent;

    public JPanel getStudentPanel() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        jPanel = new JPanel();
        table = new JTable();
        jScrollPane = new JScrollPane(table);
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        groupNumberLabel = new JLabel("Filter by group number:");
        studentNameLabel = new JLabel("Filter by student last name:");

        groupNumber = new JTextField();
        studentName = new JTextField();

        parametersStudent = new ParametersStudent();

        accessDataDAOStudent = new AccessDataDAOStudentImpl(jPanel);
        tableModelStudent = accessDataDAOStudent.getTableModelFull();
        table.setModel(tableModelStudent);

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

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.5;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel.add(studentNameLabel, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.5;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        jPanel.add(studentName, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.5;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        jPanel.add(groupNumberLabel, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.5;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        jPanel.add(groupNumber, gridBagConstraints);

        addAction();

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        return jPanel;
    }

    private void addAction() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parametersStudent.showDialog();
                parametersStudent.setVisible(true);
                if (parametersStudent.getCloseValue() == parametersStudent.getOK()) {
                    StudentBean studentBean = new StudentBean();
                    studentBean.setIdStudent(parametersStudent.getIdStudent());
                    studentBean.setFirstName(parametersStudent.getFirstName());
                    studentBean.setLastName(parametersStudent.getLastName());
                    studentBean.setPatronymic(parametersStudent.getPatronymic());
                    studentBean.setDateBirth(parametersStudent.getDateBirth());
                    studentBean.setGroupNumber(parametersStudent.getIdGroup());
                    accessDataDAOStudent.add(studentBean);
                    tableModelStudent = accessDataDAOStudent.getCreateTableModel();
                    table.setModel(tableModelStudent);
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
                    Long idStudent = Long.parseLong((String) tableModelStudent.getValueAt(rowSelect, 0));
                    accessDataDAOStudent.delete(idStudent);
                    tableModelStudent = accessDataDAOStudent.getCreateTableModel();
                    table.setModel(tableModelStudent);
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
                    Long idStudent = Long.parseLong((String) tableModelStudent.getValueAt(rowSelect, 0));
                    parametersStudent.showDialog();
                    parametersStudent.setVisible(true);
                    if (parametersStudent.getCloseValue() == parametersStudent.getOK()) {
                        StudentBean studentBean = new StudentBean();
                        studentBean.setIdStudent(parametersStudent.getIdStudent());
                        studentBean.setFirstName(parametersStudent.getFirstName());
                        studentBean.setLastName(parametersStudent.getLastName());
                        studentBean.setPatronymic(parametersStudent.getPatronymic());
                        studentBean.setDateBirth(parametersStudent.getDateBirth());
                        studentBean.setGroupNumber(parametersStudent.getIdGroup());
                        accessDataDAOStudent.update(studentBean, idStudent);
                        tableModelStudent = accessDataDAOStudent.getCreateTableModel();
                        table.setModel(tableModelStudent);
                        table.revalidate();
                        table.repaint();
                    }
                } else {
                    JOptionPane.showMessageDialog(jPanel, "Select row");
                }
            }
        });

        studentName.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER && !studentName.getText().equals("")) {
                    String lastName = studentName.getText();
                    tableModelStudent = accessDataDAOStudent.getTableModelFilterByStudent(lastName);
                    table.setModel(tableModelStudent);
                    table.revalidate();
                    table.repaint();
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER && studentName.getText().equals("")) {
                    tableModelStudent = accessDataDAOStudent.getTableModelFull();
                    table.setModel(tableModelStudent);
                    table.revalidate();
                    table.repaint();
                }
            }
        });

        groupNumber.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER && groupNumber.getText().length() != 0) {
                    Long idGroup = Long.parseLong(groupNumber.getText());
                    tableModelStudent = accessDataDAOStudent.getTableModelFilterByGroup(idGroup);
                    table.setModel(tableModelStudent);
                    table.revalidate();
                    table.repaint();
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER && groupNumber.getText().length() == 0) {
                    tableModelStudent = accessDataDAOStudent.getTableModelFull();
                    table.setModel(tableModelStudent);
                    table.revalidate();
                    table.repaint();
                }
            }
        });
    }

    public ParametersStudent getParametersStudent() {
        return parametersStudent;
    }
}
