package Graphic;

import Logic.FileController;
import Module.College;
import Module.Grade;
import Module.Minor;
import Module.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentMinorPanel extends JPanel {

    Logger log = LogManager.getLogger(StudentMinorPanel.class);

    User user;

    JTextField toCollege = new JTextField();
    JButton send = new JButton("درخواست");

    public StudentMinorPanel(User user) {
        this.user = user;
        initial();
        align();
        listener();
        repaint();
        revalidate();
    }

    private void initial() {
        this.setBackground(Color.lightGray);
        this.setBounds(0, 50, 1380, 950);
        this.setLayout(null);
    }

    private void align() {

        send.setBounds(1000, 30, 70, 40);
        toCollege.setBounds(1100, 30, 100, 40);

        this.add(send);
        this.add(toCollege);

        int i = 0;
        for (Minor m : FileController.getInstance().getMinors()) {
            JPanel panel1 = new JPanel();

            JLabel label1 = new JLabel();

            panel1.setBounds(800, (i*50)+200, 400, 40);
            if (m.isTeacherTo() != null && m.isTeacherFrom() != null) {
                if (m.getUser().getEduId().equals(user.getEduId()) && m.isTeacherFrom() && m.isTeacherTo()) {
                    label1.setText("درخواست شما برای ماینور به دانشکده " + m.getTo() + " قبول شد. ");
                    panel1.add(label1);

                    this.add(panel1);

                    i++;
                }
            }

        }

    }

    private void listener() {
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                College to = FileController.getInstance().collegeByName(toCollege.getText());
                Minor minor = new Minor(user, user.getColleges().get(0), to);
                log.info("Student " + user.getUsername() + "send a minor request for " + to + " college");
                FileController.getInstance().addMinor(minor);
                toCollege.setText("");
                repaint();
            }
        });
    }

}
