<%@ page import="java.util.LinkedList" %>
<%@ page import="entity.Gpu" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>List of current gpu</title>
</head>
<body>
<table>
    <c:forEach var="gpu" items="${param.gpuList}">

        <tr>
            <td>
                <c:out value="${gpu.title}"/>
            </td>
            <td>
                <c:out value="${gpu.price}"/>
            </td>
            <td>
                <c:out value="${gpu.url}"/>
            </td>
        </tr>

    </c:forEach>

</table>
<% List<Gpu> gpus = (LinkedList<Gpu>) request.getAttribute("gpuList");

    for (Gpu gpu : gpus) {
        out.print("Title: " + gpu.getTitle());
        out.print("<br>");
        out.print("Price: " + gpu.getPrice() + " UAH");
        out.print("<br>");
        out.print("URL: <a href=\"" + gpu.getUrl() + "\"> link </a>");
        out.print("<br>");
        out.print("<br>");
    }

%>
<table>

</table>

<h2>Name: ${name}</h2>
<h2>Name: <c:out value="name"/></h2>
<h2>Name: <c:out value="${name}"/></h2>
<h2>Name: <c:out value="${param.name}"/></h2>

<h2>My name is getServletContext().getAttribute("name")</h2>

<h2>My name is <%= request.getAttribute("name") %>
</h2>

<h3> Student information: </h3>
Id: ${student.title} <br/>
Name: ${student.price} <br/>
Age: ${student.url}

</body>
</html>
