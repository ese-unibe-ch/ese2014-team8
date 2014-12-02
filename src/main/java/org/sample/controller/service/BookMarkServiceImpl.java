package org.sample.controller.service;

import org.sample.model.BookMark;
import org.sample.model.User;
import org.sample.model.dao.ApartmentDao;
import org.sample.model.dao.BookMarkDao;
import org.sample.model.dao.ShApartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookMarkServiceImpl implements BookMarkService{
	
	@Autowired    BookMarkDao bookMarkDao;
	@Autowired    ShApartmentDao shApartmentDao;
	@Autowired    ApartmentDao apartmentDao;

	@Override
	public BookMark getBookMark(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBookMark(User user, String category, Long adId) {
		BookMark bookMark = new BookMark();
		
		if (category.equals("Apartment")){
			bookMark.setAp(apartmentDao.findOne(adId));
		} else {
			bookMark.setShAp(shApartmentDao.findOne(adId));
		}
		
		bookMark.setBookMarker(user);
		
		bookMarkDao.save(bookMark);
	}

}
