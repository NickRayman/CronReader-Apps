package test;

import java.sql.*;

/**
 * Создаю класс в котором будет проиводится проверка соединения с базой данных;
 */
public class connectBD {

    private static final String URL = "jdbc:mysql://localhost:3306/mysql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Asdf22030620";

    public static void main(String[] args) {

        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);

        } catch (SQLException e) {
            System.out.println("Неудалось загрузить класс драйвера!");
        }

        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement()){
            //statement.execute("insert into cronresult.cronresult (minutes, hours, dayMonth, month, week) values('1', '2', '3', '4', '5');");
            //statement.executeUpdate("UPDATE cronresult.cronresult SET minutes = '9999' WHERE id = 10;");
            int i = 3;
            while(i > 0){
                statement.addBatch("INSERT INTO cronresult.cronresult (minutes, hours, dayMonth, month, week) VALUES ('" + i + "', '2', '2', '2', '2');");
                statement.executeBatch();
                i--;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
