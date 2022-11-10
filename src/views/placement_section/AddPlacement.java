package views.placement_section;

import models.DepartmentModel;
import models.StudentModel;
import services.StudentService;
import views.Departments;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddPlacement extends JFrame {

    private final DefaultComboBoxModel studentList;
    private final StudentService studentService = new StudentService();

    public AddPlacement() {
        getContentPane().setBackground(Color.WHITE);

        setSize(1100, 800);
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

        JLabel studentSectionLabel = new JLabel("New Placement", JLabel.CENTER);
        studentSectionLabel.setFont(new Font("serif", Font.BOLD, 32));
        studentSectionLabel.setBounds(0, 80, 1100, 50);
        studentSectionLabel.setForeground(Color.BLACK);
        add(studentSectionLabel);

        // package
        JLabel packageLabel = new JLabel("Package");
        packageLabel.setBounds(100, 200, 200, 50);
        packageLabel.setForeground(Color.BLACK);
        packageLabel.setFont(new Font("serif", Font.PLAIN, 22));
        add(packageLabel);

        JTextField packageTf = new JTextField();
        packageTf.setBounds(320, 200, 450, 50);
        add(packageTf);

        // student
        studentList = new DefaultComboBoxModel();

        ArrayList<StudentModel> students = studentService.findAllIsNotPlaced();

        for(StudentModel e: students) {
            studentList.addElement(e.name);
        }

        JLabel studentLabel  = new JLabel("Select Student");
        studentLabel.setBounds(100, 500, 200,50);
        studentLabel.setForeground(Color.BLACK);
        studentLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(studentLabel);

        JComboBox selectedStudent = new JComboBox(studentList);
        selectedStudent.setBounds(320,500,450,50);
        add(selectedStudent);
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
