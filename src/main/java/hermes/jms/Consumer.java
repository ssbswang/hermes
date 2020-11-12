package hermes.jms;

import hermes.model.RequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

  private static final Logger log = LoggerFactory.getLogger(Consumer.class);

  private long consumed_count = 0;

  @JmsListener(destination = "${spring.artemis.hermes.destination}")
  public void receive(RequestDTO request) {
    log.info("received message");
    consumed_count++;
    log.info("processed the message");
  }

  @JmsListener(destination = "${spring.artemis.hermes.destination.test}")
  public void receiveTest(RequestDTO request) {
    log.info("received test message");
    consumed_count++;
  }

  public long getConsumedCount() {
    return this.consumed_count;
  }

}
