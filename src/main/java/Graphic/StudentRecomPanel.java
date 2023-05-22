package Graphic;

import Logic.FileController;
import Module.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentRecomPanel extends JPanel {

    Logger log = LogManager.getLogger(StudentRecomPanel.class);

    User user;

    JTextField name = new JTextField();
    JButton send = new JButton("ارسال");

    public StudentRecomPanel(User user) {
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
        name.setBounds(1080, 20, 150, 40);
        send.setBounds(1000, 20, 70, 40);
        this.add(name);
        this.add(send);
    }

    private void align() {
        int i = 0;
        for (Recommendation r : FileController.getInstance().getRecoms()) {
            if (r.isConfirmed()) {
                String lessons = "";
                String grades = "";


                for (Grade g : user.getGrades()) {
                    if (g.getLesson().getTeacher().getEduId().equals(r.getTeacher().getEduId()) && !g.isTemporary()) {
                        lessons += g.getLesson().getName() + " , ";
                        grades += g.getAmount() + " , ";
                    }
                }
                if (!grades.equals("")) {

                JPanel panel11 = new JPanel();

                JLabel label11 = new JLabel(" اینجانب " + r.getTeacher().getName() +" گواهی می دهم آقا/خانم " +
                        user.getName() + " به شماره دانشجویی " + user.getEduId() + " درس های " + lessons +
                        " را با نمره " + grades + " گذرانده اند. ");

                panel11.setBounds(200, (i*50)+200, 900, 40);

                panel11.setBackground(Color.white);

                panel11.add(label11);

                this.add(panel11);

                i++;
                }
            }
        }

    }

    private void listener() {
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Teacher teacher = FileController.getInstance().teacherByName(name.getText());
                Recommendation recom = new Recommendation(user, teacher);
                log.info("Student " + user.getUsername() + " send a request for recommendation to " + teacher.getName());
                FileController.getInstance().addRecom(recom);
                name.setText("");
            }
        });
    }

}
