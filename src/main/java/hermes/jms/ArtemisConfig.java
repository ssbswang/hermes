package hermes.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisConfigurationCustomizer;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
@EnableJms
public class ArtemisConfig {

  /* Embedded Artemis - Auto Config beans */
  @Autowired
  private ArtemisProperties artemisProperties;

  @Autowired // producer
  private CachingConnectionFactory cachingConnectionFactory;

  @Autowired // consumer
  private CachingConnectionFactory cachingConnectionFactory2;

  // Override default nil acceptor
  @Bean
  public ArtemisConfigurationCustomizer customizer() {
    return new ArtemisConfigurationCustomizer() {
      @Override
      public void customize(org.apache.activemq.artemis.core.config.Configuration configuration) {
        try {
          configuration.addAcceptorConfiguration("netty", "tcp://localhost:" + artemisProperties.getPort());
        } catch (Exception e) {
          throw new RuntimeException("Failed to add netty transport acceptor to artemis instance", e);
        }
      }
    };
  }

  /* ====================== Producer Config ====================== */
  @Bean
  public JmsTemplate jmsTemplate() {
    JmsTemplate jmsTemplate = new JmsTemplate(cachingConnectionFactory);
    jmsTemplate.setMessageConverter(jacksonJmsMessageConverter());
    return jmsTemplate;
  }

  @Bean
  public MessageConverter jacksonJmsMessageConverter() {
    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    converter.setTargetType(MessageType.TEXT);
    converter.setTypeIdPropertyName("_type");
    return converter;
  }

  /* ====================== Consumer Config ====================== */
  @Bean
  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(cachingConnectionFactory2);
    factory.setConcurrency("1");
    factory.setMessageConverter(jacksonJmsMessageConverter());
    return factory;
  }

}