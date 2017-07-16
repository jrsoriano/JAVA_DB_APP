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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jose Soriano on 2017-01-16.
 */
public class EmployeeDAO implements Transfer<EmployeeDTO> {

    /*prepared SQL statements*/
    private static final String SQL_INSERT = "INSERT INTO employees(id, lastname, firstname, depart, email, phone) VALUES(?,?,?,?,?,?)";
    private static final String SQL_DELETE = "DELETE FROM employees WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE employees SET lastname = ?, firstname = ?, depart = ?, email = ?, phone = ? WHERE id = ?";
    private static final String SQL_READ = "SELECT * FROM employees WHERE id = ?";
    private static final String SQL_READ_ALL = "SELECT * FROM employees";

    /*connection*/
    private static final Connection con = Connection.conStatus();

    @Override/*insert SQL*/
    public boolean create(EmployeeDTO a) {
        PreparedStatement ps;
        try{
            ps = con.getConnection().prepareStatement(SQL_INSERT);
            ps.setInt (1, a.getId());
            ps.setString(2, a.getLastname());
            ps.setString(3, a.getFirstname());
            ps.setString(4, a.getDepart());
            ps.setString(5, a.getEmail());
            ps.setString(6, a.getPhone());
            if(ps.executeUpdate() > 0){
                con.getConnection().commit();
                System.out.println("Insert successful!");
                return true;
            }

        }catch(SQLException ex){
            throw new EmployeeException("Could not insert: "+ ex);
        }finally {
            con.closeConnection();
        }
        return false;
    }

    @Override/*delete SQL*/
    public boolean delete(Object key) {
        PreparedStatement ps;
        try{
            ps = con.getConnection().prepareStatement(SQL_DELETE);

            ps.setString(1, key.toString());

            if(ps.executeUpdate() > 0 ){
                con.getConnection().commit();
                System.out.println("Delete successful!");
                return true;

            }
        }catch (SQLException ex){
            throw new EmployeeException("Could not delete: "+ ex);
        }finally {
            con.closeConnection();
        }
        return false;
    }

    @Override/*update SQL*/
    public boolean update(EmployeeDTO a) {
        PreparedStatement ps;
        try{
            ps = con.getConnection().prepareStatement(SQL_UPDATE);

            ps.setString(1, a.getLastname());
            ps.setString(2, a.getFirstname());
            ps.setString(3, a.getDepart());
            ps.setString(4, a.getEmail());
            ps.setString(5, a.getPhone());
            ps.setInt(6, a.getId());

            if(ps.executeUpdate() > 0){
                con.getConnection().commit();
                System.out.println("Update successful!");
                return true;
            }
        }catch (SQLException ex){
            throw new EmployeeException("Could not update: "+ ex);
        }finally {
            con.closeConnection();
        }
        return false;
    }

    @Override/*read with id SQL*/
    public EmployeeDTO read(Object key) {
        PreparedStatement ps;
        ResultSet rs;
        EmployeeDTO e = null;
        try{
            ps = con.getConnection().prepareStatement(SQL_READ);

            ps.setString(1, key.toString());

            rs = ps.executeQuery();

            while(rs.next()){

                e = new EmployeeDTO(rs.getInt(1), rs.getString(2), rs.getString(3),
                                    rs.getString(4), rs.getString(5), rs.getString(6));

            }
            con.getConnection().commit();
            System.out.println("Read successful!");

        }catch (SQLException ex){
            throw new EmployeeException("Could not Read: "+ ex);
        }finally {
            con.closeConnection();
        }
        return e;
    }

    @Override/*read all SQL*/
    public List<EmployeeDTO> readAll() {
        PreparedStatement ps;
        ResultSet rs;
        List<EmployeeDTO> employeelist = new ArrayList<>();
        try{
            ps = con.getConnection().prepareStatement(SQL_READ_ALL);

            rs = ps.executeQuery();
            /*writer wrapped in a buffer to write a result set into *.txt */
            PrintWriter pw = new PrintWriter(new FileWriter("employee.txt"));
            BufferedWriter out = new BufferedWriter(pw);

            while(rs.next()){

                employeelist.add(new EmployeeDTO(rs.getInt(1), rs.getString(2), rs.getString(3),
                                                 rs.getString(4), rs.getString(5), rs.getString(6)));
                out.newLine();
                out.write("Employee ID: "+ Integer.toString(rs.getInt("id")));
                out.newLine();
                out.write("Last Name: "+ rs.getString("lastname"));
                out.newLine();
                out.write("First Name: "+ rs.getString("firstname"));
                out.newLine();
                out.write("Department: "+ rs.getString("depart"));
                out.newLine();
                out.write("Email: "+ rs.getString("email"));
                out.newLine();
                out.write("Phone Number: "+ rs.getString("phone"));
                out.newLine();
            }
            con.getConnection().commit();
            out.close();
            System.out.println("Read all successful!");
        }catch (SQLException | IOException ex){
            throw new EmployeeException("Could not read all: "+ ex);

        }finally {
            con.closeConnection();
        }
        Collections.sort(employeelist, new EmployeeDTO());//using compare from dto
        return employeelist;
    }

    /*build employee table*/
    public boolean buildEmployeeTable() {
        PreparedStatement ps;
        final String CREATE_TABLE = "CREATE TABLE APP.employees(id INT, lastname VARCHAR(25),"
                                    +"firstname VARCHAR (25),depart VARCHAR(25),"
                                    +"email VARCHAR (25),phone VARCHAR (10))";
                                     
                                    
                                    
        try{
            ps = con.getConnection().prepareStatement(CREATE_TABLE);
            System.out.print("Table created!");
            con.getConnection().commit();
            return true;
        }catch (Exception ex){
            throw new EmployeeException("Table cannot be created: "+ ex);
        }
        
    }

    /*return result set*/
    public ResultSet resultSet(){
        ResultSet rs;
        Statement stmt;
        try{
            stmt = con.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            String SQL = "SELECT * FROM employees";
            rs = stmt.executeQuery(SQL);
            con.getConnection().commit();
        }catch (SQLException ex){
            throw new EmployeeException("Result set not created: "+ ex);
        }finally {
            con.closeConnection();
        }
        return rs;
    }
}
