package org.kallinikos.jaxrstuts.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.W3CDomHandler;

import org.kallinikos.jaxrstuts.messenger.database.DatabaseClass;
import org.kallinikos.jaxrstuts.messenger.model.Message;

public class MessageService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService() {
	
		messages.put(1L, new Message(1, "Hello World!!", "Karim"));
		messages.put(2L, new Message(2, "Hello Space!!", "Gunther"));
	}
	
	
	public List<Message> getAllMessages(){
//		
//		Message m1 = new Message(1L, "Hello World!!", "Karim");
//		Message m2 = new Message(2L, "Hello Space!!", "Gunther");
//		List<Message> list = new ArrayList<>();
//		list.add(m1);
//		list.add(m2);
//		return list;
		
		return new ArrayList<>(messages.values());
	}
	
	public List<Message> getAllMessagesForYear(int year){
		List<Message> messagesForYear = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		for (Message message : messages.values()) {
			calendar.setTime(message.getCreated());
			if(calendar.get(Calendar.YEAR)  == year) {
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size) {
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		if (start + size > list.size())
			return new ArrayList<Message>();
		
		return list.subList(start, start + size);
	}
	
	public Message getMessage(long id) {
		return messages.get(id);
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if(message.getId()<=0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
		
	}
	
	public Message removeMessage(long id) {
		return messages.remove(id);
	}
}
