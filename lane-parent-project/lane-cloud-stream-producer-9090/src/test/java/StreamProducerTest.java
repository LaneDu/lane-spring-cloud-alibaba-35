import com.galaxy.StreamProducerApplication9090;
import com.galaxy.service.IMessageProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author lane
 * @date 2021年06月25日 下午4:31
 */
@SpringBootTest(classes = {StreamProducerApplication9090.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class StreamProducerTest {

    @Autowired
    IMessageProducer iMessageProducer;
    @Test
    public void test(){

        iMessageProducer.sendMessage("hello world");
    }
}
