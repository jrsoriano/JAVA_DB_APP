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


public class EmployeeException extends RuntimeException {

    public EmployeeException(String msg){
        super(msg);
    }

    public EmployeeException(String msg, Throwable t){
        super(msg, t);
    }
}
