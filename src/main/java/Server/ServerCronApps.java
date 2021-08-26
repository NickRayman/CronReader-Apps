package Server;

import Common.CronHuman;
import Common.Cron;
import Common.ResponseCron;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Создаю класс сервера, который будет получать и отправлять информацию клиенту
 */
public class ServerCronApps {

    /**
     * Неинициализированные поля, переменные классов WorkingDatabase, CronReader, CronHuman, Cron
     */
    private final WorkingDatabase workingDatabase;
    private final CronReader cronReader;
    private final ResponseCron cronHumanWithClient;
    private CronHuman cronHumanWithClient1;
    private Cron cronFromClient;
    private final ValidateCron validateCron;


    /**
     * Конструктор класса ServerCronApps
     */
    public ServerCronApps() {
        workingDatabase = new WorkingDatabase();
        cronReader = new CronReader();
        cronFromClient = new Cron();
        cronHumanWithClient = new ResponseCron();
        validateCron = new ValidateCron();
        cronHumanWithClient1 = new CronHuman();
    }

    /* public static final Logger logger = Logger.getLogger(ServerCronApps.class.getName());*/


    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {

            while (true) {
                Socket clientSocket = serverSocket.accept();
                runWithCronFromClient(clientSocket);
            }
        } catch (IOException | ClassNotFoundException e) {
            /*logger.log(Level.WARNING, "Сервер не отправил сообщение");*/
            e.getStackTrace();
        }
    }

    /**
     * Метод runWithCronFromClient(Socket clientSocket)
     */
    public void runWithCronFromClient(Socket clientSocket) throws IOException, ClassNotFoundException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream())) {
            /*logger.log(Level.INFO, "Сервер, заработал");*/
            cronFromClient = (Cron) objectInputStream.readObject();
            /*logger.log(Level.INFO, "Получили объект");*/

            validateCron.validate(cronFromClient);

            if (!validateCron.getErrors().isEmpty()) {

                cronHumanWithClient.setErrors(validateCron.getErrors());
                objectOutputStream.writeObject(cronHumanWithClient);
                /*logger.log(Level.INFO, "Сервер отправил сообщение c ошибкой");*/
                System.out.println("Сервер отправил сообщение c ошибкой");
                objectOutputStream.flush(); // выталкиваем все из буфера
                validateCron.getErrors().clear();
                return;
            }


            cronHumanWithClient1 = cronReader.translate(cronFromClient);

            workingDatabase.request(cronFromClient, cronHumanWithClient1);

            cronHumanWithClient.setCronHuman(cronHumanWithClient1);

            objectOutputStream.writeObject(cronHumanWithClient);

            System.out.println("Сервер отправил сообщение");

            /*logger.log(Level.INFO, "Сервер отправил сообщение");*/
            objectOutputStream.flush(); // выталкиваем все из буфера
        }

    }
}
