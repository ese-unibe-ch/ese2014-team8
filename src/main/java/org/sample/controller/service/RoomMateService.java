package org.sample.controller.service;

import org.sample.controller.pojos.*;
import org.sample.model.*;

public interface RoomMateService {

    public RoomMate getRoomMate(Long id);
    public Iterable<RoomMate> getRoomMates();
    public Iterable<RoomMate> getRoomMates(Long adId);
    public RoomMateForm saveFrom(RoomMateForm newRoomMateForm);
    public RoomMateForm fillRoomMateForm(RoomMate roomMate);
	public void deleteRoomMate(Long roomMateId);
}
