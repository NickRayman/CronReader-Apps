/**
 * Создаю класс который будет выводить на читаеммом человеку языке cron-выражение;
 */
public class CronHuman {
    /**
     * Создаю 5 переменных типа String для их дальнейшей инициализации;
     */
    String minutesHuman;
    String hoursHuman;
    String dayMonthHuman;
    String monthHuman;
    String weekHuman;

    /**
     * Переопределение метода toString();
     */
    @Override
    public String toString() {

        return minutesHuman + hoursHuman + dayMonthHuman + monthHuman + weekHuman;
    }


    /**
     * Сеттеры;
     */
    public void setMinutesHuman(String minutesHuman) {
        this.minutesHuman = minutesHuman;
    }

    public void setHoursHuman(String hoursHuman) {
        this.hoursHuman = hoursHuman;
    }

    public void setDayMonthHuman(String dayMonthHuman) {
        this.dayMonthHuman = dayMonthHuman;
    }

    public void setMonthHuman(String monthHuman) {
        this.monthHuman = monthHuman;
    }

    public void setWeekHuman(String weekHuman) {
        this.weekHuman = weekHuman;
    }
}
