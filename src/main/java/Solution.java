/**
 * Создаю класс в котором будет производится создания и возовы функций
 */
public class Solution {
    public static void main(String[] args) throws Exception {
        SimpleGUI app = new SimpleGUI();
        app.setVisible(true);

    }

    /**
     * Создается объет: Cron cron в который будут вписываться данные
     */
    /*public static Cron addCron() throws Exception {
        Cron cron = new Cron();
        cron.addCronMinutes();
        cron.addCronHours();
        cron.addCronDayMonth();
        cron.addCronMonth();
        cron.addCronWeek();
        return cron;
    }*/

    /**
     * Происходит редактирование cron-выражения на понятный для человека язык
     */
    public static CronReader cronReader(Cron cron) {
        CronReader cronReader = new CronReader();
        cronReader.cronReaderMinutes(cron);
        cronReader.cronReaderHours(cron);
        cronReader.cronReaderDayMonth(cron);
        cronReader.cronReaderMonth(cron);
        cronReader.cronReaderWeek(cron);
        return cronReader;
    }

    /**
     * Происходит создание обекта класса CronHuman и вывод резульата
     */
    public static String cronHuman(CronReader cronReader) {
        CronHuman cronHuman = new CronHuman();
        cronHuman.setMinutesHuman(cronReader.getMinutesHuman());
        cronHuman.setHoursHuman(cronReader.getHoursHuman());
        cronHuman.setDayMonthHuman(cronReader.getDayMonthHuman());
        cronHuman.setMonthHuman(cronReader.getMonthHuman());
        cronHuman.setWeekHuman(cronReader.getWeekHuman());
       return cronHuman.toString();
    }
}
