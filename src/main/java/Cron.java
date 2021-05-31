import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Создаю класс в котором будет проиводится ввод крон выражения;
 */
public class Cron {

    /**
     * Создаю 5 переменных типа String для их дальнейшей инициализации;
     */
    private String minutes = "0";
    private String hours = "0";
    private String dayMonth = "0";
    private String month = "0";
    private String week = "0";
    /**
     * BufferedReader reader, который будет читать ввод с консоли;
     */
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Переменная str типа String для присваивания объекта переменной reader;
     */
    private String str;

    /**
     * Метод addCronMinutes() будет инициализировать поле second нашего объекта класса Cron;
     */
    public void addCronMinutes(String word) throws Exception {
        /*System.out.println("Введите минуты(* или любое число от 0 до 59): ");*/
        String str = word;
        /*try {
            while (Integer.parseInt(str) < 0 || Integer.parseInt(str) > 59) {
                System.out.println("Пожалуйста введите корректные данные (* или любое число от 0 до 59): ");
                str = reader.readLine();
            }
        } catch (NumberFormatException e) {
            while (!str.equals("*")) {
                System.out.println("Пожалуйста введите корректные данные (* или любое число от 0 до 59): ");
                str = reader.readLine();
            }
        }*/
        this.minutes = str;
    }

    /**
     * Метод addCronHours() будет инициализировать поле hours нашего объекта класса Cron;
     */
    public void addCronHours(String word) throws Exception {
        /*System.out.println("Введите часы(* или любое число от 1 до 23): ");*/
        str = word;
        /*try {
            while (Integer.parseInt(str) < 0 || Integer.parseInt(str) > 23) {
                System.out.println("Пожалуйста введите корректные данные (* или любое число от 0 до 23): ");
                str = reader.readLine();
            }
        } catch (NumberFormatException e) {
            while (!str.equals("*")) {
                System.out.println("Пожалуйста введите корректные данные (* или любое число от 0 до 23): ");
                str = reader.readLine();
            }
        }*/
        this.hours = str;
    }

    /**
     * Метод addCronDayMonth() будет инициализировать поле dayMonth нашего объекта класса Cron;
     */
    public void addCronDayMonth(String word) throws Exception {
        /*System.out.println("Введите день месяца(* или любое число от 1 до 31): ");*/
        str = word;
        /*try {
            while (Integer.parseInt(str) < 1 || Integer.parseInt(str) > 31) {
                System.out.println("Пожалуйста введите корректные данные (* или любое число от 1 до 31): ");
                str = reader.readLine();
            }
        } catch (NumberFormatException e) {
            while (!str.equals("*")) {
                System.out.println("Пожалуйста введите корректные данные (* или любое число от 1 до 31): ");
                str = reader.readLine();
            }
        }*/
        this.dayMonth = str;
    }

    /**
     * Метод addCronMonth() будет инициализировать поле month нашего объекта класса Cron;
     */
    public void addCronMonth(String word) throws Exception {
        /*System.out.println("Введите месяц(* или любое число от 1 до 12): ");*/
        str = word;
        /*try {
            while (Integer.parseInt(str) < 1 || Integer.parseInt(str) > 12) {
                System.out.println("Пожалуйста введите корректные данные (* или любое число от 1 до 12): ");
                str = reader.readLine();
            }
        } catch (NumberFormatException e) {
            while (!str.equals("*")) {
                System.out.println("Пожалуйста введите корректные данные (* или любое число от 1 до 12): ");
                str = reader.readLine();
            }
        }*/
        this.month = str;
    }

    /**
     * Метод addCronWeek() будет инициализировать поле week нашего объекта класса Cron
     */
    public void addCronWeek(String word) throws Exception {
        /*System.out.println("Введите день недели(* или любое число от 0 до 6): ");*/
        str = word;
        /*try {
            while (Integer.parseInt(str) < 0 || Integer.parseInt(str) > 6) {
                System.out.println("Пожалуйста введите корректные данные (* или любое число от 0 до 6): ");
                str = reader.readLine();
            }
        } catch (NumberFormatException e) {
            while (!str.equals("*")) {
                System.out.println("Пожалуйста введите корректные данные (* или любое число от 0 до 6): ");
                str = reader.readLine();
            }
        }*/
        this.week = str;
    }

    /**
     * Гетеры
     */
    public String getMinutes() {
        return minutes;
    }

    public String getHours() {
        return hours;
    }

    public String getDayMonth() {
        return dayMonth;
    }

    public String getMonth() {
        return month;
    }

    public String getWeek() {
        return week;
    }

}
