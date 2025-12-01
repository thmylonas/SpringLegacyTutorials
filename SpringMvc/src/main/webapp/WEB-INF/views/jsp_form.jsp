<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Metadata -->
    <meta name="description" content="JSP: Submit Form"/>

    <title>JSP: Submit Form</title>

    <!-- Stylesheets -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jsp_form.css"/>
</head>
<body class="body">
<div class="page_title">
    Demonstration of JSP Framework
</div>

<div class="container">
    <h2>JSP: Submit Form</h2>
    <form action="http://localhost:8080/spring-mvc/jsp-form-result" method="POST">
        <label for="name">Name:</label>
        <input id="name" type="text" name="name" class="name" required><br>
        <label for="email">Email:</label>
        <input id="email" type="email" name="email" class="email" required><br>
        <input type="submit" value="Submit" class="submit">
    </form>
</div>
</body>
</html>
