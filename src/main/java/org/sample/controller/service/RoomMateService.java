package org.sample.controller.service;

import java.util.Collection;
import java.util.List;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.*;
import org.sample.model.*;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.Serializable;

public interface RoomMateService {

    public RoomMate getRoomMate(Long id);
//    public Iterable<RoomMate> getRoomMates();
//    public RoomMate loadRoomMateByEmail(String email);
    public RoomMateForm saveFrom(RoomMateForm newRoomMateForm);
//    public ProfileForm saveFrom(ProfileForm profileForm);
    public RoomMateForm fillRoomMateForm(RoomMate roomMate);
	public RoomMate loadRoomMate();
   

}
