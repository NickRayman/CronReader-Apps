package Server;

import Client.TranslateServes;
import Common.CronHuman;
import Common.Cron;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.*;

public class ServerCronApps {

    public static final Logger logger = Logger.getLogger(ServerCronApps.class.getName());

    private static List<String> errors = new ArrayList<>();

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {

            while (true) {
                Socket clientSocket = serverSocket.accept();
                runWithCronFromClient(clientSocket);
            }
        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.WARNING,"Сервер не отправил сообщение");
            e.getStackTrace();
        }
    }

    /**
     *Метод runWithCronFromClient(Socket clientSocket)
     */
    public void runWithCronFromClient(Socket clientSocket) throws IOException, ClassNotFoundException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream())) {
            logger.log(Level.INFO,"Сервер, заработал");
            Cron cronFromClient = (Cron) objectInputStream.readObject();
           logger.log(Level.INFO,"Получили объект");

            cronFromClient = initializingCron(cronFromClient);

            try {

                WorkingDatabase.driverСheck();

            } catch (SQLException e) {
                logger.log(Level.INFO,"Неудалось загрузить класс драйвера!");
            }

            try (Connection connection = DriverManager.getConnection(WorkingDatabase.URL, WorkingDatabase.USERNAME, WorkingDatabase.PASSWORD);
                 Statement statement = connection.createStatement()) {

                if (!errors.isEmpty()) {
                    String errorsMassage = "";
                    for (String value : errors) {
                        errorsMassage = errorsMassage + value;
                    }
                    CronHuman cronHuman = new CronHuman();
                    cronHuman.errors(errorsMassage);
                    objectOutputStream.writeObject(cronHuman);
                   logger.log(Level.INFO,"Сервер отправил сообщение c ошибкой");
                    objectOutputStream.flush(); // выталкиваем все из буфера
                    errors.clear();
                    return;
                }

                CronReader cronReader = new CronReader();
                CronHuman cronHumanWithClient = cronReader.translate(cronFromClient);

                WorkingDatabase.request(statement, cronFromClient,cronHumanWithClient);

                objectOutputStream.writeObject(cronHumanWithClient);
                logger.log(Level.INFO,"Сервер отправил сообщение");
                objectOutputStream.flush(); // выталкиваем все из буфера


            } catch (SQLException e) {
                logger.log(Level.WARNING, "Не удалось подключиться к БД");
            }

        }

    }

    /**
     * Методы, которые определяют по паттернам правильно введено значение или нет, и если нет то заполняет лист ошибок
     */
    private static String validateMinute(String value) {
        Matcher matcher = Pattern.compile("^[0-9]$|^[1-5][0-9]$|^[*]$").matcher(value);
        if (!matcher.find()) {
            errors.add("Не правильно введены минуты, ");
            return null;
        }

        return value;
    }

    private static String validateHours(String value) {
        Matcher matcher = Pattern.compile("^[0-9]$|^[1-2][0-3]$|^[*]$").matcher(value);
        if (!matcher.find()) {
            errors.add("не правильно введены часы, ");
            return null;
        }

        return value;
    }

    private static String validateDayMonth(String value) {
        Matcher matcher = Pattern.compile("^[1-9]$|^[1-3][0-1]$|^[*]$").matcher(value);
        if (!matcher.find()) {
            errors.add("не правильно введены дни месяца, ");
            return null;
        }

        return value;
    }

    private static String validateMonth(String value) {
        Matcher matcher = Pattern.compile("^[1-9]$|^[1][0-2]$|^[*]$").matcher(value);
        if (!matcher.find()) {
            errors.add("не правильно введен месяц, ");
            return null;
        }

        return value;
    }

    private static String validateWeek(String value) {
        Matcher matcher = Pattern.compile("^[0-6]$|^[*]$").matcher(value);
        if (!matcher.find()) {
            errors.add("не правильно введен день недели.");
            return null;
        }

        return value;
    }

    /**
     * Метод для инициализации объекта Cron, введенными значениями в полях
     */
    private static Cron initializingCron(Cron cron){
        cron.addCronMinutes(validateMinute(cron.getMinutes()));
        cron.addCronHours(validateHours(cron.getHours()));
        cron.addCronDayMonth(validateDayMonth(cron.getDayMonth()));
        cron.addCronMonth(validateMonth(cron.getMonth()));
        cron.addCronWeek(validateWeek(cron.getWeek()));
        return cron;
    }
}
