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

import java.util.List;
/**
 * generic will keep track of the EmployeeDTO, will accept a reference of EmployeeDTO and will 
 * return and object of type EmployeeDTO.
 */

public interface Transfer<Anything> {

    boolean create(Anything a);
    boolean delete(Object key);
    boolean update(Anything a);

    public Anything read(Object key);
    public List<Anything> readAll();

}
