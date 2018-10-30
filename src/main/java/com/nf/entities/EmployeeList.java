package com.nf.entities;

import java.util.ArrayList;
import java.util.List;

public class EmployeeList {
    private List<Employee> emp;

    public static List<Employee> employeeList=new ArrayList<>();
    static {
       employeeList.add(new Employee("123","one1","男","本科","15220"));
        employeeList.add(new Employee("321","one2","女","本科","17280"));
        employeeList.add(new Employee("674","one3","女","高中","15570"));
        employeeList.add(new Employee("981","one4","男","硕士","22620"));
    }

    public List<Employee> getEmp() {
        return employeeList;
    }

    public void setEmp(List<Employee> emp) {
        this.emp = emp;

        System.out.println("-----循环输出导入Excel的对象-----");
        for (int i=0;i<emp.size();i++){
            System.out.println(emp.get(i));
        }
    }

    public EmployeeList() {
    }

    public EmployeeList(List<Employee> emp) {
        this.emp = emp;
    }

    @Override
    public String toString() {
        return "EmployeeList{" +
                "emp=" + emp +
                '}';
    }

}
