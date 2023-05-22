package Graphic;

import Logic.FileController;
import Module.Student;
import Module.Teacher;
import Module.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopupPasswordFrame extends JFrame {

    Logger log = LogManager.getLogger(PopupPasswordFrame.class);

    User user;
    JFrame frame = new JFrame();

    JTextField pass = new JTextField();
    JButton change = new JButton("تغییر رمزعبور");
    JLabel passLabel = new JLabel("رمزعبور جدید ");

    public PopupPasswordFrame(User user) {
        this.user = user;
        initial();
        listener();
        repaint();
        revalidate();
    }

    private void initial() {
        this.setSize(new Dimension(300, 200));
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pass.setBounds(30, 50, 120, 40);
        change.setBounds(100, 100, 100, 40);
        passLabel.setBounds(170, 30, 70, 40);
        this.add(pass);
        this.add(passLabel);
        this.add(change);
        this.setVisible(true);
    }

    private void end() {
        this.dispose();
    }


    private void listener() {
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.setPassword(pass.getText());
                log.info("User " + user.getUsername() + " got a new password through popup menu");
                if (user instanceof Student) {
                    FileController.getInstance().updateStudent(user);
                    end();
                    StudentMainFrame main = new StudentMainFrame(user);
                }
                else if (user instanceof Teacher) {
                    FileController.getInstance().updateTeacher(user.getUsername(), user.getPassword(), user.getName(),
                            user.getNationalId(), user.getEduId(), user.getPhoneNum(), user.getEmail(),
                            user.getCollege().toString(), user.getRank().toString(), user.getPosition().toString(),
                            user.getRoomNum(), user.isTutor());
                    end();
                    TeacherMainFrame main = new TeacherMainFrame(user);
                }


            }
        });
    }

}
