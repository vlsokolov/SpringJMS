package springjms;

import java.util.Map;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsReceiver{
	
	@Autowired
	private JmsTemplate jmsTemplate;

	/*public String receiveMessage() throws JMSException {
		TextMessage textMessage = (TextMessage) jmsTemplate.receive();		
		return textMessage.getText();
	}*/
	
	public Person receiveMessage() throws JMSException{
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) jmsTemplate.receiveAndConvert();
		Person person = new Person((String) map.get("firstname"), (String) map.get("lastname"));
		return person;
	}

}
