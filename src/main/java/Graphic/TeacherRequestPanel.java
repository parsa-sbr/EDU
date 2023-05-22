package Graphic;

import Logic.FileController;
import Module.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TeacherRequestPanel extends JPanel {

    Logger log = LogManager.getLogger(TeacherRequestPanel.class);

    User user;

    JPanel recom = new JPanel();
    JLabel recomTitle = new JLabel("توصیه نامه ها");
    JPanel minor = new JPanel();
    JLabel minorTitle = new JLabel("ماینور");
    JPanel resign = new JPanel();
    JLabel resignTitle = new JLabel("انصراف از تحصیل");

    ArrayList<Recommendation> recoms = new ArrayList<>();
    ArrayList<JButton> recomConfirms = new ArrayList<>();
    ArrayList<JButton> recomDenys = new ArrayList<>();

    ArrayList<Resign> resigns = new ArrayList<>();
    ArrayList<JButton> resignConfirms = new ArrayList<>();
    ArrayList<JButton> resignDenys = new ArrayList<>();

    ArrayList<Minor> minors = new ArrayList<>();
    ArrayList<JButton> minorConfirms = new ArrayList<>();
    ArrayList<JButton> minorDenys = new ArrayList<>();

    public TeacherRequestPanel(User user) {
        this.user = user;
        initial();
        align();
        listener();
        repaint();
        revalidate();
    }

    private void initial() {
        this.setBackground(Color.lightGray);
        this.setBounds(0, 50, 1380, 950);
        this.setLayout(null);
    }

    private void align() {

        recom.add(recomTitle);
        minor.add(minorTitle);
        resign.add(resignTitle);
        recom.setBounds(1000, 100, 300, 30);
        minor.setBounds(600, 100, 300, 30);
        resign.setBounds(200, 100, 300, 30);


        this.add(recom);
        if (user.getPosition().equals(Position.ASSISTANT)) {
            this.add(minor);
            this.add(resign);
        }

        int i = 0;
        for (Recommendation r : FileController.getInstance().getRecoms()) {
            if (r.getTeacher().getEduId().equals(user.getEduId()) && (r.isConfirmed() == null)) {
                recoms.add(r);

                JPanel panel1 = new JPanel();
                JPanel panel2 = new JPanel();

                JLabel label1 = new JLabel(r.getUser().getEduId());
                JLabel label2 = new JLabel(String.valueOf(r.getUser().getName()));

                JButton confirm = new JButton("تایید");
                confirm.setBackground(Color.GREEN);
                JButton deny = new JButton("مخالفت");
                deny.setBackground(Color.red);

                panel1.setBounds(1250, (i*50)+140, 50, 40);
                panel2.setBounds(1130, (i*50)+140, 100, 40);
                confirm.setBounds(1000, (i*50)+140, 60, 40);
                deny.setBounds(1070, (i*50)+140, 60, 40);

                panel1.add(label1);
                panel2.add(label2);

                this.add(panel1);
                this.add(panel2);

                recomConfirms.add(confirm);
                recomDenys.add(deny);

                this.add(confirm);
                this.add(deny);

                i++;
            }
        }

        i = 0;
        for (Resign r : FileController.getInstance().getResigns()) {
            if (r.getTeacher().getEduId().equals(user.getEduId()) && (r.isConfirmed() == null)) {
                resigns.add(r);

                JPanel panel1 = new JPanel();
                JPanel panel2 = new JPanel();

                JLabel label1 = new JLabel(r.getUser().getEduId());
                JLabel label2 = new JLabel(String.valueOf(r.getUser().getName()));

                JButton confirm = new JButton("تایید");
                confirm.setBackground(Color.GREEN);
                JButton deny = new JButton("مخالفت");
                deny.setBackground(Color.red);

                panel1.setBounds(450, (i*50)+140, 50, 40);
                panel2.setBounds(330, (i*50)+140, 100, 40);
                confirm.setBounds(200, (i*50)+140, 60, 40);
                deny.setBounds(270, (i*50)+140, 60, 40);

                panel1.add(label1);
                panel2.add(label2);

                this.add(panel1);
                this.add(panel2);

                resignConfirms.add(confirm);
                resignDenys.add(deny);

                this.add(confirm);
                this.add(deny);

                i++;
            }
        }

        i = 0;
        if (user.getPosition().equals(Position.ASSISTANT)) {
            for (Minor m : FileController.getInstance().getMinors()) {
                if ((m.getTo().equals(user.getCollege()) && m.isTeacherTo() == null) ||
                        (m.getFrom().equals(user.getCollege()) && m.isTeacherFrom() == null)) {

                    minors.add(m);

                    JPanel panel1 = new JPanel();
                    JPanel panel2 = new JPanel();

                    JLabel label1 = new JLabel(m.getUser().getEduId());
                    JLabel label2 = new JLabel(String.valueOf(m.getUser().getName()));

                    JButton confirm = new JButton("تایید");
                    confirm.setBackground(Color.GREEN);
                    JButton deny = new JButton("مخالفت");
                    deny.setBackground(Color.red);

                    panel1.setBounds(850, (i*50)+140, 50, 40);
                    panel2.setBounds(730, (i*50)+140, 100, 40);
                    confirm.setBounds(600, (i*50)+140, 60, 40);
                    deny.setBounds(670, (i*50)+140, 60, 40);

                    panel1.add(label1);
                    panel2.add(label2);

                    this.add(panel1);
                    this.add(panel2);

                    minorConfirms.add(confirm);
                    minorDenys.add(deny);

                    this.add(confirm);
                    this.add(deny);

                    i++;

                }
            }
        }
    }

    private void listener() {
        for (JButton button : recomConfirms) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int i = recomConfirms.indexOf(button);
                    recoms.get(i).setConfirmed(true);
                    log.info("Teacher " + user.getUsername() + "confirmed student " + recoms.get(i).getUser().getUsername() +
                            "request for recommendation");
                    FileController.getInstance().updateRecom(recoms.get(i));
                    removeAll();
                    align();
                    repaint();
                    revalidate();
                }
            });
        }

        for (JButton button : recomDenys) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int i = recomDenys.indexOf(button);
                    recoms.get(i).setConfirmed(false);
                    log.info("Teacher " + user.getUsername() + "denied student " + recoms.get(i).getUser().getUsername() +
                            "request for recommendation");
                    FileController.getInstance().updateRecom(recoms.get(i));
                    removeAll();
                    align();
                    repaint();
                    revalidate();
                }
            });
        }

        for (JButton button : resignConfirms) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int i = resignConfirms.indexOf(button);
                    resigns.get(i).setConfirmed(true);
                    log.info("Teacher " + user.getUsername() + "confirmed student " + resigns.get(i).getUser().getUsername() +
                            "request to resign");
                    FileController.getInstance().updateResign(resigns.get(i), resigns.get(i).getUser());
                    removeAll();
                    align();
                    repaint();
                    revalidate();
                }
            });
        }

        for (JButton button : resignDenys) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int i = resignDenys.indexOf(button);
                    resigns.get(i).setConfirmed(false);
                    log.info("Teacher " + user.getUsername() + "denied student " + resigns.get(i).getUser().getUsername() +
                            "request to resign");
                    FileController.getInstance().updateResign(resigns.get(i), resigns.get(i).getUser());
                    removeAll();
                    align();
                    repaint();
                    revalidate();
                }
            });
        }

        for (JButton button : minorConfirms) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int i = minorConfirms.indexOf(button);
                    if (user.getCollege().equals(minors.get(i).getFrom()))
                        minors.get(i).setTeacherFrom(true);
                    else
                        minors.get(i).setTeacherTo(true);
                    log.info("Teacher " + user.getUsername() + "confirmed student " + minors.get(i).getUser().getUsername() +
                            "request for minor");
                    FileController.getInstance().updateMinor(minors.get(i));
                    removeAll();
                    align();
                    repaint();
                    revalidate();
                }
            });
        }

        for (JButton button : minorDenys) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int i = minorConfirms.indexOf(button);
                    if (user.getCollege().equals(minors.get(i).getFrom()))
                        minors.get(i).setTeacherFrom(false);
                    else
                        minors.get(i).setTeacherTo(false);
                    log.info("Teacher " + user.getUsername() + "denied student " + minors.get(i).getUser().getUsername() +
                            "request for minor");
                    FileController.getInstance().updateMinor(minors.get(i));
                    removeAll();
                    align();
                    repaint();
                    revalidate();
                }
            });
        }
    }

}
