import Client.Cron;
import Client.CronReader;
import junit.framework.TestCase;
import org.junit.Test;

public class CronReaderTest extends TestCase {
    @Test
    public void testTranslate() {
        String expected = new CronReader().translate(new Cron());
        Cron code = new Cron();
        String actual = "В " + code.getMinutes() + " минуту, " + "в " + code.getHours() + " час, " + "в " + code.getDayMonth() + " день месяца, " + "в " + code.getMonth() + " месяц, " + "в " + code.getWeek() + " день недели, ";
        assertEquals(expected, actual);

    }
}