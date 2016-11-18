package springjms;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {		
	
		public static void main(String[] args) throws URISyntaxException, Exception{
				
		BrokerService broker = BrokerFactory.createBroker(new URI("broker:(tcp://localhost:61616)"));
		broker.start();
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		try {
			JmsProducer jmsProducer = context.getBean(JmsProducer.class);
			JmsReceiver jmsReceiver = context.getBean(JmsReceiver.class);
						
		//	jmsProducer.sendMessage("Fisrt JMS message");
			jmsProducer.sendMessage(new Person("Will", "Smith"));
			System.out.println("Received message: " + jmsReceiver.receiveMessage());			
		} finally {
			broker.stop();
			context.close();
		}
	}
}
