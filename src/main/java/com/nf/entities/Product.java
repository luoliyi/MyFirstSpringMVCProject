package com.nf.entities;

public class Product {
    private String pno;
    private String pname;
    private int pcount;

    public String getPno() {
        return pno;
    }

    public void setPno(String pno) {
        this.pno = pno;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getPcount() {
        return pcount;
    }

    public void setPcount(int pcount) {
        this.pcount = pcount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pno='" + pno + '\'' +
                ", pname='" + pname + '\'' +
                ", pcount=" + pcount +
                '}';
    }
    public Product(){}

    public Product(String pno, String pname, int pcount) {
        this.pno = pno;
        this.pname = pname;
        this.pcount = pcount;
    }
}
