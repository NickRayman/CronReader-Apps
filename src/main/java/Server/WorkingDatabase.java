package Server;

import Common.Cron;
import Common.CronHuman;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkingDatabase {
    /**
     * Константные аргументы для подключение к БД
     */
    protected static final String URL = "jdbc:mysql://localhost:3306/mysql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    protected static final String USERNAME = "root";
    protected static final String PASSWORD = "Asdf22030620";

    /**
     * Метод driverСheck(), который выбрасывает исключение,если драйвер не загружен
     */
    public static void driverСheck() throws SQLException{
        Driver driver = new com.mysql.cj.jdbc.Driver();
        DriverManager.registerDriver(driver);
    }

    /**
     * Метод request(Statement statement) отправляем запрос на добавление результата в базу данных
     */
    public static void request(Statement statement, Cron cron, CronHuman cronHumanWithClient) throws SQLException{
        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("E yyyy.MM.dd 'и время' hh:mm:ss a zzz");
        statement.execute("INSERT INTO cronresult.cronresult " +
                "(cron, cronHuman, data) VALUES " +
                "('" +cron.toString()+"'," +
                " '" +cronHumanWithClient.toString()+ "'," +
                " '" +formatForDateNow.format(date)+ "');");
    }

}
