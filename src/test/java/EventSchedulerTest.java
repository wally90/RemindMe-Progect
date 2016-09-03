import com.event.scheduler.entity.Event;
import com.event.scheduler.repository.EventRepository;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config/app-config.xml"})
@TransactionConfiguration(transactionManager = "transactionManager")
public class EventSchedulerTest {
    private static final Logger LOG = Logger.getLogger(EventRepositoryTest.class);

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void testFind() throws Exception {
        // formatted date
        DateFormat formatter;
        formatter = new SimpleDateFormat("MM/dd/yyyy");
        String start= "03/22/2016";
        String end = "04/23/2016";

        Date startDate = null;
        Date endDate = null;
        try {
            startDate = formatter.parse(start);
            endDate = formatter.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp timeStampStartDate = new Timestamp(startDate.getTime());
        Timestamp timeStampEndDate = new Timestamp(endDate.getTime());

        System.out.println(timeStampStartDate);
        System.out.println(timeStampEndDate);

        List<Event> events = eventRepository.getAllByEventPointAndDate(8, timeStampStartDate, timeStampEndDate);

        for (Event event : events) {
            System.out.println(event.toString());
        }
    }
}
