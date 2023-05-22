package Graphic;

import Module.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.jws.soap.SOAPBinding;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import static java.lang.System.exit;

public class MainFrame extends JFrame {

    Logger log = LogManager.getLogger(MainFrame.class);

    User user;
    JMenuBar menuBar = new JMenuBar();
    JMenu enrollment;
    JMenu requests = new JMenu("درخواست ها");
    JPanel panel = new JPanel();
    JMenu report;
    JMenu educational;
    JMenu profile;
    JMenu exit;
    JMenuItem Exit;
    JMenuItem main;

    public MainFrame(User user) {
        this.user = user;
        initial();
        initialPanel(user);
        revalidate();
        repaint();

    }


    private void initial() {
        this.setSize(new Dimension(1400, 1000));
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setJMenuBar(menuBar);
        menuBuild();
        this.setVisible(true);
    }

    private void initialPanel(User user) {
        panel.setBackground(Color.cyan);
        panel.setBounds(0, 0, 1380, 50);
        panel.setLayout(null);

        ClockComponent clock = new ClockComponent();
        clock.setBounds(1000, 10, 130, 30);
        clock.setBackground(Color.GREEN);

        panel.add(clock);

        Date currentDate = new Date();
        String[] dates = currentDate.toString().split(" ");
        String weekDay = dates[0];
        String time = dates[3];
        //JLabel dateAndTime = new JLabel(time +" " +  weekDay + "  " + ":روز و ساعت");
        JLabel lastIn = new JLabel( "زمان آخرین ورود:" + " " + user.getLastIn());

        JLabel personName = new JLabel("نام:" + " " + user.getName());
        JLabel personEmail = new JLabel(user.getEmail() + "  " + ":ایمیل");

        lastIn.setBounds(20, 10, 150, 20);
        personEmail.setBounds(30, 30, 140, 20);
        personName.setBounds(200, 15, 120, 30);


        panel.add(personName);
        panel.add(personEmail);
        panel.add(lastIn);
        //panel.add(dateAndTime);
        this.add(panel);
    }

    private void menuBuild() {
        enrollment = new JMenu("امور ثبت نام و ترمیم");
        report = new JMenu("امور کارنامه");
        educational = new JMenu("خدمات آموزشی");
        profile = new JMenu("پروفایل کاربری");
        exit = new JMenu("shortcuts");
        Exit = new JMenuItem("خروج");
        main = new JMenuItem("صفحه اصلی");
        exit.add(Exit);
        exit.add(main);
        educational.add(requests);
        menuBar.add(enrollment);
        menuBar.add(report);
        menuBar.add(educational);
        menuBar.add(profile);
        menuBar.add(exit);
        setListener();
    }


    protected void end() {
        this.dispose();
    }

    private JFrame meh() {
        return this;
    }

    private void setListener() {

        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log.info("User " + user.getUsername() + " logged out");
                end();
                SignInFrame frame = new SignInFrame();
            }
        });

    }


}
