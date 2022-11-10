package views.comapny_section;

import models.CompanyModel;
import models.DepartmentModel;
import services.CompanyService;
import views.Companies;
import views.Departments;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeleteCompany extends JFrame {

    private final Choice companyId;
    private final JLabel intakeVal;

    private JLabel nameVal;
    private final CompanyService service = new CompanyService();

    public DeleteCompany() {
        getContentPane().setBackground(Color.WHITE);

        setSize(1100,700);
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

        JLabel studentSectionLabel = new JLabel("Delete a company", JLabel.CENTER);
        studentSectionLabel.setFont(new Font("serif",Font.BOLD,32));
        studentSectionLabel.setBounds(0, 80,1100,50);
        studentSectionLabel.setForeground(Color.BLACK);
        add(studentSectionLabel);

        //  id
        JLabel studentIdLabel  = new JLabel("Company Id");
        studentIdLabel.setBounds(100, 200, 200,50);
        studentIdLabel.setForeground(Color.BLACK);
        studentIdLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(studentIdLabel);

        companyId = new Choice();
        companyId.setBounds(320,200,450,150);
        companyId.addItemListener(e -> {
            showRecord();
        });
        add(companyId);

        ArrayList<CompanyModel> result = service.findAll();
        for(CompanyModel e: result) {
            companyId.add(String.valueOf(e.id));
        }

        //  name
        JLabel nameLabel  = new JLabel("Company Name:");
        nameLabel.setBounds(100, 300, 200,50);
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(nameLabel);

        nameVal  = new JLabel("");
        nameVal.setBounds(310, 300, 200,50);
        nameVal.setForeground(Color.BLACK);
        nameVal.setFont(new Font("serif",Font.PLAIN,28));
        add(nameVal);

        // intake
        JLabel intakeLabel  = new JLabel("Company Intake:");
        intakeLabel.setBounds(100, 400, 200,50);
        intakeLabel.setForeground(Color.BLACK);
        intakeLabel.setFont(new Font("serif",Font.PLAIN,22));
        add(intakeLabel);

        intakeVal  = new JLabel("");
        intakeVal.setBounds(310, 400, 200,50);
        intakeVal.setForeground(Color.BLACK);
        intakeVal.setFont(new Font("serif",Font.PLAIN,28));
        add(intakeVal);

        textButton("Delete", 310,500,200,50,e -> {
            int id = Integer.parseInt(companyId.getSelectedItem());
            boolean ok = service.deleteById(id);

            if (ok) {
                JOptionPane.showMessageDialog(new JFrame(), "Record deleted successfully!!");
                setVisible(false);
                new Companies();
                return;
            }

            JOptionPane.showMessageDialog(new JFrame(), "Something went wrong while delete record!");
        });

        showRecord();
    }

    private void showRecord() {
        CompanyModel company = service.findById(Integer.parseInt(companyId.getSelectedItem()));

        if (company == null) {
            JOptionPane.showMessageDialog(new JFrame(), "No company found with given id!");
            return;
        }

        nameVal.setText(company.name);
        intakeVal.setText(String.valueOf(company.intake));
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
