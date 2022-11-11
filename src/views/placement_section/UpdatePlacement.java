package views.placement_section;

import models.CompanyModel;
import models.DepartmentModel;
import models.PlacementModel;
import services.CompanyService;
import services.PlacementService;
import views.Departments;
import views.Placements;
import views.Students;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UpdatePlacement extends JFrame {

    private final JTextField packageTf;
    private final DefaultComboBoxModel companyList;
    private final CompanyService companyService = new CompanyService();
    private final DefaultComboBoxModel typeList;
    private final JLabel studentNameVal;
    private final DefaultComboBoxModel placementList;
    private PlacementModel placement;
    private final PlacementService service = new PlacementService();

    public UpdatePlacement() {
        getContentPane().setBackground(Color.WHITE);

        setSize(1100,800);
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

        JLabel studentSectionLabel = new JLabel("Update a placement", JLabel.CENTER);
        studentSectionLabel.setFont(new Font("serif",Font.BOLD,32));
        studentSectionLabel.setBounds(0, 80,1100,50);
        studentSectionLabel.setForeground(Color.BLACK);
        add(studentSectionLabel);

        //  id
        JLabel placementIdLabel  = new JLabel("Placement Id");
        placementIdLabel.setBounds(100, 200, 200,50);
        placementIdLabel.setForeground(Color.BLACK);
        placementIdLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(placementIdLabel);

        placementList = new DefaultComboBoxModel();

        ArrayList<PlacementModel> result = service.findAll();
        for(PlacementModel e: result) {
            placementList.addElement(String.valueOf(e.id));
        }

        JComboBox placementId = new JComboBox(placementList);
        placementId.setBounds(320,200,450,50);
        placementId.addActionListener(e -> {
            showRecord();
        });
        add(placementId);


        //  name
        JLabel nameLabel  = new JLabel("Student Name:");
        nameLabel.setBounds(100, 300, 200,50);
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(nameLabel);

        studentNameVal  = new JLabel("");
        studentNameVal.setBounds(310, 300, 500,50);
        studentNameVal.setForeground(Color.BLACK);
        studentNameVal.setFont(new Font("serif",Font.PLAIN,22));
        add(studentNameVal);


        // package
        JLabel packageLabel = new JLabel("Package");
        packageLabel.setBounds(100, 400, 200, 50);
        packageLabel.setForeground(Color.BLACK);
        packageLabel.setFont(new Font("serif", Font.PLAIN, 22));
        add(packageLabel);

        packageTf = new JTextField();
        packageTf.setBounds(320, 400, 450, 50);
        add(packageTf);

        // company
        companyList = new DefaultComboBoxModel();

        ArrayList<CompanyModel> companies = companyService.findAll();

        for(CompanyModel e: companies) {
            companyList.addElement(e.name);
        }


        JLabel companyName  = new JLabel("Select Company");
        companyName.setBounds(100, 500, 200,50);
        companyName.setForeground(Color.BLACK);
        companyName.setFont(new Font("serif",Font.PLAIN,22));
        add(companyName);

        JComboBox selectedCompany = new JComboBox(companyList);
        selectedCompany.setBounds(320,500,450,50);
        add(selectedCompany);

        // placement type
        typeList = new DefaultComboBoxModel();

        String [] types = {"On Campus", "Off Campus"};

        for(String e: types) {
            typeList.addElement(e);
        }

        JLabel typeLabel  = new JLabel("Select Type");
        typeLabel.setBounds(100, 600, 200,50);
        typeLabel.setForeground(Color.BLACK);
        typeLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(typeLabel);

        JComboBox selectType = new JComboBox(typeList);
        selectType.setBounds(320,600,450,50);
        add(selectType);

        // insert button
        textButton("Update", 800,600,250,50, e -> {
            String company = companyList.getSelectedItem().toString();
            float getPackage = Float.parseFloat(packageTf.getText());
            String type = typeList.getSelectedItem().toString();
            int id = Integer.parseInt(String.valueOf(placementList.getSelectedItem()));

            boolean ok = service.updateById(id, type, getPackage, company);

            if (!ok) {
                JOptionPane.showMessageDialog(new JFrame(), "Something went wrong while updating record!");
                return;
            }

            JOptionPane.showMessageDialog(new JFrame(), "Record updated successfully!!");
            setVisible(false);
            new Placements();
        });

        showRecord();
    }

    private void showRecord() {
        placement = service.findById(Integer.parseInt(placementList.getSelectedItem().toString()));

        if (placement == null) {
            JOptionPane.showMessageDialog(new JFrame(), "No placement found with given id!");
            return;
        }

        companyList.setSelectedItem(placement.company);
        typeList.setSelectedItem(placement.type);
        packageTf.setText(String.valueOf(placement.totalPackage));
        studentNameVal.setText(placement.name);
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
