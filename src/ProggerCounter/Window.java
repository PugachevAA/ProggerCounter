package ProggerCounter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private JButton btnStart = new JButton("Засекаем вручную :)");
    private JButton btnStop = new JButton("Пока хватит :)");

    private JLabel labelTextStringCount = new JLabel("Всего: ");
    private JLabel labelStringCount = new JLabel("22");

    private JLabel labelTextDay = new JLabel("В днях:");
    private JLabel labelDay = new JLabel("33");

    private JLabel labelTextHour = new JLabel("В часах");
    private JLabel labelHour = new JLabel("44");

    private JLabel labelTextMin = new JLabel("В минутах");
    private JLabel labelMin = new JLabel("55");

    private JLabel labelTextSec = new JLabel("В секундах");
    private JLabel labelSec = new JLabel("66");
    private boolean isWork = false;


    public void setCount(long count) {
        labelSec.setText(count + "");
    }
    public void setDay(long count) {
        labelDay.setText(count + "");
    }
    public void setHour(long count) {
        labelHour.setText(count + "");
    }
    public void setMin(long count) {
        labelMin.setText(count + "");
    }
    public void setStringCount(String count) {
        labelStringCount.setText(count + "");
    }

    public boolean isWork() {
        return isWork;
    }

    public Window() {
        super("Счетчик программиста :)");
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(7,2,10,10));
        container.add(labelTextStringCount);
        container.add(labelStringCount);
        container.add(labelTextDay);
        container.add(labelDay);
        container.add(labelTextHour);
        container.add(labelHour);
        container.add(labelTextMin);
        container.add(labelMin);
        container.add(labelTextSec);
        container.add(labelSec);

        ButtonGroup group = new ButtonGroup();
        btnStart.addActionListener(new Start());
        container.add(btnStart);
        btnStop.addActionListener(new Stop());
        container.add(btnStop);
    }

    class Start implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            isWork = true;
        }
    }
    class Stop implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            isWork = false;
        }
    }



}
