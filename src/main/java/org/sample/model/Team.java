package org.sample.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zilti on 28.09.14.
 */
@Entity
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    private String teamname;

    @GeneratedValue
    private Timestamp timestamp;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getTeamname() {return teamname;}
    public void setTeamname(String teamname) {this.teamname = teamname;}

    public Timestamp getTimestamp() {return timestamp;}
    public void setTimestamp(Timestamp timestamp) {this.timestamp = timestamp;}
}
