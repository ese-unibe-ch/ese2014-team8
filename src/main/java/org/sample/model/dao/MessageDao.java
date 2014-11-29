package org.sample.model.dao;

import java.util.Collection;
import org.sample.model.Message;
import org.sample.model.User;
import org.springframework.data.repository.CrudRepository;

public interface MessageDao extends CrudRepository<Message,Long> {
	Collection<Message> findBySender(User sender);
	Collection<Message> findByReceiver(User receiver);

}
