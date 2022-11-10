package views;

import views.placement_section.AddPlacement;
import views.student_section.AddStudent;
import views.student_section.DeleteStudent;
import views.student_section.UpdateStudent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Placements extends JFrame {

    public Placements() {
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

        JLabel studentSectionLabel = new JLabel("Placements Section", JLabel.CENTER);
        studentSectionLabel.setFont(new Font("serif",Font.BOLD,32));
        studentSectionLabel.setBounds(0, 80,1100,50);
        studentSectionLabel.setForeground(Color.BLACK);
        add(studentSectionLabel);

        // add student btn
        textButton("New Placement", 300,200,250,50, e -> {
            setVisible(false);
            new AddPlacement();
        });


        // show all records btn
        textButton("Show All Records",600,200,250,50,e -> {
        });

        // delete button
        textButton("Delete Placement",300,300,250,50, e -> {
            setVisible(false);
        });


        // update button
        textButton("Update Placement", 600,300,250,50, e -> {
            setVisible(false);
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
