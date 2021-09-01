package Common;

import javax.swing.*;
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
    private DefaultListModel<String> dbTable = new DefaultListModel<String>();


    /**
     * Геттеры
     */
    public List<String> getErrors() {
        return errors;
    }

    public CronHuman getCronHuman() {
        return cronHuman;
    }

    public DefaultListModel getDBTable() {
        return dbTable;
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

    public void setDBTable(DefaultListModel dbTable) {
        this.dbTable = dbTable;
    }
}
