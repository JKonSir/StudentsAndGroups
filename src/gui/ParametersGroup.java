package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by gijoe on 6/30/2015.
 */
public class ParametersGroup extends JDialog {

    private JPanel parametersGroupPanel;
    private JButton okButton;
    private JButton cancelButton;
    private JTextField idGroupField;
    private JTextField facultyNameField;
    private JLabel idGroupLabel;
    private JLabel facultyNameLabel;

    private Long idGroup;
    private String facultyName;

    private int OK = 1;
    private int CANCEL = 2;
    private int closeValue;

    public ParametersGroup() {

        parametersGroupPanel = new JPanel();
        okButton = new JButton("okButton");
        cancelButton = new JButton("cancelButton");
        idGroupField = new JTextField("0");
        idGroupLabel = new JLabel("Group ¹");
        facultyNameField = new JTextField("informatics");
        facultyNameLabel = new JLabel("Faculty name");

        parametersGroupPanel.setLayout(new GridLayout(3, 2));

        parametersGroupPanel.add(idGroupLabel);
        parametersGroupPanel.add(idGroupField);
        parametersGroupPanel.add(facultyNameLabel);
        parametersGroupPanel.add(facultyNameField);
        parametersGroupPanel.add(okButton);
        parametersGroupPanel.add(cancelButton);

        setContentPane(parametersGroupPanel);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setModal(true);
        setResizable(false);
        pack();

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idGroup = Long.parseLong(idGroupField.getText());
                facultyName = facultyNameField.getText();
                closeValue = OK;
                setVisible(false);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeValue = CANCEL;
                setVisible(false);
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                dispose();
            }
        });
    }

    public int getOK() {
        return OK;
    }

    public int getCANCEL() {
        return CANCEL;
    }

    public int getCloseValue() {
        return closeValue;
    }

    public Long getIdGroup() {
        return idGroup;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public JPanel showDialog() {
        return parametersGroupPanel;
    }
}
