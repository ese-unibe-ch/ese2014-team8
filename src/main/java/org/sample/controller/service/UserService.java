package org.sample.controller.service;


import java.util.Map;
import org.sample.controller.pojos.*;
import org.sample.model.*;


public interface UserService {

   // public SignupForm saveFrom(SignupForm signupForm) throws InvalidUserException;

    public User getUser(Long id);
    public Iterable<User> getUsers();
    public User loadUserByEmail(String email);
    public NewProfileForm saveFrom(NewProfileForm newProfileForm);
    public ProfileForm saveFrom(ProfileForm profileForm);
    public ProfileForm fillProfileForm(User user);
	public Person getPerson(Long pId);
	public Map<String, ?> getUpdates(User user);
	public User imageSaved(User user);
	public void removeImage(Long personId);
   

}
