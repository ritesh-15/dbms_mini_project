package views.comapny_section;

import services.CompanyService;
import services.Database;
import views.Companies;
import views.Students;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddCompany extends JFrame {

    private final CompanyService service = new CompanyService();

    public AddCompany() {
        getContentPane().setBackground(Color.WHITE);

        setSize(1100, 800);
        setVisible(true);
        setLocation(200, 10);
        setLayout(null);
        setResizable(false);

        textButton("Back", 10, 10, 150, 45, e -> {
            setVisible(false);
            new Companies();
        });

        JLabel homeHeadingLabel = new JLabel("Placement Management System", JLabel.CENTER);
        homeHeadingLabel.setFont(new Font("serif", Font.BOLD, 36));
        homeHeadingLabel.setBounds(0, 25, 1100, 50);
        homeHeadingLabel.setForeground(Color.BLACK);
        add(homeHeadingLabel);

        JLabel studentSectionLabel = new JLabel("Add new company", JLabel.CENTER);
        studentSectionLabel.setFont(new Font("serif", Font.BOLD, 32));
        studentSectionLabel.setBounds(0, 80, 1100, 50);
        studentSectionLabel.setForeground(Color.BLACK);
        add(studentSectionLabel);

        // Name of company
        JLabel nameLabel = new JLabel("Company Name");
        nameLabel.setBounds(100, 200, 200, 50);
        studentSectionLabel.setForeground(Color.BLACK);
        studentSectionLabel.setFont(new Font("serif", Font.PLAIN, 22));
        add(nameLabel);

        JTextField nameTf = new JTextField();
        nameTf.setBounds(320, 200, 450, 50);
        add(nameTf);

        // intake
        JLabel intakeLabel = new JLabel("Total Intake");
        intakeLabel.setBounds(100, 300, 200, 50);
        intakeLabel.setForeground(Color.BLACK);
        intakeLabel.setFont(new Font("serif", Font.PLAIN, 22));
        add(intakeLabel);

        JTextField intakeTf = new JTextField();
        intakeTf.setBounds(320, 300, 450, 50);
        add(intakeTf);

        // add company button
        textButton("Add company", 100, 400, 250, 50, e -> {
            String name = nameTf.getText();
            int intake = Integer.parseInt(intakeTf.getText());

            boolean ok = service.insert(name, intake);

            if (!ok) {
                JOptionPane.showMessageDialog(new JFrame(), "Something went wrong while inserting record!!!");
                return;
            }

            JOptionPane.showMessageDialog(new JFrame(), "New record added successfully!");

            setVisible(false);
            new Companies();
        });
    }

    public void textButton(String title, int x, int y, int width, int height, ActionListener actionListener) {
        JButton showRecords = new JButton(title);
        showRecords.setBounds(x, y, width, height);
        showRecords.setFont(new Font("serif", Font.PLAIN, 18));
        showRecords.setBackground(Color.BLACK);
        showRecords.setForeground(Color.white);
        showRecords.addActionListener(actionListener);
        add(showRecords);
    }
}
