package controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

//一个项目可以有多个页面控制器（controller），一个页面控制器可以有多个方法,动作（action）
//在一个类上面引用@controller这个注解表示把这个类标记成一个处理器，@RequestMapping 映射从jsp传过来的请求。

/*
* Spring MVC框架像许多其他MVC框架一样, 请求驱动,围绕一个中心Servlet分派请求及提供其他功能，
* DispatcherServlet是一个实际的Servlet (它继承自HttpServlet 基类)。
* 如下图所示当发起请求时被前置的控制器拦截到请求，根据请求参数生成代理请求，找到请求对应的实际控制器，
* 控制器处理请求，创建数据模型，访问数据库，将模型响应给中心控制器，控制器使用模型与视图渲染视图结果，
* 将结果返回给中心控制器，再将结果返回给请求者。
* */

@Controller
@RequestMapping(value = "/",method =RequestMethod.GET) //defalut:GET
public class HelloServlet { //c：调用业务逻辑，取得数据，转发页面
    @RequestMapping("")
    public String hello(Model model) {
        model.addAttribute("message", "Hello,Welcome to my website!"); //m：业务逻辑，保持数据状态
        return "hi"; //v：显示
    }
    @RequestMapping(value = "show",method =RequestMethod.GET) //defalut:GET
    public String show(Model model){

        Map<String,Object> map=new HashMap<String, Object>();
        map.put("1","abc");
        map.put("2","abce");

        model.addAttribute("message","我是show页面!"+map);
        return "show";
    }
    @RequestMapping(value = "details",method =RequestMethod.GET) //defalut:GET
    public String details(Model model){
        model.addAttribute("message","我是details页面!");
        return "details";
    }
}