package views.student_section;

import models.DepartmentModel;
import services.DepartmentService;
import services.StudentService;
import views.Students;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddStudent extends JFrame {

    public AddStudent() {
        getContentPane().setBackground(Color.WHITE);

        setSize(1100,800);
        setVisible(true);
        setLocation(200,10);
        setLayout(null);
        setResizable(false);

        textButton("Back", 10, 10,150,45,e -> {
            setVisible(false);
            new Students();
        });

        JLabel homeHeadingLabel = new JLabel("Placement Management System", JLabel.CENTER);
        homeHeadingLabel.setFont(new Font("serif",Font.BOLD,36));
        homeHeadingLabel.setBounds(0, 25,1100,50);
        homeHeadingLabel.setForeground(Color.BLACK);
        add(homeHeadingLabel);

        JLabel studentSectionLabel = new JLabel("Add new student record", JLabel.CENTER);
        studentSectionLabel.setFont(new Font("serif",Font.BOLD,32));
        studentSectionLabel.setBounds(0, 80,1100,50);
        studentSectionLabel.setForeground(Color.BLACK);
        add(studentSectionLabel);

        // Name
        JLabel nameLabel  = new JLabel("Student Name");
        nameLabel.setBounds(100, 200, 200,50);
        studentSectionLabel.setForeground(Color.BLACK);
        studentSectionLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(nameLabel);

        JTextField nameTf = new JTextField();
        nameTf.setBounds(320,200,450,50);
        add(nameTf);
                    
        // Email address
        JLabel emailLabel  = new JLabel("Student Email address");
        emailLabel.setBounds(100, 300, 200,50);
        studentSectionLabel.setForeground(Color.BLACK);
        studentSectionLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(emailLabel);

        JTextField emailTf = new JTextField();
        emailTf.setBounds(320,300,450,50);
        add(emailTf);

        // PRN number
        JLabel prnNumberLabel  = new JLabel("Student PRN Number");
        prnNumberLabel.setBounds(100, 400, 200,50);
        studentSectionLabel.setForeground(Color.BLACK);
        studentSectionLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(prnNumberLabel);

        JTextField prnNumberTf = new JTextField();
        prnNumberTf.setBounds(320,400,450,50);
        add(prnNumberTf);

        // Department
        DepartmentService service = new DepartmentService();
        ArrayList<DepartmentModel> result = service.findAllDepartments();

        if (result == null) {
            // show the message to the screen
            setVisible(false);
            new Students();
            return;
        }


        DefaultComboBoxModel list = new DefaultComboBoxModel();
        for(DepartmentModel e: result) {
            list.addElement(e.name);
        }

        JLabel departmentLabel  = new JLabel("Select Student Department");
        departmentLabel.setBounds(100, 500, 200,50);
        studentSectionLabel.setForeground(Color.BLACK);
        studentSectionLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(departmentLabel);

        JComboBox departmentSelect = new JComboBox(list);
        departmentSelect.setBounds(320,500,450,50);
        add(departmentSelect);

        // Phone Number
        JLabel phoneNumberLabel  = new JLabel("Student Phone Number");
        phoneNumberLabel.setBounds(100, 600, 200,50);
        studentSectionLabel.setForeground(Color.BLACK);
        studentSectionLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(phoneNumberLabel);

        JTextField phoneNumberTf = new JTextField();
        phoneNumberTf.setBounds(320,600,450,50);
        add(phoneNumberTf);

        // Add button
        textButton("Add a record",320,700,450,50, e -> {
            // add record to the database

            String name = nameTf.getText();
            String email = emailTf.getText();
            String prn_number = prnNumberTf.getText();
            int selectedDepartment = departmentSelect.getSelectedIndex();
            long phone_number = Integer.parseInt(phoneNumberTf.getText());
            int departmentId = result.get(selectedDepartment).id;

            // TODO: validate the data before insert into the database

            StudentService service1 = new StudentService();

            boolean ok = service1.insert(name, email, prn_number, phone_number, departmentId);

            if (!ok) {
                JOptionPane.showMessageDialog(new JFrame(), "Something went wrong while inserting record!!!");
                return;
            }

            JOptionPane.showMessageDialog(new JFrame(), "New record added successfully!");

            setVisible(false);
            new Students();
        });
    }

    public void textButton(String title, int x, int y, int width, int height, ActionListener actionListener) {
        JButton showRecords = new JButton(title);
        showRecords.setBounds(x,y,width,height);
        showRecords.setFont(new Font("serif", Font.PLAIN, 18));
        showRecords.setBackground(Color.BLACK);
        showRecords.setForeground(Color.white);
        showRecords.addActionListener(actionListener);
        add(showRecords);
    }

}
