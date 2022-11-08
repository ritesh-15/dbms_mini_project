package views;

import models.DepartmentModel;
import models.StudentModel;
import services.DepartmentService;
import services.StudentService;
import views.department_section.AddDepartment;
import views.department_section.DeleteDepartment;
import views.department_section.UpdateDepartment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Departments extends JFrame {

    public final DepartmentService service = new DepartmentService();
    public Departments() {
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

        JLabel studentSectionLabel = new JLabel("Department Section", JLabel.CENTER);
        studentSectionLabel.setFont(new Font("serif",Font.BOLD,32));
        studentSectionLabel.setBounds(0, 80,1100,50);
        studentSectionLabel.setForeground(Color.BLACK);
        add(studentSectionLabel);

        // aad department
        textButton("Add Department", 300,200,250,50, e -> {
            setVisible(false);
            new AddDepartment();
        });

        // show all departments
        textButton("Show All Departments",600,200,250,50,e -> {
            showStudentsRecords();
        });

        // delete department
        textButton("Delete Department",300,300,250,50,e -> {
            setVisible(false);
            new DeleteDepartment();
        });

        // update department
        textButton("Update Department",600,300,250,50,e -> {
            setVisible(false);
            new UpdateDepartment();
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
        String [] colums = {"id","name"};

        ArrayList<DepartmentModel> result = service.findAllDepartments();

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
