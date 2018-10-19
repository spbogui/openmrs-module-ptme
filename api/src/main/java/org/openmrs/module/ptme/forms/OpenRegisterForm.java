package org.openmrs.module.ptme.forms;

public class OpenRegisterForm {
    private String pregnantNumber;
    private String register;
    private String mode;

    public OpenRegisterForm() {
    }

    public String getPregnantNumber() {
        return pregnantNumber;
    }

    public void setPregnantNumber(String pregnantNumber) {
        this.pregnantNumber = pregnantNumber;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
