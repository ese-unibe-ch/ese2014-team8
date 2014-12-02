package org.sample.controller.service;
import org.sample.model.*;

public interface BookMarkService {

    public BookMark getBookMark(Long id);
    public void setBookMark(User user, String category, Long adId);
    
//    public Iterable<RoomMate> getRoomMates(Long adId);
//    public RoomMateForm saveFrom(RoomMateForm newRoomMateForm);
//    public RoomMateForm fillRoomMateForm(RoomMate roomMate);
//	public void deleteRoomMate(Long roomMateId);
}

