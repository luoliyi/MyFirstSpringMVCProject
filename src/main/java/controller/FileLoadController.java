package controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping(path = "/fileLoadController")
public class FileLoadController {

    @RequestMapping("/upLoadFile")
    public String file3(Model model){
        return "up/upfile3";
    }

    @RequestMapping(value="/file3Save",method=RequestMethod.POST)
    public String file3Save(Model model,MultipartFile[] files,HttpServletRequest request) throws Exception{

        //文件存放的位置
        String path=request.getSession().getServletContext().getRealPath("/files");
        File pathfile=new File(path);
        if(!pathfile.exists()){
            pathfile.mkdir();
        }
        System.out.println(path);
        String msg="";
        for (MultipartFile file : files) {
            //保存文件
            File tempFile=new File(path, file.getOriginalFilename());
            file.transferTo(tempFile);
            msg+="<img src='../files/"+file.getOriginalFilename()+"' width='200' />";
        }
        model.addAttribute("message", msg);
        return "up/upfile3";
    }
}