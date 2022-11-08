package views.department_section;

import models.DepartmentModel;
import services.DepartmentService;
import views.Departments;
import views.Students;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeleteDepartment extends JFrame {

    private final Choice departmentId;
    private JLabel nameVal;
    private DepartmentService service = new DepartmentService();

    public DeleteDepartment() {
        getContentPane().setBackground(Color.WHITE);

        setSize(1100,500);
        setVisible(true);
        setLocation(200,10);
        setLayout(null);
        setResizable(false);

        textButton("Back", 10, 10,150,45,e -> {
            setVisible(false);
            new Departments();
        });

        JLabel homeHeadingLabel = new JLabel("Placement Management System", JLabel.CENTER);
        homeHeadingLabel.setFont(new Font("serif",Font.BOLD,36));
        homeHeadingLabel.setBounds(0, 25,1100,50);
        homeHeadingLabel.setForeground(Color.BLACK);
        add(homeHeadingLabel);

        JLabel studentSectionLabel = new JLabel("Delete a department", JLabel.CENTER);
        studentSectionLabel.setFont(new Font("serif",Font.BOLD,32));
        studentSectionLabel.setBounds(0, 80,1100,50);
        studentSectionLabel.setForeground(Color.BLACK);
        add(studentSectionLabel);

        // department id
        JLabel studentIdLabel  = new JLabel("Student Id");
        studentIdLabel.setBounds(100, 200, 200,50);
        studentSectionLabel.setForeground(Color.BLACK);
        studentSectionLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(studentIdLabel);

        departmentId = new Choice();
        departmentId.setBounds(320,200,450,150);
        departmentId.addItemListener(e -> {
            showRecord();
        });
        add(departmentId);

        ArrayList<DepartmentModel> result = service.findAllDepartments();
        for(DepartmentModel e: result) {
            departmentId.add(String.valueOf(e.id));
        }

        // department name
        JLabel nameLabel  = new JLabel("Department Name:");
        nameLabel.setBounds(100, 300, 200,50);
        studentSectionLabel.setForeground(Color.BLACK);
        studentSectionLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(nameLabel);

        nameVal  = new JLabel("");
        nameVal.setBounds(310, 300, 200,50);
        studentSectionLabel.setForeground(Color.BLACK);
        studentSectionLabel.setFont(new Font("serif",Font.PLAIN,28));
        add(nameVal);

        textButton("Delete", 310,400,200,50,e -> {
            int id = Integer.parseInt(departmentId.getSelectedItem());
            boolean ok = service.deleteById(id);

            if (ok) {
                JOptionPane.showMessageDialog(new JFrame(), "Record deleted successfully!!");
                setVisible(false);
                new Departments();
                return;
            }

            JOptionPane.showMessageDialog(new JFrame(), "Something went wrong while delete record!");
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
        DepartmentModel department = service.findById(Integer.parseInt(departmentId.getSelectedItem()));

        if (department == null) {
            JOptionPane.showMessageDialog(new JFrame(), "No department found with given id!");
            return;
        }

        nameVal.setText(department.name);
    }
}
