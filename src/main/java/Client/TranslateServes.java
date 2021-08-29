package Client;

import Common.Cron;
import Common.ResponseCron;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

public class TranslateServes {

    /**
     * Поле логгер
     */
    final static org.apache.log4j.Logger logger = Logger.getLogger(TranslateServes.class);

    public ResponseCron translateCroneMessage(Cron cron) {
        ResponseCron responseCron = null;

        /**
         * Этой строкой мы запрашиваем у сервера доступ на соединение
         */
        try (Socket clientSocket = new Socket("127.0.0.1", 8080)) {
            responseCron = runWithText(clientSocket, cron);
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Объект не передался");
        }
        return responseCron;
    }

    /**
     * Метод runWithText(Socket clientSocket, Cron cron) отправляет на сервер объект Cron и получает объект CronHuman, потом возвращает его
     */
    public ResponseCron runWithText(Socket clientSocket, Cron cron) throws IOException, ClassNotFoundException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream())) {
            logger.info("Клиент заработал");

            objectOutputStream.writeObject(cron);
            logger.info("Клиент отправил объект");
            objectOutputStream.flush();

            ResponseCron cronFromServer = (ResponseCron) objectInputStream.readObject();
            logger.info("Клиент получил объект");
            return cronFromServer;

        }
    }
}
