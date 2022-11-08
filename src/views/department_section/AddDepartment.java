package views.department_section;

import services.DepartmentService;
import views.Departments;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddDepartment extends JFrame {

    public AddDepartment() {
        getContentPane().setBackground(Color.WHITE);

        setSize(1100, 500);
        setVisible(true);
        setLocation(200, 10);
        setLayout(null);
        setResizable(false);

        textButton("Back", 10, 10,150,45,e -> {
            setVisible(false);
            new Departments();
        });

        JLabel homeHeadingLabel = new JLabel("Placement Management System", JLabel.CENTER);
        homeHeadingLabel.setFont(new Font("serif", Font.BOLD, 36));
        homeHeadingLabel.setBounds(0, 25, 1100, 50);
        homeHeadingLabel.setForeground(Color.BLACK);
        add(homeHeadingLabel);

        JLabel studentSectionLabel = new JLabel("Add new department", JLabel.CENTER);
        studentSectionLabel.setFont(new Font("serif", Font.BOLD, 32));
        studentSectionLabel.setBounds(0, 80, 1100, 50);
        studentSectionLabel.setForeground(Color.BLACK);
        add(studentSectionLabel);

        // Name
        JLabel nameLabel = new JLabel("Department Name");
        nameLabel.setBounds(100, 200, 200, 50);
        studentSectionLabel.setForeground(Color.BLACK);
        studentSectionLabel.setFont(new Font("serif", Font.PLAIN, 22));
        add(nameLabel);

        JTextField nameTf = new JTextField();
        nameTf.setBounds(320, 200, 450, 50);
        add(nameTf);

        // Add button
        textButton("Add a department",320,300,450,50,e-> {
            // add record to the database
            String name = nameTf.getText();
            // TODO: validate the data before insert into the database

            DepartmentService model = new DepartmentService();
            boolean result = model.insert(name);

            if (result) {
                JOptionPane.showMessageDialog(new JFrame(), "Department added successfully!!!");
                setVisible(false);
                new Departments();
                return;
            }

            JOptionPane.showMessageDialog(new JFrame(), "Something went wrong!!!");
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
