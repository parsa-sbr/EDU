package Graphic;

import Logic.FileController;
import Module.College;
import Module.Position;
import Module.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddingStudentPanel extends JPanel {

    Logger log = LogManager.getLogger(AddingStudentPanel.class);

    User user;
    College college;

    JTextField field1 = new JTextField();
    JTextField field2 = new JTextField();
    JTextField field3 = new JTextField();
    JTextField field4 = new JTextField();
    JTextField field5 = new JTextField();
    JTextField field6 = new JTextField();
    JTextField field7 = new JTextField();
    JTextField field8 = new JTextField();
    JTextField field9 = new JTextField();
    JTextField field10 = new JTextField();
    JTextField field11 = new JTextField();
    JTextField field12 = new JTextField();
    JTextField field13 = new JTextField();


    JLabel label1 = new JLabel("یوزرنیم");
    JLabel label2 = new JLabel("پسوورد");
    JLabel label3 = new JLabel("نام");
    JLabel label4 = new JLabel("کد ملّی");
    JLabel label5 = new JLabel("eduID");
    JLabel label6 = new JLabel("شماره تماس");
    JLabel label7 = new JLabel("ایمیل");
    JLabel label8 = new JLabel("دانشکده");
    JLabel label9 = new JLabel("رشته");
    JLabel label10 = new JLabel("تاریخ ورود");
    JLabel label11 = new JLabel("استاد راهنما");
    JLabel label12 = new JLabel("مقطع");
    JLabel label13 = new JLabel("وضعیت تحصیلی");

    JButton addButton = new JButton("اضافه کن");

    JLabel info = new JLabel("دانشجو اضافه شد");

    public AddingStudentPanel(User user) {
        this.user = user;
        this.college = user.getCollege();
        initial();
        align();
        repaint();
        revalidate();
    }

    private void initial() {
        this.setBounds(0, 50, 1380, 950);
        this.setLayout(null);
        this.setBackground(Color.YELLOW);
        listener();
    }

    private void align() {
        field1.setBounds(200, 50, 130, 30);
        field2.setBounds(200, 100, 130, 30);
        field3.setBounds(200, 150, 130, 30);
        field4.setBounds(200, 200, 130, 30);
        field5.setBounds(200, 250, 130, 30);
        field6.setBounds(200, 300, 130, 30);
        field7.setBounds(200, 350, 130, 30);
        field8.setBounds(200, 400, 130, 30);
        field8.setText(college.toString());
        field8.setEditable(false);
        field9.setBounds(200, 450, 130, 30);
        field10.setBounds(200, 500, 130, 30);
        field11.setBounds(200, 550, 130, 30);
        field12.setBounds(200, 600, 130, 30);
        field13.setBounds(200, 650, 130, 30);

        label1.setBounds(340, 50, 100, 30);
        label2.setBounds(340, 100, 100, 30);
        label3.setBounds(340, 150, 100, 30);
        label4.setBounds(340, 200, 100, 30);
        label5.setBounds(340, 250, 100, 30);
        label6.setBounds(340, 300, 100, 30);
        label7.setBounds(340, 350, 100, 30);
        label8.setBounds(340, 400, 100, 30);
        label9.setBounds(340, 450, 100, 30);
        label10.setBounds(340, 500, 100, 30);
        label11.setBounds(340, 550, 100, 30);
        label12.setBounds(340, 600, 100, 30);
        label13.setBounds(340, 650, 100, 30);

        addButton.setBounds(260, 700, 100, 40);

        info.setBounds(240, 750, 120, 30);
        info.setForeground(Color.red);

        this.add(field1);
        this.add(field2);
        this.add(field3);
        this.add(field4);
        this.add(field5);
        this.add(field6);
        this.add(field7);
        this.add(field8);
        this.add(field9);
        this.add(field10);
        this.add(field11);
        this.add(field12);
        this.add(field13);

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

        this.add(addButton);
    }

    private void listener() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileController.getInstance().addStudent(field1.getText(), field2.getText(), field3.getText(),
                        field4.getText(), field5.getText(), field6.getText(), field7.getText(), field8.getText(),
                        field9.getText(), field10.getText(), field11.getText(), field12.getText(), field13.getText());
                field1.setText("");
                field2.setText("");
                field4.setText("");
                field5.setText("");
                field6.setText("");
                field7.setText("");
                field3.setText("");
                field9.setText("");
                field10.setText("");
                field11.setText("");
                field12.setText("");
                field13.setText("");

                add(info);
                repaint();
                revalidate();

                log.info("Student: " + label1.getText() + "added by " + user.getUsername());
            }
        });
    }

}
