<%--
  Created by IntelliJ IDEA.
  User: Mr.Ho
  Date: 2018/10/25
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>批量添加员工信息</title>
    <style>
        #table1 ,#table2{
            width: 70%;
            margin: 0 auto;
        }

        .input {
            width: 120px;
        }

        #table1 td {
            text-align: center;
        }
    </style>
</head>
<body>
<h2>${msg}</h2>
<form method="post" action="SpringMvc/employee">
    <table id="table1" border="1">
        <tr>
            <th>员工编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>学历</th>
            <th>月薪</th>
            <th>增加</th>
            <th>移除</th>
        </tr>
        <tr>
            <td><input class="input" type="text" name="emp[0].eid"></td>
            <td><input class="input" type="text" name="emp[0].ename"></td>
            <td>
                <select class="input" name="emp[0].esex">
                    <option>-请选择-</option>
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </td>
            <td>
                <select class="input" name="emp[0].education">
                    <option>-请选择-</option>
                    <option value="初中">初中</option>
                    <option value="高中">高中</option>
                    <option value="大专">大专</option>
                    <option value="本科">本科</option>
                </select>
            </td>
            <td><input class="input" type="text" name="emp[0].monthly"></td>
            <td><input class="addbut" type="button" value="+"></td>
            <td><input class="delbut" type="button" value="-"></td>
        </tr>
    </table>
    <div style="text-align: center">
        <input style="margin-top: 20px; width: 200px" type="submit" value="批量添加"/>
    </div>
</form>

<%--添加数据--%>
<table border="1" id="table2">
    <thead>
    <tr>
        <td>用户编号</td>
        <td>用户姓名</td>
        <td>用户性别</td>
        <td>学历</td>
        <td>月薪</td>
    </tr>
    </thead>
    <tbody>
      <c:forEach items="${msg}" var="map">
          <tr>
              <td>${map.eid}</td>
              <td>${map.ename}</td>
              <td>${map.esex}</td>
              <td>${map.education}</td>
              <td>${map.monthly}</td>
          </tr>
      </c:forEach>
    </tbody>
</table>

<script src="js/jquery-1.11.3.js"></script>
<script>
    var i = 0;
    $("body").on("click", ".addbut", function (obj) {
        i++;
        var tr = "<tr>\n" +
            "            <td><input class=\"input\" type=\"text\" name=\"emp[" + i + "].eid\"></td>\n" +
            "            <td><input class=\"input\" type=\"text\" name=\"emp[" + i + "].ename\"></td>\n" +
            "            <td>\n" +
            "                <select class=\"input\" name=\"emp[" + i + "].esex\">\n" +
            "                    <option>-请选择-</option>\n" +
            "                    <option value=\"男\">男</option>\n" +
            "                    <option value=\"女\">女</option>\n" +
            "                </select>\n" +
            "            </td>\n" +
            "            <td>\n" +
            "                <select class=\"input\" name=\"emp[" + i + "].education\">\n" +
            "                    <option>-请选择-</option>\n" +
            "                    <option value=\"初中\">初中</option>\n" +
            "                    <option value=\"高中\">高中</option>\n" +
            "                    <option value=\"大专\">大专</option>\n" +
            "                    <option value=\"本科\">本科</option>\n" +
            "                </select>\n" +
            "            </td>\n" +
            "            <td><input class=\"input\" type=\"text\" name=\"emp[" + i + "].monthly\"></td>\n" +
            "            <td><input class=\"addbut\" type=\"button\" value=\"+\"></td>\n" +
            "            <td><input class=\"delbut\" type=\"button\" value=\"-\"></td>\n" +
            "        </tr>";
        var tr2 = $(tr).clone();
        $("#table1").append(tr2);
    });
    $("body").on("click", ".delbut", function (obj) {
        var tr = this.parentNode.parentNode;
        if ($("#table1 tr").length > 2) {
            $(tr).remove();
        } else {
            return false;
        }
    });
</script>
</body>
</html>
