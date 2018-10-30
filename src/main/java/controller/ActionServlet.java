package controller;

import com.nf.entities.EmployeeList;
import com.nf.utils.JSONUtil;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;

/**
 * 相当于servlet，@RequestMapping("/SpringMvc")映射的路径
 */
@Component
@RequestMapping("/SpringMvc")
public class ActionServlet {

    /**
     * Ant风格路径模式：问号匹配任何单个字符，一个星号匹配0或者任意数量的字符，多个星号表示可以有多个目录
     */
    @RequestMapping({"/index.do/{id}", "/**/bar/{id}"})
    public String mvc(Model model, @PathVariable int id) {
        model.addAttribute("msg", "hello SprintMVC,id是" + id);
        return "index";
    }

    /**
     * method属性指定谓词类型,可以收窄请求范围。指定请求谓词的类型如GET, POST, HEAD, OPTIONS, PUT, PATCH, DELETE, TRACE
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Model model) {
        model.addAttribute("msg", "这是一个post请求");
        return "index";
    }

    /**
     * consumes属性指定请求的Content-Type， 指定处理请求的提交内容类型（Content-Type），例如application/json, text/html，
     * 收窄请求范围，如果用户发送的请求内容类型不匹配则方法不会响应请求
     */
    @RequestMapping(value = "/con", consumes = "text/html")
    public String con(Model model) {
        model.addAttribute("msg", "请求的提交内容类型（Content-Type）是text/html");
        return "index";
    }

    //客户端接收json且编码为utf-8，多数浏览器Accept设置的为*/*，接收任意类型
    @RequestMapping(value = "/pro", produces = "application/json; charset=UTF-8")
    public String pro(Model model) {
        model.addAttribute("msg", "客户端可以接收的类型是application/json; charset=UTF-8");
        return "index";
    }

    @RequestMapping(value = "/act01")
    public String act01(Model model,Integer[] id) {
        model.addAttribute("msg", Arrays.toString(id));
        return "index";
    }

    @RequestMapping(value = "/employee")
    public String employee(Model model, EmployeeList emp) {
        model.addAttribute("msg", emp.getEmp());
        return "indexEmployee";
    }
}
