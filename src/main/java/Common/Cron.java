package Common;

import java.io.Serializable;

/**
 * Создаю класс в котором будет проиводится ввод крон выражения;
 */
public class Cron implements Serializable {

    /**
     * Создаю 5 переменных типа String для их дальнейшей инициализации;
     */
    private String minutes = "0";
    private String hours = "0";
    private String dayMonth = "0";
    private String month = "0";
    private String week = "0";

    /**
     * Переменная str типа String для присваивания объекта переменной reader;
     */
    private String str;

    /**
     * Метод addCronMinutes() будет инициализировать поле second нашего объекта класса Common.Cron;
     */
    public void addCronMinutes(String word){
        this.minutes = word;
    }

    /**
     * Метод addCronHours() будет инициализировать поле hours нашего объекта класса Common.Cron;
     */
    public void addCronHours(String word){
        this.hours = word;
    }

    /**
     * Метод addCronDayMonth() будет инициализировать поле dayMonth нашего объекта класса Common.Cron;
     */
    public void addCronDayMonth(String word){
        this.dayMonth = word;
    }

    /**
     * Метод addCronMonth() будет инициализировать поле month нашего объекта класса Common.Cron;
     */
    public void addCronMonth(String word){
        this.month = word;
    }

    /**
     * Метод addCronWeek() будет инициализировать поле week нашего объекта класса Common.Cron
     */
    public void addCronWeek(String word){
        this.week = word;
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

    /**
     * Переопределение метода toString();
     */
    @Override
    public String toString() {
        return minutes + " " + hours + " " + dayMonth + " " + month + " " + week + " ";
    }
}
