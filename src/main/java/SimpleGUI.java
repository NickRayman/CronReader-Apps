import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleGUI extends JFrame {
    protected JButton button = new JButton("Расшифровать");
    protected JTextField input1 = new JTextField("", 5);
    protected JTextField input2 = new JTextField("", 5);
    protected JTextField input3 = new JTextField("", 5);
    protected JTextField input4 = new JTextField("", 5);
    protected JTextField input5 = new JTextField("", 5);
    protected JLabel label = new JLabel("Input:");
    protected List<String> errors = new ArrayList<>();

    public SimpleGUI() {
        super("CronReader");
        this.setBounds(100, 100, 1000, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(1, 2, 2, 2));
        container.add(label);
        container.add(input1);
        container.add(input2);
        container.add(input3);
        container.add(input4);
        container.add(input5);


        ButtonGroup group = new ButtonGroup();
        button.addActionListener(new ButtonEventListener());
        container.add(button);
    }
    private String validateMinute(String value) {
        Matcher matcher = Pattern.compile("[012]").matcher(value);
        if (!matcher.find()){
            errors.add("Не правильно введены минуты; ");
            return null;
        }
        return value;
    }

    private String validateHours(String value) {
        Matcher matcher = Pattern.compile("[012]").matcher(value);
        if (!matcher.find()){
            errors.add("Не правильно введены часы; ");
            return null;
        }
        return value;
    }

    private String validateDayMonth(String value) {
        Matcher matcher = Pattern.compile("[012]").matcher(value);
        if (!matcher.find()){
            errors.add("Не правильно введены дни месяца; ");
            return null;
        }
        return value;
    }

    private String validateMonth(String value) {
        Matcher matcher = Pattern.compile("[012]").matcher(value);
        if (!matcher.find()){
            errors.add("Не правильно введен месяц; ");
            return null;
        }
        return value;
    }

    private String validateWeek(String value) {
        Matcher matcher = Pattern.compile("[012]").matcher(value);
        if (!matcher.find()){
            errors.add("Не правильно введен день недели; ");
            return null;
        }
        return value;
    }

    class ButtonEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Cron cron = new Cron();
            try {
                cron.addCronMinutes(validateMinute(input1.getText()));
                cron.addCronHours(validateHours(input2.getText()));
                cron.addCronDayMonth(validateDayMonth(input3.getText()));
                cron.addCronMonth(validateMonth(input4.getText()));
                cron.addCronWeek(validateWeek(input5.getText()));
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            if (!errors.isEmpty()){
                String errorsMassage = "";
                for(String value : errors){
                    errorsMassage = errorsMassage + value;
                }
                JOptionPane.showMessageDialog(null, errorsMassage, "Результат", JOptionPane.PLAIN_MESSAGE);
                errors.clear();
                return;
            }
            CronReader cronReader = new CronReader();
            String str = cronReader.translate(cron);

            JOptionPane.showMessageDialog(null, str, "Результат", JOptionPane.PLAIN_MESSAGE);
        }
    }



}