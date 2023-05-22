package Graphic;

import Logic.SignInController;
import Module.Situation;
import Module.Student;
import Module.Teacher;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.Date;

public class SignInFrame extends JFrame {

    static Logger log = LogManager.getLogger(SignInFrame.class);

    JPanel panel = new JPanel();

    JTextField nameField;
    JTextField passwordField;
    JButton signIn;
    JLabel nameLabel;
    JLabel passLabel;
    JButton changeCaptcha = new JButton();

    ImageIcon[] images = new ImageIcon[5];
    String[] captchaCode = {"1169", "2789", "4129", "4966", "9539"};
    int i = (int)(Math.random()*10) % 5;

    {
        images[0] = new ImageIcon("src/main/java/Files/Images/1169.jpg");
        images[1] = new ImageIcon("src/main/java/Files/Images/2789.jpg");
        images[2] = new ImageIcon("src/main/java/Files/Images/4129.jpg");
        images[3] = new ImageIcon("src/main/java/Files/Images/4966.jpg");
        images[4] = new ImageIcon("src/main/java/Files/Images/9539.jpg");
    }
    JLabel captcha = new JLabel();
    JTextField captchaField = new JTextField();

    public SignInFrame() {
        initial();
        initialPanel();
        align();
        this.add(panel);
        update();
    }

    private void initialPanel() {
        panel.setBounds(0, 0, 500, 400);
        panel.setBackground(Color.cyan);
        panel.setLayout(null);

        nameField = new JTextField();
        passwordField = new JTextField();
        signIn = new JButton("Sign In");
        nameLabel = new JLabel("Username : ");
        passLabel = new JLabel("Password : ");
        setListener();

    }

    private void align() {

        nameLabel.setBounds(20, 20, 100, 100);
        panel.add(nameLabel);
        nameField.setBounds(100, 58, 150, 30);
        panel.add(nameField);
        passLabel.setBounds(20, 120, 100, 100);
        panel.add(passLabel);
        passwordField.setBounds(100, 158, 150, 30);
        panel.add(passwordField);
        signIn.setBounds(300, 300, 150, 60);
        captchaField.setBounds(20, 300, 60, 30);
        changeCaptcha.setBounds(180, 260, 36, 36);
        panel.add(changeCaptcha);
        panel.add(captchaField);
        panel.add(signIn);
        changeCaptcha.setBackground(Color.red);
        setCaptcha();
    }

    private void setCaptcha() {

        captcha.setIcon(images[i % 5]);
        captcha.setBounds(20, 260, 150, 36);
        captcha.setOpaque(true);
        panel.add(captcha);
    }

    private void initial() {
        this.setSize(new Dimension(500, 400));
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void update() {
        repaint();
        revalidate();
    }

    private void end() {
        this.dispose();
    }


    private void setListener() {

        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = nameField.getText();
                String password = passwordField.getText();
                Student student = SignInController.getInstance().isStudentValid(username, password);
                Teacher teacher = SignInController.getInstance().isTeacherValid(username, password);

                if (captchaField.getText().equals(captchaCode[(i++) % 5])) {

                    if (student != null && !student.getSituation().equals(Situation.RESIGNED)) {
                        end();
                        Date currentDate = new Date();
                        String[] dates = currentDate.toString().split(" ");
                        String time = dates[3];
                        student.setLastIn(time);
                        log.info("user with username: " + student.getUsername() + "logged in as a student");
                        StudentMainFrame mainFrame = new StudentMainFrame(student);
                    } else if (teacher != null) {
                        end();
                        Date currentDate = new Date();
                        String[] dates = currentDate.toString().split(" ");
                        String time = dates[3];
                        teacher.setLastIn(time);
                        log.info("user with username: " + teacher.getUsername() + " logged in as a teacher");
                        TeacherMainFrame mainFrame = new TeacherMainFrame(teacher);
                    } else {
                        JOptionPane.showMessageDialog(null, "The username or password is invalid",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                        remove(captcha);
                        setCaptcha();
                        captchaField.setText("");
                        passwordField.setText("");
                        log.info("unsuccessful log in " + " invalid user/pass");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "the captcha is wrong!",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                    remove(captcha);
                    setCaptcha();
                    captchaField.setText("");
                    passwordField.setText("");
                    log.info("unsuccessful log in " + " invalid captcha");
                }
            }
        });

        changeCaptcha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(captcha);
                i++;
                setCaptcha();
            }
        });

    }


}
