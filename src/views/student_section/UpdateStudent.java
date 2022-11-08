package views.student_section;

import models.DepartmentModel;
import models.StudentModel;
import services.DepartmentService;
import services.StudentService;
import views.Students;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UpdateStudent extends JFrame {

    private final Choice studentId;

    private final StudentService service = new StudentService();

    public  final DepartmentService departmentService = new DepartmentService();
    private final JTextField nameTf;
    private final JTextField emailTf;
    private final JTextField prnNumberTf;
    private final JTextField phoneNumberTf;
    private final Choice departmentValue;

    public UpdateStudent() {
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

        JLabel studentSectionLabel = new JLabel("Update a student", JLabel.CENTER);
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

        // Name
        JLabel nameLabel  = new JLabel("Student Name");
        nameLabel.setBounds(100, 300, 200,50);
        studentSectionLabel.setForeground(Color.BLACK);
        studentSectionLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(nameLabel);

        nameTf = new JTextField();
        nameTf.setBounds(320,300,450,50);
        add(nameTf);

        // Email address
        JLabel emailLabel  = new JLabel("Student Email address");
        emailLabel.setBounds(100, 400, 200,50);
        studentSectionLabel.setForeground(Color.BLACK);
        studentSectionLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(emailLabel);

        emailTf = new JTextField();
        emailTf.setBounds(320,400,450,50);
        add(emailTf);

        // PRN number
        JLabel prnNumberLabel  = new JLabel("Student PRN Number");
        prnNumberLabel.setBounds(100, 500, 200,50);
        studentSectionLabel.setForeground(Color.BLACK);
        studentSectionLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(prnNumberLabel);

        prnNumberTf = new JTextField();
        prnNumberTf.setBounds(320,500,450,50);
        add(prnNumberTf);

        // Phone Number
        JLabel phoneNumberLabel  = new JLabel("Student Phone Number");
        phoneNumberLabel.setBounds(100, 600, 200,50);
        studentSectionLabel.setForeground(Color.BLACK);
        studentSectionLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(phoneNumberLabel);

        phoneNumberTf = new JTextField();
        phoneNumberTf.setBounds(320,600,450,50);
        add(phoneNumberTf);

        // department
        JLabel departmentLabel  = new JLabel("Student Department");
        departmentLabel.setBounds(100, 700, 200,50);
        studentSectionLabel.setForeground(Color.BLACK);
        studentSectionLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(departmentLabel);

        departmentValue = new Choice();
        departmentValue.setBounds(320,700,450,150);
        add(departmentValue);

        ArrayList<StudentModel> studentsResult = service.findAll();
        for(StudentModel e: studentsResult) {
            studentId.add(String.valueOf(e.id));
        }

        ArrayList<DepartmentModel> result = departmentService.findAllDepartments();
        for(DepartmentModel e: result) {
            departmentValue.add(String.valueOf(e.name));
        }

        textButton("Update Record",800,700,250,50,e -> {
            String name = nameTf.getText();
            String email = emailTf.getText();
            String prn_number = prnNumberTf.getText();
            long phone_number = Integer.parseInt(phoneNumberTf.getText());
            String deptName = departmentValue.getSelectedItem();
            int department = -1;

            for(DepartmentModel model: result) {
                if (model.name.equals(deptName)) {
                    department = model.id;
                    break;
                }
            }

            int id = Integer.parseInt(studentId.getSelectedItem());

            boolean ok = service.updateStudent(id,name,email,department,phone_number,prn_number);

            if (!ok) {
                JOptionPane.showMessageDialog(new JFrame(), "Something went wrong while updating record!");
                return;
            }

            JOptionPane.showMessageDialog(new JFrame(), "Record updated successfully!!");
            setVisible(false);
            new Students();
        });

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

        nameTf.setText(foundStudent.name);
        emailTf.setText(foundStudent.email);
        prnNumberTf.setText(foundStudent.prn_number);
        phoneNumberTf.setText(String.valueOf(foundStudent.phone_number));
        departmentValue.select(foundStudent.department);
    }

}
