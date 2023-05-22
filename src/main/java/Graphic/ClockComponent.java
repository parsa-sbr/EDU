package Graphic;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ClockComponent extends JPanel implements ActionListener {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy HH:mm:ss");
    public JLabel clock;

    public ClockComponent() {
        clock = new JLabel();
        add(clock);
        updateClock();
        new Timer(1000, this).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateClock();
    }

    private void updateClock() {
        clock.setText(dateFormat.format(Calendar.getInstance().getTime()));
    }

}
