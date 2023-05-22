package Graphic;

import Module.Grade;
import Module.User;

import javax.swing.*;
import java.awt.*;

public class StudentSituationPanel extends JPanel {

    User user;

    public StudentSituationPanel(User user) {
        this.user = user;
        initial();
        alignStatics();
        align();
        repaint();
        revalidate();
    }

    private void initial() {
        this.setBackground(Color.lightGray);
        this.setBounds(0, 50, 1380, 950);
        this.setLayout(null);
    }

    private void alignStatics() {
        JLabel unit = new JLabel();
        JLabel finalGrade = new JLabel();

        int units = 0;
        for (Grade g : user.getGrades()) {
            if (!g.isTemporary()){
                units += g.getLesson().getNumberOfUnites();
            }
        }
        unit.setText("تعداد واحد های پاس شده:  " + units + " ");

        Double grades = 0.00;
        for (Grade g : user.getGrades()) {
            if (!g.isTemporary()){
                grades += g.getAmount() / units;
            }
        }
        finalGrade.setText("معدل کل:  " + grades + " ");

        unit.setBounds(1080, 50, 200, 30);
        finalGrade.setBounds(1080, 90, 200, 30);
        this.add(unit);
        this.add(finalGrade);

    }

    private void align() {

        JPanel panel11 = new JPanel();
        JPanel panel21 = new JPanel();
        JPanel panel31 = new JPanel();

        JLabel label11 = new JLabel("شماره");
        JLabel label21 = new JLabel("نام درس");
        JLabel label31 = new JLabel("نمره");

        panel11.setBackground(Color.white);
        panel21.setBackground(Color.white);
        panel31.setBackground(Color.white);

        panel11.setBounds(880, 50, 50, 30);
        panel21.setBounds(760, 50, 100, 30);
        panel31.setBounds(690, 50, 50, 30);

        panel11.add(label11);
        panel21.add(label21);
        panel31.add(label31);

        this.add(panel11);
        this.add(panel21);
        this.add(panel31);

        int i = 0;
        for (Grade g : user.getGrades()) {
            JPanel panel1 = new JPanel();
            JPanel panel2 = new JPanel();
            JPanel panel3 = new JPanel();

            JLabel label1 = new JLabel(g.getLesson().getLessonNum());
            JLabel label2 = new JLabel(g.getLesson().getName());
            JLabel label3 = new JLabel();
            if (g.isTemporary())
                label3.setText("N/A");
            else
                label3.setText(g.getAmount().toString());

            panel1.setBounds(880, (i*40)+100, 50, 30);
            panel2.setBounds(760, (i*40)+100, 100, 30);
            panel3.setBounds(690, (i*40)+100, 50, 30);

            panel1.add(label1);
            panel2.add(label2);
            panel3.add(label3);

            this.add(panel1);
            this.add(panel2);
            this.add(panel3);

            i++;
        }

    }

}
