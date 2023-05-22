package Graphic;

import Logic.FileController;
import Module.Grade;
import Module.Lesson;
import Module.Student;
import Module.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AssistantGradesPanel extends JPanel {

    User user;
    String lesson;
    private int passed;
    private int failed;
    private Double passedGrade;

    ArrayList<JButton> lessonButtons = new ArrayList<>();

    JButton idFilter = new JButton("فیلتر");
    JTextField id = new JTextField();

    JButton teacherFilter = new JButton("فیلتر");
    JTextField teacher = new JTextField();


    public AssistantGradesPanel(User user) {
        this.user = user;
        initial();
        alignButtons();
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
        for (Lesson l : FileController.getInstance().getLessons()) {
            if (l.getCollege().equals(user.getCollege())) {
                JButton button = new JButton(l.getName());
                lessonButtons.add(button);
            }
        }

        for (int i = 0; i < lessonButtons.size(); i++) {
            lessonButtons.get(i).setBounds(1280, (i*50)+20, 90, 30);
            this.add(lessonButtons.get(i));
        }

        idFilter.setBounds(1080, 300, 70, 30);
        id.setBounds(1160, 300, 50, 30);
        JLabel studentFilter = new JLabel("فیلتر بر اساس شماره دانشجویی");
        studentFilter.setBounds(1220, 300, 150, 30);

        this.add(idFilter);
        this.add(id);
        this.add(studentFilter);

        teacherFilter.setBounds(990, 360, 70, 30);
        teacher.setBounds(1080, 360, 150, 30);
        JLabel teacherInfo = new JLabel("فیلتر بر اساس نام استاد");
        teacherInfo.setBounds(1250, 360, 150, 30);

        this.add(teacherFilter);
        this.add(teacher);
        this.add(teacherInfo);


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

        if (classGrade(FileController.getInstance().lessonByName(name)) != null) {
            Double grade = classGrade(FileController.getInstance().lessonByName(name));
            JLabel wholeGrade = new JLabel("معدل کل:  " + grade);
            JLabel failed = new JLabel("تعداد مردودی:  " + this.failed);
            JLabel passed = new JLabel("تعداد قبولی:  " + this.passed);
            JLabel passedGrade = new JLabel("معدل کل قبولی ها:  " + this.passedGrade);

            wholeGrade.setBounds(1150, 100, 100, 30);
            failed.setBounds(1150, 140, 100, 30);
            passed.setBounds(1150, 180, 100, 30);
            passedGrade.setBounds(1150, 220, 100, 30);

            this.add(wholeGrade);
            this.add(failed);
            this.add(passed);
            this.add(passedGrade);
        }


        int i = 0;
        for (Student s : students) {
            for (Grade g : s.getGrades()) {
                if (g.getLesson().getName().equals(name) && !(g.isTemporary())) {
                    JPanel panel1 = new JPanel();
                    JPanel panel2 = new JPanel();
                    JPanel panel3 = new JPanel();
                    JPanel panel4 = new JPanel();
                    JPanel panel5 = new JPanel();

                    JLabel label1 = new JLabel(s.getEduId());
                    JLabel label2 = new JLabel(s.getName());
                    JLabel label3 = new JLabel(g.getAmount().toString());
                    JLabel label4 = new JLabel(g.getRespond());
                    JLabel label5 = new JLabel(g.getObjection());


                    JTextField respond = new JTextField(g.getRespond());

                    panel1.setBounds(980, (i*40)+100, 50, 30);
                    panel2.setBounds(860, (i*40)+100, 100, 30);
                    panel3.setBounds(790, (i*40)+100, 50, 30);
                    panel5.setBounds(470, (i*40)+100, 300, 30);
                    panel4.setBounds(150, (i*40)+100, 300, 30);

                    panel1.add(label1);
                    panel2.add(label2);
                    panel5.add(label5);
                    panel3.add(label3);
                    panel4.add(label4);

                    this.add(panel1);
                    this.add(panel2);
                    this.add(panel3);
                    this.add(panel5);
                    this.add(panel4);

                    i++;
                }
            }
        }
    }

    private Double classGrade(Lesson lesson) {
        ArrayList<Grade> grades = new ArrayList<>();
        Double wholeGrade = 0.00;
        int failed = 0;
        int passed = 0;

        for (Student s : FileController.getInstance().getStudents()) {
            for (Grade g : s.getGrades()) {
                if (g.getLesson().getLessonNum().equals(lesson.getLessonNum())) {
                    grades.add(g);
                }
            }
        }

        if (grades.get(0).isTemporary())
            return null;

        for (Grade g : grades) {
            wholeGrade += g.getAmount();
            if (g.getAmount() < 10)
                failed++;
            else
                passed++;
        }

        Double passedGrade = wholeGrade / passed;

        this.passed = passed;
        this.failed = failed;
        this.passedGrade = passedGrade;

        wholeGrade /= grades.size();

        return wholeGrade;

    }

    private void idFilter(Student student) {
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

        int i = 0;
        for (Grade g : student.getGrades()) {
            if (g.isTemporary()) {
                JPanel panel1 = new JPanel();
                JPanel panel2 = new JPanel();
                JPanel panel3 = new JPanel();
                JPanel panel4 = new JPanel();
                JPanel panel5 = new JPanel();

                JLabel label1 = new JLabel(g.getLesson().getLessonNum());
                JLabel label2 = new JLabel(g.getLesson().getName());
                JLabel label3 = new JLabel(g.getAmount().toString());
                JLabel label4 = new JLabel(g.getRespond());
                JLabel label5 = new JLabel(g.getObjection());


                panel1.setBounds(980, (i*40)+100, 50, 30);
                panel2.setBounds(860, (i*40)+100, 100, 30);
                panel3.setBounds(790, (i*40)+100, 50, 30);
                panel5.setBounds(470, (i*40)+100, 300, 30);
                panel4.setBounds(150, (i*40)+100, 300, 30);

                panel1.add(label1);
                panel2.add(label2);
                panel5.add(label5);
                panel3.add(label3);
                panel4.add(label4);

                this.add(panel1);
                this.add(panel2);
                this.add(panel3);
                this.add(panel5);
                this.add(panel4);

                i++;
            }
        }
    }

    private void teacherFilter(ArrayList<Lesson> lessons) {
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

        int i = 0;
        for (Lesson l : lessons) {
            for (Student s : FileController.fileController.getStudents()) {
                for (Grade g : s.getGrades()) {
                    if (g.getLesson().getLessonNum().equals(l.getLessonNum())) {
                        JPanel panel1 = new JPanel();
                        JPanel panel2 = new JPanel();
                        JPanel panel3 = new JPanel();
                        JPanel panel4 = new JPanel();
                        JPanel panel5 = new JPanel();

                        JLabel label1 = new JLabel(s.getName());
                        JLabel label2 = new JLabel(g.getLesson().getName());
                        JLabel label3 = new JLabel();
                        if (g.getAmount() != null)
                            label3.setText(g.getAmount().toString());
                        else
                            label3.setText("N/A");
                        JLabel label4 = new JLabel(g.getRespond());
                        JLabel label5 = new JLabel(g.getObjection());


                        panel1.setBounds(980, (i*40)+100, 50, 30);
                        panel2.setBounds(860, (i*40)+100, 100, 30);
                        panel3.setBounds(790, (i*40)+100, 50, 30);
                        panel5.setBounds(470, (i*40)+100, 300, 30);
                        panel4.setBounds(150, (i*40)+100, 300, 30);

                        panel1.add(label1);
                        panel2.add(label2);
                        panel5.add(label5);
                        panel3.add(label3);
                        panel4.add(label4);

                        this.add(panel1);
                        this.add(panel2);
                        this.add(panel3);
                        this.add(panel5);
                        this.add(panel4);

                        i++;
                    }
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
                    align(students, lesson);
                    repaint();
                    revalidate();
                }
            });
        }

        idFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student student = FileController.getInstance().studentById(id.getText());
                if (student.getCollege().equals(user.getCollege())) {
                    removeAll();
                    idFilter(student);
                    repaint();
                    revalidate();
                }
            }
        });

        teacherFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Lesson> lessons = new ArrayList<>();
                for (Lesson l : FileController.getInstance().getLessons()) {
                    if (l.getTeacher().getName().equals(teacher.getText())) {
                        lessons.add(l);
                    }
                }

                if (FileController.getInstance().teacherByName(teacher.getText()).getCollege().equals(user.getCollege())) {
                    removeAll();
                    teacherFilter(lessons);
                    repaint();
                    revalidate();
                }
            }
        });

    }

}
