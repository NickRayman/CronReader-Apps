package Client;

/**
 * Создаю класс который будет выводить на читаеммом человеку языке cron-выражение;
 */
public class CronHuman {
    /**
     * Создаю 5 переменных типа String для их дальнейшей инициализации;
     */
    protected String minutesHuman;
    protected String hoursHuman;
    protected String dayMonthHuman;
    protected String monthHuman;
    protected String weekHuman;

    /**
     * Флаг, для проверки данных
     */
    private boolean error = false;

    /**
     * Поле ошибки
     */
    private String errors;
    /**
     * Переопределение метода toString();
     */
    @Override
    public String toString() {
        if(!this.error)
        return minutesHuman + hoursHuman + dayMonthHuman + monthHuman + weekHuman;
        else
            return errors;
    }

    public void errors(String errors){
        this.error = true;
        this.errors = errors;
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
