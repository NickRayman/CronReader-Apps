package Server;

import Common.Cron;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateCron {

    /**
     * Неинициализированные поля, переменные классов List<String>
     */
    private List<String> errors;

    /**
     * Конструктор класса ValidateCron
     */
    public ValidateCron() {
        errors = new ArrayList<>();
    }

    /**
     * Методы, которые определяют по паттернам правильно введено значение или нет, и если нет то заполняет лист ошибок
     */
    private void validateMinute(String value) {
        Matcher matcher = Pattern.compile("^[0-9]$|^[1-5][0-9]$|^[*]$").matcher(value);
        if (!matcher.find()) {
            errors.add("Не правильно введены минуты, ");
        }
    }

    private void validateHours(String value) {
        Matcher matcher = Pattern.compile("^[0-9]$|^[1-2][0-3]$|^[*]$").matcher(value);
        if (!matcher.find()) {
            errors.add("не правильно введены часы, ");
        }
    }

    private void validateDayMonth(String value) {
        Matcher matcher = Pattern.compile("^[1-9]$|^[1-3][0-1]$|^[*]$").matcher(value);
        if (!matcher.find()) {
            errors.add("не правильно введены дни месяца, ");
        }
    }

    private void validateMonth(String value) {
        Matcher matcher = Pattern.compile("^[1-9]$|^[1][0-2]$|^[*]$").matcher(value);
        if (!matcher.find()) {
            errors.add("не правильно введен месяц, ");
        }
    }

    private void validateWeek(String value) {
        Matcher matcher = Pattern.compile("^[0-6]$|^[*]$").matcher(value);
        if (!matcher.find()) {
            errors.add("не правильно введен день недели.");
        }
    }

    /**
     * Метод для инициализации объекта Cron, введенными значениями в полях
     */
    public void validate(Cron cron) {
        validateMinute(cron.getMinutes());
        validateHours(cron.getHours());
        validateDayMonth(cron.getDayMonth());
        validateMonth(cron.getMonth());
        validateWeek(cron.getWeek());
    }

    /**
     * Геттер
     */
    public List<String> getErrors() {
        return errors;
    }

    /**
     * Сеттер
     */
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
