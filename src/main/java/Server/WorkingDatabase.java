package Server;

import Common.Cron;
import Common.CronHuman;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Создаю класс, который будет проверять подключен ли драйвер и отправлять запросы на базу данных
 */
public class WorkingDatabase {

    /**
     * Конструктор класса WorkingDatabase
     */
    public WorkingDatabase() {

        WorkingDatabase.driverСheck();

    }

    /**
     * Константные аргументы для подключение к БД
     */
    protected static final String URL = "jdbc:mysql://localhost:3306/mysql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    protected static final String USERNAME = "root";
    protected static final String PASSWORD = "Asdf22030620";

    /**
     * Метод driverСheck(), который выбрасывает исключение,если драйвер не загружен
     */
    public static void driverСheck() {

        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            /*logger.log(Level.INFO, "Неудалось загрузить класс драйвера!");*/
        }
    }

    /**
     * Метод request(Statement statement) отправляем запрос на добавление результата в базу данных
     */
    public void request(Cron cron, CronHuman cronHumanWithClient) {
        try (Connection connection = DriverManager.getConnection(WorkingDatabase.URL, WorkingDatabase.USERNAME, WorkingDatabase.PASSWORD);
             Statement statement = connection.createStatement()) {

            Date date = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("E yyyy.MM.dd 'и время' hh:mm:ss a zzz");
            statement.execute("INSERT INTO cronresult.cronresult " +
                    "(cron, cronHuman, data) VALUES " +
                    "('" + cron.toString() + "'," +
                    " '" + cronHumanWithClient.toString() + "'," +
                    " '" + formatForDateNow.format(date) + "');");
        } catch (SQLException e) {
            /*logger.log(Level.WARNING, "Не удалось подключиться к БД");*/
        }

    }

}
