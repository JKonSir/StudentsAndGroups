package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by gijoe on 7/3/2015.
 */
public class PrimaryFrame extends JFrame {

    private StudentPanel studentPanel;
    private GroupPanel groupPanel;
    private JPanel jPanelStudent;
    private JPanel jPanelGroup;
    private JPanel jPanel;

    public PrimaryFrame() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        studentPanel = new StudentPanel();
        groupPanel = new GroupPanel();
        jPanel = new JPanel();
        jPanelStudent = studentPanel.getStudentPanel();
        jPanelGroup = groupPanel.getGroupPanel();
        jPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel.add(jPanelStudent, gridBagConstraints);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel.add(jPanelGroup, gridBagConstraints);
        getContentPane().add(jPanel);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                int ret = JOptionPane.showConfirmDialog(jPanel, "Are you sure you want to exit?");
                if (JOptionPane.OK_OPTION == ret) {
                    studentPanel.getParametersStudent().dispose();
                    groupPanel.getParametersGroup().dispose();
                    studentPanel.dispose();
                    groupPanel.dispose();
                    dispose();
                }
            }
        });
    }
}
