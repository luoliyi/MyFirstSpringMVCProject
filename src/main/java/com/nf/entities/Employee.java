package com.nf.entities;

public class Employee {
    private String eid;//员工编号
    private String ename;//员工姓名
    private String esex;//员工性别
    private String education;//学历
    private String monthly;//月薪

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eid=" + eid +
                ", ename='" + ename + '\'' +
                ", esex='" + esex + '\'' +
                ", education='" + education + '\'' +
                ", monthly=" + monthly +
                '}';
    }

    public Employee(String eid, String ename, String esex, String education, String monthly) {
        this.eid = eid;
        this.ename = ename;
        this.esex = esex;
        this.education = education;
        this.monthly = monthly;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEsex() {
        return esex;
    }

    public void setEsex(String esex) {
        this.esex = esex;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMonthly() {
        return monthly;
    }

    public void setMonthly(String monthly) {
        this.monthly = monthly;
    }
}
