import junit.framework.TestCase;
import org.junit.Test;

public class CronReaderTest extends TestCase {
    @Test
    public void testCronReaderMinutes() {
        Cron cron = new Cron();
        CronReader cronReader = new CronReader();
        cronReader.cronReaderMinutes(cron);
        String actualValue = cronReader.minutesHuman;
        String expectedValue = "В 0 минуту, ";
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCronReaderHours() {
        Cron cron = new Cron();
        CronReader cronReader = new CronReader();
        cronReader.cronReaderHours(cron);
        String actualValue = cronReader.hoursHuman;
        String expectedValue = "в 0 час, ";
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCronReaderDayMonth() {
        Cron cron = new Cron();
        CronReader cronReader = new CronReader();
        cronReader.cronReaderDayMonth(cron);
        String actualValue = cronReader.dayMonthHuman;
        String expectedValue = "в 0 день месяца, ";
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCronReaderMonth() {
        Cron cron = new Cron();
        CronReader cronReader = new CronReader();
        cronReader.cronReaderMonth(cron);
        String actualValue = cronReader.monthHuman;
        String expectedValue = "в 0 месяц, ";
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCronReaderWeek() {
        Cron cron = new Cron();
        CronReader cronReader = new CronReader();
        cronReader.cronReaderWeek(cron);
        String actualValue = cronReader.weekHuman;
        String expectedValue = "в 0 день недели, ";
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testGetMinutesHuman() {
        CronReader cron = new CronReader();
        String actualValue = cron.getMinutesHuman();
        String expectedValue = null;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testGetHoursHuman() {
        CronReader cron = new CronReader();
        String actualValue = cron.getHoursHuman();
        String expectedValue = null;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testGetDayMonthHuman() {
        CronReader cron = new CronReader();
        String actualValue = cron.getDayMonthHuman();
        String expectedValue = null;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testGetMonthHuman() {
        CronReader cron = new CronReader();
        String actualValue = cron.getMonthHuman();
        String expectedValue = null;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testGetWeekHuman() {
        CronReader cron = new CronReader();
        String actualValue = cron.getWeekHuman();
        String expectedValue = null;
        assertEquals(expectedValue, actualValue);
    }
}