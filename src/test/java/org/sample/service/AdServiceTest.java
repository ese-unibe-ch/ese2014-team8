package org.sample.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.sample.controller.exceptions.InvalidDateException;
import org.sample.controller.pojos.ApartmentForm;
import org.sample.controller.pojos.ShApartmentForm;
import org.sample.controller.service.AdService;
import org.sample.controller.service.AdServiceImpl;
import org.sample.model.Apartment;
import org.sample.model.ShApartment;
import org.sample.model.dao.AddressDao;
import org.sample.model.dao.ApartmentDao;
import org.sample.model.dao.ShApartmentDao;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AdServiceTest {
	
	private ApartmentDao apDao;
	private ShApartmentDao shApDao;
	private AdService adService;
	
	@Before
	public void doSetup(){
		apDao = mock(ApartmentDao.class);
		shApDao = mock(ShApartmentDao.class);
		adService = new AdServiceImpl(apDao, shApDao);
	}
	
	@Test
	public void testSaveApartment(){
		ApartmentForm apForm = new ApartmentForm();
		apForm.setTitle("TestTitle");
		
		when(apDao.save(any(Apartment.class)))
			.thenAnswer(new Answer<Apartment>(){
				public Apartment answer(InvocationOnMock invocation) throws Throwable{
					Apartment apartment = (Apartment) invocation.getArguments()[0];
					apartment.setId(1L);
					return apartment;
				}
			});
		
		assertEquals(0L, apForm.getId());
		
		Apartment ap = adService.saveFrom(apForm);
		assertNotNull(ap.getId());
		assertTrue(ap.getId() > 0);
		
	}
	
	@Test
	public void testSaveSharedApartment(){
		ShApartmentForm shApForm = new ShApartmentForm();
		shApForm.setTitle("TestTitle");
		
		when(shApDao.save(any(ShApartment.class)))
			.thenAnswer(new Answer<ShApartment>(){
				public ShApartment answer(InvocationOnMock invocation) throws Throwable{
					ShApartment apartment = (ShApartment) invocation.getArguments()[0];
					apartment.setId(1L);
					return apartment;
				}
			});
		
		assertEquals( 0L, shApForm.getId());
		
		ShApartment ap = adService.saveFrom(shApForm);
		assertNotNull(ap.getId());
		assertTrue(ap.getId() > 0);
	}
	
	@Test(expected = InvalidDateException.class)
	public void testInvalidDateException(){
		ApartmentForm apForm = new ApartmentForm();
		apForm.setTitle("TestTitle");
		apForm.setFixedMoveIn(true);
		apForm.setMoveIn(new java.util.Date(0L));
		adService.saveFrom(apForm);
	}
	
	
	

}
