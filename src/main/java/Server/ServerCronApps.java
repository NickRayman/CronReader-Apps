package Server;

import Common.CronHuman;
import Common.Cron;
import Common.ResponseCron;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Создаю класс сервера, который будет получать и отправлять информацию клиенту
 */
public class ServerCronApps {

    /**
     * Поле логгер
     */
    final static Logger logger = Logger.getLogger(ServerCronApps.class);

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


    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {

            while (true) {
                Socket clientSocket = serverSocket.accept();
                runWithCronFromClient(clientSocket);
            }
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Сервер не отправил сообщение");
        }
    }

    /**
     * Метод runWithCronFromClient(Socket clientSocket)
     */
    public void runWithCronFromClient(Socket clientSocket) throws IOException, ClassNotFoundException, NullPointerException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream())) {

            logger.info("Сервер, заработал");

            cronFromClient = (Cron) objectInputStream.readObject();

            logger.info("Сервер получили объект");
            if (cronFromClient.getIndex().equals(-2)) {
                validateCron.validate(cronFromClient);

                if (!validateCron.getErrors().isEmpty()) {

                    cronHumanWithClient.setErrors(validateCron.getErrors());

                    objectOutputStream.writeObject(cronHumanWithClient);

                    logger.info("Сервер отправил объект ResponseCron с заполненным списком ошибок");

                    objectOutputStream.flush(); // выталкиваем все из буфера

                    validateCron.getErrors().clear();
                    return;
                }

                cronHumanWithClient1 = cronReader.translate(cronFromClient);

                workingDatabase.request(cronFromClient, cronHumanWithClient1);

                cronHumanWithClient.setCronHuman(cronHumanWithClient1);

                objectOutputStream.writeObject(cronHumanWithClient);

                logger.info("Сервер отправил объект ResponseCron без ошибок");

                objectOutputStream.flush(); // выталкиваем все из буфера
            } else if (cronFromClient.getIndex().equals(-1)) {

                cronHumanWithClient.setDBTable(workingDatabase.requestDBTable());
                objectOutputStream.writeObject(cronHumanWithClient);
                logger.info("Сервер отправил историю запросов БД");

            } else {

                workingDatabase.removeIndexBD(cronFromClient);
                logger.info("Сервер удалил запрос из БД");

            }


        }

    }
}
