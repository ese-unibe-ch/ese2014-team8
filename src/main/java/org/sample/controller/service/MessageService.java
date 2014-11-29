package org.sample.controller.service;

import java.util.Collection;

import org.sample.controller.pojos.MessageForm;
import org.sample.model.Message;
import org.sample.model.User;

public interface MessageService {

	void saveFrom(MessageForm enquiry);

	Collection<Message> getReceivedMessages(User user);

	Collection<Message> getSentMessages(User user);

	Message getMessage(long messageId);

	void markMessageRead(long parseLong);

}
