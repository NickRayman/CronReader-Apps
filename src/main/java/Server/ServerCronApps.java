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

public class ServerCronApps {
    private static List<String> errors = new ArrayList<>();
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(8080)){

            while (true){
                Socket clientSocket = serverSocket.accept();
                runWithObject(clientSocket);
            }
        }
        catch (IOException | ClassNotFoundException e){
            System.out.println("Сервер не отправил сообщение");
            e.getStackTrace();
        }
    }

    public void runWithObject(Socket clientSocket) throws IOException, ClassNotFoundException{
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream())){
            System.out.println("Сервер, заработал");
            Cron cronFromClient = (Cron) objectInputStream.readObject();
            System.out.println("Получили объект");
            cronFromClient.addCronMinutes(validateMinute(cronFromClient.getMinutes()));
            cronFromClient.addCronHours(validateHours(cronFromClient.getHours()));
            cronFromClient.addCronDayMonth(validateDayMonth(cronFromClient.getDayMonth()));
            cronFromClient.addCronMonth(validateMonth(cronFromClient.getMonth()));
            cronFromClient.addCronWeek(validateWeek(cronFromClient.getWeek()));

            if (!errors.isEmpty()) {
                String errorsMassage = "";
                for (String value : errors) {
                    errorsMassage = errorsMassage + value;
                }
                CronHuman cronHuman = new CronHuman();
                cronHuman.errors(errorsMassage);
                objectOutputStream.writeObject(cronHuman);
                System.out.println("Сервер отправил сообщение");
                objectOutputStream.flush(); // выталкиваем все из буфера
                errors.clear();
                return;
            }

                 CronReader cronReader = new CronReader();
                 CronHuman cronHumanWithClient = cronReader.translate(cronFromClient);
                 objectOutputStream.writeObject(cronHumanWithClient);
                 System.out.println("Сервер отправил сообщение");
                 objectOutputStream.flush();

             }

    }

    /**
     *Методы, которые определяют по паттернам правильно введено значение или нет, и если нет то заполняет лист ошибок
     */
    private static String validateMinute(String value) {
        Matcher matcher = Pattern.compile("^[0-9]$|^[1-5][0-9]$|^[*]$").matcher(value);
        if (!matcher.find()) {
            errors.add("Не правильно введены минуты; ");
            return null;
        }
        return value;
    }

    private static String validateHours(String value) {
        Matcher matcher = Pattern.compile("^[0-9]$|^[1-2][0-3]$|^[*]$").matcher(value);
        if (!matcher.find()) {
            errors.add("Не правильно введены часы; ");
            return null;
        }
        return value;
    }

    private static String validateDayMonth(String value) {
        Matcher matcher = Pattern.compile("^[1-9]$|^[1-3][0-1]$|^[*]$").matcher(value);
        if (!matcher.find()) {
            errors.add("Не правильно введены дни месяца; ");
            return null;
        }
        return value;
    }

    private static String validateMonth(String value) {
        Matcher matcher = Pattern.compile("^[1-9]$|^[1][0-2]$|^[*]$").matcher(value);
        if (!matcher.find()) {
            errors.add("Не правильно введен месяц; ");
            return null;
        }
        return value;
    }

    private static String validateWeek(String value) {
        Matcher matcher = Pattern.compile("^[0-6]$|^[*]$").matcher(value);
        if (!matcher.find()) {
            errors.add("Не правильно введен день недели; ");
            return null;
        }
        return value;
    }
}
