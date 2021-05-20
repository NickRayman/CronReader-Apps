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

    @Test
    public void testSetMinutesHuman() {
        CronHuman cronHuman = new CronHuman();
        cronHuman.setMinutesHuman("1");
        String actualValue = cronHuman.minutesHuman;
        String expectedValue = "1";
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testSetHoursHuman() {
        CronHuman cronHuman = new CronHuman();
        cronHuman.setHoursHuman("1");
        String actualValue = cronHuman.hoursHuman;
        String expectedValue = "1";
        assertEquals(expectedValue, actualValue);

    }

    @Test
    public void testSetDayMonthHuman() {
        CronHuman cronHuman = new CronHuman();
        cronHuman.setDayMonthHuman("1");
        String actualValue = cronHuman.dayMonthHuman;
        String expectedValue = "1";
        assertEquals(expectedValue, actualValue);

    }

    @Test
    public void testSetMonthHuman() {
        CronHuman cronHuman = new CronHuman();
        cronHuman.setMonthHuman("1");
        String actualValue = cronHuman.monthHuman;
        String expectedValue = "1";
        assertEquals(expectedValue, actualValue);

    }

    @Test
    public void testSetWeekHuman() {
        CronHuman cronHuman = new CronHuman();
        cronHuman.setWeekHuman("1");
        String actualValue = cronHuman.weekHuman;
        String expectedValue = "1";
        assertEquals(expectedValue, actualValue);
    }
}