package bean;

import java.beans.Beans;

/**
 * Created by gijoe on 6/29/2015.
 */

public class StudentBean extends Beans {

    private Long idStudent;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String dateBirth;
    private long groupNumber;

    public StudentBean() {

    }

    public Long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Long idStudent) {
        this.idStudent = idStudent;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public long getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(long groupNumber) {
        this.groupNumber = groupNumber;
    }
}
