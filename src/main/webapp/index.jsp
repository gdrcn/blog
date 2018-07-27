
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>UEditor</title>
    <script type="text/javascript">
        var basePath = "<%=basePath%>";
    </script>
    <script type="text/javascript" language="javascript" src="../js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="./ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="./ueditor/ueditor.all.js"></script>
    <script type="text/javascript" src="./ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" src="./js/index.js"></script>
</head>
<body>
<div>
    <textarea id="myEditor"></textarea>
</div>
</body>
</html>