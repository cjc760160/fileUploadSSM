<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>用户上传图片页面</title>
    <base href="<%=basePath%>">
    <meta http-equiv="content-type" content="text/html" ; charset="UTF-8">
</head>
<body>
<center>
    <form action="file/onefile" method="post"
          enctype="multipart/form-data">
        <input type="file" name="file"/>
        <input type="submit" value="上传"/>
    </form>
    <h5>上传结果</h5>
    <image alt="暂无图片" src="${fileUrl}"></image>

</center>

</body>
</html>
