package Graphic;

import Logic.FileController;
import Module.College;
import Module.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherProfilePanel extends JPanel {

    Logger log = LogManager.getLogger(TeacherProfilePanel.class);

    User user;

    JLabel label1 = new JLabel();
    JLabel label2 = new JLabel();
    JLabel label3 = new JLabel();
    JLabel label4 = new JLabel();
    JLabel label5 = new JLabel();
    JLabel label6 = new JLabel();
    JLabel label7 = new JLabel();
    JLabel label8 = new JLabel();
    JLabel label9 = new JLabel();
    JLabel label10 = new JLabel();
    JLabel label11 = new JLabel();

    JTextField email = new JTextField();
    JButton emailChange = new JButton();

    JTextField phone = new JTextField();
    JButton phoneChange = new JButton();

    public TeacherProfilePanel(User user) {
        this.user = user;
        initial();
        align();
        repaint();
        revalidate();
    }

    private void initial() {
        this.setBounds(0, 0, 1380, 950);
        this.setLayout(null);
        this.setBackground(Color.white);

        label1.setText("نام کاربری:  " + user.getUsername());
        label2.setText("رمزعبور:  " + user.getPassword());
        label3.setText("نام و نام خانوادگی:  " + user.getName());
        label4.setText("کد ملّی:  " + user.getNationalId());
        label5.setText("شماره استادی:  " + user.getEduId());
        label6.setText("شماره تماس:  " + user.getPhoneNum());
        label7.setText("ایمیل:  " + user.getEmail());
        label8.setText("دانشکده:  " + user.getCollege().toString());
        label9.setText("درجه استادی:  " + user.getRank().toString());
        label10.setText("سِمَت:  " + user.getPosition().toString());
        label11.setText("شماره اتاق:  " + user.getRoomNum());

        email.setText(user.getEmail());
        emailChange.setText("ویرایش");

        phone.setText(user.getPhoneNum());
        phoneChange.setText("ویرایش");

        listener();
    }

    private void align() {

        label1.setBounds(1180, 200, 200, 30);
        label2.setBounds(1180, 240, 200, 30);
        label3.setBounds(1180, 280, 200, 30);
        label4.setBounds(1180, 320, 200, 30);
        label5.setBounds(1180, 360, 200, 30);
        label6.setBounds(1180, 400, 200, 30);
        label7.setBounds(1180, 440, 200, 30);
        label8.setBounds(1180, 480, 200, 30);
        label9.setBounds(1180, 520, 200, 30);
        label10.setBounds(1180, 560, 200, 30);
        label11.setBounds(1180, 600, 200, 30);

        email.setBounds(500, 300, 150, 30);
        emailChange.setBounds(570, 350, 70, 30);

        phone.setBounds(500, 400, 150, 30);
        phoneChange.setBounds(570, 450, 70, 30);

        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(label4);
        this.add(label5);
        this.add(label6);
        this.add(label7);
        this.add(label8);
        this.add(label9);
        this.add(label10);
        this.add(label11);

        this.add(email);
        this.add(emailChange);
        this.add(phone);
        this.add(phoneChange);

    }

    private void listener() {

        emailChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.setEmail(email.getText());
                log.info("Teacher with username " + user.getUsername() + "changed their E-mail");
                FileController.getInstance().editTeacher(user);
            }
        });

        phoneChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.setPhoneNum(phone.getText());
                log.info("Teacher with username " + user.getUsername() + "changed their phone number");
                FileController.getInstance().editTeacher(user);
            }
        });

    }

}
