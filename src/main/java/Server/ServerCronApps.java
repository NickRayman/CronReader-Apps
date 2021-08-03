package Server;

import Client.CronHuman;
import Client.CronReader;
import Common.Cron;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.*;

public class ServerCronApps {
    /**
     * Константные аргументы для подключение к БД
     */
    private static final String URL = "jdbc:mysql://localhost:3306/mysql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Asdf22030620";

    private static List<String> errors = new ArrayList<>();


    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {

            while (true) {
                Socket clientSocket = serverSocket.accept();
                runWithObject(clientSocket);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Сервер не отправил сообщение");
            e.getStackTrace();
        }
    }

    public void runWithObject(Socket clientSocket) throws IOException, ClassNotFoundException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream())) {
            System.out.println("Сервер, заработал");
            Cron cronFromClient = (Cron) objectInputStream.readObject();
            System.out.println("Получили объект");

            /**
             * Заполним список нулями, так как если элемент списка null, то пользователь ввел верное значение
             */
            errors = nullErrors(errors);

            cronFromClient.addCronMinutes(validateMinute(cronFromClient.getMinutes()));
            cronFromClient.addCronHours(validateHours(cronFromClient.getHours()));
            cronFromClient.addCronDayMonth(validateDayMonth(cronFromClient.getDayMonth()));
            cronFromClient.addCronMonth(validateMonth(cronFromClient.getMonth()));
            cronFromClient.addCronWeek(validateWeek(cronFromClient.getWeek()));

            try {
                Driver driver = new com.mysql.cj.jdbc.Driver();
                DriverManager.registerDriver(driver);

            } catch (SQLException e) {
                System.out.println("Неудалось загрузить класс драйвера!");
            }

            try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                 Statement statement = connection.createStatement()) {

                /**
                 * Булевая переменная - флаг
                 */
                boolean notNull = false;

                for (String value : errors){
                    if (value != null){
                        notNull = true;
                        break;
                    }
                }

                if (notNull) {
                    String errorsMassage = "";
                    for (String value : errors) {
                        errorsMassage = errorsMassage + value;
                    }
                    CronHuman cronHuman = new CronHuman();
                    cronHuman.errors(errorsMassage);

                    /**
                     * Отправляем запрос на добавление результата в базу данных
                     */
                    statement.execute("INSERT INTO cronresult.cronresult " +
                            "(minutes, hours, dayMonth, month, week) VALUES " +
                            "('" +errors.get(0)+"'," +
                            " '" +errors.get(1)+ "'," +
                            " '" +errors.get(2)+ "'," +
                            " '" +errors.get(3)+ "'," +
                            " '" +errors.get(4)+ "');");

                    objectOutputStream.writeObject(cronHuman);
                    System.out.println("Сервер отправил сообщение c ошибкой");
                    objectOutputStream.flush(); // выталкиваем все из буфера
                    errors.clear();
                    return;
                }

                CronReader cronReader = new CronReader();
                CronHuman cronHumanWithClient = cronReader.translate(cronFromClient);

                /**
                 * Отправляем запрос на добавление результата в базу данных
                 */
                statement.execute("INSERT INTO cronresult.cronresult " +
                        "(minutes, hours, dayMonth, month, week) VALUES " +
                        "('" +cronHumanWithClient.getMinutesHuman()+"'," +
                        " '" +cronHumanWithClient.getHoursHuman()+ "'," +
                        " '" +cronHumanWithClient.getDayMonthHuman()+ "'," +
                        " '" +cronHumanWithClient.getMonthHuman()+ "'," +
                        " '" +cronHumanWithClient.getWeekHuman()+ "');");

                objectOutputStream.writeObject(cronHumanWithClient);
                System.out.println("Сервер отправил сообщение");
                objectOutputStream.flush(); // выталкиваем все из буфера


            } catch (SQLException e) {
                System.out.println("Не удалось подключиться к БД");
            }

        }

    }

    /**
     * Методы, которые определяют по паттернам правильно введено значение или нет, и если нет то заполняет лист ошибок
     */
    private static String validateMinute(String value) {
        Matcher matcher = Pattern.compile("^[0-9]$|^[1-5][0-9]$|^[*]$").matcher(value);
        if (!matcher.find()) {
            errors.set(0,"Не правильно введены минуты");
            return null;
        }

        return value;
    }

    private static String validateHours(String value) {
        Matcher matcher = Pattern.compile("^[0-9]$|^[1-2][0-3]$|^[*]$").matcher(value);
        if (!matcher.find()) {
            errors.set(1,"Не правильно введены часы");
            return null;
        }

        return value;
    }

    private static String validateDayMonth(String value) {
        Matcher matcher = Pattern.compile("^[1-9]$|^[1-3][0-1]$|^[*]$").matcher(value);
        if (!matcher.find()) {
            errors.set(2,"Не правильно введены дни месяца");
            return null;
        }

        return value;
    }

    private static String validateMonth(String value) {
        Matcher matcher = Pattern.compile("^[1-9]$|^[1][0-2]$|^[*]$").matcher(value);
        if (!matcher.find()) {
            errors.set(3,"Не правильно введен месяц");
            return null;
        }

        return value;
    }

    private static String validateWeek(String value) {
        Matcher matcher = Pattern.compile("^[0-6]$|^[*]$").matcher(value);
        if (!matcher.find()) {
            errors.set(4,"Не правильно введен день недели");
            return null;
        }

        return value;
    }

    /**
     *Метод, который заполнит наш массив нулями
     */
    private static List<String> nullErrors(List<String> errors){

        for(int i = 0; i < 5; i++){

            errors.add(null);
        }
        return errors;
    }
}
