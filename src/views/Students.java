package views;

import models.DepartmentModel;
import models.StudentModel;
import services.DepartmentService;
import services.StudentService;
import views.student_section.AddStudent;
import views.student_section.DeleteStudent;
import views.student_section.UpdateStudent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Students extends JFrame {

    public final StudentService service = new StudentService();

    public Students() {
        getContentPane().setBackground(Color.WHITE);

        setSize(1100,800);
        setVisible(true);
        setLocation(200,10);
        setLayout(null);
        setResizable(false);

        textButton("Back", 10, 10,150,45,e -> {
            setVisible(false);
            new Home();
        });

        JLabel homeHeadingLabel = new JLabel("Placement Management System", JLabel.CENTER);
        homeHeadingLabel.setFont(new Font("serif",Font.BOLD,36));
        homeHeadingLabel.setBounds(0, 25,1100,50);
        homeHeadingLabel.setForeground(Color.BLACK);
        add(homeHeadingLabel);

        JLabel studentSectionLabel = new JLabel("Student Section", JLabel.CENTER);
        studentSectionLabel.setFont(new Font("serif",Font.BOLD,32));
        studentSectionLabel.setBounds(0, 80,1100,50);
        studentSectionLabel.setForeground(Color.BLACK);
        add(studentSectionLabel);

        // add student btn
        textButton("Add Student", 300,200,250,50, e -> {
            setVisible(false);
            new AddStudent();
        });


        // show all records btn
        textButton("Show All Records",600,200,250,50,e -> {
            showStudentsRecords();
        });

        // delete button
        textButton("Delete Student",300,300,250,50, e -> {
            setVisible(false);
            new DeleteStudent();
        });


        // update button
        textButton("Update Student", 600,300,250,50, e -> {
            setVisible(false);
            new UpdateStudent();
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

    public void showStudentsRecords() {
        String [] colums = {"id","name","email","prn_number","phone_number","department"};

        ArrayList<StudentModel> result = service.findAll();

        if (result.size() == 0)
            return;

        String [][] data = new String[result.size()][colums.length];

        for(int i =0;i<result.size(); i++) {
            data[i] = result.get(i).getData();
        }

        JTable table = new JTable(data,colums);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,500,1100,300);
        scrollPane.getViewport().setBackground(Color.white);
        add(scrollPane);
    }

}
