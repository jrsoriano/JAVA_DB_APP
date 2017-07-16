/**
 * Final Project 
 * This application will create,delete,update,and display employee records stored
 * in a db.
 * 
 * @author Jose R. Soriano
 * @version 1.0
 * 
 * Course: COMP-1348 Programming (Java 4)
 * Section: Online
 * Date Created: 12.31.2016
 * Last Update: 01.28.2017
**/

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by Jose Soriano on 2017-01-17.
 */
public class EmployeeTableModel extends AbstractTableModel{
    /*updated employee list*/
    private final List<EmployeeDTO> employeelist;

    /*constructor*/
    public EmployeeTableModel(List<EmployeeDTO> employeelist){
        this.employeelist = employeelist;
    }

    @Override/*set column names on jtable*/
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "Employee ID";
            case 1:
                return "Last Name";
            case 2:
                return "First Name";
            case 3:
                return "Department";
            case 4:
                return "Email";
            case 5:
                return "Phone Number";
            default:
                return "";
        }
    }

    @Override/*returns employeelist size*/
    public int getRowCount() {
        return employeelist.size();
    }

    @Override/*returns column count*/
    public int getColumnCount() {
        return 6;
    }

    @Override/*returns value from selected row/column*/
    public Object getValueAt(int rowIndex, int columnIndex) {
        EmployeeDTO e = employeelist.get(rowIndex);
        switch (columnIndex){
            case 0:
                return e.getId();
            case 1:
                return e.getLastname();
            case 2:
                return e.getFirstname();
            case 3:
                return e.getDepart();
            case 4:
                return e.getEmail();
            case 5:
                return e.getPhone();
            default:
                return null;
        }
    }
}

