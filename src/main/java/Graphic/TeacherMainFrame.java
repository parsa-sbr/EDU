package Graphic;

import Logic.FileController;
import Module.Position;
import Module.Student;
import Module.Teacher;
import Module.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class TeacherMainFrame extends MainFrame {

    JPanel panel = new JPanel();

    JMenuItem lessons;
    JMenuItem teachers;

    JMenuItem daily;
    JMenuItem exams;
    JMenu adding;
    JMenuItem addStudent;
    JMenuItem addTeacher;


    JMenuItem req;

    JMenuItem grades;
    JMenuItem tempGrades;
    JMenuItem situation;

    JMenuItem prof;

    public TeacherMainFrame(User user) {
        super(user);
        initial(user);

        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //...Perform a task...
                log.info("session of user " + user.getUsername() + " got finished");
                end();
                user.setPassword("bannedqwertyui");
                FileController.getInstance().updateStudent(user);
                PopupPasswordFrame frame = new PopupPasswordFrame(user);
            }
        };
        Timer timer = new Timer(10800000 ,taskPerformer);
        timer.setRepeats(true);
        timer.start();

        repaint();
        revalidate();
    }

    private void initial(User user) {
        initMenu(user);
        action();
    }

    private void initMenu(User user) {

        lessons = new JMenuItem("لیست دروس");
        teachers = new JMenuItem("لیست اساتید");
        enrollment.add(lessons);
        enrollment.add(teachers);

        daily = new JMenuItem("برنامه هفتگی");
        exams =new JMenuItem("لیست امتحانات");
        adding = new JMenu("ثبت کاربر جدید");
        addTeacher = new JMenuItem("استاد");
        addStudent = new JMenuItem("دانشجو");
        adding.add(addTeacher);
        adding.add(addStudent);
        if (user.getPosition().equals(Position.ASSISTANT))
            educational.add(adding);
        educational.add(daily);
        educational.add(exams);

        req = new JMenuItem("درخواست ها");
        requests.add(req);


        tempGrades = new JMenuItem("نمرات موقت");
        report.add(tempGrades);
        if (user.getPosition().equals(Position.ASSISTANT)){
            grades = new JMenuItem("نمرات");
            report.add(grades);
            situation = new JMenuItem("وضعیت تحصیلی");
            report.add(situation);
        }


        prof = new JMenuItem("مشخصات کاربر");
        profile.add(prof);

    }

    private void action() {
        main.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
                TeacherMainFrame frame = new TeacherMainFrame(user);
            }
        });

        lessons.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(panel);
                try {
                    panel = new TeacherLessonsPanel(user);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                add(panel);
                repaint();
                revalidate();
            }
        });

        teachers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(panel);
                try {
                    panel = new TeacherTeachersPanel(user);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                add(panel);
                repaint();
                revalidate();
            }
        });

        if (user.getPosition().equals(Position.ASSISTANT)) {
            situation.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove(panel);

                    panel = new AssistantSituationPanel(user);

                    add(panel);
                    repaint();
                    revalidate();
                }
            });
        }

        tempGrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(panel);

                panel = new TeacherGradesPanel(user);

                add(panel);
                repaint();
                revalidate();
            }
        });

        if (user.getPosition().equals(Position.ASSISTANT)) {
            grades.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove(panel);

                    panel = new AssistantGradesPanel(user);

                    add(panel);
                    repaint();
                    revalidate();
                }
            });
        }

        if (user.getPosition().equals(Position.ASSISTANT)) {
            addTeacher.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove(panel);

                    panel = new AddingTeacherPanel(user);

                    add(panel);
                    repaint();
                    revalidate();
                }
            });
        }

        if (user.getPosition().equals(Position.ASSISTANT)) {
            addStudent.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove(panel);

                    panel = new AddingStudentPanel(user);

                    add(panel);
                    repaint();
                    revalidate();
                }
            });
        }

        prof.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(panel);

                panel = new TeacherProfilePanel(user);

                add(panel);
                repaint();
                revalidate();
            }
        });

        daily.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(panel);

                panel = new TeacherDailyPanel(user);

                add(panel);
                repaint();
                revalidate();
            }
        });

        exams.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(panel);

                panel = new TeacherExamsPanel(user);

                add(panel);
                repaint();
                revalidate();
            }
        });

        req.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(panel);

                panel = new TeacherRequestPanel(user);

                add(panel);
                repaint();
                revalidate();
            }
        });

    }
}
