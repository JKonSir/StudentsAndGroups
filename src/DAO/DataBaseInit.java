package DAO;

import com.sun.rowset.CachedRowSetImpl;
import com.sun.rowset.FilteredRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.FilteredRowSet;
import java.sql.*;

/**
 * Created by gijoe on 6/28/2015.
 */
public class DataBaseInit {

    private static String userName;
    private static String password;
    private static String connectionURL;
    private static String tableName;

    public static void setConnectionURL(String connectionURL) {
        DataBaseInit.connectionURL = connectionURL;
    }

    public static void setUserName(String userName) {
        DataBaseInit.userName = userName;
    }

    public static void setPassword(String password) {
        DataBaseInit.password = password;
    }

    public static Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            conn = DriverManager.getConnection(connectionURL, userName, password);
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }

        System.out.println("Connected to database");
        return conn;
    }

    public static void init() {

        Connection connection = null;
        Statement statement = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();

            statement.executeUpdate("CREATE TABLE studentGroup(" +
                    "idGroup BIGINT PRIMARY KEY NOT NULL , " +
                    "facultyName VARCHAR(255) NOT NULL)");
            System.out.println("table studentGroup create");

            statement.executeUpdate("CREATE TABLE student(" +
                    "idStudent BIGINT PRIMARY KEY NOT NULL, " +
                    "firstName VARCHAR(255) NOT NULL, " +
                    "lastName VARCHAR(255) NOT NULL, " +
                    "patronymic VARCHAR(255) NOT NULL, " +
                    "dateBirth DATE NOT NULL, " +
                    "idGroup BIGINT NOT NULL REFERENCES studentGroup(idGroup))");
            System.out.println("table student create");

            statement.executeUpdate("INSERT INTO studentGroup(" +
                    "idGroup, facultyName)" +
                    "VALUES (6406, 'informatics')");
            System.out.println("group create");

            statement.executeUpdate("INSERT INTO student(" +
                    "idStudent, firstName, lastName, patronymic, dateBirth, idGroup)" +
                    "VALUES (1, 'Sergey', 'Ivanov', 'Andreevich', '1980-10-10', 6406)");
            System.out.println("student create");

//            statement.executeUpdate("DELETE FROM student " +
//                    "WHERE idStudent = 4");
//            System.out.println("row has been deleted from student");
//
//            statement.executeUpdate("DELETE FROM studentGroup " +
//                    "WHERE idGroup = 1");
//            System.out.println("row has been deleted from studentGroup");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public static FilteredRowSet getCachedRowSetTable(String tableName) {

        DataBaseInit.tableName = tableName;
        FilteredRowSet cachedRowSet = null;
        try {
            cachedRowSet = new FilteredRowSetImpl();
            cachedRowSet.setUsername(userName);
            cachedRowSet.setPassword(password);
            cachedRowSet.setUrl(connectionURL);
            cachedRowSet.setKeyColumns(new int[]{1});
            cachedRowSet.setCommand("SELECT * FROM " + tableName);
            cachedRowSet.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return cachedRowSet;
    }
}
