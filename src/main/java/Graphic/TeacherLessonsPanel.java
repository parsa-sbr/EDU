package Graphic;

import Logic.FileController;
import Module.Lesson;
import Module.Position;
import Module.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class TeacherLessonsPanel extends StudentLessonsPanel {

    User user;

    JLabel delete = new JLabel("حذف درس با شماره:");
    JButton deleteButton = new JButton("حذف");
    JTextField deleteField = new JTextField();
    JLabel edit = new JLabel("ویرایش درس با شماره:");
    JButton editButton = new JButton("ویرایش");
    JTextField editField = new JTextField();
    JButton addLesson = new JButton("اضافه کردن درس");


    JLabel info = new JLabel("برای دیدن نتیجه صفحه را رفرش کنید");

    public TeacherLessonsPanel(User user) throws FileNotFoundException {
        super(user);
        this.user = user;
        initial(user);
        repaint();
        revalidate();
    }

    private void initial(User user) {
        if (user.getPosition().equals(Position.ASSISTANT)){
            delete.setBounds(800, 50, 100, 30);
            deleteButton.setBounds(600, 50, 60, 30);
            deleteField.setBounds(680, 50, 100, 30);
            edit.setBounds(800, 90, 100, 30);
            editButton.setBounds(600, 90, 80, 30);
            editField.setBounds(690, 90, 100, 30);
            addLesson.setBounds(750, 150, 150, 40);

            this.add(delete);
            this.add(deleteButton);
            this.add(addLesson);
            this.add(edit);
            this.add(editButton);
            this.add(editField);
            this.add(deleteField);
        }

        listener();
    }

    private void meh() {
        info.setBounds(600, 100, 160, 40);
        info.setForeground(Color.red);
        this.add(info);
    }

    private void listener() {
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = deleteField.getText();
                if (FileController.getInstance().lessonById(id).getCollege().equals(user.getCollege())) {
                    deleteField.setText("");
                    FileController.getInstance().deleteLesson(id, user);
                    meh();
                }
                repaint();
                revalidate();
            }
        });

        addLesson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                AddingLessonPanel addingLessonPanel = new AddingLessonPanel(user);
                add(addingLessonPanel);
                repaint();
                revalidate();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = editField.getText();
                Lesson lesson = FileController.getInstance().lessonById(id);
                if (lesson.getCollege().equals(user.getCollege())) {
                    removeAll();
                    editField.setText("");
                    EditingLessonPanel editPanel = new EditingLessonPanel(user, lesson);
                    add(editPanel);
                }
                repaint();
                revalidate();
            }
        });
    }
}
