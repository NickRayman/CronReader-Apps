import Common.Cron;
import junit.framework.TestCase;
import org.junit.Test;

public class CronTest extends TestCase {
    @Test
    public void testGetMinutes() {
        Cron cron = new Cron();
        String actualValue = cron.getMinutes();
        String expectedValue = "0";
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testGetHours() {
        Cron cron = new Cron();
        String actualValue = cron.getHours();
        String expectedValue = "0";
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testGetDayMonth() {
        Cron cron = new Cron();
        String actualValue = cron.getDayMonth();
        String expectedValue = "0";
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testGetMonth() {
        Cron cron = new Cron();
        String actualValue = cron.getMonth();
        String expectedValue = "0";
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testGetWeek() {
        Cron cron = new Cron();
        String actualValue = cron.getWeek();
        String expectedValue = "0";
        assertEquals(expectedValue, actualValue);
    }
}