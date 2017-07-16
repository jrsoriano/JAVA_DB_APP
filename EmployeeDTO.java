/**
 * This application will create,delete,update,and display employee records stored
 * in a db.
 * 
 * @author Jose R. Soriano
 * @version 1.0
 * 
 * Date Created: 12.31.2016
 * Last Update: 01.28.2017
**/

import java.util.Comparator;

public class EmployeeDTO implements Comparator<EmployeeDTO> {
    private int id;
    private String lastname;
    private String firstname;
    private String depart;
    private String email;
    private String phone;

    /*constructor*/
    public EmployeeDTO(int id, String lastname, String firstname, String depart, String email, String phone) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.depart = depart;
        this.email = email;
        this.phone = phone;
    }

    public EmployeeDTO() {

    }

    /*employee setters and getters*/
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override/*lists employees by id on table*/
    public int compare(EmployeeDTO emp1, EmployeeDTO emp2) {
         return Integer.parseInt(String.valueOf(emp1.getId())) - Integer.parseInt(String.valueOf(emp2.getId()));
    }

}
