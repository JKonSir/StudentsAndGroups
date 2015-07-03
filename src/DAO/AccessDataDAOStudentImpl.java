package DAO;

import bean.StudentBean;
import filters.GroupNumberFilter;
import filters.StudentNameFilter;
import table.CreateTableModel;

import javax.sql.rowset.FilteredRowSet;
import javax.swing.*;
import java.awt.*;
import java.beans.Beans;
import java.sql.SQLException;

/**
 * Created by gijoe on 7/1/2015.
 */
public class AccessDataDAOStudentImpl implements AccessDateDAO{

    private FilteredRowSet filteredRowSet;
    private CreateTableModel createTableModel;
    private Component component;
    private StudentNameFilter studentNameFilter;
    private GroupNumberFilter groupNumberFilter;

    public AccessDataDAOStudentImpl(Component component) {
        this.filteredRowSet = DataBaseInit.getCachedRowSetTable("student");
        this.createTableModel = new CreateTableModel(filteredRowSet);
        this.component = component;
        this.studentNameFilter = null;
        this.groupNumberFilter = null;
    }

    @Override
    public void add(Beans bean) {
        try {
            filteredRowSet.setFilter(null);
            filteredRowSet.beforeFirst();
            filteredRowSet.moveToInsertRow();
            filteredRowSet.updateLong("IDSTUDENT", ((StudentBean) bean).getIdStudent());
            filteredRowSet.updateString("FIRSTNAME", ((StudentBean) bean).getFirstName());
            filteredRowSet.updateString("LASTNAME", ((StudentBean) bean).getLastName());
            filteredRowSet.updateString("PATRONYMIC", ((StudentBean) bean).getPatronymic());
            filteredRowSet.updateString("DATEBIRTH", ((StudentBean) bean).getDateBirth());
            filteredRowSet.updateLong("IDGROUP", ((StudentBean) bean).getGroupNumber());
            filteredRowSet.insertRow();
            filteredRowSet.moveToCurrentRow();
            filteredRowSet.acceptChanges();
            System.out.println("row has been inserted");
            if(groupNumberFilter != null){
                filteredRowSet.setFilter(groupNumberFilter);
            }
            if(studentNameFilter != null){
                filteredRowSet.setFilter(studentNameFilter);
            }
        } catch (SQLException ex) {
            try {
                filteredRowSet.restoreOriginal();
                System.out.println(ex);
                JOptionPane.showMessageDialog(component, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex1) {
                System.out.println(ex1);
                JOptionPane.showMessageDialog(component, ex1, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        createTableModel = new CreateTableModel(filteredRowSet);
    }

    @Override
    public void delete(Long idStudent) {
        try {
            filteredRowSet.setFilter(null);
            filteredRowSet.beforeFirst();
            while (filteredRowSet.next()) {
                if (filteredRowSet.getLong(1) == idStudent) {
                    filteredRowSet.deleteRow();
                    break;
                }
            }
            filteredRowSet.acceptChanges();
            System.out.println("row has been deleted");
            if(groupNumberFilter != null){
                filteredRowSet.setFilter(groupNumberFilter);
            }
            if(studentNameFilter != null){
                filteredRowSet.setFilter(studentNameFilter);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(component, ex);
            try {
                filteredRowSet.restoreOriginal();
            } catch (SQLException ex1) {
                System.out.println(ex1);
                JOptionPane.showMessageDialog(component, ex1);
            }
        } catch (NumberFormatException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(component, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        createTableModel = new CreateTableModel(filteredRowSet);
    }

    @Override
    public void update(Beans bean, Long idStudent) {
        try {
            filteredRowSet.setFilter(null);
            filteredRowSet.beforeFirst();
            while (filteredRowSet.next()) {
                if (filteredRowSet.getLong(1) == idStudent) {
                    filteredRowSet.updateLong("IDSTUDENT", ((StudentBean) bean).getIdStudent());
                    filteredRowSet.updateString("FIRSTNAME", ((StudentBean) bean).getFirstName());
                    filteredRowSet.updateString("LASTNAME", ((StudentBean) bean).getLastName());
                    filteredRowSet.updateString("PATRONYMIC", ((StudentBean) bean).getPatronymic());
                    filteredRowSet.updateString("DATEBIRTH", ((StudentBean) bean).getDateBirth());
                    filteredRowSet.updateLong("IDGROUP", ((StudentBean) bean).getGroupNumber());
                    filteredRowSet.updateRow();
                    break;
                }
            }
            filteredRowSet.acceptChanges();
            System.out.println("row has been updated");
            if(groupNumberFilter != null){
                filteredRowSet.setFilter(groupNumberFilter);
            }
            if(studentNameFilter != null){
                filteredRowSet.setFilter(studentNameFilter);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(component, ex);
            try {
                filteredRowSet.restoreOriginal();
            } catch (SQLException ex1) {
                System.out.println(ex1);
                JOptionPane.showMessageDialog(component, ex1);
            }
        } catch (NumberFormatException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(component, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        createTableModel = new CreateTableModel(filteredRowSet);
    }

    @Override
    public CreateTableModel getTableModelFull() {
        try {
            studentNameFilter = null;
            groupNumberFilter = null;
            filteredRowSet.setFilter(null);
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(component, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        createTableModel = new CreateTableModel(filteredRowSet);

        return createTableModel;
    }

    public CreateTableModel getTableModelFilterByGroup(Long groupNumber) {
        groupNumberFilter = new GroupNumberFilter(groupNumber, 6);
        try {
            filteredRowSet.setFilter(groupNumberFilter);
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(component, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        createTableModel = new CreateTableModel(filteredRowSet);

        return createTableModel;
    }

    public CreateTableModel getTableModelFilterByStudent(String lastName) {
        studentNameFilter = new StudentNameFilter(lastName, 3);
        try {
            filteredRowSet.setFilter(studentNameFilter);
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(component, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        createTableModel = new CreateTableModel(filteredRowSet);

        return createTableModel;
    }

    public CreateTableModel getCreateTableModel() {
        return createTableModel;
    }
}
