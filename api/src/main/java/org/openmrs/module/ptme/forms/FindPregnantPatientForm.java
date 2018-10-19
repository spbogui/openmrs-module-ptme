package org.openmrs.module.ptme.forms;

public class FindPregnantPatientForm {

    private String pregnantNumber;
    private String hivCareNumber;
    private String mode;

    public FindPregnantPatientForm() {
        setMode("list");
    }

    public String getPregnantNumber() {
        return pregnantNumber;
    }

    public void setPregnantNumber(String pregnantNumber) {
        this.pregnantNumber = pregnantNumber;
    }

    public String getHivCareNumber() {
        return hivCareNumber;
    }

    public void setHivCareNumber(String hivCareNumber) {
        this.hivCareNumber = hivCareNumber;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

}
