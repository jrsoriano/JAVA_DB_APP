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

import javax.swing.*;
/**
 * class implements Runnable for more flexibility 
 */

public class Control implements Runnable {
    
    public void run(){
        ResultFrame rf = new ResultFrame();
        rf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    public static void main(String [] args){
            (new Thread(new Control())).start();
    }
}
