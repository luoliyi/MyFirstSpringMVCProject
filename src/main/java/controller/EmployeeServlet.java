package controller;

import com.nf.entities.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping( path = "EmployeeServlet")
public class EmployeeServlet {

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

    //删除的第一种方式
    @RequestMapping(path = "/delOneByEid",method = RequestMethod.POST)
    @ResponseBody
    public List<Employee> delOneByEid(HttpServletRequest request){
        String eid=request.getParameter("eid");
        int eid2=0;
        if(!(eid!=null&&eid.equals(""))){
            eid2=Integer.parseInt(eid);
        }
        for (int i=0;i<employees.size();i++){
            if(employees.get(i).getEid().equals(eid)){
                employees.remove(i);
           }
        }
        return employees;
    }

    //删除的第二种方式
//    @RequestMapping(path = "/delOneByEid2",method = RequestMethod.GET)
//    @ResponseBody
//    public List<Employee> delOneByEid2(@RequestBody String eid){
//        int eid2=0;
//        if(!(eid!=null&&eid.equals(""))){
//            System.out.println("eid："+eid);
//            eid2=Integer.parseInt(eid);
//        }
//        for (int i=0;i<employees.size();i++){
//            if(employees.get(i).getEid()==eid2){
//                employees.remove(i);
//            }
//        }
//        return employees;
//    }

    @RequestMapping(path = "/updOneByEid",method = RequestMethod.POST)
    @ResponseBody
    public List<Employee> updOneByEid(@RequestBody Employee employee){
        for (int i=0;i<employees.size();i++){
            if(employees.get(i).getEid()==employee.getEid()){
                employees.remove(i);
                employees.add(i,employee);
            }
        }
        System.out.println(employee);
        return employees;
    }

    //测试返回集合
    @RequestMapping(path = "/getAllByMap",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,List<Employee>> getAllByMap(){
        Map<String,List<Employee>> listMap=new HashMap<>();
        listMap.put("onedata1",new ArrayList(employees));
        listMap.put("onedata2",new ArrayList(employees));
        listMap.put("onedata3",new ArrayList(employees));
        return listMap;
    }
}