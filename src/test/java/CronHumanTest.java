import Client.CronHuman;
import junit.framework.TestCase;
import org.junit.Test;

public class CronHumanTest extends TestCase {

    @Test
    public void testTestToString() {
        CronHuman cronHuman = new CronHuman();
        cronHuman.setWeekHuman("1");
        cronHuman.setHoursHuman("1");
        cronHuman.setDayMonthHuman("1");
        cronHuman.setMonthHuman("1");
        cronHuman.setMinutesHuman("1");
        String actualValue = cronHuman.toString();
        String expectedValue = "11111";
        assertEquals(expectedValue, actualValue);
    }
}