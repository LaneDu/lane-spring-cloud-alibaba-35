import com.galaxy.AutodeliverApplication8091;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author lane
 * @date 2021年06月23日 下午3:20
 */
@SpringBootTest(classes = AutodeliverApplication8091.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AutodeliverApplication8091Test {


    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testInstanceMetadata() {
        List<ServiceInstance> instances = discoveryClient.getInstances("lane-service-resume");
        for (int i = 0; i < instances.size(); i++) {
            ServiceInstance serviceInstance =  instances.get(i);
            System.out.println(serviceInstance.getMetadata());
        }
    }


}
