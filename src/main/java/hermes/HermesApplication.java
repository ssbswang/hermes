package hermes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/*
  TODO 1 - local memory storage threshold
  TODO 2 - queue size limit - to queue or not to queue
  TODO 3 - queue persistence
 */

@SpringBootApplication
@EnableJms
public class HermesApplication {

	private static final Logger log = LoggerFactory.getLogger(HermesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HermesApplication.class, args);
	}

}