package Client;

import java.util.logging.Level;
import java.util.logging.Logger;
import Common.Cron;
import Common.CronHuman;

import java.io.*;
import java.net.Socket;

public class TranslateServes {
    public static final Logger logger = Logger.getLogger(TranslateServes.class.getName());
    public CronHuman translateCroneMessage(Cron cron){
        CronHuman cronHuman = null;

        /**
         * Этой строкой мы запрашиваем у сервера доступ на соединение
         */
        try(Socket clientSocket = new Socket("127.0.0.1", 8080)){
            cronHuman = runWithText(clientSocket, cron);
        }
        catch (IOException | ClassNotFoundException e){
            logger.log(Level.WARNING,"Объект не передался");
        }
        return cronHuman;
    }

    /**
     *Метод runWithText(Socket clientSocket, Cron cron) отправляет на сервер объект Cron и получает объект CronHuman, потом возвращает его
     */
    public CronHuman runWithText(Socket clientSocket, Cron cron) throws IOException, ClassNotFoundException {
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream())) {
            logger.log(Level.INFO,"Клиент заработал");

            objectOutputStream.writeObject(cron);
            logger.log(Level.INFO,"Клиент отправил объект");
            objectOutputStream.flush();

            CronHuman cronFromServer = (CronHuman) objectInputStream.readObject();
           logger.log(Level.INFO,"Клиент получил объект");
            return cronFromServer;

            }
    }
}
