package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener {

    public Home() {
        getContentPane().setBackground(Color.WHITE);

        setSize(1100,700);
        setVisible(true);
        setLocation(200,50);
        setLayout(null);
        setResizable(false);

        JLabel homeHeadingLabel = new JLabel("Placement Management System", JLabel.CENTER);
        homeHeadingLabel.setFont(new Font("serif",Font.BOLD,36));
        homeHeadingLabel.setBounds(0, 25,1100,50);
        homeHeadingLabel.setForeground(Color.BLACK);
        add(homeHeadingLabel);

        // Students btn
        textButton("Students",300,200,250,50,e -> {
            setVisible(false);
            new Students();
        });

        // placements btn
        textButton("Placements", 600,200,250,50, null);

        // companies btn
        textButton("Companies",300,300,250,50,null);


        // departments btn
        textButton("Departments",600,300,250,50, e-> {
            setVisible(false);
            new Departments();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getID());
    }
}
