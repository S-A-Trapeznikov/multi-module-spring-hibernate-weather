package org.sonatype.mavenbook.weather.model;

import javax.persistence.*;

import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name="Weather.byLocation", query="from Weather w where w.location = :location")
})

public class Weather {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @ManyToOne(cascade=CascadeType.ALL)
    private Location location;

    @OneToOne(mappedBy="weather", cascade=CascadeType.ALL)
    private Condition condition;

    @OneToOne(mappedBy="weather", cascade=CascadeType.ALL)
    private Wind wind;

    @OneToOne(mappedBy="weather", cascade=CascadeType.ALL)
    private Atmosphere atmosphere;

    private Date date;

    public Weather() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id){
        this.id=id;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Location getLocation() {
        return location;
    }

    public Condition getCondition() {
        return condition;
    }

    public Wind getWind() {
        return wind;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {

        this.date = date;
    }
}


