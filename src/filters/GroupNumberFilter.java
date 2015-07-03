package filters;

import javax.sql.RowSet;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.Predicate;
import java.sql.SQLException;

/**
 * Created by gijoe on 6/30/2015.
 */
public class GroupNumberFilter implements Predicate {

    private Long idGroup;
    private int columnIndex;
    private String columnName;

    public GroupNumberFilter(Long idGroup, int columnIndex, String columnName) {
        this.idGroup = idGroup;
        this.columnIndex = columnIndex;
        this.columnName = columnName;
    }

    public GroupNumberFilter(Long idGroup, int columnIndex) {
        this.idGroup = idGroup;
        this.columnIndex = columnIndex;
    }

    @Override
    public boolean evaluate(Object value, String columnName) {
        boolean evaluation = true;
        if (columnName.equalsIgnoreCase(this.columnName)) {
            Long columnValue = ((Long) value).longValue();
            if (columnValue == this.idGroup) {
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
            Long columnValue = ((Long) value).longValue();
            if (columnValue == this.idGroup) {
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
            Long columnValue = filteredRowSet.getLong(this.columnIndex);
            if (columnValue.equals(this.idGroup)) {
                evaluation = true;
            }
        } catch (SQLException ex) {
            return false;
        }
        return evaluation;
    }
}
