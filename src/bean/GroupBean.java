package bean;

import java.beans.Beans;

/**
 * Created by gijoe on 6/29/2015.
 */
public class GroupBean extends Beans{

    private long id;
    private String facultyName;

    public GroupBean() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }
}
