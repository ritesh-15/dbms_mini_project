package views.placement_section;

import models.CompanyModel;
import models.StudentModel;
import services.CompanyService;
import services.PlacementService;
import services.StudentService;
import views.Departments;
import views.Placements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddPlacement extends JFrame {

    private final DefaultComboBoxModel studentList;
    private final StudentService studentService = new StudentService();
    private final DefaultComboBoxModel companyList;
    private final CompanyService companyService = new CompanyService();
    private final DefaultComboBoxModel typeList;
    private final PlacementService service = new PlacementService();

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
            studentList.addElement(e.prn_number);
        }

        JLabel studentLabel  = new JLabel("Select Student");
        studentLabel.setBounds(100, 300, 200,50);
        studentLabel.setForeground(Color.BLACK);
        studentLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(studentLabel);

        JComboBox selectedStudent = new JComboBox(studentList);
        selectedStudent.setBounds(320,300,450,50);
        add(selectedStudent);

        // company
        companyList = new DefaultComboBoxModel();

        ArrayList<CompanyModel> companies = companyService.findAll();

        for(CompanyModel e: companies) {
            companyList.addElement(e.name);
        }

        JLabel companyName  = new JLabel("Select Company");
        companyName.setBounds(100, 400, 200,50);
        companyName.setForeground(Color.BLACK);
        companyName.setFont(new Font("serif",Font.PLAIN,22));
        add(companyName);

        JComboBox selectedCompany = new JComboBox(companyList);
        selectedCompany.setBounds(320,400,450,50);
        add(selectedCompany);

        // placement type
        typeList = new DefaultComboBoxModel();

        String [] types = {"On Campus", "Off Campus"};

        for(String e: types) {
            typeList.addElement(e);
        }

        JLabel typeLabel  = new JLabel("Select Type");
        typeLabel.setBounds(100, 500, 200,50);
        typeLabel.setForeground(Color.BLACK);
        typeLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(typeLabel);

        JComboBox selectType = new JComboBox(typeList);
        selectType.setBounds(320,500,450,50);
        add(selectType);

        // insert button
        textButton("Add", 320,600,250,50, e -> {
            String prnNumber = studentList.getSelectedItem().toString();
            String company = companyList.getSelectedItem().toString();
            float getPackage = Float.parseFloat(packageTf.getText());
            String type = typeList.getSelectedItem().toString();

            boolean ok = service.insert(prnNumber, getPackage, company, type);

            if (!ok) {
                JOptionPane.showMessageDialog(new JFrame(), "Something went wrong while inserting record!!!");
                return;
            }

            JOptionPane.showMessageDialog(new JFrame(), "New record added successfully!");

            setVisible(false);
            new Placements();
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
