package org.openmrs.module.ptme.forms;

import java.util.Date;

public class ManageChildFollowupForm {
    private String status;
//    private Integer followupResult;
    private Date followupResultStartDate;
    private Date followupResultEndDate;

    public ManageChildFollowupForm() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public Integer getFollowupResult() {
//        return followupResult;
//    }
//
//    public void setFollowupResult(Integer followupResult) {
//        this.followupResult = followupResult;
//    }

    public Date getFollowupResultStartDate() {
        return followupResultStartDate;
    }

    public void setFollowupResultStartDate(Date followupResultStartDate) {
        this.followupResultStartDate = followupResultStartDate;
    }

    public Date getFollowupResultEndDate() {
        return followupResultEndDate;
    }

    public void setFollowupResultEndDate(Date followupResultEndDate) {
        this.followupResultEndDate = followupResultEndDate;
    }
}
