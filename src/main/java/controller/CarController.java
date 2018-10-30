package controller;

import com.nf.entities.Car;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(path = "CarController")
public class CarController {

    //@RequestBody的作用：是让Spring MVC在收到客户端请求时将选择合适的转换器将参数转换成相应的对象。
    //将HTTP请求正文转换为适合的HttpMessageConverter对象。
    @RequestMapping(path = "/listcar" ,method = RequestMethod.POST)
    public void ListCar(@RequestBody List<Car> carList, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        System.out.println(Arrays.deepToString(carList.toArray()));
        try {
            response.getWriter().print("添加成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //返回前台json
    //@ResponseBody：将内容或对象作为 HTTP 响应正文返回，返回json。
    // 并调用适合HttpMessageConverter（消息转换器）的Adapter（适配器）转换对象，写入输出流。
    //系统会使用jackson将该对象自动序列化成json字符
    @RequestMapping(path = "/getCarlist" ,method = RequestMethod.POST)
    @ResponseBody
    public  List<Car> getCarlist(@RequestBody List<Car> carList, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        return carList;
    }

    //@ModelAttribute：类似于类初始化，每次走进这个方法就会调用这个方法。
    @ModelAttribute
    public String noaction() {
        System.out.println("noaction 方法被调用！");
        String message = "来自noaction方法的信息";
        return message;
    }
}
