/**
 * Создаю класс в котором будет проиводится редактирование cron-выражения в читаемый для человека язык
 */
public class CronReader extends CronHuman {

    /**
     * Метод cronReaderMinutes() будет редактировать minutes класса Cron в читаемый для человека язык,
     * в параметре принимает объект класса Cron
     */
    public void cronReaderMinutes(Cron code) {
        if (code.getMinutes().equals("*")) {
            this.minutesHuman = "Каждую минуту, ";
        } else this.minutesHuman = "В " + code.getMinutes() + " минуту, ";
    }

    /**
     * Метод cronReaderHours() будет редактировать hours класса Cron в читаемый для человека язык,
     * в параметре принимает объект класса Cron
     */
    public void cronReaderHours(Cron code) {
        if (code.getHours().equals("*")) {
            this.hoursHuman = "каждый час, ";
        } else this.hoursHuman = "в " + code.getHours() + " час, ";
    }

    /**
     * Метод cronReaderDayMonth() будет редактировать dayMonth класса Cron в читаемый для человека язык,
     * в параметре принимает объект класса Cron
     */
    public void cronReaderDayMonth(Cron code) {
        if (code.getDayMonth().equals("*")) {
            this.dayMonthHuman = "каждый день месяца, ";
        } else this.dayMonthHuman = "в " + code.getDayMonth() + " день месяца, ";
    }

    /**
     * Метод cronReaderMonth() будет редактировать month класса Cron в читаемый для человека язык,
     * в параметре принимает объект класса Cron
     */
    public void cronReaderMonth(Cron code) {
        if (code.getMonth().equals("*")) {
            this.monthHuman = "каждый месяц, ";
        } else this.monthHuman = "в " + code.getMonth() + " месяц, ";
    }

    /**
     * Метод cronReaderWeek() будет редактировать week класса Cron в читаемый для человека язык,
     * в параметре принимает объект класса Cron
     */
    public void cronReaderWeek(Cron code) {
        if (code.getWeek().equals("*")) {
            this.weekHuman = "Каждый день недели, ";
        } else this.weekHuman = "в " + code.getWeek() + " день недели, ";
    }

    /**
     * Гетеры
     */
    public String getMinutesHuman() {
        return minutesHuman;
    }

    public String getHoursHuman() {
        return hoursHuman;
    }

    public String getDayMonthHuman() {
        return dayMonthHuman;
    }

    public String getMonthHuman() {
        return monthHuman;
    }

    public String getWeekHuman() {
        return weekHuman;
    }
}
