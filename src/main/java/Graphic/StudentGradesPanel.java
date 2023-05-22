package Graphic;

import Logic.FileController;
import Module.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentGradesPanel extends JPanel {

    Logger log = LogManager.getLogger(StudentGradesPanel.class);

    User user;

    ArrayList<JTextField> fields = new ArrayList<>();
    ArrayList<JButton> buttons = new ArrayList<>();

    public StudentGradesPanel(User user) {
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
        int i = 0;

        JPanel panel11 = new JPanel();
        JPanel panel21 = new JPanel();
        JPanel panel31 = new JPanel();
        JPanel panel41= new JPanel();
        JPanel panel51 = new JPanel();

        JLabel label11 = new JLabel("شماره");
        JLabel label21 = new JLabel("نام درس");
        JLabel label31 = new JLabel("نمره");
        JLabel label41 = new JLabel("جوابیه استاد");
        JLabel label51 = new JLabel("اعتراض");

        panel11.setBackground(Color.white);
        panel21.setBackground(Color.white);
        panel31.setBackground(Color.white);
        panel41.setBackground(Color.white);
        panel51.setBackground(Color.white);

        panel11.setBounds(980, 60, 50, 30);
        panel21.setBounds(860, 60, 100, 30);
        panel31.setBounds(790, 60, 50, 30);
        panel41.setBounds(150, 60, 300, 30);
        panel51.setBounds(470, 60, 300, 30);

        panel11.add(label11);
        panel21.add(label21);
        panel31.add(label31);
        panel41.add(label41);
        panel51.add(label51);

        this.add(panel11);
        this.add(panel21);
        this.add(panel31);
        this.add(panel41);
        this.add(panel51);

        for (Grade g : user.getGrades()) {
            if (g.isTemporary() && g.getAmount() != null) {
                JPanel panel1 = new JPanel();
                JPanel panel2 = new JPanel();
                JPanel panel3 = new JPanel();
                JPanel panel4 = new JPanel();

                JLabel label1 = new JLabel(g.getLesson().getLessonNum());
                JLabel label2 = new JLabel(g.getLesson().getName());
                JLabel label3 = new JLabel(g.getAmount().toString());
                JLabel label4 = new JLabel(g.getRespond());

                JTextField objection = new JTextField(g.getObjection());

                JButton button = new JButton("ثبت");

                panel1.setBounds(980, (i*40)+100, 50, 30);
                panel2.setBounds(860, (i*40)+100, 100, 30);
                panel3.setBounds(790, (i*40)+100, 50, 30);
                panel4.setBounds(150, (i*40)+100, 300, 30);

                objection.setBounds(470, (i*40)+100, 300, 30);

                button.setBounds(50, (i*40)+100, 60, 30);

                panel1.add(label1);
                panel2.add(label2);
                panel3.add(label3);
                panel4.add(label4);

                this.add(panel1);
                this.add(panel2);
                this.add(panel3);
                this.add(panel4);
                this.add(objection);
                this.add(button);

                fields.add(objection);
                buttons.add(button);

                i++;
            }

            else {
                fields.add(new JTextField());
                buttons.add(new JButton());
            }

        }

    }

    private void listener() {
        for (JButton button : buttons) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int i = buttons.indexOf(button);
                    String object = fields.get(i).getText();
                    user.getGrades().get(i).setObjection(object);

                    log.info("Student " + user.getUsername() + "made an objection for lesson with number " +
                            user.getGrades().get(i).getLesson().getLessonNum());

                    FileController.getInstance().updateStudent(user);
                    fields.get(i).setText("");
                    repaint();
                    revalidate();
                }
            });
        }
    }

}
