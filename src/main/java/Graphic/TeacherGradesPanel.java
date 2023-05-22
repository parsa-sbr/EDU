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

public class TeacherGradesPanel extends JPanel {

    Logger log = LogManager.getLogger(TeacherGradesPanel.class);

    User user;

    String lesson;

    int cnt = 0;

    ArrayList<JButton> lessonButtons = new ArrayList<>();

    JButton submit = new JButton("ذخیره");
    JButton finalSubmit = new JButton("ثبت نهایی");

    JLabel infoSubmit = new JLabel("ذخیره شد");
    JLabel finalInfo = new JLabel("با موفقیت ثبت شد رفرش کنید");

    ArrayList<JTextField> responds = new ArrayList<>();
    ArrayList<JTextField> grades = new ArrayList<>();

    public TeacherGradesPanel(User user) {
        this.user = user;
        initial();
        alignButtons();
        //listener2();
        listener();

        repaint();
        revalidate();
    }

    private void initial() {
        this.setBackground(Color.lightGray);
        this.setBounds(0, 50, 1380, 950);
        this.setLayout(null);
    }

    private void alignButtons() {

        if (cnt == 0) {
            for (Lesson l : FileController.getInstance().getLessons()) {
                if (l.getTeacher().getEduId().equals(user.getEduId())) {
                    JButton button = new JButton(l.getName());
                    lessonButtons.add(button);
                }
            }
        }

        for (int i = 0; i < lessonButtons.size(); i++) {
            lessonButtons.get(i).setBounds(1280, (i * 50) + 20, 90, 30);
            this.add(lessonButtons.get(i));
        }

        cnt++;

    }

    private void align(ArrayList<Student> students, String name) {
        JPanel panel11 = new JPanel();
        JPanel panel21 = new JPanel();
        JPanel panel31 = new JPanel();
        JPanel panel41= new JPanel();
        JPanel panel51 = new JPanel();

        JLabel label11 = new JLabel("شماره");
        JLabel label21 = new JLabel("نام دانشجو");
        JLabel label31 = new JLabel("نمره");
        JLabel label41 = new JLabel("جوابیه استاد");
        JLabel label51 = new JLabel("اعتراض");

        submit.setBounds(20, 20, 100, 30);
        finalSubmit.setBounds(120, 20, 150, 30);

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

        infoSubmit.setBounds(300, 20, 100, 30);
        finalInfo.setBounds(300, 20, 100, 30);

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
        this.add(submit);
        this.add(finalSubmit);


        int i = 0;
        for (Student s : students) {
            for (Grade g : s.getGrades()) {
                if (g.getLesson().getName().equals(name) && (g.isTemporary())) {
                    JPanel panel1 = new JPanel();
                    JPanel panel2 = new JPanel();
                    JPanel panel5 = new JPanel();

                    JLabel label1 = new JLabel(s.getEduId());
                    JLabel label2 = new JLabel(s.getName());
                    JLabel label5 = new JLabel(g.getObjection());

                    JTextField grade = new JTextField();
                    if (g.getAmount() != null)
                        grade.setText(g.getAmount().toString());

                    JTextField respond = new JTextField(g.getRespond());

                    panel1.setBounds(980, (i*40)+100, 50, 30);
                    panel2.setBounds(860, (i*40)+100, 100, 30);
                    grade.setBounds(790, (i*40)+100, 50, 30);
                    panel5.setBounds(470, (i*40)+100, 300, 30);

                    respond.setBounds(150, (i*40)+100, 300, 30);

                    panel1.add(label1);
                    panel2.add(label2);
                    panel5.add(label5);

                    this.add(panel1);
                    this.add(panel2);
                    this.add(grade);
                    this.add(panel5);
                    this.add(respond);

                    responds.add(respond);
                    grades.add(grade);

                    i++;
                }
            }
        }
    }

    private ArrayList<Student> findStudent(String name) {
        ArrayList<Student> students = new ArrayList<>();
        for (Student s : FileController.getInstance().getStudents()) {
            for (Grade g : s.getGrades()) {
                if (g.getLesson().getName().equals(name)) {
                    students.add(s);
                }
            }
        }
        return students;
    }

    private void listener() {
        for (JButton lessonButton : lessonButtons) {
            lessonButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    lesson = lessonButton.getText();
                    ArrayList<Student> students = findStudent(lesson);
                    removeAll();
                    alignButtons();
                    align(students, lessonButton.getText());
                    repaint();
                    revalidate();
                }
            });
        }

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = true;
                for (JTextField f : grades) {
                    if (Double.parseDouble(f.getText()) > 20.00 || Double.parseDouble(f.getText()) < 0.00)
                        flag = false;
                }

                if (flag) {
                    if (!grades.contains(null)) {
                        log.info("Teacher " + user.getUsername() + "submitted grades for lesson " + lesson);
                        for (int i = 0; i < findStudent(lesson).size(); i++) {
                            Student s = findStudent(lesson).get(i);
                            for (Grade g : s.getGrades()) {
                                if (g.getLesson().getName().equals(lesson)) {
                                    g.setAmount(Double.parseDouble(grades.get(i).getText()));
                                    g.setRespond(responds.get(i).getText());
                                }
                            }


                            FileController.getInstance().updateStudent(s);
                        }
                        remove(finalInfo);
                        add(infoSubmit);
                        repaint();
                        revalidate();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Enter valid grades!",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        finalSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!grades.contains(null)) {
                    log.info("Teacher " + user.getUsername() + "finalized grades for lesson " + lesson);
                    for (int i = 0; i < findStudent(lesson).size(); i++) {
                        Student s = findStudent(lesson).get(i);
                        for (Grade g : s.getGrades()) {
                            if (g.getLesson().getName().equals(lesson)) {
                                g.setTemporary(false);
                            }
                        }
                        FileController.getInstance().updateStudent(s);
                    }
                    remove(infoSubmit);
                    add(finalInfo);
                    repaint();
                    revalidate();
                }
            }
        });

    }


}
