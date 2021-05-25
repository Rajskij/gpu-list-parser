<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <h1>Finding best GPU for mining</h1>
    <form action="<%= request.getContextPath() %>/index" method="post">
        <table style="with: 80%">
            <tr>
                <td>Input your max price of GPU</td>
                <td><input type="text" name="price"/></td>
            </tr>
            <tr>
                <td>Input max value of Mh/s you want to get</td>
                <td><input type="text" name="ethash"/></td>
            </tr>
        </table>
        <input type="submit" value="Submit"/>
    </form>
</div>
</body>
</html>
