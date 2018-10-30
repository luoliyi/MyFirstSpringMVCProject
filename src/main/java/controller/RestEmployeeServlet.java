package controller;

import com.nf.entities.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
@RestController
@RequestMapping( path = "restEmployeeServlet")
public class RestEmployeeServlet {

    List<Employee> employees;
    @RequestMapping(path = "/getAllData",method = RequestMethod.POST)
    public void getAllData(@RequestBody List<Employee> employeeList,HttpServletResponse response){
        employees=employeeList;
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().print("success add!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(path = "/sendAllData",method = RequestMethod.POST)
    @ResponseBody
    public List<Employee> sendAllData(){
        return employees;
    }


    @RequestMapping(path = "/addEmployee",method = RequestMethod.POST)
    public Employee addEmployee(@RequestBody Employee employee){
        System.out.println("post提交新增："+employee);
        return employee;
    }

    @RequestMapping(path = "/updateEmployee",method = RequestMethod.PUT)
    public Employee updateEmployee(@RequestBody Employee employee){
        System.out.println("put修改："+employee);
        return employee;
    }

    @RequestMapping(path = "/deleteEmployeeByEno",method = RequestMethod.POST)
    @ResponseBody
    public String deleteEmployeeByEno(HttpServletRequest request){
        String eno=request.getParameter("eno");
        System.out.println("delete传过来的删除："+eno);
        return eno;
    }
}