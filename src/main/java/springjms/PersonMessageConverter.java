package springjms;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

@Component
public class PersonMessageConverter implements MessageConverter{
		
	public Object fromMessage(Message message) throws JMSException, MessageConversionException {
		MapMessage mapMessage = (MapMessage) message;
		Person person = new Person(mapMessage.getString("firstname"), mapMessage.getString("lastname"));
		return person;
	}

	public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
		Person person = (Person) object;
		MapMessage message = session.createMapMessage();
		message.setString("firstname", person.getFirstname());
		message.setString("lastname", person.getLastname());
		return message;
	}

}
