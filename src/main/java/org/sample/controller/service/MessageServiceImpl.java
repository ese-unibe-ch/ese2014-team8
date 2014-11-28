package org.sample.controller.service;

import java.util.Date;

import org.sample.controller.pojos.MessageForm;
import org.sample.model.Message;
import org.sample.model.dao.ApartmentDao;
import org.sample.model.dao.MessageDao;
import org.sample.model.dao.ShApartmentDao;
import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


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
		message.setTimestamp(new Date());
		message.setContent(enquiry.getMessage());
		message.setRead(false);
		if(enquiry.getCategory().equals("Apartment")){
			message.setAp(apartmentDao.findOne(enquiry.getAdId()));
		}
		else if(enquiry.getCategory().equals("Shared Apartment")){
			message.setShAp(shApartmentDao.findOne(enquiry.getAdId()));
		}
		messageDao.save(message);
		
	}

}
