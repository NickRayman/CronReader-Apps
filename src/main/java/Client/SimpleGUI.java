package Client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class SimpleGUI extends JFrame {
    protected JButton button = new JButton("Расшифровать");
    protected JTextField input1 = new JTextField("", 3);
    protected JTextField input2 = new JTextField("", 3);
    protected JTextField input3 = new JTextField("", 3);
    protected JTextField input4 = new JTextField("", 3);
    protected JTextField input5 = new JTextField("", 3);
    protected JLabel label = new JLabel("Input:");
    protected JTextArea area = new JTextArea(5, 40);

    public SimpleGUI() {
        super("Client.CronReader");
        this.setSize(450, 250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new FlowLayout());
        container.add(label);
        container.add(input1);
        container.add(input2);
        container.add(input3);
        container.add(input4);
        container.add(input5);
        ButtonGroup group = new ButtonGroup();
        button.addActionListener(new ButtonEventListener());
        container.add(button);
        area.setLineWrap(true);
        container.add(new JScrollPane(area));
    }


    class ButtonEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                try {

                    // адрес - локальный хост, порт - 4004, такой же как у сервера
                    Client.clientSocket = new Socket("localhost", 4004); // этой строкой мы запрашиваем
                    //  у сервера доступ на соединение
                    // читать соообщения с сервера
                    Client.in = new BufferedReader(new InputStreamReader(Client.clientSocket.getInputStream()));
                    // писать туда же
                    Client.out = new BufferedWriter(new OutputStreamWriter(Client.clientSocket.getOutputStream()));
                    Client.out.write(input1.getText());
                    Client.out.flush();

                    Client.out.write(input2.getText());
                    Client.out.flush();

                    Client.out.write(input3.getText());
                    Client.out.flush();

                    Client.out.write(input4.getText());
                    Client.out.flush();

                    Client.out.write(input5.getText());
                    Client.out.flush();

                    String serverWord = Client.in.readLine(); // ждём, что скажет сервер
                    area.setText(serverWord);

                } finally {// в любом случае необходимо закрыть сокет и потоки
                    System.out.println("Клиент был закрыт...");
                    Client.clientSocket.close();
                    Client.in.close();
                    Client.out.close();
                }
            } catch (IOException exception) {
                System.out.println(exception);
            }
        }
    }


}