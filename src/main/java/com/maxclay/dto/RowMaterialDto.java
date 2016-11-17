package com.maxclay.dto;

/**
 * @author maxclay
 */
public class RowMaterialDto {

    private String name;
    private Double weight;
    private Long vendor;

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

    public Long getVendor() {
        return vendor;
    }

    public void setVendor(Long vendor) {
        this.vendor = vendor;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class RowMaterialDto {\n");
        sb.append("  name: ").append(name).append("\n");
        sb.append("  weight: ").append(weight).append("\n");
        sb.append("  vendor: ").append(vendor).append("\n");
        sb.append("}\n");

        return sb.toString();
    }

}
