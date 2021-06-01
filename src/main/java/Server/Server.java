package Server;

import Client.Cron;
import Client.CronReader;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Создаю класс в котором будет производится создания и возовы функций;
 */
public class Server {
    private static List<String> errors = new ArrayList<>();
    /**
     * Сокет для общения
     */
    private static Socket clientSocket;
    /**
     * Серверсокет
     */
    private static ServerSocket server;
    /**
     * Поток чтения из сокета
     */
    private static BufferedReader in;
    /**
     * Поток записи в сокет
     */
    private static BufferedWriter out;
    /**
     * Крон переменная для объекта крон
     */
    private static Cron cron;
    /**
     * КронРидер переменная для объекта кронРидер
     */
    private static CronReader cronReader;
    public static void main(String[] args) {
        try {
            try {
                server = new ServerSocket(4004);//серверсокет прошслушивает порт 4004
                System.out.println("Сервер запущен");//объявление сервера о своем запуске
                clientSocket = server.accept();// accept() буду ждать пока кто-нибудь не захочет подключиться
                try {
                    // установив связь и воссоздав сокет для общения с клиентом можно перейти
                    // к выполнению работы.
                    cron = new Cron();
                    // теперь мы можем принимать сообщения
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    // и отправлять
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                    String word = in.readLine(); // ждём пока клиент что-нибудь нам напишет
                    try {
                        cron.addCronMinutes(validateMinute(word));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                    word = in.readLine(); // ждём пока клиент что-нибудь нам напишет
                    try {
                        cron.addCronHours(validateHours(word));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                    word = in.readLine(); // ждём пока клиент что-нибудь нам напишет
                    try {
                        cron.addCronDayMonth(validateDayMonth(word));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                    word = in.readLine(); // ждём пока клиент что-нибудь нам напишет
                    try {
                        cron.addCronMonth(validateMonth(word));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                    word = in.readLine(); // ждём пока клиент что-нибудь нам напишет
                    try {
                        cron.addCronWeek(validateWeek(word));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }


                    if (!errors.isEmpty()) {
                        String errorsMassage = "";
                        for (String value : errors) {
                            errorsMassage = errorsMassage + value;
                        }
                        out.write(errorsMassage);
                        out.flush(); // выталкиваем все из буфера
                        errors.clear();
                        return;
                    }
                    cronReader = new CronReader();
                    String str = cronReader.translate(cron);
                    out.write(str);
                    out.flush(); // выталкиваем все из буфера

                } finally {// в любом случае сокет будет закрыт
                    clientSocket.close();
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Сервер закрыт!");
                server.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static String validateMinute(String value) {
        Matcher matcher = Pattern.compile("[0-59]").matcher(value);
        if (!matcher.find()) {
            errors.add("Не правильно введены минуты; ");
            return null;
        }
        return value;
    }

    private static String validateHours(String value) {
        Matcher matcher = Pattern.compile("[0-23]").matcher(value);
        if (!matcher.find()) {
            errors.add("Не правильно введены часы; ");
            return null;
        }
        return value;
    }

    private static String validateDayMonth(String value) {
        Matcher matcher = Pattern.compile("[1-31]").matcher(value);
        if (!matcher.find()) {
            errors.add("Не правильно введены дни месяца; ");
            return null;
        }
        return value;
    }

    private static String validateMonth(String value) {
        Matcher matcher = Pattern.compile("[1-12]").matcher(value);
        if (!matcher.find()) {
            errors.add("Не правильно введен месяц; ");
            return null;
        }
        return value;
    }

    private static String validateWeek(String value) {
        Matcher matcher = Pattern.compile("[0-6]").matcher(value);
        if (!matcher.find()) {
            errors.add("Не правильно введен день недели; ");
            return null;
        }
        return value;
    }
}
