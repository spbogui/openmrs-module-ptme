package org.openmrs.module.ptme.forms;

import java.util.Date;

public class ManageConsultationForm {
    private String register;
    private Date sDate;
    private Date eDate;
    private Integer page;
    private Integer size;

    public ManageConsultationForm() {
        page = 0;
        size = 10;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public Date getsDate() {
        return sDate;
    }

    public void setsDate(Date sDate) {
        this.sDate = sDate;
    }

    public Date geteDate() {
        return eDate;
    }

    public void seteDate(Date eDate) {
        this.eDate = eDate;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
