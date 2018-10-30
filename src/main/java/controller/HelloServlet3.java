package controller;
import com.nf.entities.Car;
import com.nf.entities.CarMap;
import com.nf.entities.Product;
import com.nf.utils.JSONUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

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
@RequestMapping(path = {"/helloservlet3","/HelloServlet3","/servlet3","/Servlet3"},method =RequestMethod.GET) //defalut:GET
public class HelloServlet3 { //c：调用业务逻辑，取得数据，转发页面
    @RequestMapping("")
    public String hello(Model model) {
        model.addAttribute("message", "/helloservlet3"); //m：业务逻辑，保持数据状态
        return "hi"; //v：显示
    }

    @RequestMapping(path = "/createProduct",method = RequestMethod.GET) //defalut:GET
    public String createProduct(String pname,String pno,int pcount,Model model, Product product) {
        product.setPname(pname);
        product.setPno(pno);
        product.setPcount(pcount);
        model.addAttribute("message", product);
        return "show";
    }

    @RequestMapping(path = "/action01",method = RequestMethod.GET) //defalut:GET
    public String action01(Model model, Product product) {
        model.addAttribute("message", product);
        return "show";
    }

    //Car{cno='1', cname='bmw', cprice=33.33, size=Size{length='null', height='null', weight=3}}
    @RequestMapping(path = "/action02",method = RequestMethod.GET) //defalut:GET
    public String action02(Model model, Car car) {
        model.addAttribute("message", car);
         return "show";
    }

    @RequestMapping(path = "/action03",method = RequestMethod.POST) //defalut:GET
    public String action03(Model model, Car car) {
        model.addAttribute("message", car);
        return "show";
    }

    //基本数据类型绑定与注解属性
    @RequestMapping(path = "/action04",method = RequestMethod.POST) //defalut:GET
    public String action04(Model model, @RequestParam(required = false, defaultValue = "9") int cno) {
        model.addAttribute("message", "汽车编号："+cno);
        return "show";
    }

    //获得全部车辆信息F9CD
    @RequestMapping(path = "/action05",method = RequestMethod.POST) //defalut:GET
    public String action05(Model model) {
        model.addAttribute("message",JSONUtil.toJson(Car.carList));
        return "show";
    }

    //数组1
    @RequestMapping(path = "/action06",method = RequestMethod.POST) //defalut:GET
    public String action06(Model model,Integer[]id) {
        model.addAttribute("message", Arrays.toString(id));
        return "show";
    }

    //数组2
    @RequestMapping(path = "/action07",method = RequestMethod.POST) //defalut:GET
    public String action07(Model model, @RequestParam("id") List<String>id) {
        model.addAttribute("message", id.get(0)+","+id.get(1));
        return "show";
    }

    //List集合
    @RequestMapping(path = "/action08",method = RequestMethod.POST) //defalut:GET
    public String action08(Model model, @RequestParam(value = "cars" ,defaultValue = "",required = false)
                           CarMap carMap) {
        model.addAttribute("message",carMap.getCarMap().get(0)
                + "<br/>" + carMap.getCarMap().get(1));
        return "show";
    }

    //测试重定向,接收string类型的参数,请求头部带信息、
    @RequestMapping(path = "/action09",method = RequestMethod.POST)
    public String action09(Model model,String message){
        model.addAttribute("message", message);
        System.out.println(message);
        return "show";
    }
    // 重定向
    @RequestMapping(path = "/action10",method = RequestMethod.POST)
    public String action10(Model model){
        model.addAttribute("message","重定向的内容！");
        return "redirect:action09";
    }
    // 重定向
    @RequestMapping(path = "/testRedirect01",method = RequestMethod.POST)
    public String testRedirect01(Model model){
        model.addAttribute("message",new Car("123","奔驰c200",123325).toString());
        return "redirect:action09";
    }

    //测试转发
    @RequestMapping(path = "/action11",method = RequestMethod.GET)
    public String action11(Model model){
        return "show";
    }

    //转发，请求两次。
    @RequestMapping(path = "/action12",method = RequestMethod.GET)
    public String action12(Model model){
        model.addAttribute("message","action12里面的内容！");
        return "forward:action11";
    }

    @RequestMapping(path = "/action13",method = RequestMethod.POST)
    //@ResponseBody /*转json返回前台*/
    public ModelAndView action13(Model model){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("show");
        modelAndView.addObject("message","action13里面的内容,用到了modelAndView。");
        return modelAndView;
    }

    //返回值是任意类型，例如返回一个excel表格，用作项目
    @RequestMapping("/action14")
    @ResponseBody
    public String action14(HttpServletResponse response)    {
        response.setHeader("Content-type","application/octet-stream");
        response.setHeader("Content-Disposition","attachment; filename=table.xls");
        return "<table><tr><td>Hello</td><td>Excel</td></tr></table>";
    }

    //POI导入导出

}