<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.GregorianCalendar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Hotline GPU list</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <script type="text/javascript" src="https://www.kryogenix.org/code/browser/sorttable/sorttable.js"></script>
</head>
<body>
<style><%@include file="css/rating.css"%></style>

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: dimgrey">
        <div>
            <a href="/" class="btn btn-primary"> Go back to main page </a>
        </div>
        <h5>
        <%
            // Set refresh, autoload time as 1200 seconds == 20 min
            response.setIntHeader("Refresh", 1200);

            Calendar calendar = new GregorianCalendar();
            String am_pm;

            int hour = calendar.get(Calendar.HOUR);
            int minute = calendar.get(Calendar.MINUTE) + 20;
            int second = calendar.get(Calendar.SECOND);

            if (minute >= 60) {
                hour += 1;
                minute = minute % 60;
            }
            if (calendar.get(Calendar.AM_PM) == 0)
                am_pm = "AM";
            else
                am_pm = "PM";
            String CT = hour + ":" + String.format("%02d", minute) + ":" + String.format("%02d", second) + " " + am_pm;
            out.println("This page will be reloaded at " + CT + "\n");
        %>
        </h5>
    </nav>
</header>
<br>
<div class="row">
    <div class="container">
        <h3 class="text-center">Table of graphics cards based on AMD and NVIDIA GPU</h3><br>
        <h6 name="price">Yor current maximum price in UAH: <c:out value="${price}"/> UAH</h6>
        <h6 name="ethash">Your minimum desire value of hashrate: <c:out value="${ethash}"/> Mh/s</h6>
        <hr>
        <br>
        <table class="table table-bordered sortable">
            <thead>
            <tr>
                <th class="title">Title</th>
                <th class="title">Hashrate(Mh/s)</th>
                <th class="title">Average price(UAH)</th>
                <th class="title">Hotline link</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <c:forEach var="gpu" items="${gpuList}">
            <tr>
                <td>${gpu.title}</td>
                <td class="title"> ${gpu.ethash} </td>
                <td class="title">${gpu.price}</td>
                <td class="title"><a href="${gpu.url}" target="_blank">link</a></td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>