package Graphic;

import Logic.FileController;
import Module.Grade;
import Module.Student;
import Module.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AssistantSituationPanel extends JPanel {

    User user;

    JTextField nameField = new JTextField();
    JTextField numField = new JTextField();

    JButton nameButton = new JButton("جستجو");
    JButton numButton = new JButton("جستجو");

    public AssistantSituationPanel(User user) {
        this.user = user;
        initial();
        alignStatics();
        align(FileController.getInstance().getStudents());
        listener();
        repaint();
        revalidate();
    }

    private void initial() {
        this.setBackground(Color.lightGray);
        this.setBounds(0, 50, 1380, 950);
        this.setLayout(null);
    }

    private void alignStatics() {
        JLabel searchName = new JLabel("سرچ بر اساس نام");
        JLabel searchNum = new JLabel("سرچ بر اساس شماره");

        searchName.setBounds(1200, 20, 150, 30);
        searchNum.setBounds(1200, 70, 150, 30);
        nameField.setBounds(1070, 20, 100, 30);
        numField.setBounds(1070, 70, 100, 30);
        nameButton.setBounds(980, 20, 80, 30);
        numButton.setBounds(980, 70, 80, 30);

        this.add(searchName);
        this.add(searchNum);
        this.add(nameField);
        this.add(numField);
        this.add(nameButton);
        this.add(numButton);
    }

    private void align(ArrayList<Student> students) {
        int j = 0;
        for (Student s : students) {
            if (s.getColleges().contains(user.getCollege())){
                JLabel unit = new JLabel();
                JLabel finalGrade = new JLabel();
                JLabel name = new JLabel();

                int units = 0;
                for (Grade g : s.getGrades()) {
                    if (!g.isTemporary()){
                        units += g.getLesson().getNumberOfUnites();
                    }
                }
                unit.setText("تعداد واحد های پاس شده:  " + units + " ");

                Double grades = 0.00;
                for (Grade g : s.getGrades()) {
                    if (!g.isTemporary()){
                        grades += g.getAmount() / units;
                    }
                }
                finalGrade.setText("معدل کل:  " + grades + " ");
                name.setText("نام دانشجو:  " + s.getName() + " ");

                name.setBounds((j*260)+50, 100, 150, 30);
                unit.setBounds((j*260)+50, 130, 200, 30);
                finalGrade.setBounds((j*260)+50, 160, 200, 30);

                this.add(name);
                this.add(unit);
                this.add(finalGrade);


                JPanel panel11 = new JPanel();
                JPanel panel21 = new JPanel();
                JPanel panel31 = new JPanel();

                JLabel label11 = new JLabel("شماره");
                JLabel label21 = new JLabel("نام درس");
                JLabel label31 = new JLabel("نمره");

                panel11.setBackground(Color.white);
                panel21.setBackground(Color.white);
                panel31.setBackground(Color.white);

                panel11.setBounds((j*260)+240, 200, 50, 30);
                panel21.setBounds((j*260)+120, 200, 100, 30);
                panel31.setBounds((j*260)+50, 200, 50, 30);

                panel11.add(label11);
                panel21.add(label21);
                panel31.add(label31);

                this.add(panel11);
                this.add(panel21);
                this.add(panel31);

                int i = 0;
                for (Grade g : s.getGrades()) {
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

                    panel1.setBounds((j*260)+240, (i*40)+250, 50, 30);
                    panel2.setBounds((j*260)+120, (i*40)+250, 100, 30);
                    panel3.setBounds((j*260)+50, (i*40)+250, 50, 30);

                    panel1.add(label1);
                    panel2.add(label2);
                    panel3.add(label3);

                    this.add(panel1);
                    this.add(panel2);
                    this.add(panel3);

                    i++;
                }
                j++;
            }
        }

    }

    private void listener() {
        nameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                ArrayList<Student> students = new ArrayList<>();
                for (Student s : FileController.getInstance().getStudents()) {
                    if (s.getName().equals(name))
                        students.add(s);
                }
                removeAll();
                nameField.setText("");
                alignStatics();
                align(students);
                repaint();
                revalidate();
            }
        });

        numButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String num = numField.getText();
                ArrayList<Student> students = new ArrayList<>();
                for (Student s : FileController.getInstance().getStudents()) {
                    if (s.getEduId().equals(num))
                        students.add(s);
                }
                removeAll();
                numField.setText("");
                alignStatics();
                align(students);
                repaint();
                revalidate();
            }
        });
    }

}
