/**
 * Создаю класс в котором будет проиводится редактирование cron-выражения в читаемый для человека язык;
 */
public class CronReader {

    /**
     * Метод cronReaderMinutes() будет редактировать minutes класса Cron в читаемый для человека язык,
     * в параметре принимает объект класса Cron;
     */
    private String cronReaderMinutes(Cron code) {
        if (code.getMinutes().equals("*")) {
            return "Каждую минуту, ";
        } else return "В " + code.getMinutes() + " минуту, ";
    }

    /**
     * Метод cronReaderHours() будет редактировать hours класса Cron в читаемый для человека язык,
     * в параметре принимает объект класса Cron;
     */
    private String cronReaderHours(Cron code) {
        if (code.getHours().equals("*")) {
            return "каждый час, ";
        } else return "в " + code.getHours() + " час, ";
    }

    /**
     * Метод cronReaderDayMonth() будет редактировать dayMonth класса Cron в читаемый для человека язык,
     * в параметре принимает объект класса Cron;
     */
    private String cronReaderDayMonth(Cron code) {
        if (code.getDayMonth().equals("*")) {
            return "каждый день месяца, ";
        } else return "в " + code.getDayMonth() + " день месяца, ";
    }

    /**
     * Метод cronReaderMonth() будет редактировать month класса Cron в читаемый для человека язык,
     * в параметре принимает объект класса Cron;
     */
    private String cronReaderMonth(Cron code) {
        if (code.getMonth().equals("*")) {
            return  "каждый месяц, ";
        } else return "в " + code.getMonth() + " месяц, ";
    }

    /**
     * Метод cronReaderWeek() будет редактировать week класса Cron в читаемый для человека язык,
     * в параметре принимает объект класса Cron;
     */
    private String cronReaderWeek(Cron code) {
        if (code.getWeek().equals("*")) {
            return "каждый день недели.";
        } return "в " + code.getWeek() + " день недели.";
    }

    /**
     *Метод создаст обект CronHuman, инициализирует его поля и сразу вернет toString() обекта CronHuman c уже инициализированными полями;
     * а в качестве параметра метод примет объект Cron;
     */
    public String translate(Cron cron){
        CronHuman cronHuman = new CronHuman();
        cronHuman.setMinutesHuman(cronReaderMinutes(cron));
        cronHuman.setHoursHuman(cronReaderHours(cron));
        cronHuman.setDayMonthHuman(cronReaderDayMonth(cron));
        cronHuman.setMonthHuman(cronReaderMonth(cron));
        cronHuman.setWeekHuman(cronReaderWeek(cron));
        return cronHuman.toString();
    }

}
