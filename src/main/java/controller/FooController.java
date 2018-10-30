package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/foo")
public class FooController {
    @RequestMapping("/jstl")
    public String jstl(Model model) {
        model.addAttribute("message", "Hello JSTL View!");
        return "foo/jstl";
    }

    @RequestMapping("/ftl")
    public String ftl(Model model) {
        model.addAttribute("users", new String[]{"tom","mark","jack"});
        model.addAttribute("message", "Hello FreeMarker View!");
        return "foo/ftl";
    }
}