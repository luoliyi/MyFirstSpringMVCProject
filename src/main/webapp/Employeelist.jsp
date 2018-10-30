<%--
  Created by IntelliJ IDEA.
  User: one
  Date: 2018/10/25
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employeelist</title>
</head>
<body>
<form id="form1" method="post" action="/EmployeeServlet/list">
    <table id="table1" border="1">
        <thead>
        <tr>
            <th style="width: 120px">员工编号</th>
            <th style="width: 120px">姓名</th>
            <th style="width: 120px">性别</th>
            <th style="width: 120px">学历</th>
            <th style="width: 120px">月薪</th>
            <th style="width: 120px">增加</th>
            <th style="width: 120px">移除</th>
        </tr>
        </thead>
        <tbody id="tbody">
        <tr class="onerow">
            <td><input type="text" id="eno" name="eno"/></td>
            <td><input type="text" id="ename" name="ename"/></td>
            <td>
                <select style="width: 120px;" id="esex" name="esex">
                    <option value="">请选择</option>
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </td>
            <td>
                <select style="width: 120px;" name="eeducate" id="eeducate">
                    <option value="">请选择</option>
                    <option value="本科毕业">本科毕业</option>
                    <option value="研究生">研究生</option>
                    <option value="博士">博士</option>
                </select>
            </td>
            <td>
                <input type="text" id="esalary" name="esalary"/>
            </td>
            <td>
                <img src="images/加号.jpg" width="120px" height="30px" class="img1">
            </td>
            <td>
                <img src="images/减号.jpg" width="120px" height="30px" class="img2">
            </td>
        </tr>
        </tbody>
    </table>
    <input type="submit" value="批量添加" style="text-align: center;">
</form>

</body>
<script src="js/jquery-1.11.3.js" type="text/javascript"></script>
<script>
    $(function () {
        $("#table1").on("click",".img1",function () {
            //克隆
            var tr=$(".onerow").clone();
            $("#tbody").append(tr);
        });

        $("#table1").on("click",".img2",function () {
            if ($("#tbody tr").length==1){
                alert("最后一行了！");
                return false;
            }
            $(this).closest("tr").remove();
        })
    })
</script>
</html>
