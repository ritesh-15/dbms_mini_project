package views.placement_section;

import models.CompanyModel;
import models.PlacementModel;
import services.CompanyService;
import services.PlacementService;
import views.Companies;
import views.Placements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeletePlacement extends JFrame {

    private final Choice placementId;
    private final JLabel studentNameVal;
    private final JLabel emailVal;
    private final JLabel prnNumberVal;
    private final JLabel companyVal;
    private final PlacementService service = new PlacementService();
    private final JLabel packageVal;
    private PlacementModel placement;

    public DeletePlacement() {
        getContentPane().setBackground(Color.WHITE);

        setSize(1100,900);
        setVisible(true);
        setLocation(200,10);
        setLayout(null);
        setResizable(false);

        textButton("Back", 10, 10,150,45,e -> {
            setVisible(false);
            new Companies();
        });

        JLabel homeHeadingLabel = new JLabel("Placement Management System", JLabel.CENTER);
        homeHeadingLabel.setFont(new Font("serif",Font.BOLD,36));
        homeHeadingLabel.setBounds(0, 25,1100,50);
        homeHeadingLabel.setForeground(Color.BLACK);
        add(homeHeadingLabel);

        JLabel studentSectionLabel = new JLabel("Delete a placement", JLabel.CENTER);
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

        placementId = new Choice();
        placementId.setBounds(320,200,450,150);
        placementId.addItemListener(e -> {
            showRecord();
        });
        add(placementId);

        ArrayList<PlacementModel> result = service.findAll();
        for(PlacementModel e: result) {
            placementId.add(String.valueOf(e.id));
        }

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

        //  Email
        JLabel emailLabel  = new JLabel("Student Email:");
        emailLabel.setBounds(100, 400, 200,50);
        emailLabel.setForeground(Color.BLACK);
        emailLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(emailLabel);

        emailVal  = new JLabel("");
        emailVal.setBounds(310, 400, 500,50);
        emailVal.setForeground(Color.BLACK);
        emailVal.setFont(new Font("serif",Font.PLAIN,22));
        add(emailVal);

        //  PRN number
        JLabel prnNumberLabel  = new JLabel("PRN Number:");
        prnNumberLabel.setBounds(100, 500, 200,50);
        prnNumberLabel.setForeground(Color.BLACK);
        prnNumberLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(prnNumberLabel);

        prnNumberVal  = new JLabel("");
        prnNumberVal.setBounds(310, 500, 500,50);
        prnNumberVal.setForeground(Color.BLACK);
        prnNumberVal.setFont(new Font("serif",Font.PLAIN,22));
        add(prnNumberVal);

        //  Company
        JLabel companyLabel  = new JLabel("Company:");
        companyLabel.setBounds(100, 600, 200,50);
        companyLabel.setForeground(Color.BLACK);
        companyLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(companyLabel);

        companyVal  = new JLabel("");
        companyVal.setBounds(310, 600, 500,50);
        companyVal.setForeground(Color.BLACK);
        companyVal.setFont(new Font("serif",Font.PLAIN,22));
        add(companyVal);

        //  package
        JLabel packageLabel  = new JLabel("Package:");
        packageLabel.setBounds(720, 300, 200,50);
        packageLabel.setForeground(Color.BLACK);
        packageLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(packageLabel);

        packageVal  = new JLabel("");
        packageVal.setBounds(930, 300, 200,50);
        packageVal.setForeground(Color.BLACK);
        packageVal.setFont(new Font("serif",Font.PLAIN,22));
        add(packageVal);

        textButton("Delete", 310,700,250,50, e-> {
            int id = Integer.parseInt(placementId.getSelectedItem());
            boolean ok = service.deleteById(id, placement.prn_number);

            if (ok) {
                JOptionPane.showMessageDialog(new JFrame(), "Record deleted successfully!!");
                setVisible(false);
                new Placements();
                return;
            }

            JOptionPane.showMessageDialog(new JFrame(), "Something went wrong while delete record!");
        });

        showRecord();
    }

    private void showRecord() {
        placement = service.findById(Integer.parseInt(placementId.getSelectedItem()));

        if (placement == null) {
            JOptionPane.showMessageDialog(new JFrame(), "No plcement found with given id!");
            return;
        }

        studentNameVal.setText(placement.name);
        emailVal.setText(placement.email);
        prnNumberVal.setText(placement.prn_number);
        companyVal.setText(placement.company);
        packageVal.setText(String.valueOf(placement.totalPackage));
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
