import com.event.scheduler.entity.Event;
import com.event.scheduler.repository.EventRepository;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config/app-config.xml"})
@TransactionConfiguration(transactionManager = "transactionManager")
public class EventRepositoryTest {
    private static final Logger LOG = Logger.getLogger(EventRepositoryTest.class);

    @Autowired private EventRepository eventRepository;

    @Test
    public void testAdd() throws Exception {
        final Event event = new Event();

        DateFormat formatter;
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        // you can change format of date
        String str = "04/07/2016";
        Date date = formatter.parse(str);
        java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());

        event.setUsername("mishakhno");
        event.setEventPointId(1);
        event.setDescription("description");
        event.setStartDate(timeStampDate);
        event.setEndDate(new Timestamp(18));

        eventRepository.add(event);
    }
}
