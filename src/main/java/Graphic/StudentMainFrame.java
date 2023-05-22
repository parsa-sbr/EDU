package Graphic;

import Logic.FileController;
import Module.*;
import Module.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class StudentMainFrame extends MainFrame {

    Logger log = LogManager.getLogger(StudentMainFrame.class);

    protected JPanel panel = new StudentMainPanel(user);
    JPanel studyPanel = new JPanel();
    JLabel studyLabel = new JLabel();

    JMenuItem lessons;
    JMenuItem teachers;

    JMenuItem daily;
    JMenuItem exams;
    JMenuItem recommendation;
    JMenuItem study;
    JMenuItem minor;
    JMenuItem resign;
    JMenuItem defend;
    JMenuItem dorm;

    JMenuItem tempGrades;
    JMenuItem situation;

    JMenuItem prof;

    public StudentMainFrame(User user) {
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
        Timer timer = new Timer(60000 ,taskPerformer);
        timer.setRepeats(true);
        timer.start();

        repaint();
        revalidate();
    }

    private void initial(User user) {
        this.add(panel);
        initMenu(user);
        action();
    }


    private void initMenu(User user) {
        lessons = new JMenuItem("لیست دروس");
        teachers = new JMenuItem("لیست اساتید");
        enrollment.add(lessons);
        enrollment.add(teachers);

        daily = new JMenuItem("برنامه درسی");
        exams = new JMenuItem("لیست امتحانات");
        recommendation = new JMenuItem("توصیه نامه");
        study = new JMenuItem("اشتغال به تحصیل");
        minor = new JMenuItem("ماینور");
        resign = new JMenuItem("انصراف از تحصیل");
        dorm = new JMenuItem("خوابگاه");
        defend = new JMenuItem("نوبت دفاع");
        requests.add(study);
        requests.add(resign);
        switch (user.getType()) {
            case BACHELORS:
                requests.add(recommendation);
                requests.add(minor);
                break;
            case MASTERS:
                requests.add(dorm);
                break;
            default:
                requests.add(defend);
        }
        educational.add(daily);
        educational.add(exams);

        tempGrades = new JMenuItem("نمرات موقت");
        situation = new JMenuItem("وضعیت تحصیلی");
        report.add(tempGrades);
        report.add(situation);

        prof = new JMenuItem("مشخصات کاربر");
        profile.add(prof);

    }


    private void action() {
        main.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
                StudentMainFrame frame = new StudentMainFrame(user);
            }
        });

        lessons.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(panel);
                try {
                    panel = new StudentLessonsPanel(user);
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
                    panel = new StudentTeachersPanel(user);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                add(panel);
                repaint();
                revalidate();
            }
        });

        tempGrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(panel);

                panel = new StudentGradesPanel(user);

                add(panel);
                repaint();
                revalidate();
            }
        });

        situation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(panel);

                panel = new StudentSituationPanel(user);

                add(panel);
                repaint();
                revalidate();
            }
        });

        prof.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(panel);

                panel = new StudentProfilePanel(user);

                add(panel);
                repaint();
                revalidate();
            }
        });

        daily.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(panel);

                panel = new StudentDailyPanel(user);

                add(panel);
                repaint();
                revalidate();
            }
        });

        exams.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(panel);

                panel = new StudentExamsPanel(user);

                add(panel);
                repaint();
                revalidate();
            }
        });

        study.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log.info("Student " + user.getUsername() + " requested a Currently studying certificate");

                studyPanel.setBackground(Color.YELLOW);
                studyPanel.setBounds(250, 50, 300, 100);

                String part2 = " مشغول به تحصیل در رشته *** در دانشگاه صنعتی شریف است.";
                String part3 = " تاریخ اعتبار گواهی: 29.12.1401";
                String part1 = "گواهی میشود آقا/خانم * به شماره دانشجویی **،";

                part2 = part2.replace("***", user.getField());
                part1 = part1.replace("**", user.getEduId());
                part1 = part1.replace("*", user.getName());

                studyLabel.setText(part1);
                JLabel label2 = new JLabel(part2);
                JLabel label3 = new JLabel(part3);
                studyPanel.add(studyLabel);
                studyPanel.add(label2);
                studyPanel.add(label3);
                panel.add(studyPanel);

                repaint();
                revalidate();

            }
        });

        if (user.getType().equals(Module.Type.MASTERS)) {
            dorm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel dormPanel = new JPanel();
                    dormPanel.setBackground(Color.green);
                    dormPanel.setBounds(250, 200, 200, 30);
                    JLabel dorm = new JLabel();

                    if (user.getDorm() == null) {
                        if (Math.random() > 0.5)
                            user.setDorm(true);
                        else
                            user.setDorm(false);
                        FileController.getInstance().updateStudent(user);
                    }

                    if (user.getDorm()) {
                        dorm.setText("به شما خوابگاه تعلق گرفته است");
                        log.info("Student " + user.getUsername() + " requested for dorm and got accepted");
                    }
                    else {
                        dorm.setText("به شما خوابگاه تعلق نگرفته است");
                        dormPanel.setBackground(Color.red);
                        log.info("Student " + user.getUsername() + " requested for dorm and got rejected");
                    }

                    dormPanel.add(dorm);
                    panel.add(dormPanel);

                    repaint();
                    revalidate();
                }
            });
        }

        if (user.getType().equals(Module.Type.PHD)) {
            defend.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel defendPanel = new JPanel();
                    defendPanel.setBackground(Color.green);
                    defendPanel.setBounds(250, 200, 400, 40);
                    JLabel defend = new JLabel();

                    if (user.getDefend() == null) {
                        int date = (int)(Math.random()*31) + 1;
                        user.setDefend(date);
                        FileController.getInstance().updateStudent(user);
                    }

                    defend.setText(" شما میتوانید در تاریخ " + "1400/04/" + user.getDefend() + " از پایان نامه خود دفاع کنید. ");

                    log.info("Student " + user.getUsername() + " requested for a defending session");

                    defendPanel.add(defend);
                    panel.add(defendPanel);

                    repaint();
                    revalidate();
                }
            });
        }

        if (!user.getType().equals(Module.Type.PHD)) {
            recommendation.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove(panel);

                    panel = new StudentRecomPanel(user);

                    add(panel);
                    repaint();
                    revalidate();
                }
            });
        }

        resign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel resignPanel = new JPanel();
                resignPanel.setBackground(Color.yellow);
                resignPanel.setBounds(250, 250, 300, 30);
                JLabel resignMessage = new JLabel();

                boolean flag = false;

                log.info("Student " + user.getUsername() + " requested to resign");

                for (Resign r : FileController.getInstance().getResigns()) {
                    if (r.getUser().getEduId().equals(user.getEduId())) {
                        if (r.isConfirmed() == null) {
                            resignMessage.setText("درخواست شما به معاون آموزشی مربوطه ارسال شده است");
                            resignPanel.setBackground(Color.GREEN);
                        }
                        else if (!r.isConfirmed()){
                            resignMessage.setText("با درخواست شما مخالفت شده است");
                            resignPanel.setBackground(Color.red);
                        }
                        flag = true;
                    }
                }

                if (!flag) {
                    for (Teacher t : FileController.getInstance().getTeachers()) {
                        if (t.getPosition().equals(Position.ASSISTANT) && user.getColleges().get(0).equals(t.getCollege())) {
                            Resign resign = new Resign(user, t);
                            FileController.getInstance().addResign(resign);
                            break;
                        }
                    }
                    resignMessage.setText("درخواست شما به معاون آموزشی مربوطه ارسال شده است");
                }

                resignPanel.add(resignMessage);
                panel.add(resignPanel);

                repaint();
                revalidate();
            }
        });

        if (user.getType().equals(Module.Type.BACHELORS)) {
            minor.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove(panel);

                    panel = new StudentMinorPanel(user);

                    add(panel);
                    repaint();
                    revalidate();
                }
            });
        }
    }

}
