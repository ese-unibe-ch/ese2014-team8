package org.sample.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.model.Address;
import org.sample.model.Apartment;
import org.sample.model.ShApartment;
import org.sample.model.dao.ApartmentDao;
import org.sample.model.dao.ShApartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/config/springMVC.xml","file:src/main/webapp/WEB-INF/config/springData.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class SharedApartmentDaoTest {
	@Autowired
    ShApartmentDao shApartmentDao;

    @Test
    public void testAddressReference(){
    	String STREET = "testStreet";
    	Integer ZIPCODE = 1111;
    	Address address = new Address();
    	address.setStreet(STREET);
    	address.setZipCode(ZIPCODE);
    	ShApartment apartment = new ShApartment();
    	apartment.setAddress(address);
    	apartment.setPrice(1);
    	apartment.setTitle("title");
    	apartment = shApartmentDao.save(apartment);
    	
    	assertEquals(apartment.getAddress().getStreet(), STREET);
    }
    
    @Test
    public void testFindApartmentByZipCode(){
    	Integer ZIPCODE = 1111;
    	Address address = new Address();
    	address.setZipCode(ZIPCODE);
    	ShApartment apartment = new ShApartment();
    	apartment.setAddress(address);
    	apartment.setPrice(1);
    	apartment.setTitle("title");
    	apartment = shApartmentDao.save(apartment);
    	ShApartment foundApartment = shApartmentDao.findByAddressZipCode(ZIPCODE).get(0);
    	assertEquals(foundApartment.getAddress().getZipCode(), ZIPCODE);
    }
    
}
