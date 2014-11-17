package org.sample.model.dao;

import org.sample.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonDao extends CrudRepository<Person,Long> {
    
}
