<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.GregorianCalendar" %>
<%@ page import="servlet.GpuServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Hotline GPU list</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <base target="_blank">
</head>

<body>
<%
    // Set refresh, autoload time as 1200 seconds == 20 min
    response.setIntHeader("Refresh", 1200);

    // Get current time
    Calendar calendar = new GregorianCalendar();
    String am_pm;

    int hour = calendar.get(Calendar.HOUR);
    int minute = calendar.get(Calendar.MINUTE);
    int second = calendar.get(Calendar.SECOND);

    if(calendar.get(Calendar.AM_PM) == 0)
        am_pm = "AM";
    else
        am_pm = "PM";
    String CT = hour+":"+ minute +":"+ second +" "+ am_pm;
    out.println("Crrent Time: " + CT + "\n");
%>
<style>
    .title {
        text-align: center;
    }
    .btn {
        background-color: dodgerblue;
        border-radius: 5px;
        padding: 5px 10px;
        margin: 10px 60px;
    }
</style>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: dimgrey">
        <div>
            <a href="/" class="btn btn-primary"> Go back to main page </a>
        </div>
    </nav>
</header>
<br>
<div class="row">
    <div class="container">
        <h3 class="text-center">List of GPU</h3>
        <h6 name="price">Yor current maximum price in UAH: <c:out value="${price}"/> UAH</h6>
        <h6 name="ethash">Your minimum desire value of hashrate: <c:out value="${ethash}"/> Mh/s</h6>
        <hr>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th class="title">Title</th>
                <th class="title">Hashrate(Mh/s)</th>
                <th class="title">Average price</th>
                <th class="title">Hotline link</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <c:forEach var="gpu" items="${gpuList}">
            <tr>
                <td>${gpu.title}</td>
                <td class="title"> ${gpu.ethash} </td>
                <td class="title">${gpu.price} UAH</td>
                <td class="title"><a href="${gpu.url}">link</a></td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>

</html>