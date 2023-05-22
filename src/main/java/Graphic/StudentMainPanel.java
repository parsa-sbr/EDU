package Graphic;

import Module.*;

import javax.swing.*;
import java.awt.*;

public class StudentMainPanel extends JPanel {

    User user;

    JPanel panel11 = new JPanel();
    JPanel panel12 = new JPanel();
    JPanel panel21 = new JPanel();
    JPanel panel22 = new JPanel();
    JPanel panel31 = new JPanel();
    JPanel panel32 = new JPanel();
    JPanel panel41 = new JPanel();
    JPanel panel42 = new JPanel();




    public StudentMainPanel(User user) {
        this.user = user;
        initial();
        align(user);
        repaint();
        revalidate();

    }

    private void initial() {
        this.setBackground(Color.lightGray);
        this.setBounds(0, 50, 1380, 950);
        this.setLayout(null);

    }

    private void align(User user) {
        panel11.setBackground(Color.pink);
        panel11.setBounds(1100, 150, 100, 20);
        panel12.setBackground(Color.pink);
        panel12.setBounds(780, 150, 300, 20);

        panel21.setBackground(Color.pink);
        panel21.setBounds(1100, 180, 100, 20);
        panel22.setBackground(Color.pink);
        panel22.setBounds(780, 180, 300, 20);

        panel31.setBackground(Color.pink);
        panel31.setBounds(1100, 210, 100, 20);
        panel32.setBackground(Color.pink);
        panel32.setBounds(780, 210, 300, 20);

        panel41.setBackground(Color.pink);
        panel41.setBounds(1100, 240, 100, 20);
        panel42.setBackground(Color.pink);
        panel42.setBounds(780, 240, 300, 20);

        this.add(panel11);
        this.add(panel12);
        this.add(panel21);
        this.add(panel22);
        this.add(panel31);
        this.add(panel32);
        this.add(panel41);
        this.add(panel42);

        JLabel jLabel11 = new JLabel("وضعیت تحصیلی");
        JLabel jLabel12 = new JLabel();
        JLabel jLabel21 = new JLabel("استاد راهنما");
        JLabel jLabel22 = new JLabel();
        if (user.getTutor() != null)
            jLabel22.setText("استاد راهنمای شما " + user.getTutor().getName() + " است");
        else
            jLabel22.setText("نامشخص");
        JLabel jLabel31 = new JLabel("مجوز ثبت نام");
        JLabel jLabel32 = new JLabel("مجوز ثبت نام شما توسط استاد راهنما ثبت شده است");
        JLabel jLabel41 = new JLabel("ساعت ثبت نام");
        JLabel jLabel42 = new JLabel( "مهلت ثبت نام شما "+ user.getTimeToEnroll() + " تا پایان زمان تعیین شده است");

        panel11.add(jLabel11);
        switch (user.getSituation()) {
            case STUDYING:
                jLabel12.setText("در حال تحصیل");
                break;
            case GRADUATE:
                jLabel12.setText("فارغ التحصیل");
                break;
            default:
                jLabel12.setText("انصراف از تحصیل");
        }
        panel12.add(jLabel12);
        panel21.add(jLabel21);
        panel22.add(jLabel22);
        panel31.add(jLabel31);
        panel32.add(jLabel32);
        panel41.add(jLabel41);
        panel42.add(jLabel42);
    }

}
