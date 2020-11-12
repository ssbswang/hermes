package hermes.jms;

import hermes.model.RequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

  private static final Logger log = LoggerFactory.getLogger(Producer.class);

  @Value("${spring.artemis.hermes.destination}")
  private String destination;

  @Autowired
  private JmsTemplate jmsTemplate;

  public void send(RequestDTO dto) {
    send(destination, dto);
  }

  public void send(String dest, RequestDTO dto) {
    jmsTemplate.convertAndSend(dest, dto);
  }

}
