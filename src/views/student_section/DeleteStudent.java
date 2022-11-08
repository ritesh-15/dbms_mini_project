package views.student_section;

import models.StudentModel;
import services.StudentService;
import views.Students;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeleteStudent extends JFrame {

    private final JLabel nameVal;
    private final JLabel emailVal;
    private final JLabel departmentVal;
    private final JLabel prnNumberVal;
    private final JLabel phoneNumberVal;
    private final StudentService service;
    private final Choice studentId;

    public DeleteStudent() {
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

        JLabel studentSectionLabel = new JLabel("Delete a student", JLabel.CENTER);
        studentSectionLabel.setFont(new Font("serif",Font.BOLD,32));
        studentSectionLabel.setBounds(0, 80,1100,50);
        studentSectionLabel.setForeground(Color.BLACK);
        add(studentSectionLabel);

        // student id
        JLabel studentIdLabel  = new JLabel("Student Id");
        studentIdLabel.setBounds(100, 200, 200,50);
        studentSectionLabel.setForeground(Color.BLACK);
        studentSectionLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(studentIdLabel);

        studentId = new Choice();
        studentId.setBounds(320,200,450,150);
        studentId.addItemListener(e -> {
            showRecord();
        });
        add(studentId);

        service = new StudentService();
        ArrayList<StudentModel> result = service.findAll();

        for(StudentModel e: result) {
            studentId.add(String.valueOf(e.id));
        }

        // Name
        JLabel nameLabel  = new JLabel("Student Name:");
        nameLabel.setBounds(100, 300, 200,50);
        studentSectionLabel.setForeground(Color.BLACK);
        studentSectionLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(nameLabel);

        nameVal  = new JLabel("");
        nameVal.setBounds(310, 300, 200,50);
        studentSectionLabel.setForeground(Color.BLACK);
        studentSectionLabel.setFont(new Font("serif",Font.PLAIN,28));
        add(nameVal);

        // email
        JLabel emailLabel  = new JLabel("Student Email:");
        emailLabel.setBounds(100, 350, 200,50);
        studentSectionLabel.setForeground(Color.BLACK);
        studentSectionLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(nameLabel);

        emailVal  = new JLabel("");
        emailVal.setBounds(310, 350, 200,50);
        studentSectionLabel.setForeground(Color.BLACK);
        studentSectionLabel.setFont(new Font("serif",Font.PLAIN,28));
        add(emailVal);

        // department
        JLabel departmentLabel  = new JLabel("Student Department:");
        departmentLabel.setBounds(100, 400, 200,50);
        studentSectionLabel.setForeground(Color.BLACK);
        studentSectionLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(departmentLabel);

        departmentVal  = new JLabel("");
        departmentVal.setBounds(310, 400, 200,50);
        studentSectionLabel.setForeground(Color.BLACK);
        studentSectionLabel.setFont(new Font("serif",Font.PLAIN,28));
        add(departmentVal);

        // prn number
        JLabel prnNumberLabel  = new JLabel("Student PRN Number:");
        prnNumberLabel.setBounds(100, 450, 200,50);
        studentSectionLabel.setForeground(Color.BLACK);
        studentSectionLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(prnNumberLabel);

        prnNumberVal  = new JLabel("");
        prnNumberVal.setBounds(310, 450, 200,50);
        studentSectionLabel.setForeground(Color.BLACK);
        studentSectionLabel.setFont(new Font("serif",Font.PLAIN,28));
        add(prnNumberVal);

        // phone number
        JLabel phoneNumberLabel  = new JLabel("Student Phone Number:");
        phoneNumberLabel.setBounds(100, 500, 200,50);
        studentSectionLabel.setForeground(Color.BLACK);
        studentSectionLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(phoneNumberLabel);

        phoneNumberVal  = new JLabel("");
        phoneNumberVal.setBounds(310, 500, 200,50);
        studentSectionLabel.setForeground(Color.BLACK);
        studentSectionLabel.setFont(new Font("serif",Font.PLAIN,28));
        add(phoneNumberVal);

        textButton("Delete Record",310,600,200,50,e -> {
            // delete a record
            boolean ok = service.deleteById(Integer.parseInt(studentId.getSelectedItem()));

            if (!ok) {
                JOptionPane.showMessageDialog(new JFrame(), "Something went wrong while delete record!");
                return;
            }

            JOptionPane.showMessageDialog(new JFrame(), "Record deleted successfully!!");
            setVisible(false);
            new Students();
        });

        // show record of current selected index
        showRecord();
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

    private void showRecord() {
        StudentModel foundStudent = service.findById(Integer.parseInt(studentId.getSelectedItem()));

        if (foundStudent == null) {
            JOptionPane.showMessageDialog(new JFrame(), "No student found with given id!");
            return;
        }

        nameVal.setText(foundStudent.name);
        emailVal.setText(foundStudent.email);
        departmentVal.setText(foundStudent.department);
        prnNumberVal.setText(foundStudent.prn_number);
        phoneNumberVal.setText(String.valueOf(foundStudent.phone_number));
    }
}
