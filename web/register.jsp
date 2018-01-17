<%--
  Created by IntelliJ IDEA.
  User: dllo
  Date: 18/1/15
  Time: 上午11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
<form action="register.action" method="post">

    用户名:<input type="text" name="sname">
    <br><br>
    密码:<input type="password" name="spassword">
    <br><br>
    班级:<select id="clazzId" name="clazzId">
        <option value="-1">--请选择--</option>
    </select><br><br>
    <input type="submit" value="提交">
    <input type="reset" value="重置">

    <%--js引入--%>
    <script src="js/jquery-3.2.1.js" type="text/javascript"></script>
    <%--js代码--%>
    <script>
        /*文档加载完成*/
        $(document).ready(function () {
            /*定义一个获取班级列表的方法*/
            function getClazzAll() {
                $.ajax({
                    url: "getClazzAll.action",
                    dataType: "json",
                    success: function (result) {
                        console.log(result);
                        /*遍历返回的班级列表数组 追加到下拉列表*/
                        for (i = 0; i < result.length; i++) {
                            var cid = result[i].cid;
                            var cname = result[i].cname;
                            var opt = $("<option>").val(cid).text(cname);

                            $("#clazzId").append(opt);
                        }
                    }
                })
            }

            getClazzAll();
        })
    </script>


</form>

</body>
</html>
