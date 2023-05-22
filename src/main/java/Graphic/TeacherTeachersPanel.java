package Graphic;

import Logic.FileController;
import Module.Lesson;
import Module.Position;
import Module.Teacher;
import Module.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class TeacherTeachersPanel extends StudentTeachersPanel {

    User user;

    JLabel delete = new JLabel("حذف استاد با شماره:");
    JButton deleteButton = new JButton("حذف");
    JTextField deleteField = new JTextField();
    JLabel edit = new JLabel("ویرایش استاد با شماره:");
    JButton editButton = new JButton("ویرایش");
    JTextField editField = new JTextField();
    JButton addTeacher = new JButton("اضافه کردن استاد");

    JLabel info = new JLabel("برای دیدن نتیجه صفحه را رفرش کنید");

    public TeacherTeachersPanel(User user) throws FileNotFoundException {
        super(user);
        this.user = user;
        initial(user);
        repaint();
        revalidate();
    }

    private void initial(User user) {
        if (user.getPosition().equals(Position.MANAGER)){
            delete.setBounds(800, 50, 100, 30);
            deleteButton.setBounds(600, 50, 60, 30);
            deleteField.setBounds(680, 50, 100, 30);
            edit.setBounds(800, 90, 100, 30);
            editButton.setBounds(600, 90, 80, 30);
            editField.setBounds(690, 90, 100, 30);
            addTeacher.setBounds(750, 150, 150, 40);
            info.setBounds(600, 125, 160, 40);
            info.setForeground(Color.red);

            this.add(delete);
            this.add(deleteButton);
            this.add(edit);
            this.add(editField);
            this.add(editButton);
            this.add(addTeacher);
            this.add(deleteField);
        }

        listener();
    }

    private void listener() {
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = deleteField.getText();
                if (FileController.getInstance().teacherById(id).getCollege().equals(user.getCollege())) {
                    FileController.getInstance().deleteTeacher(id, user);
                    add(info);
                    deleteField.setText("");
                }
                repaint();
                revalidate();
            }
        });

        addTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                AddingTeacherPanel addingTeacherPanel = new AddingTeacherPanel(user);
                add(addingTeacherPanel);
                repaint();
                revalidate();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = editField.getText();
                Teacher teacher = FileController.getInstance().teacherById(id);
                if (teacher.getCollege().equals(user.getCollege())) {
                    removeAll();
                    editField.setText("");
                    EditingTeacherPanel editPanel = new EditingTeacherPanel(user, teacher);
                    add(editPanel);
                }
                repaint();
                revalidate();
            }
        });
    }

}
