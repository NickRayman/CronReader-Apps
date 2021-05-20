import junit.framework.TestCase;
import org.junit.Test;

public class SolutionTest extends TestCase {

    @Test
    public void testCronReader() {
        Cron cron = new Cron();
        CronReader actualValue = new CronReader();
        actualValue.cronReaderMinutes(cron);
        actualValue.cronReaderHours(cron);
        actualValue.cronReaderDayMonth(cron);
        actualValue.cronReaderMonth(cron);
        actualValue.cronReaderWeek(cron);
        CronReader expectedValue = Solution.cronReader(cron);
        assertEquals(expectedValue.toString(), actualValue.toString());
    }
}