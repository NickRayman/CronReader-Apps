package Client;

import Common.Cron;
import Common.CronHuman;
import Server.WorkingDatabase;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;


public class SimpleGUI extends JFrame {
    private JButton button = new JButton("Расшифровать");
    private static JTextField minutes = new JTextField("", 3);
    private static JTextField hours = new JTextField("", 3);
    private static JTextField dayMonth = new JTextField("", 3);
    private static JTextField month = new JTextField("", 3);
    private static JTextField week = new JTextField("", 3);
    private JLabel label = new JLabel("Input:");
    private JTextArea area = new JTextArea(5, 40);

    public SimpleGUI() {
        super("Server.CronReader");
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
            Cron cron = initializingCron();
            CronHuman cronHuman = translateServes.translateCroneMessage(cron);
            Date date = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("E yyyy.MM.dd 'и время' hh:mm:ss a zzz");
            area.append(formatForDateNow.format(date) + ": " + cron.toString() + " -> " + cronHuman.toString() + "\n");
        }
    }

    /**
     * Метод initializingCron() для инициализации объекта Cron, введенными значениями в полях
     */
    private static Cron initializingCron(){
        /**
         * Создаю обект Cron
         */
        Cron cron = new Cron();

        cron.addCronMinutes(minutes.getText());
        cron.addCronHours(hours.getText());
        cron.addCronDayMonth(dayMonth.getText());
        cron.addCronMonth(month.getText());
        cron.addCronWeek(week.getText());

        return cron;
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