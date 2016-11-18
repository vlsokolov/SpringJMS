package springjms;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsProducer{
		
	@Autowired
	private JmsTemplate jmsTemplate;

/*	public void sendMessage(final String msg) {
		System.out.println("Producer sends " + msg);
		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}});				
	}*/
	
	public void sendMessage(final Person person){
		Map<String, String> map = new HashMap<String, String>();
		map.put("firstname", person.getFirstname());
		map.put("lastname", person.getLastname());
		jmsTemplate.convertAndSend(map);
	}

}
