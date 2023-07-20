<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <style>
        body {
            background-image: url("https://www.intofilm.org/intofilm-production/9628/scaledcropped/630x355/resources/9628/into-film-plus-catalogue-image-07-22.jpg");
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            color: #000000;
            margin-top: 50px; /* Add some distance between top and forms */
        }
        .form-container {
            background-color: rgba(255, 255, 255, 0.8); /* Slightly transparent white background color */
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #902a2a; /* Dark red */
        }
        .input {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button[type="submit"] {
            background-color: #902a2a; /* Dark red */
            color: #ffffff; /* White */
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            cursor: pointer;
        }
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login and Registration Page</title>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-5">
            <div class="form-container">
                <h2>Register</h2>
                <form:form action="/register" method="post" modelAttribute="newUser">
                    <form:errors path="*" cssClass="text-danger" element="div" />
                    <form:input class="input mb-3" path="firstName" placeholder="First Name" />
                    <form:input class="input mb-3" path="lastName" placeholder="Last Name" />
                    <form:input class="input mb-3" path="email" placeholder="Email" />
                    <form:input class="input mb-3" path="password" type="password" placeholder="Password" />
                    <form:input class="input mb-3" path="confirm" type="password" placeholder="Confirm Password" />
                    <button type="submit">Submit</button>
                </form:form>
            </div>
        </div>
        <div class="col-md-5">
            <div class="form-container">
                <h2>Log In</h2>
                <form:form action="/login" method="post" modelAttribute="newLogin">
                    <form:errors path="*" cssClass="text-danger" element="div" />
                    <form:input class="input mb-3" path="email" placeholder="Email" />
                    <form:input class="input mb-3" path="password" type="password" placeholder="Password" />
                    <button type="submit">Submit</button>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
