package Graphic;

import Module.Lesson;
import Module.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class StudentLessonsPanel extends JPanel {

    JTextField field1 = new JTextField();
    JTextField field2 = new JTextField();
    JTextField field3 = new JTextField();
    JButton button1 = new JButton("برو");
    JButton button2 = new JButton("برو");
    JButton button3 = new JButton("برو");

    public StudentLessonsPanel(User user) throws FileNotFoundException {
        initialPanel();
    }

    private void initialPanel() throws FileNotFoundException {
        this.setBackground(Color.lightGray);
        this.setBounds(0, 50, 1380, 950);
        this.setLayout(null);
        alignStatics();
        align(loadLesson());
        listener();
    }

    private void alignStatics() {
        JLabel label1 = new JLabel("فیلتر بر اساس واحد");
        JLabel label2 = new JLabel("فیلتر بر اساس مقطع");
        JLabel label3 = new JLabel("فیلتر بر اساس دانشکده");

        field1.setBounds(1100, 50, 130, 30);
        field2.setBounds(1100, 110, 130, 30);
        field3.setBounds(1100, 170, 130, 30);
        button1.setBounds(1050, 50, 50, 30);
        button2.setBounds(1050, 110, 50, 30);
        button3.setBounds(1050, 170, 50, 30);
        label1.setBounds(1250, 50, 100, 30);
        label2.setBounds(1250, 110, 100, 30);
        label3.setBounds(1250, 170, 100, 30);

        this.add(field1);
        this.add(field2);
        this.add(field3);
        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(label1);
        this.add(label2);
        this.add(label3);
    }

    private void align(ArrayList<Lesson> lessons) throws FileNotFoundException {
        int i = 0;

        JPanel panel11 = new JPanel();
        JPanel panel21 = new JPanel();
        JPanel panel31 = new JPanel();
        JPanel panel41 = new JPanel();
        JPanel panel51 = new JPanel();
        JPanel panel61 = new JPanel();
        JPanel panel71 = new JPanel();
        JPanel panel81 = new JPanel();
        JLabel label11 = new JLabel("شماره درس");
        JLabel label21 = new JLabel("واحد");
        JLabel label31 = new JLabel("نام درس");
        JLabel label41 = new JLabel("زمان کلاس");
        JLabel label51 = new JLabel("زمان امتحان");
        JLabel label61 = new JLabel("مقطع درس");
        JLabel label71 = new JLabel("دانشکده درس");
        JLabel label81 = new JLabel("استاد درس");

        panel11.setBounds(1100, 250, 50, 40);
        panel21.setBounds(1030, 250, 50, 40);
        panel31.setBounds(910, 250, 100, 40);
        panel41.setBounds(740, 250, 150, 40);
        panel51.setBounds(620, 250, 100, 40);
        panel61.setBounds(500, 250, 100, 40);
        panel71.setBounds(380, 250, 100, 40);
        panel81.setBounds(260, 250, 100, 40);


        panel11.add(label11);
        panel21.add(label21);
        panel31.add(label31);
        panel41.add(label41);
        panel51.add(label51);
        panel61.add(label61);
        panel71.add(label71);
        panel81.add(label81);

        this.add(panel11);
        this.add(panel21);
        this.add(panel31);
        this.add(panel41);
        this.add(panel51);
        this.add(panel61);
        this.add(panel71);
        this.add(panel81);


        for (Lesson l : lessons) {
            JPanel panel1 = new JPanel();
            JPanel panel2 = new JPanel();
            JPanel panel3 = new JPanel();
            JPanel panel4 = new JPanel();
            JPanel panel5 = new JPanel();
            JPanel panel6 = new JPanel();
            JPanel panel7 = new JPanel();
            JPanel panel8 = new JPanel();
            JLabel label1 = new JLabel(l.getLessonNum());
            JLabel label2 = new JLabel(String.valueOf(l.getNumberOfUnites()));
            JLabel label3 = new JLabel(l.getName());
            JLabel label4 = new JLabel(l.getTimeOfClass());
            JLabel label5 = new JLabel("1401.4." + String.valueOf(l.getDateOfExam()));
            JLabel label6 = new JLabel(l.getLessonSection().toString());
            JLabel label7 = new JLabel(l.getCollege().toString());
            JLabel label8;
            if (l.getTeacher() != null) {
                label8 = new JLabel(l.getTeacher().getName());
            }
            else {
                label8 = new JLabel("نامشخص");
            }


            panel1.setBackground(Color.white);
            panel2.setBackground(Color.white);
            panel3.setBackground(Color.white);
            panel4.setBackground(Color.white);
            panel5.setBackground(Color.white);
            panel6.setBackground(Color.white);
            panel7.setBackground(Color.white);
            panel8.setBackground(Color.white);

            panel1.setBounds(1100, (i*40)+300, 50, 40);
            panel2.setBounds(1030, (i*40)+300, 50, 40);
            panel3.setBounds(910, (i*40)+300, 100, 40);
            panel4.setBounds(740, (i*40)+300, 150, 40);
            panel5.setBounds(620, (i*40)+300, 100, 40);
            panel6.setBounds(500, (i*40)+300, 100, 40);
            panel7.setBounds(380, (i*40)+300, 100, 40);
            panel8.setBounds(260, (i*40)+300, 100, 40);

            panel1.add(label1);
            panel2.add(label2);
            panel3.add(label3);
            panel4.add(label4);
            panel5.add(label5);
            panel6.add(label6);
            panel7.add(label7);
            panel8.add(label8);

            this.add(panel1);
            this.add(panel2);
            this.add(panel3);
            this.add(panel4);
            this.add(panel5);
            this.add(panel6);
            this.add(panel7);
            this.add(panel8);

            i++;
        }
    }

    private ArrayList<Lesson> loadLesson() throws FileNotFoundException {
        File file = new File("src/main/java/Files/Lessons.txt");
        ArrayList<Lesson> lessons = new ArrayList<>();
        Lesson lesson;

        Scanner scanner= new Scanner(file);
        String userjson="";
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson= gsonBuilder.create();

        while (scanner.hasNextLine()){
            userjson = scanner.nextLine();
            lesson = gson.fromJson(userjson,Lesson.class);
            lessons.add(lesson);
        }
        return lessons;
    }

    private ArrayList<Lesson> filterUnit(int unit) throws FileNotFoundException {

        File file = new File("src/main/java/Files/Lessons.txt");
        ArrayList<Lesson> lessons = new ArrayList<>();
        Lesson lesson;

        Scanner scanner= new Scanner(file);
        String userjson="";
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson= gsonBuilder.create();

        while (scanner.hasNextLine()){
            userjson = scanner.nextLine();
            lesson = gson.fromJson(userjson,Lesson.class);
            if (lesson.getNumberOfUnites() == unit)
                lessons.add(lesson);
        }

        return lessons;

    }

    private ArrayList<Lesson> filterType(String txt) throws FileNotFoundException {

        File file = new File("src/main/java/Files/Lessons.txt");
        ArrayList<Lesson> lessons = new ArrayList<>();
        Lesson lesson;

        Scanner scanner= new Scanner(file);
        String userjson="";
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson= gsonBuilder.create();

        while (scanner.hasNextLine()){
            userjson = scanner.nextLine();
            lesson = gson.fromJson(userjson,Lesson.class);
            if (lesson.getLessonSection().toString().equals(txt))
                lessons.add(lesson);
        }

        return lessons;
    }

    private ArrayList<Lesson> filterCollege(String txt) throws FileNotFoundException {

        File file = new File("src/main/java/Files/Lessons.txt");
        ArrayList<Lesson> lessons = new ArrayList<>();
        Lesson lesson;

        Scanner scanner= new Scanner(file);
        String userjson="";
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson= gsonBuilder.create();

        while (scanner.hasNextLine()){
            userjson = scanner.nextLine();
            lesson = gson.fromJson(userjson,Lesson.class);
            if (lesson.getCollege().toString().equals(txt))
                lessons.add(lesson);
        }

        return lessons;
    }

    private void listener() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String txt = field1.getText();
                int unit = Integer.parseInt(txt);
                removeAll();
                alignStatics();
                field1.setText("");
                try {
                    align(filterUnit(unit));
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                repaint();
                revalidate();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String txt = field2.getText();
                txt = txt.toUpperCase();
                removeAll();
                alignStatics();
                field2.setText("");
                try {
                    align(filterType(txt));
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                repaint();
                revalidate();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String txt = field3.getText();
                txt = txt.toUpperCase();
                removeAll();
                alignStatics();
                field3.setText("");
                try {
                    align(filterCollege(txt));
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                repaint();
                revalidate();
            }
        });
    }

}
