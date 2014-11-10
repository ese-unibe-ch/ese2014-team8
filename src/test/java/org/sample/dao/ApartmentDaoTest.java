package org.sample.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.model.Address;
import org.sample.model.Apartment;
import org.sample.model.dao.ApartmentDao;
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
public class ApartmentDaoTest {
	@Autowired
    ApartmentDao apartmentDao;

    @Test
    public void testAddressReference(){
    	String STREET = "testStreet";
    	
    	Address address = new Address();
    	address.setStreet(STREET);
    	Apartment apartment = new Apartment();
    	apartment.setAddress(address);
    	apartment = apartmentDao.save(apartment);
    	
    	assertEquals(apartment.getAddress().getStreet(), STREET);
    }
    
    @Test
    public void testFindApartmentByZipCode(){
    	int ZIPCODE = 1111;
    	Address address = new Address();
    	address.setZipCode(ZIPCODE);
    	Apartment apartment = new Apartment();
    	apartment.setAddress(address);
    	apartment = apartmentDao.save(apartment);
    	Apartment foundApartment = apartmentDao.findByAddressZipCode(ZIPCODE).get(0);
    	assertEquals(foundApartment.getAddress().getZipCode(), ZIPCODE);
    }
    
}
