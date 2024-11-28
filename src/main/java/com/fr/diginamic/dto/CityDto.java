package com.fr.diginamic.dto;

public class CityDto {
    private String cityCode;
    private int nbrInhabitants;
    private String departmentCode;
    private String departmentName;

    /**
     * Getter
     *
     * @return cityCode
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * Setter
     *
     * @param cityCode cityCode
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * Getter
     *
     * @return nbInhabitants
     */
    public int getNbrInhabitants() {
        return nbrInhabitants;
    }

    /**
     * Setter
     *
     * @param nbrInhabitants nbInhabitants
     */
    public void setNbrInhabitants(int nbrInhabitants) {
        this.nbrInhabitants = nbrInhabitants;
    }

    /**
     * Getter
     *
     * @return departmentCode
     */
    public String getDepartmentCode() {
        return departmentCode;
    }

    /**
     * Setter
     *
     * @param departmentCode departmentCode
     */
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    /**
     * Getter
     *
     * @return departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Setter
     *
     * @param departmentName departmentName
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}



