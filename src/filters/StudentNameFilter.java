package filters;

import javax.sql.RowSet;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.Predicate;
import java.sql.SQLException;

/**
 * Created by gijoe on 6/30/2015.
 */
public class StudentNameFilter implements Predicate {

    private String lastName;
    private int columnIndex;
    private String columnName;

    public StudentNameFilter(String lastName, int columnIndex, String columnName) {
        this.lastName = lastName;
        this.columnIndex = columnIndex;
        this.columnName = columnName;
    }

    public StudentNameFilter(String lastName, int columnIndex) {
        this.lastName = lastName;
        this.columnIndex = columnIndex;
    }

    @Override
    public boolean evaluate(Object value, String columnName) {
        boolean evaluation = true;
        if (columnName.equalsIgnoreCase(this.columnName)) {
            String columnValue = (String) value;
            if (columnValue.equalsIgnoreCase(this.lastName)) {
                evaluation = true;
            } else {
                evaluation = false;
            }
        }
        return evaluation;
    }

    @Override
    public boolean evaluate(Object value, int columnNumber) {
        boolean evaluation = true;
        if (this.columnIndex == columnNumber) {
            String columnValue = (String) value;
            if (columnValue.equalsIgnoreCase(this.lastName)) {
                evaluation = true;
            } else {
                evaluation = false;
            }
        }
        return evaluation;
    }

    @Override
    public boolean evaluate(RowSet rowSet) {
        if (rowSet == null) {
            return false;
        }

        FilteredRowSet filteredRowSet = (FilteredRowSet) rowSet;
        boolean evaluation = false;
        try {
            String columnValue = filteredRowSet.getString(this.columnIndex);
            if (columnValue.equalsIgnoreCase(this.lastName)) {
                evaluation = true;
            }
        } catch (SQLException ex) {
            return false;
        }
        return evaluation;
    }
}
