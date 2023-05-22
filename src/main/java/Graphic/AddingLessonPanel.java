package Graphic;

import Logic.FileController;
import Module.College;
import Module.Lesson;
import Module.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddingLessonPanel extends JPanel {

    Logger log = LogManager.getLogger(AddingLessonPanel.class);

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

    JLabel label1 = new JLabel("نام درس");
    JLabel label2 = new JLabel("استاد درس");
    JLabel label3 = new JLabel("دانشکده");
    JLabel label4 = new JLabel("زمان کلاس");
    JLabel label5 = new JLabel("تعداد واحد");
    JLabel label6 = new JLabel("تاریخ امتحان");
    JLabel label7 = new JLabel("شماره درس");
    JLabel label8 = new JLabel("مقطع درس");

    JButton addButton = new JButton("اضافه کن");

    JLabel info = new JLabel("درس اضافه شد");


    public AddingLessonPanel(User user) {
        this.user = user;
        this.college = user.getCollege();
        initial();
        align();
        repaint();
        revalidate();
    }

    private void initial() {
        this.setBounds(0, 0, 1380, 950);
        this.setLayout(null);
        this.setBackground(Color.YELLOW);
        listener();
    }

    private void align() {
        field1.setBounds(200, 50, 130, 30);
        field2.setBounds(200, 100, 130, 30);
        field3.setBounds(200, 150, 130, 30);
        field3.setText(college.toString());
        field3.setEditable(false);
        field4.setBounds(200, 200, 130, 30);
        field5.setBounds(200, 250, 130, 30);
        field6.setBounds(200, 300, 130, 30);
        field7.setBounds(200, 350, 130, 30);
        field8.setBounds(200, 400, 130, 30);

        label1.setBounds(340, 50, 100, 30);
        label2.setBounds(340, 100, 100, 30);
        label3.setBounds(340, 150, 100, 30);
        label4.setBounds(340, 200, 100, 30);
        label5.setBounds(340, 250, 100, 30);
        label6.setBounds(340, 300, 100, 30);
        label7.setBounds(340, 350, 100, 30);
        label8.setBounds(340, 400, 100, 30);

        addButton.setBounds(260, 460, 100, 40);

        info.setBounds(240, 510, 120, 60);
        info.setForeground(Color.red);

        this.add(field1);
        this.add(field2);
        this.add(field3);
        this.add(field4);
        this.add(field5);
        this.add(field6);
        this.add(field7);
        this.add(field8);

        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(label4);
        this.add(label5);
        this.add(label6);
        this.add(label7);
        this.add(label8);

        this.add(addButton);

    }

    private void meh() {
        this.add(info);
        repaint();
        revalidate();
    }

    private void listener() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileController.getInstance().addLesson(field1.getText(), field2.getText(), college.toString(),
                        field4.getText(), field5.getText(), field6.getText(), field7.getText(), field8.getText());
                field1.setText("");
                field2.setText("");
                field4.setText("");
                field5.setText("");
                field6.setText("");
                field7.setText("");
                field8.setText("");
                meh();

                log.info("lesson: " + label1.getText() + " added by " + user.getUsername());
            }
        });
    }

}
