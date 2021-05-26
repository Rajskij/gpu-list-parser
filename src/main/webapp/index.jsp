<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>My best GPU</title>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
            crossorigin="anonymous"></script>
    <style><%@include file="css/index.css"%> ></style>
</head>
<body>
<form action="<c:out value="${pageContext.request.contextPath}"/>/rating" method="post">
    <div class="background">
        <div class="container">
            <h1 class="text-center">Find best GPU for mining</h1><br>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text">Enter the MAXIMUM price in UAH</span>
                    <input type="text" class="form-control" name="price" id="price" max="200000" value="15000"
                           onchange="price_range.value = price.value"/>
                </div>
                <input type="range" oninput="price.value = price_range.value" class="form-control-range slider"
                       type="range" min="100" max="200000" value="15000" id="price_range" step="100"
                       onchange="price.value = price_range.value"/>
            </div>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text">Enter the MINIMUM desired value of Mh/s</span>
                    <input type="text" class="form-control" name="ethash" id="ethash" max="120" value="30"
                           onchange="ethash_range.value = ethash.value"/>
                </div>
                <input type="range" oninput="ethash.value = ethash_range.value" class="form-control-range slider"
                       type="range" min="1" max="120" value="30" id="ethash_range" step="1"
                       onchange="ethash.value = ethash_range.value"/>
            </div>
            <br>
            <input class="btn btn-primary" type="submit" value="Submit"/>
        </div>
    </div>
</form>
</body>
</html>