package org.openmrs.module.ptme.forms;

public class FindChildForm {

    private String childFollowupNumber;
    private String mode;

    public FindChildForm() {
        setMode("list");
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getChildFollowupNumber() {
        return childFollowupNumber;
    }

    public void setChildFollowupNumber(String childFollowupNumber) {
        this.childFollowupNumber = childFollowupNumber;
    }
}
