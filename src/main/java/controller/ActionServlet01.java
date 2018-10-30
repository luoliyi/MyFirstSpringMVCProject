package controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
@RequestMapping(path = "/ActionServlet01",method =RequestMethod.GET) //defalut:GET
public class ActionServlet01 {

    @RequestMapping("")
    public String action00(Model model){
        model.addAttribute("message","welcome to action00!");
        return "show";
    }
    @RequestMapping("/action02")
    public String action02( Model model){
        model.addAttribute("message", "welcome to action02!");
        return "show";
    }

    @RequestMapping("/action03/{p1}/{p2}")
    public String action03(@PathVariable int p1, @PathVariable int p2, Model model){
        model.addAttribute("message", "总数等于："+(p1+p2));
        return "show";
    }

    //正则表达式模式的URI模板
    @RequestMapping(path = "/action4/{id:\\d{5}}-{name:[a-z]{3}}")
    public String action4(@PathVariable int id, @PathVariable String name, Model model){
        model.addAttribute("message", "id:"+id+" name:"+name);
        return "show";
    }

    //Ant风格路径模式
    @RequestMapping(path = "/action5/*.do")
    public String action5(Model model){
        model.addAttribute("message","Ant风格路径模式");
        return "show";
    }

    // 请求内容类型必须为text/html，注意浏览器默认没有指定Content-type
    @RequestMapping(value = "/action6",consumes="text/html")
    public String action6(Model model) {
        model.addAttribute("message", "请求的提交内容类型（Content-Type）是text/html");
        return "show";
    }

    // 请求方法必须是post
    @RequestMapping(value = "/action7",method = RequestMethod.POST)
    public String action7(Model model) {
        model.addAttribute("message", "请求的提交内容的方法是post");
        return "show";
    }

    // 请求方法必须是post
    @RequestMapping(value = "/action8",method = RequestMethod.GET)
    public String action8(Model model) {
        model.addAttribute("message", "请求的提交内容的方法是GET");
        return "show";
    }
    // 请求方法必须是delete
    @RequestMapping(value = "/action9",method = RequestMethod.DELETE)
    public String action9(Model model) {
        model.addAttribute("message", "请求的提交内容的方法是DELETE");
        return "show";
    }

    //客户端接收json且编码为utf-8，多数浏览器Accept设置的为*/*，接收任意类型
    @RequestMapping(value = "/action10",produces="application/json; charset=UTF-8")
    public String action10(Model model) {
        model.addAttribute("message", "客户端可以接收的类型是application/json; charset=UTF-8");
        return "show";
    }

    //请求的参数必须包含id=215与name不等于abc
    @RequestMapping(value = "/action11",params={"id=215","name!=abc"})
    public String action11(Model model) {
        model.addAttribute("message", "请求的参数必须包含id=215与name不等于abc");
        return "show";
    }

    //请求头部信息中必须包含Host=localhost:8088
    @RequestMapping(value = "/action12",headers="Host=localhost:8089")
    public String action12(Model model) {
        model.addAttribute("message", "请求头部信息中必须包含Host=localhost:8089");
        return "show";
    }
}