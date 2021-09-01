package Server;

/**
 * Создаю класс StringBD, который будет отвечать за строку в таблице БД
 */
public class StringBD {

    /**
     * Поля, строки таблицы БД
     */
    private int id;
    private String cron;
    private String cronHuman;
    private String data;

    /**
     * Переопределение метода toString();
     */
    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", cron ='" + cron + '\'' +
                ", cronHuman ='" + cronHuman + '\'' +
                ", data ='" + data + '\'' +
                '}';
    }

    /**
     * Геттеры
     */
    public int getId() {
        return id;
    }

    public String getCron() {
        return cron;
    }

    public String getCronHuman() {
        return cronHuman;
    }

    public String getData() {
        return data;
    }

    /**
     * Сеттеры
     */
    public void setId(int id) {
        this.id = id;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public void setCronHuman(String cronHuman) {
        this.cronHuman = cronHuman;
    }

    public void setData(String data) {
        this.data = data;
    }
}
