<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: dllo
  Date: 18/1/16
  Time: 下午4:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
    <style type="text/css">
        td {

            border: 2px solid blueviolet;
            padding: 5px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<%--获取域 需要加#--%>
<h1>欢迎<s:property value="#session.student.sname"/>登录本系统</h1>
<p>
    班级<select id="clazz" name="clazzId">
    <option value="">--请选择--</option>
</select>

姓名:<input type="text" name="sname">
<button id="query">查询</button>
</p>

<table>
    <thead>
    <tr>
        <td>学生编号</td>
        <td>学生姓名</td>
        <td>所在班级</td>
        <td>其他信息</td>
    </tr>
    </thead>
    <tbody id="content"></tbody>
</table>
<div>
    <span>第1/3页</span>
    <a href="">[首页]</a>
    <a href="">[上一页]</a>
    <a href="">[下一页]</a>
    <a href="">[尾页]</a>
</div>
    <%--引入jquery--%>
    <script src="js/jquery-3.2.1.js"
            type="text/javascript"></script>
    <%--js代码--%>
    <script>
        $(document).ready(function () {
            /*定义一个查询学生的方法*/
            function selectStudent(clazzID, name) {
                /*发起ajax请求*/
                $.ajax({
                    url: "selectStudent.action",
                    data: {
                        "clazzId": clazzID,
                        "sname": name
                    },
                    dataType: "json",
                    success: function (studentList) {
                        /*处理返回结果*/
                        console.log(studentList)
                        /*1.先清空原来的表格内容*/
                        $("#content").empty();
                        /*2.遍历返回回来的数组*/
                        for (var i = 0; i < studentList.length; i++) {


                            var sid = studentList[i].sid;
                            var sname = studentList[i].sname;
                            var cname = studentList[i].clazz.cname;
                            var cinfor = studentList[i].clazz.cinfor;
                            var tr = $("<tr>");

                            var td1 = $("<td>").text(sid);
                            var td2 = $("<td>").text(sname);
                            var td3 = $("<td>").text(cname);
                            var td4 = $("<td>").text(cinfor);
                            /*将四列追加到当前行对象中*/
                            tr.append(td1);
                            tr.append(td2);
                            tr.append(td3);
                            tr.append(td4);

                            /*将tr行追加到tbody对象中*/
                            $("#content").append(tr);
                        }
                    }
                })
            }

            selectStudent(null, null);
            /*高级查询*/
            $("#query").click(function () {
                var clazzID = $("#clazz").val();
                /*取学生姓名输入框,eq取第0个*/
                var name = $("input").eq(0).val();
                console.log(clazzID + "--" + name);
                /*调用查询学生方法*/
                selectStudent(clazzID, name);
            });

            /*调用查询学生的方法*/
            selectStudent();


            function getClazzAll() {
                $.ajax({
                    url: "getClazzAll.action",
                    dataType: "json",
                    success: function (result) {
                        console.log(result);
                        for (i = 0; i < result.length; i++) {
                            var cid = result[i].cid;
                            var cname = result[i].cname;
                            var opt = $("<option>").val(cid).text(cname);
                            $("#clazz").append(opt);
                        }
                    }
                })
            }

            getClazzAll();
        })


    </script>

</body>
</html>