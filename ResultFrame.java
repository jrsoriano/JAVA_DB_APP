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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*GUI components*/
public class ResultFrame extends JFrame {
    private EmployeeTableModel etm;

    /*Content Pane*/
    protected Container content;

    /*Region Panels*/
    protected JPanel labelPanel, buttonPanel;
    protected JTable resultTable;
    protected JScrollPane resultPane;

    /*Label components*/
    protected JLabel id;
    protected JLabel lastname;
    protected JLabel firstname;
    protected JLabel department;
    protected JLabel email;
    protected JLabel phone;

    /*Text field components*/
    protected JTextField idField;
    protected JTextField lastField;
    protected JTextField firstField;
    protected JTextField departmentField;
    protected JTextField emailField;
    protected JTextField phoneField;


    /*Button components*/
    protected JButton clearButton, deleteButton, editButton, saveButton, searchButton, viewButton;
    //protected JButton firstButton, nextButton, previousButton, lastButton;


    public ResultFrame() {

        super("Final Project");

        /*Panel Container*/
        content = getContentPane();

        /*Label and Text Field Region*/
        labelPanel = new JPanel();

        id = new JLabel("Employee ID ");
        lastname = new JLabel("Last Name ");
        firstname = new JLabel("First Name ");
        department = new JLabel("Department ");
        email = new JLabel("Email ");
        phone = new JLabel("Phone ");
        idField = new JTextField(10);
        lastField = new JTextField(10);
        firstField = new JTextField(10);
        departmentField = new JTextField(9);
        emailField = new JTextField(10);
        phoneField = new JTextField(10);

        labelPanel.setLayout(new GridLayout(6, 1));

        labelPanel.add(id);
        id.setHorizontalAlignment(JLabel.RIGHT);
        labelPanel.add(idField);
        idField.setHorizontalAlignment(JTextField.LEFT);

        labelPanel.add(lastname);
        lastname.setHorizontalAlignment(JLabel.RIGHT);
        labelPanel.add(lastField);
        lastField.setHorizontalAlignment(JTextField.LEFT);

        labelPanel.add(firstname);
        firstname.setHorizontalAlignment(JLabel.RIGHT);
        labelPanel.add(firstField);
        firstField.setHorizontalAlignment(JTextField.LEFT);

        labelPanel.add(department);
        department.setHorizontalAlignment(JLabel.RIGHT);
        labelPanel.add(departmentField);
        departmentField.setHorizontalAlignment(JTextField.LEFT);

        labelPanel.add(email);
        email.setHorizontalAlignment(JLabel.RIGHT);
        labelPanel.add(emailField);
        emailField.setHorizontalAlignment(JTextField.LEFT);

        labelPanel.add(phone);
        phone.setHorizontalAlignment(JLabel.RIGHT);
        labelPanel.add(phoneField);
        phoneField.setHorizontalAlignment(JTextField.LEFT);

        /*Buttons Region*/
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        //firstButton = new JButton("<<");
        //previousButton = new JButton("<");
        saveButton = new JButton("Create");
        deleteButton = new JButton("Delete");
        searchButton = new JButton("Search");
        editButton = new JButton("Update");
        viewButton = new JButton("Read");
        clearButton = new JButton("Clear");

        //nextButton = new JButton(">");
        //lastButton = new JButton(">>");
        /*Panel*/
        //buttonPanel.add(firstButton);
        //buttonPanel.add(previousButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(editButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(clearButton);
        //buttonPanel.add(nextButton);
        //buttonPanel.add(lastButton);

        /*invoke handler*/
        ButtonHandler handler = new ButtonHandler();
        /*apply handler controls*/
        //firstButton.addActionListener(handler);
        //previousButton.addActionListener(handler);
        clearButton.addActionListener(handler);
        deleteButton.addActionListener(handler);
        editButton.addActionListener(handler);
        saveButton.addActionListener(handler);
        searchButton.addActionListener(handler);
        viewButton.addActionListener(handler);
        //nextButton.addActionListener(handler);
        //lastButton.addActionListener(handler);

        /*results Region*/

        resultTable = new JTable(0, 6);
        resultPane = new JScrollPane(resultTable);


        /*add panel name and borders*/
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Admin Utilities"));
        labelPanel.setBorder(BorderFactory.createTitledBorder("Employee Information Input"));
        resultPane.setBorder(BorderFactory.createTitledBorder("Employee Records Table"));

        /*add panels to container*/
        content.add(buttonPanel, BorderLayout.SOUTH);
        content.add(labelPanel, BorderLayout.NORTH);
        content.add(resultPane, BorderLayout.CENTER);


        /*set frame parameters*/
        setSize(950, 630);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    /*Private inner class to control button actions*/
    private class ButtonHandler implements ActionListener {
        EmployeeDAO dao = new EmployeeDAO();

        /*Button source*/
        public synchronized void actionPerformed(ActionEvent e) {
            Object src = e.getSource();
            if (src == clearButton) {
                CLEAR_ACTION();
            }
            if (src == saveButton) {
                CREATE_ACTION();
            }
            if (src == deleteButton) {
                DELETE_ACTION();
            }
            if (src == editButton) {
                UPDATE_ACTION();
            }
            if (src == viewButton) {
                READ_ALL_ACTION();
            }
            if (src == searchButton) {
                SEARCH_ACTION();
            }
        }

        /*clear Button*/
        public void CLEAR_ACTION() {
            idField.setText("");
            lastField.setText("");
            firstField.setText("");
            departmentField.setText("");
            emailField.setText("");
            phoneField.setText("");
        }

        /*create Button*/
        public void CREATE_ACTION() throws NumberFormatException {
            try {
                dao.create(new EmployeeDTO(Integer.parseInt(idField.getText()), lastField.getText(), firstField.getText(),
                        departmentField.getText(), emailField.getText(), phoneField.getText()));
                etm = new EmployeeTableModel(dao.readAll());
                resultTable.setModel(etm);
                idField.setText(null);
                lastField.setText(null);
                firstField.setText(null);
                departmentField.setText(null);
                emailField.setText(null);
                phoneField.setText(null);
            } catch (NumberFormatException n) {
                //throw new EmployeeException("Please don't leave the employee id field blank: " + n);
                JOptionPane optionPane = new JOptionPane("Please don't leave the employee id field blank!", JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog("ErrorMsg");
                dialog.setAlwaysOnTop(true);
                dialog.setVisible(true);
            }
        }

        /*delete Button*/
        public void DELETE_ACTION() throws NumberFormatException {
            try {
                dao.delete(Integer.parseInt(idField.getText()));
                etm = new EmployeeTableModel(dao.readAll());
                resultTable.setModel(etm);
                idField.setText(null);
                lastField.setText(null);
                firstField.setText(null);
                departmentField.setText(null);
                emailField.setText(null);
                phoneField.setText(null);
            } catch (NumberFormatException n) {
                //throw new EmployeeException("Please enter an employee id to delete: " + n);
                JOptionPane optionPane = new JOptionPane("Please enter an employee id number to delete!", JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog("ErrorMsg");
                dialog.setAlwaysOnTop(true);
                dialog.setVisible(true);
            }
        }

        /*update Button*/
        public void UPDATE_ACTION() throws NumberFormatException {
            try {
                dao.update(new EmployeeDTO(Integer.parseInt(idField.getText()), lastField.getText(), firstField.getText(),
                        departmentField.getText(), emailField.getText(), phoneField.getText()));
                etm = new EmployeeTableModel(dao.readAll());
                resultTable.setModel(etm);
                idField.setText(null);
                lastField.setText(null);
                firstField.setText(null);
                departmentField.setText(null);
                emailField.setText(null);
                phoneField.setText(null);
            } catch (NumberFormatException n) {
                //throw new EmployeeException("Please enter an employee id to update: " + n);
                JOptionPane optionPane = new JOptionPane("Please enter an employee id number to update!", JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog("ErrorMsg");
                dialog.setAlwaysOnTop(true);
                dialog.setVisible(true);
            }
        }

        /*read Button*/
        public void READ_ALL_ACTION() {
            try{
                etm = new EmployeeTableModel(dao.readAll());
                resultTable.setModel(etm);
            }catch(NullPointerException np){
                //throw new EmployeeException("No Connection Detected!");
                JOptionPane optionPane = new JOptionPane("No connection detected!", JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog("ErrorMsg");
                dialog.setAlwaysOnTop(true);
                dialog.setVisible(true);
            }
        }

        /*search by id*/
        public void SEARCH_ACTION() {
            try{    
                EmployeeDTO emp  = dao.read(idField.getText().toString());
                if (emp != null) {
                    String ln = emp.getLastname();
                    String fn = emp.getFirstname();
                    String dp = emp.getDepart();
                    String em = emp.getEmail();
                    String ph = emp.getPhone();
                    lastField.setText(ln);
                    firstField.setText(fn);
                    departmentField.setText(dp);
                    emailField.setText(em);
                    phoneField.setText(ph);
                }else{
                    //throw new EmployeeException("No Record found");
                    JOptionPane optionPane = new JOptionPane("No Record found!", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("ErrorMsg");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                }
            }catch(NullPointerException np){
                JOptionPane optionPane = new JOptionPane("Please enter id number to search!", JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog("ErrorMsg");
                dialog.setAlwaysOnTop(true);
                dialog.setVisible(true);
            }
        }
    }
}


