import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;

public class SimpleGUI extends JFrame {
    protected JButton button = new JButton("Расшифровать");
    protected JTextField input1 = new JTextField("", 5);
    protected JTextField input2 = new JTextField("", 5);
    protected JTextField input3 = new JTextField("", 5);
    protected JTextField input4 = new JTextField("", 5);
    protected JTextField input5 = new JTextField("", 5);
    protected JLabel label = new JLabel("Input:");

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

    class ButtonEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Cron cron = new Cron();
            try {
                cron.addCronMinutes(input1.getText());
                cron.addCronHours(input2.getText());
                cron.addCronDayMonth(input3.getText());
                cron.addCronMonth(input4.getText());
                cron.addCronWeek(input5.getText());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            String message = "";
            message += Solution.cronHuman(Solution.cronReader(cron));
            JOptionPane.showMessageDialog(null, message, "Результат", JOptionPane.PLAIN_MESSAGE);
        }
    }

}