package Client;

import Common.Cron;
import Common.ResponseCron;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import org.apache.log4j.Logger;

import javax.swing.*;

/**
 * Создаю класс, который будет на основе билиотеки Swing создавать оконное приложение и отправлять запросы на сервер
 */
public class SimpleGUI extends JFrame {

    /**
     * Поле Cron и ResponseCron выражения
     */
    private Cron cron;
    private ResponseCron cronHuman;

    /**
     * Поля, для отпарвки запросов на сервер
     */

    private TranslateServes translateServes;

    /**
     * Поля, элементы интерфейса главного окна
     */

    private JButton button = new JButton("Расшифровать");
    private JButton button1 = new JButton("Показать историю запросов в БД");
    private static JTextField minutes = new JTextField("", 3);
    private static JTextField hours = new JTextField("", 3);
    private static JTextField dayMonth = new JTextField("", 3);
    private static JTextField month = new JTextField("", 3);
    private static JTextField week = new JTextField("", 3);
    private JLabel label = new JLabel("Input:");
    private JTextArea area = new JTextArea(5, 40);

    /**
     * Окно,которое вылазеет и показывает запросы в БД
     */
    private SimpleGUI1 app;

    /**
     * Список истории запросов в БД
     */
    private JList<String> list;

    /**
     * Кнопка удаления элемента списка БД
     */
    private JButton button2 = new JButton("Удалить");


    /**
     * Поле логгер
     */
    final static Logger logger = Logger.getLogger(SimpleGUI.class);

    /**
     * Конструктор класс SimpleGUI
     */
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
        button1.addActionListener(new ButtonEventListener1());
        container.add(button1);
        area.setLineWrap(true);
        container.add(new JScrollPane(area));

    }


    /**
     * Класс ButtonEventListener для реализации кнопки button
     */
    class ButtonEventListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            translateServes = new TranslateServes();
            cron = initializingCron();

            cronHuman = translateServes.translateCroneMessage(cron);

            if (cronHuman.getErrors().isEmpty()) {
                logger.info("Сервер передал объект ResponseCron без ошибок");

                Date date = new Date();
                SimpleDateFormat formatForDateNow = new SimpleDateFormat("E yyyy.MM.dd 'и время' hh:mm:ss a zzz");
                area.append(formatForDateNow.format(date) + ": " + cron.toString() + " -> " + cronHuman.getCronHuman().toString() + "\n");
            } else {
                logger.info("Сервер передал объект ResponseCron с заполненным списком ошибок");
                String errorsMassage = "";
                for (String value : cronHuman.getErrors()) {
                    errorsMassage = errorsMassage + value;
                }
                cronHuman.getCronHuman().errors(errorsMassage);
                Date date = new Date();
                SimpleDateFormat formatForDateNow = new SimpleDateFormat("E yyyy.MM.dd 'и время' hh:mm:ss a zzz");
                area.append(formatForDateNow.format(date) + ": " + cron.toString() + " -> " + cronHuman.getCronHuman().toString() + "\n");
            }
        }
    }

    /**
     * Класс ButtonEventListener1 для реализации кнопки button1
     */
    class ButtonEventListener1 implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {

            if (app == null) {
                app = new SimpleGUI1();
                app.setVisible(true);
                app.setResizable(false);
                /*GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
                Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
                int x = (int) rect.getMaxX() - getWidth();
                int y = 0;*/
                app.setLocation(150, 0);
            }


        }
    }

    /**
     * Класс ButtonEventListener2 для реализации кнопки button2
     */
    class ButtonEventListener2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            DefaultListModel model = (DefaultListModel) list.getModel();
            int selectIndex = list.getSelectedIndex();

            String str = (String) model.getElementAt(selectIndex);
            Integer index = Integer.parseInt(str.substring(str.indexOf("=") + 1, str.indexOf(",")));
            cron = new Cron();
            cron.setIndex(index);
            translateServes.removeIndexBD(cron);
            model.remove(selectIndex);
        }
    }


    /**
     * Класс SimpleGUI1, который отвечает за окно в котром показывается список запросов
     */
    class SimpleGUI1 extends JFrame {
        /**
         * Конструктор класс SimpleGUI1
         */
        public SimpleGUI1() {
            super("Server.SQL_History");
            this.setSize(1200, 250);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Container container = this.getContentPane();
            container.setLayout(new FlowLayout());
            button2.addActionListener(new ButtonEventListener2());
            container.add(button2);
            translateServes = new TranslateServes();

            cronHuman = translateServes.getList();

            list = new JList<String>(cronHuman.getDBTable());
            container.add(new JScrollPane(list));
        }
    }

    /**
     * Метод initializingCron() для инициализации объекта Cron, введенными значениями в полях
     */
    private static Cron initializingCron() {
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
}