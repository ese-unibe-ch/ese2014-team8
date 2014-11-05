package org.sample.controller.pojos;

import javax.validation.constraints.NotNull;

/**
 * Created by zilti on 05.11.14.
 */
public class ProfileForm {

    @NotNull
    private String email;

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
