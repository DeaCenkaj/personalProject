<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
    <title>New Comment</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-image: url('https://sdavidmiller.com/octo/images/best_of_2018/best_of_2018.jpg');
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-position: center center;
            background-color: darkred; /* Fallback color if the background photo is not loaded */
        }

        .container {
            background-color: rgba(0, 0, 0, 0.8); /* Semi-transparent black background for the content */
            padding: 20px;
            border-radius: 10px;
            margin-top: 50px;
        }

        .text-light {
            color: #fff; /* White text color */
        }

        .btn-primary {
            background-color: darkred; /* Dark red background for the submit button */
            border-color: darkred; /* Dark red border for the submit button */
        }

        /* Add additional custom styling here */

    </style>
</head>
<body>

<div class="container">
    <h2 class="text-light"><a href="/dashboard">Dashboard</a></h2>

    <h1 class="text-light">Film: ${film.title}</h1>
    <h5 class="text-light">Posted By: ${film.lead.firstName}</h5>

    <div>
        <form:form action="/films/${film.id}/comments" method="post" modelAttribute="comment">
            <div class="form-group">
                <label for="comment" class="float-left text-light">Add a comment for this movie:</label>
                <form:textarea rows="4" class="form-control" path="name" id="comment"></form:textarea>
                <form:errors path="name" cssClass="text-danger"/>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form:form>
    </div>

    <hr>

    <c:forEach var="comment" items="${comments}">
        <div class="card mt-2">
            <div class="card-body">
                <h4 >Added by <c:out value="${comment.creator.firstName}"></c:out> at <fmt:formatDate value="${comment.createdAt}" pattern="h:mm a MMMM dd"/></h4>
                <p ><c:out value="${comment.name}"></c:out></p>
            </div>
        </div>
    </c:forEach>
</div>

</body>
</html>
