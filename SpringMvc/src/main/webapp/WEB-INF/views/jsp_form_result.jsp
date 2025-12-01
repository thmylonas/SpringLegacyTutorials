<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Metadata -->
    <meta name="description" content="JSP: Form Result"/>

    <title>JSP: Form Result</title>

    <!-- Stylesheets -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jsp_form.css"/>
</head>
<body class="body">
<div class="page_title">
    Demonstration of JSP Framework
</div>

<div class="container">
    <h2>JSP: Form Result</h2>

    <!-- Fetch the request parameters -->
    <% String name = request.getParameter("name"); %>
    <% String email = request.getParameter("email"); %>

    <p class="result">
        User:
        <span class="result_param">
            <%= name %>
        </span>

    </p>
    <p class="result">
        Email:
        <span class="result_param">
            <%= email %>
        </span>
    </p>
</div>
</body>
</html>
