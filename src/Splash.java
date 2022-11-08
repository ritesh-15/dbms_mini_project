import views.Home;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Splash extends JFrame implements ActionListener {
    public static void main(String[] args) {
        new Splash();
    }

    // #F6024A

    Splash() {
        ImageIcon bgImageIcon = new ImageIcon(ClassLoader.getSystemResource("img/splash.jpg"));
        Image bgImage = bgImageIcon.getImage().getScaledInstance(1100, 700, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(bgImage);
        JLabel image = new JLabel(icon);
        image.setBounds(50,100,1100,700);
        add(image);

        JLabel splashHeading = new JLabel("Welcome to Placement Management System", JLabel.CENTER);
        splashHeading.setFont(new Font("serif",Font.BOLD,42));
        splashHeading.setBounds(0, 50,1100,100);
        splashHeading.setForeground(Color.WHITE);
        image.add(splashHeading);

        JButton continueBtn = new JButton("Let's Continue");
        continueBtn.setBounds(425,550,250,50);
        continueBtn.setFont(new Font("serif", Font.PLAIN, 18));
        continueBtn.addActionListener(this);
        image.add(continueBtn);

        setSize(1100,700);
        setVisible(true);
        setLocation(200,50);
        setLayout(null);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new Home();
    }
}