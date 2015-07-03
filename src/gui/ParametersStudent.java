package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by gijoe on 6/29/2015.
 */
public class ParametersStudent extends JDialog {

    private JPanel parametersStudentPanel;
    private JButton okButton;
    private JButton cancelButton;
    private JTextField idStudentField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField patronymicField;
    private JTextField dateBirthField;
    private JTextField idGroupField;
    private JLabel idStudntLabel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel patronymicLabel;
    private JLabel dateBirthLabel;
    private JLabel idGroupLabel;

    private Long idStudent;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String dateBirth;
    private Long idGroup;

    private int OK = 1;
    private int CANCEL = 2;
    private int closeValue;

    public ParametersStudent() {

        parametersStudentPanel = new JPanel();
        okButton = new JButton("okButton");
        cancelButton = new JButton("cancelButton");
        idStudentField = new JTextField("0");
        firstNameField = new JTextField("Ivan");
        lastNameField = new JTextField("Ivanov");
        patronymicField = new JTextField("Ivanovich");
        dateBirthField = new JTextField("YYYY-MM-DD");
        idGroupField = new JTextField("0");
        idStudntLabel = new JLabel("Id student");
        firstNameLabel = new JLabel("First name student");
        lastNameLabel = new JLabel("Last name student");
        patronymicLabel = new JLabel("Patronymic");
        dateBirthLabel = new JLabel("Date birth");
        idGroupLabel = new JLabel("Group ¹");

        parametersStudentPanel.setLayout(new GridLayout(7, 2));

        parametersStudentPanel.add(idStudntLabel);
        parametersStudentPanel.add(idStudentField);
        parametersStudentPanel.add(firstNameLabel);
        parametersStudentPanel.add(firstNameField);
        parametersStudentPanel.add(lastNameLabel);
        parametersStudentPanel.add(lastNameField);
        parametersStudentPanel.add(patronymicLabel);
        parametersStudentPanel.add(patronymicField);
        parametersStudentPanel.add(dateBirthLabel);
        parametersStudentPanel.add(dateBirthField);
        parametersStudentPanel.add(idGroupLabel);
        parametersStudentPanel.add(idGroupField);
        parametersStudentPanel.add(okButton);
        parametersStudentPanel.add(cancelButton);

        setContentPane(parametersStudentPanel);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setModal(true);
        setResizable(false);
        pack();

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idStudent = Long.parseLong(idStudentField.getText());
                firstName = firstNameField.getText();
                lastName = lastNameField.getText();
                patronymic = patronymicField.getText();
                dateBirth = dateBirthField.getText();
                idGroup = Long.parseLong(idGroupField.getText());
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

    public Long getIdGroup() {
        return idGroup;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Long getIdStudent() {
        return idStudent;
    }

    public int getCloseValue() {
        return closeValue;
    }

    public int getOK() {
        return OK;
    }

    public int getCANCEL() {
        return CANCEL;
    }

    public JPanel showDialog() {
        return parametersStudentPanel;
    }
}
