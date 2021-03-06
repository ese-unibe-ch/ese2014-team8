package org.sample.controller.service;

import java.util.Collection;
import java.util.Date;

import org.sample.controller.pojos.MessageForm;
import org.sample.model.Message;
import org.sample.model.User;
import org.sample.model.dao.ApartmentDao;
import org.sample.model.dao.MessageDao;
import org.sample.model.dao.ShApartmentDao;
import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	MessageDao messageDao;
	@Autowired
	UserDao userDao;
	@Autowired
	ApartmentDao apartmentDao;
	@Autowired
	ShApartmentDao shApartmentDao;

	@Override
	public void saveFrom(MessageForm enquiry) {
		Message message = new Message();
		message.setSender(userDao.findOne(enquiry.getSenderId()));
		message.setReceiver(userDao.findOne(enquiry.getReceiverId()));
		message.setSubject(enquiry.getSubject());
		message.setDateTime(new Date());
		message.setContent(enquiry.getMessage());
		message.setMessageRead(false);
		if(enquiry.getCategory().equals("Apartment")){
			message.setAp(apartmentDao.findOne(enquiry.getAdId()));
		}
		else if(enquiry.getCategory().equals("Shared Apartment")){
			message.setShAp(shApartmentDao.findOne(enquiry.getAdId()));
		}
		messageDao.save(message);
		
	}

	@Override
	public Collection<Message> getReceivedMessages(User user) {
		return messageDao.findByReceiver(user);
	}

	@Override
	public Collection<Message> getSentMessages(User user) {
		return messageDao.findBySender(user);
	}

	@Override
	public Message getMessage(long messageId) {
		return messageDao.findOne(messageId);
	}

	@Override
	public void markMessageRead(long id) {
		Message message= messageDao.findOne(id);
		message.setMessageRead(true);
		messageDao.save(message);
		
	}

}
