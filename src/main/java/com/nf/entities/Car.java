package com.nf.entities;

import java.util.ArrayList;
import java.util.List;

public class Car {
    private String cno;
    private String cname;
    private double cprice;
    private Size size;


    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public double getCprice() {
        return cprice;
    }

    public void setCprice(double cprice) {
        this.cprice = cprice;
    }
    public  Car(){}

    public Car(String cno, String cname, double cprice) {
        this.cno = cno;
        this.cname = cname;
        this.cprice = cprice;
    }

    public Car(String cno, String cname, double cprice,Size size) {
        this.cno = cno;
        this.cname = cname;
        this.cprice = cprice;
        this.size=size;
    }

    public static List<Car> carList=new ArrayList<>();
    static {
        carList.add(new Car("123456","奔驰C200",260000,new Size("123","123",123)));
        carList.add(new Car("123123","奥迪A4L",240000,new Size("123","123",123)));
        carList.add(new Car("513575","奥迪A6L",450000,new Size("123","123",123)));
        carList.add(new Car("968073","丰田凯美瑞",200000,new Size("123","123",123)));
        carList.add(new Car("124862","宝马520Li",500000,new Size("123","123",123)));
    }

    @Override
    public String toString() {
        return "Car{" +
                "cno='" + cno + '\'' +
                ", cname='" + cname + '\'' +
                ", cprice=" + cprice +
                ", size=" + size +
                '}';
    }
}
