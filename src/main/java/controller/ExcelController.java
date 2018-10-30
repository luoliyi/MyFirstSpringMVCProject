package controller;


import com.nf.entities.Car;
import com.nf.entities.Employee;
import com.nf.entities.EmployeeList;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/excelController")
public class ExcelController {

    EmployeeList employeeList=new EmployeeList();

    /**
     * 导出csv表格
     *
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/exploreCsv",method = RequestMethod.GET)
    @ResponseBody
    public void joinXml(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type","application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename="+new String("EmployeeData".getBytes(),"UTF-8")+".csv");
        PrintWriter out=response.getWriter();
        //加上bom头,解决excel打开乱码问题
        byte[] bomStrByteArr = new byte[] { (byte) 0xef, (byte) 0xbb, (byte) 0xbf };
        String bomStr = new String(bomStrByteArr, "UTF-8");
        out.write(bomStr);
        StringBuffer str=new StringBuffer("");
        //数据的来源
        str.append("编号,姓名,性别,学历,工资\r\n");
        for (Employee item:employeeList.getEmp()) {
            str.append(item.getEid()+","+item.getEname()+","+item.getEsex()+","+item.getEducation()+","+item.getMonthly()+"\r\n");
        }
        response.getWriter().write(str.toString());
    }

    /**
     * 导出Excel表格
     *
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/exploreExcel")
    @SuppressWarnings("resource")
    public void excel(HttpServletResponse response) throws IOException {
        EmployeeList employeeList=new EmployeeList();
        //设置标题
        String head = "员工";
        //设置表头行
        String[] headrow = {"员工编号", "员工姓名", "员工性别","学历","月薪"};

        if (null != employeeList.getEmp() && employeeList.getEmp().size() > 0) {
            String fileName = "员工数据.xls";//定义导出头
            response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));    //设置文件头编码格式
            response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");//设置类型
            response.setHeader("Cache-Control", "no-cache");//设置头
            response.setDateHeader("Expires", 0);//设置日期头

            //创建工作簿HSSFWorkbook 对象
            HSSFWorkbook book = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet = book.createSheet();
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
            HSSFRow row = sheet.createRow(0);
            //由工作簿创建表HSSFSheet对象
            CellStyle cellStyle = book.createCellStyle();

            cellStyle.setDataFormat(book.createDataFormat().getFormat("yyyy-MM-dd"));

            //设置表头
            HSSFCell cell = row.createCell((short) 0);
            cell.setCellValue(head);
            row = sheet.createRow(1);
            for (int i = 0; i < headrow.length; i++) {
                cell = row.createCell((short) i);
                cell.setCellValue(headrow[i]);
            }

            for (int i = 0; i < employeeList.getEmp().size(); i++) {
                //实体类对象
                Employee emp = employeeList.getEmp().get(i);
                row = sheet.createRow((i + 2));
                row.createCell((short) 0).setCellValue(emp.getEid());
                row.createCell((short) 1).setCellValue(emp.getEname());
                row.createCell((short) 2).setCellValue(emp.getEsex());
                row.createCell((short) 3).setCellValue(emp.getEducation());
                row.createCell((short) 4).setCellValue(emp.getMonthly());
            }
            //写出流  刷新缓冲流  关闭流对象
            book.write(response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }
    }

    /**
     * 导入ToExcel表格的数据
     *
     * @throws IOException
     */
    @RequestMapping(value = "/importExcels",method = RequestMethod.POST)
    @SuppressWarnings("resource")
    public String excels(MultipartFile files, HttpServletResponse response, HttpServletRequest request) throws IOException {
        //文件存放的位置
        String path=request.getSession().getServletContext().getRealPath("/files");
        //保存文件
        File tempFile=new File(path, files.getOriginalFilename());
        files.transferTo(tempFile);//把文件从内存存到磁盘中
        System.out.println(path+"\\"+files.getOriginalFilename());

        //Excel导入数据
        InputStream is = new FileInputStream(path+"\\"+files.getOriginalFilename());
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        Employee emp = null;
        List<Employee> list = new ArrayList<Employee>();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                    emp = new Employee();
                    XSSFCell eid = xssfRow.getCell(0);
                    XSSFCell ename = xssfRow.getCell(1);
                    XSSFCell esex = xssfRow.getCell(2);
                    XSSFCell education = xssfRow.getCell(3);
                    XSSFCell monthly = xssfRow.getCell(4);
                    emp.setEid(getValue(eid));
                    emp.setEname(getValue(ename));
                    emp.setEsex(getValue(esex));
                    emp.setEducation(getValue(education));
                    emp.setMonthly(getValue(monthly));
                    list.add(emp);
                }
            }
        }
        employeeList.setEmp(list);
        return "redirect:http://localhost:8080/";
    }

    @SuppressWarnings("static-access")
    private String getValue(XSSFCell xssfCell) {
        if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(xssfCell.getBooleanCellValue());
        } else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            return String.valueOf(xssfCell.getNumericCellValue());
        } else {
            // 返回字符串类型的值
            return String.valueOf(xssfCell.getStringCellValue());
        }
    }
}
