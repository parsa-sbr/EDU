package Graphic;

import Logic.FileController;
import Module.Grade;
import Module.Lesson;
import Module.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class TeacherExamsPanel extends JPanel {

    User user;

    public TeacherExamsPanel(User user) {
        this.user = user;
        initial();
        align();
        repaint();
        revalidate();
    }

    private void initial() {
        this.setBackground(Color.lightGray);
        this.setBounds(0, 50, 1380, 950);
        this.setLayout(null);
    }

    private void align() {

        JPanel panel11 = new JPanel();
        JPanel panel31 = new JPanel();

        JLabel label11 = new JLabel("نام درس");
        JLabel label31 = new JLabel("زمان امتحان");

        panel11.setBounds(1180, 150, 100, 40);
        panel31.setBounds(1020, 150, 200, 40);

        panel11.setBackground(Color.white);
        panel31.setBackground(Color.white);

        panel11.add(label11);
        panel31.add(label31);

        this.add(panel11);
        this.add(panel31);

        int i = 0;

        ArrayList<Lesson> lessons = FileController.getInstance().getLessons();

        Collections.sort(lessons, new SortByDate());

        for (Lesson l : lessons) {
            if (l.getTeacher().getEduId().equals(user.getEduId())) {
                JPanel panel1 = new JPanel();
                JPanel panel3 = new JPanel();

                JLabel label1 = new JLabel(l.getName());
                JLabel label3 = new JLabel("1400.04." + l.getDateOfExam());

                panel1.setBounds(1180, (i * 50) + 200, 100, 40);
                panel3.setBounds(1020, (i * 50) + 200, 200, 40);

                panel1.add(label1);
                panel3.add(label3);

                this.add(panel1);
                this.add(panel3);

                i++;
            }
        }

    }

}
