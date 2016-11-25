package com.maxclay.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

/**
 * @author maxclay
 */
@Entity
@Table(name = RowMaterial.TABLE_NAME)
public class RowMaterial {

    public static final String TABLE_NAME = "RowMaterial";

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private Double weight;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vendor")
    private Vendor vendor;

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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class RowMaterial {\n");
        sb.append("  id: ").append(id).append("\n");
        sb.append("  name: ").append(name).append("\n");
        sb.append("  weight: ").append(weight).append("\n");

        Long vendorId = (vendor != null) ? vendor.getId() : null;
        sb.append("  vendor: ").append(vendorId).append("\n");
        sb.append("}\n");

        return sb.toString();
    }
    
}
