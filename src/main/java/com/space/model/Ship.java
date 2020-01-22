package com.space.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

@Entity
@Table(name = "ship", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Ship implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "planet")
    private String planet;
    @Column(name = "shipType")
    private String shipType;
    @Column(name = "prodDate")
    private Date prodDate;
    @Column(name = "isUsed")
    private Boolean isUsed;
    @Column(name = "speed")
    private Double speed;
    @Column(name = "crewSize")
    private Integer crewSize;
    @Column(name = "rating")
    private Double rating;

    public Ship() {
    }

    public Ship(Long id, String name, String planet, String shipType, java.sql.Date prodDate, Boolean isUsed, Double speed, Integer crewSize, Double rating){
        this.id = id;
        this.name = name;
        this.planet = planet;
        this.shipType = ShipType.valueOf(shipType).toString();
        this.prodDate = prodDate;
        this.isUsed = isUsed;
        this.speed = speed;
        this.crewSize = crewSize;
        this.rating = rating;
    }

    public Ship(ShipDto shipDto) {
        this.name = shipDto.getName();
        this.planet = shipDto.getPlanet();
        this.shipType = shipDto.getShipType();
        this.prodDate = new Date(shipDto.getProdDate());
        if (shipDto.getUsed() != null)
            this.isUsed = shipDto.getUsed();
        else this.isUsed = false;
        this.speed = shipDto.getSpeed();
        this.crewSize = shipDto.getCrewSize();
        this.rating = (80.0 * speed * (isUsed ? 0.5 : 1)) / (3019 - prodDate.getYear() - 1900 + 1);
        rating = Math.round(rating * 100) / 100.0;
    }

    public ResponseEntity<Ship> update(ShipDto shipDto) {
        if (shipDto.getName() != null) {
            if (shipDto.getName().isEmpty() || shipDto.getName().length() > 50)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            this.name = shipDto.getName();
        }
        if (shipDto.getPlanet() != null) {
            if (shipDto.getPlanet().isEmpty() || shipDto.getPlanet().length() > 50)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            this.planet = shipDto.getPlanet();
        }
        if (shipDto.getShipType() != null) {
            if (shipDto.getShipType().isEmpty())
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            this.shipType = shipDto.getShipType();
        }
        if (shipDto.getProdDate() != null) {
            if (shipDto.getProdDate() < 0 ||
                    shipDto.getProdDate() < new GregorianCalendar(2800, 0, 1).getTimeInMillis() ||
                    shipDto.getProdDate() > new GregorianCalendar(3020, 0, 1).getTimeInMillis())
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            this.prodDate = new Date(shipDto.getProdDate());
        }
        if (shipDto.getUsed() != null)
        this.isUsed = shipDto.getUsed();
        if (shipDto.getSpeed() != null) {
            shipDto.setSpeed(Math.round(shipDto.getSpeed() *100) / 100.0);
            if (shipDto.getSpeed() < 0.01 || shipDto.getSpeed() > 0.99)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            this.speed = shipDto.getSpeed();
        }
        if (shipDto.getCrewSize() != null) {
            if (shipDto.getCrewSize() < 1 || shipDto.getCrewSize() > 9999)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            this.crewSize = shipDto.getCrewSize();
        }
        this.rating = (80.0 * speed * (isUsed ? 0.5 : 1)) / (3019 - prodDate.getYear() - 1900 + 1);
        rating = Math.round(rating * 100) / 100.0;
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType.toString();
    }
    public void setShipType(String shipType) {
        this.shipType =  ShipType.valueOf(shipType).toString();
    }

    public Date getProdDate() {
        return prodDate;
    }

  /*  public void setProdDate(Date prodDate) {
        this.prodDate = prodDate;
    }*/
    public void setProdDate(java.sql.Date prodDate) {
        this.prodDate = prodDate;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(Integer crewSize) {
        this.crewSize = crewSize;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return Objects.equals(id, ship.id) &&
                Objects.equals(name, ship.name) &&
                Objects.equals(planet, ship.planet) &&
                shipType == ship.shipType &&
                Objects.equals(prodDate, ship.prodDate) &&
                Objects.equals(isUsed, ship.isUsed) &&
                Objects.equals(speed, ship.speed) &&
                Objects.equals(crewSize, ship.crewSize) &&
                Objects.equals(rating, ship.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, planet, shipType, prodDate, isUsed, speed, crewSize, rating);
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", planet='" + planet + '\'' +
                ", shipType=" + shipType +
                ", prodDate=" + prodDate +
                ", isUsed=" + isUsed +
                ", speed=" + speed +
                ", crewSize=" + crewSize +
                ", rating=" + rating +
                '}';
    }
}


