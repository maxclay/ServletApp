package com.maxclay.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author maxclay
 */
@Entity
@Table(name = Vehicle.TABLE_NAME)
public class Vehicle implements Serializable {

    public static final String TABLE_NAME = "Vehicle";

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "carryingCapacity")
    private Double carryingCapacity;

    @Column(name = "mileage")
    private Double mileage;

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

    public Double getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(Double carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class Vehicle {\n");
        sb.append("  id: ").append(id).append("\n");
        sb.append("  name: ").append(name).append("\n");
        sb.append("  carryingCapacity: ").append(carryingCapacity).append("\n");
        sb.append("  mileage: ").append(mileage).append("\n");
        sb.append("}\n");

        return sb.toString();
    }

}
