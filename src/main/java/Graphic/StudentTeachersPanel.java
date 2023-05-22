package Graphic;

import Module.Lesson;
import Module.Teacher;
import Module.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentTeachersPanel extends JPanel {

    JTextField field1 = new JTextField();
    JTextField field2 = new JTextField();
    JTextField field3 = new JTextField();
    JButton button1 = new JButton("برو");
    JButton button2 = new JButton("برو");
    JButton button3 = new JButton("برو");

    public StudentTeachersPanel(User user) throws FileNotFoundException {
        initialPanel();
    }

    private void initialPanel() throws FileNotFoundException {
        this.setBackground(Color.lightGray);
        this.setBounds(0, 50, 1380, 950);
        this.setLayout(null);
        alignStatics();
        align(loadTeacher());
        listener();
    }

    private void alignStatics() {

        JLabel label1 = new JLabel("فیلتر بر اساس نام");
        JLabel label2 = new JLabel("فیلتر بر اساس درجه");
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

    private void align(ArrayList<Teacher> teachers) throws FileNotFoundException {
        int i = 0;

        JPanel panel11 = new JPanel();
        JPanel panel21 = new JPanel();
        JPanel panel31 = new JPanel();
        JPanel panel41 = new JPanel();
        JPanel panel51 = new JPanel();
        JPanel panel61 = new JPanel();
        JPanel panel71 = new JPanel();
        JPanel panel81 = new JPanel();
        JLabel label11 = new JLabel("نام");
        JLabel label21 = new JLabel("دانشکده");
        JLabel label31 = new JLabel("ایمیل");
        JLabel label41 = new JLabel("شماره تماس");
        JLabel label51 = new JLabel("شماره اتاق");
        JLabel label61 = new JLabel("درجه استادی");
        JLabel label71 = new JLabel("سِمَت");
        JLabel label81 = new JLabel("eduID");

        panel11.setBounds(1100, 250, 150, 40);
        panel21.setBounds(980, 250, 100, 40);
        panel31.setBounds(860, 250, 100, 40);
        panel41.setBounds(690, 250, 150, 40);
        panel51.setBounds(620, 250, 50, 40);
        panel61.setBounds(500, 250, 100, 40);
        panel71.setBounds(380, 250, 100, 40);
        panel81.setBounds(1270, 250, 50, 40);


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


        for (Teacher t : teachers) {
            JPanel panel1 = new JPanel();
            JPanel panel2 = new JPanel();
            JPanel panel3 = new JPanel();
            JPanel panel4 = new JPanel();
            JPanel panel5 = new JPanel();
            JPanel panel6 = new JPanel();
            JPanel panel7 = new JPanel();
            JPanel panel8 = new JPanel();
            JLabel label1 = new JLabel(t.getName());
            JLabel label2 = new JLabel(t.getCollege().toString());
            JLabel label3 = new JLabel(t.getEmail());
            JLabel label4 = new JLabel(t.getPhoneNum());
            JLabel label5 = new JLabel(t.getRoomNum());
            JLabel label6 = new JLabel(t.getRank().toString());
            JLabel label7 = new JLabel(t.getPosition().toString());
            JLabel label8 = new JLabel(t.getEduId());

            panel1.setBackground(Color.white);
            panel2.setBackground(Color.white);
            panel3.setBackground(Color.white);
            panel4.setBackground(Color.white);
            panel5.setBackground(Color.white);
            panel6.setBackground(Color.white);
            panel7.setBackground(Color.white);
            panel8.setBackground(Color.white);

            panel1.setBounds(1100, (i*40)+300, 150, 40);
            panel2.setBounds(980, (i*40)+300, 100, 40);
            panel3.setBounds(860, (i*40)+300, 100, 40);
            panel4.setBounds(690, (i*40)+300, 150, 40);
            panel5.setBounds(620, (i*40)+300, 50, 40);
            panel6.setBounds(500, (i*40)+300, 100, 40);
            panel7.setBounds(380, (i*40)+300, 100, 40);
            panel8.setBounds(1270, (i*40)+300, 50, 40);

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

    private ArrayList<Teacher> loadTeacher() throws FileNotFoundException {
        File file = new File("src/main/java/Files/Teachers.txt");
        ArrayList<Teacher> teachers = new ArrayList<>();
        Teacher teacher;

        Scanner scanner= new Scanner(file);
        String userjson="";
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson= gsonBuilder.create();

        while (scanner.hasNextLine()){
            userjson = scanner.nextLine();
            teacher = gson.fromJson(userjson,Teacher.class);
            teachers.add(teacher);
        }
        return teachers;
    }

    private ArrayList<Teacher> filterName(String txt) throws FileNotFoundException {

        File file = new File("src/main/java/Files/Teachers.txt");
        ArrayList<Teacher> teachers = new ArrayList<>();
        Teacher teacher;

        Scanner scanner= new Scanner(file);
        String userjson="";
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson= gsonBuilder.create();

        while (scanner.hasNextLine()){
            userjson = scanner.nextLine();
            teacher = gson.fromJson(userjson,Teacher.class);
            if (teacher.getName().equals(txt))
                teachers.add(teacher);
        }

        return teachers;

    }

    private ArrayList<Teacher> filterRank(String txt) throws FileNotFoundException {

        File file = new File("src/main/java/Files/Teachers.txt");
        ArrayList<Teacher> teachers = new ArrayList<>();
        Teacher teacher;

        Scanner scanner= new Scanner(file);
        String userjson="";
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson= gsonBuilder.create();

        while (scanner.hasNextLine()){
            userjson = scanner.nextLine();
            teacher = gson.fromJson(userjson,Teacher.class);
            if (teacher.getRank().toString().equals(txt))
                teachers.add(teacher);
        }

        return teachers;

    }

    private ArrayList<Teacher> filterCollege(String txt) throws FileNotFoundException {

        File file = new File("src/main/java/Files/Teachers.txt");
        ArrayList<Teacher> teachers = new ArrayList<>();
        Teacher teacher;

        Scanner scanner= new Scanner(file);
        String userjson="";
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson= gsonBuilder.create();

        while (scanner.hasNextLine()){
            userjson = scanner.nextLine();
            teacher = gson.fromJson(userjson,Teacher.class);
            if (teacher.getCollege().toString().equals(txt))
                teachers.add(teacher);
        }

        return teachers;

    }

    private void listener() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String txt = field1.getText();
                removeAll();
                alignStatics();
                field1.setText("");
                try {
                    align(filterName(txt));
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
                    align(filterRank(txt));
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
