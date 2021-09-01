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

    /**
     *Метод translateCroneMessage(Cron cron) для получения обекта ResponseCron с сервера
     */
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
     *Метод getList() для получения обекта ResponseCron с сервера, который содержит историю запросов
     */
    public ResponseCron getList() {
        ResponseCron responseCron = null;

        /**
         * Этой строкой мы запрашиваем у сервера доступ на соединение
         */
        try (Socket clientSocket = new Socket("127.0.0.1", 8080)) {
            responseCron = runWithText1(clientSocket);
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Объект не передался");
        }
        return responseCron;
    }

    /**
     *Метод removeIndexBD() для удаления запроса из БД по переданному индексу
     */
    public void removeIndexBD(Cron cron) {
        /**
         * Этой строкой мы запрашиваем у сервера доступ на соединение
         */
        try (Socket clientSocket = new Socket("127.0.0.1", 8080)) {
            removeIndex(clientSocket, cron);
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Объект не передался");
        }
    }

    /**
     * Метод runWithText(Socket clientSocket, Cron cron) отправляет на сервер объект Cron и получает объект ResponseCron, потом возвращает его
     */
    private ResponseCron runWithText(Socket clientSocket, Cron cron) throws IOException, ClassNotFoundException {
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

    /**
     * Метод runWithText1(Socket clientSocket) получает объект ResponseCron, потом возвращает его
     */
    private ResponseCron runWithText1(Socket clientSocket) throws IOException, ClassNotFoundException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream())) {
            logger.info("Клиент заработал");
            Cron cron = new Cron();
            cron.setIndex(-1);
            objectOutputStream.writeObject(cron);
            logger.info("Клиент отправил объект");
            ResponseCron cronFromServer = (ResponseCron) objectInputStream.readObject();
            logger.info("Клиент получил объект");
            return cronFromServer;

        }
    }

    /**
     * Метод runWithText1(Socket clientSocket) получает объект ResponseCron, потом возвращает его
     */
    private void removeIndex(Socket clientSocket, Cron cron) throws IOException, ClassNotFoundException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream())) {
            logger.info("Клиент заработал");
            objectOutputStream.writeObject(cron);
            logger.info("Клиент отправил объект");
        }
    }
}
