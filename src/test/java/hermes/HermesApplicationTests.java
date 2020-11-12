package hermes;

import static org.assertj.core.api.Assertions.assertThat;

import hermes.jms.Consumer;
import hermes.jms.Producer;
import hermes.model.Model;
import hermes.model.RequestDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HermesApplicationTests {

  @Autowired
  private Producer producer;

  @Autowired
  private Consumer consumer;

  @Value("${spring.artemis.hermes.destination.test}")
  private String testDestination;

  @Test
  public void testProduceAndConsume() throws InterruptedException { // assumption: tests run non-concurrent
    RequestDTO testData = new RequestDTO("12345", Model.STATE1.getVal());

    long countBefore = consumer.getConsumedCount();
    producer.send(testDestination, testData);
    Thread.sleep(1000);
    assertThat(consumer.getConsumedCount()).isEqualTo(countBefore+1);
  }

}