package com.fr.diginamic.dto;

public class DepartmentDto {
    private String departmentCode;
    private String departmentName;
    private int nbrInhabitants;

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

    /**
     * Getter
     *
     * @return nbrInhabitants
     */
    public int getNbrInhabitants() {
        return nbrInhabitants;
    }

    /**
     * Setter
     *
     * @param nbrInhabitants nbrInhabitants
     */
    public void setNbrInhabitants(int nbrInhabitants) {
        this.nbrInhabitants = nbrInhabitants;
    }
}
