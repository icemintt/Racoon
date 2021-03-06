<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Employee Registration Form</title>

    <style>

        .error {
            color: #ff0000;
        }
    </style>

</head>

<body>

<h2>Login Form</h2>

<form:form method="POST" modelAttribute="user">
    <form:input type="hidden" path="id" id="id"/>
    <table>
        <tr>
            <td><label for="username">User Name: </label> </td>
            <td><form:input path="username" id="username"/></td>
            <td><form:errors path="username" cssClass="error"/></td>
        </tr>


        <tr>
            <td><label for="password">PASSWORD: </label> </td>
            <td><form:input path="password" id="password"/></td>
            <td><form:errors path="password" cssClass="error"/></td>
        </tr>

        <tr>
            <td colspan="3">

                        <input type="submit" value="login"/>

            </td>
        </tr>
    </table>
</form:form>
<br/>
<br/>
Go back to <a href="<c:url value='/list' />">List of All Employees</a>
</body>
</html>