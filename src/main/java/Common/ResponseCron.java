package Common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Создаю класс, который будет хранить объект CronHuman и список ошибок, если они есть
 */
public class ResponseCron implements Serializable {

    /**
     * Неинициализированные поля, переменные классов List<String>, CronHuman
     */
    private List<String> errors = new ArrayList<>();
    private CronHuman cronHuman = new CronHuman();


    /**
     * Геттеры
     */
    public List<String> getErrors() {
        return errors;
    }

    public CronHuman getCronHuman() {
        return cronHuman;
    }

    /**
     * Сеттеры
     */
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void setCronHuman(CronHuman cronHuman) {
        this.cronHuman = cronHuman;
    }
}
