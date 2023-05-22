package Graphic;

import Logic.FileController;
import Module.College;
import Module.Position;
import Module.Teacher;
import Module.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class EditingTeacherPanel extends JPanel {

    Logger log = LogManager.getLogger(EditingTeacherPanel.class);

    User user;
    College college;
    Teacher teacher;

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


    JLabel label1 = new JLabel("یوزرنیم");
    JLabel label2 = new JLabel("پسوورد");
    JLabel label3 = new JLabel("نام");
    JLabel label4 = new JLabel("کد ملّی");
    JLabel label5 = new JLabel("eduID");
    JLabel label6 = new JLabel("شماره تماس");
    JLabel label7 = new JLabel("ایمیل");
    JLabel label8 = new JLabel("دانشکده");
    JLabel label9 = new JLabel("درجه استادی");
    JLabel label10 = new JLabel("سِمَت");
    JLabel label11 = new JLabel("شماره اتاق");

    JButton addButton = new JButton("ویرایش کن");

    JLabel info = new JLabel("استاد ویرایش شد");

    public EditingTeacherPanel(User user, Teacher teacher) {
        this.user = user;
        this.college = user.getCollege();
        this.teacher = teacher;
        initial();
        align();
        repaint();
        revalidate();
    }

    private void initial() {
        this.setBounds(0, 0, 1380, 950);
        this.setLayout(null);
        this.setBackground(Color.pink);
        listener();
    }

    private void align() {
        field1.setBounds(200, 50, 130, 30);
        field1.setText(teacher.getUsername());
        field2.setBounds(200, 100, 130, 30);
        field2.setText(teacher.getPassword());
        field3.setBounds(200, 150, 130, 30);
        field3.setText(teacher.getName());
        field4.setBounds(200, 200, 130, 30);
        field4.setText(teacher.getNationalId());
        field5.setBounds(200, 250, 130, 30);
        field5.setText(teacher.getEduId());
        field5.setEditable(false);
        field6.setBounds(200, 300, 130, 30);
        field6.setText(teacher.getPhoneNum());
        field7.setBounds(200, 350, 130, 30);
        field7.setText(teacher.getEmail());
        field8.setBounds(200, 400, 130, 30);
        field8.setText(college.toString());
        field8.setEditable(false);
        field9.setBounds(200, 450, 130, 30);
        field9.setText(teacher.getRank().toString());
        field10.setBounds(200, 500, 130, 30);
        field10.setText(teacher.getPosition().toString());
        field11.setBounds(200, 550, 130, 30);
        field11.setText(teacher.getRoomNum());


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

        addButton.setBounds(260, 680, 100, 40);

        info.setBounds(240, 730, 120, 60);
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

        this.add(addButton);
    }

    private void listener() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int cnt = 0;
                field10.setText(field10.getText().toUpperCase());
                for (Teacher t : FileController.getInstance().getTeachers()) {
                    if (field10.getText().equals(Position.ASSISTANT.toString()) && t.getCollege().equals(user.getCollege()) && t.getPosition().equals(Position.ASSISTANT) &&
                            !t.getEduId().equals(teacher.getEduId()))
                        cnt++;
                }
                field10.setText(field10.getText().toLowerCase());

                if (cnt == 0) {

                    log.info("user " + user.getUsername() + " edited teacher with number " + field5.getText());

                    FileController.getInstance().updateTeacher(field1.getText(), field2.getText(), field3.getText(),
                            field4.getText(), field5.getText(), field6.getText(), field7.getText(), field8.getText(),
                            field9.getText(), field10.getText(), field11.getText(), teacher.isTutor());
                    field1.setText("");
                    field2.setText("");
                    field4.setText("");
                    field6.setText("");
                    field7.setText("");
                    field3.setText("");
                    field9.setText("");
                    field10.setText("");
                    field11.setText("");

                    add(info);
                    repaint();
                    revalidate();
                }
            }
        });
    }

}
