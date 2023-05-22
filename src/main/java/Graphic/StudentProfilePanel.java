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

public class StudentProfilePanel extends JPanel {

    Logger log = LogManager.getLogger(StudentProfilePanel.class);

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
    JLabel label12 = new JLabel();
    JLabel label13 = new JLabel();
    JLabel label14 = new JLabel();

    JTextField email = new JTextField();
    JButton emailChange = new JButton();

    JTextField phone = new JTextField();
    JButton phoneChange = new JButton();



    public StudentProfilePanel(User user) {
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
        label5.setText("شماره دانشجویی:  " + user.getEduId());
        label6.setText("شماره تماس:  " + user.getPhoneNum());
        label7.setText("ایمیل:  " + user.getEmail());
        label8.setText("رشته:  " + user.getField());
        label9.setText("تاریخ ورود:  " + user.getDateOfEntry());
        label10.setText("معدل:  " + user.getGrade());
        label11.setText("استاد راهنما:  " + user.getTutor().getName());
        label12.setText("مقطع:  " + user.getType().toString());
        label14.setText("وضعیت تحصیلی:  " + user.getSituation().toString());

        email.setText(user.getEmail());
        emailChange.setText("ویرایش");

        phone.setText(user.getPhoneNum());
        phoneChange.setText("ویرایش");

        listener();
    }

    private void align() {
        
        String colleges = "";
        for (College c : user.getColleges()) {
            colleges += c.toString() + "  ";
        }
        label13.setText("دانشکده:  " + colleges);
        
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
        label12.setBounds(1180, 640, 200, 30);
        label13.setBounds(1180, 680, 200, 30);
        label14.setBounds(1180, 7200, 200, 30);
        
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
        this.add(label12);
        this.add(label13);
        this.add(label14);

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
                log.info("Student with username " + user.getUsername() + "changed their E-mail");
                FileController.getInstance().updateStudent(user);
            }
        });

        phoneChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.setPhoneNum(phone.getText());
                log.info("Student with username " + user.getUsername() + "changed their E-mail");
                FileController.getInstance().updateStudent(user);
            }
        });

    }

}
