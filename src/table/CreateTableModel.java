package table;

import javax.sql.rowset.FilteredRowSet;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by gijoe on 6/29/2015.
 */
public class CreateTableModel extends DefaultTableModel {

    private FilteredRowSet tableModelRowSet;
    private int rowCount;
    private int columnCount;
    private ResultSetMetaData metaData;

    public CreateTableModel(FilteredRowSet cachedRowSet) {
        this.tableModelRowSet = cachedRowSet;
        try {
            this.metaData = tableModelRowSet.getMetaData();
            columnCount = metaData.getColumnCount();

            tableModelRowSet.beforeFirst();
            rowCount = 0;
            while (tableModelRowSet.next()) {
                rowCount++;
            }
            tableModelRowSet.beforeFirst();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public FilteredRowSet getTableModelRowSet() {
        return tableModelRowSet;
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Class getColumnClass(int column) {
        return String.class;
    }

    @Override
    public String getColumnName(int column) {
        try {
            return metaData.getColumnLabel(column + 1);
        } catch (SQLException ex) {
            System.out.println(ex);
            return ex.toString();
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            tableModelRowSet.absolute(rowIndex + 1);
            Object object = tableModelRowSet.getObject(columnIndex + 1);
            if (object == null) {
                return null;
            } else {
                return object.toString();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return ex.toString();
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void addTableModelListener(TableModelListener tableModelListener) {

    }

    @Override
    public void removeTableModelListener(TableModelListener tableModelListener) {

    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {

    }
}
