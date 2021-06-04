package Client;

import Common.Cron;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class SimpleGUI extends JFrame {
    private JButton button = new JButton("Расшифровать");
    private JTextField minutes = new JTextField("", 3);
    private JTextField hours = new JTextField("", 3);
    private JTextField dayMonth = new JTextField("", 3);
    private JTextField month = new JTextField("", 3);
    private JTextField week = new JTextField("", 3);
    private JLabel label = new JLabel("Input:");
    private JTextArea area = new JTextArea(5, 40);

    public SimpleGUI() {
        super("Client.CronReader");
        this.setSize(450, 250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new FlowLayout());
        container.add(label);
        container.add(minutes);
        container.add(hours);
        container.add(dayMonth);
        container.add(month);
        container.add(week);
        button.addActionListener(new ButtonEventListener());
        container.add(button);
        area.setLineWrap(true);
        container.add(new JScrollPane(area));
    }


    class ButtonEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TranslateServes translateServes = new TranslateServes();
            /**
             * Создаю обект Cron
             */
            Cron cron = new Cron();
            /**
             * Инициализирую поля объекта Cron, введенными значениями в полях
             */
            cron.addCronMinutes(minutes.getText());
            cron.addCronHours(hours.getText());
            cron.addCronDayMonth(dayMonth.getText());
            cron.addCronMonth(month.getText());
            cron.addCronWeek(week.getText());
            CronHuman cronHuman;
            cronHuman = translateServes.translateCroneMessage(cron);
            area.setText(cronHuman.toString());

        }
    }

    /**
     * Геттеры
     */
    public JTextField getMinutes() {
        return minutes;
    }

    public JTextField getHours() {
        return hours;
    }

    public JTextField getDayMonth() {
        return dayMonth;
    }

    public JTextField getMonth() {
        return month;
    }

    public JTextField getWeek() {
        return week;
    }
}