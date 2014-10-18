package org.sample.controller.pojos;

import java.sql.Timestamp;

/**
 * Created by zilti on 30.09.14.
 */
public class TeamCreationForm {

    private Long id;
    private String teamname;
    private Timestamp timestamp;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public Timestamp getTimestamp() {return timestamp;}
    public void setTimestamp(Timestamp timestamp) {this.timestamp = timestamp;}

    public String getTeamname() {return teamname;}
    public void setTeamname(String teamname) {this.teamname = teamname;}

}
