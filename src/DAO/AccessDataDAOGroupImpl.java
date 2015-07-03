package DAO;

import bean.GroupBean;
import bean.StudentBean;
import table.CreateTableModel;

import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.spi.SyncProviderException;
import javax.swing.*;
import java.awt.*;
import java.beans.Beans;
import java.sql.SQLException;

/**
 * Created by gijoe on 7/1/2015.
 */
public class AccessDataDAOGroupImpl implements AccessDateDAO {

    private FilteredRowSet filteredRowSet;
    private CreateTableModel createTableModel;
    private Component component;

    public AccessDataDAOGroupImpl(Component component) {
        filteredRowSet = DataBaseInit.getCachedRowSetTable("studentGroup");
        createTableModel = new CreateTableModel(filteredRowSet);
        this.component = component;
    }

    @Override
    public void add(Beans bean) {
        try {
            filteredRowSet.moveToInsertRow();
            filteredRowSet.updateLong("IDGROUP", ((GroupBean) bean).getId());
            filteredRowSet.updateString("FACULTYNAME", ((GroupBean) bean).getFacultyName());
            filteredRowSet.insertRow();
            filteredRowSet.moveToCurrentRow();
            filteredRowSet.acceptChanges();
            System.out.println("row has been inserted");
        } catch (SyncProviderException ex) {
            JOptionPane.showMessageDialog(component, ex);
            System.out.println(ex);
            try {
                filteredRowSet.restoreOriginal();
            } catch (SQLException ex1) {
                System.out.println(ex1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(component, ex);

        }
        createTableModel = new CreateTableModel(filteredRowSet);
    }

    @Override
    public void delete(Long idGroup) {
        try {
            filteredRowSet.beforeFirst();
            while (filteredRowSet.next()) {
                if (filteredRowSet.getLong(1) == idGroup) {
                    filteredRowSet.deleteRow();
                    break;
                }
            }
            filteredRowSet.acceptChanges();
            System.out.println("row has been deleted");
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
    public void update(Beans bean, Long idGroup) {
        try {
            filteredRowSet.beforeFirst();
            while (filteredRowSet.next()) {
                if (filteredRowSet.getLong(1) == idGroup) {
                    filteredRowSet.updateLong("IDGROUP", ((GroupBean) bean).getId());
                    filteredRowSet.updateString("FACULTYNAME", ((GroupBean) bean).getFacultyName());
                    filteredRowSet.updateRow();
                    break;
                }
            }
            filteredRowSet.acceptChanges();
            System.out.println("row has been updated");
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
            filteredRowSet.setFilter(null);
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
