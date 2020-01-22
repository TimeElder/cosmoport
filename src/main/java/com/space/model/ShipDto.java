package com.space.model;

import java.io.Serializable;
import java.util.Objects;

public class ShipDto implements Serializable {
    private String name;
    private String planet;
    private String shipType;
    private Long prodDate;
    private Boolean isUsed;
    private Double speed;
    private  Integer crewSize;

    public ShipDto() {
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

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public Long getProdDate() {
        return prodDate;
    }

    public void setProdDate(Long prodDate) {
        this.prodDate = prodDate;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean isUsed) {
        this.isUsed = isUsed;
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

    public void setCrewSize(Integer creeSize) {
        this.crewSize = creeSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShipDto shipDto = (ShipDto) o;
        return Objects.equals(name, shipDto.name) &&
                Objects.equals(planet, shipDto.planet) &&
                Objects.equals(shipType, shipDto.shipType) &&
                Objects.equals(prodDate, shipDto.prodDate) &&
                Objects.equals(isUsed, shipDto.isUsed) &&
                Objects.equals(speed, shipDto.speed) &&
                Objects.equals(crewSize, shipDto.crewSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, planet, shipType, prodDate, isUsed, speed, crewSize);
    }

    @Override
    public String toString() {
        return "ShipDto{" +
                "name='" + name + '\'' +
                ", planet='" + planet + '\'' +
                ", shipType='" + shipType + '\'' +
                ", prodDate=" + prodDate +
                ", isUser=" + isUsed +
                ", speed=" + speed +
                ", crewSize=" + crewSize +
                '}';
    }
}
