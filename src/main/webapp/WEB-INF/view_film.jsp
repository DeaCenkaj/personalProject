<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Film Details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: darkred;
            color: white;
            background-image: url("https://sdavidmiller.com/octo/images/best_of_2018/best_of_2018.jpg");

        }

        .container {
            background-color: rgba(0, 0, 0, 0.8);
            padding: 20px;
            border-radius: 10px;
            margin-top: 50px;
            max-width: 500px;
            margin: 50px auto;
        }

        .text-light {
            color: white;
        }

        .table {
            width: 100%;
            margin-bottom: 20px;
        }

        .table td {
            padding: 10px;
            border-bottom: 1px solid #ccc;
        }

        .table td:first-child {
            font-weight: bold;
            width: 30%;
        }

        .btn-primary,
        .btn-secondary {
            background-color: darkred;
            color: white;
            border-radius: 5px;
            border: 1px solid #ccc;
            padding: 10px 20px;
            margin-right: 10px;
        }

        .btn-primary:hover,
        .btn-secondary:hover {
            background-color: #9b0000;
        }

        .film-details {
            margin-top: 20px;
        }

        .film-details h1 {
            font-size: 24px;
        }

        .film-details h5 {
            font-size: 16px;
        }

        .film-details .btn-group {
            margin-top: 10px;
        }

        .film-details .btn-group a {
            text-decoration: none;
        }

        .film-details .btn-group a:hover {
            text-decoration: none;
        }

    </style>
</head>
<body>

<div class="container">
    <h2 class="text-light"><a href="/dashboard">Dashboard</a></h2>

    <div class="film-details">
        <h1 class="text-light">Film Details</h1>
        <table class="table">
            <tbody class="text-light">
            <tr>
                <td>Film:</td>
                <td><c:out value="${film.title}"></c:out></td>
            </tr>
            <tr>
                <td>Director:</td>
                <td><c:out value="${film.director}"></c:out></td>
            </tr>
            <tr>
                <td>Actors:</td>
                <td><c:out value="${film.actors}"></c:out></td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><c:out value="${film.description}"></c:out></td>
            </tr>
            <tr>
                <td>Genre:</td>
                <td><c:out value="${film.genre}"></c:out></td>
            </tr>
            </tbody>
        </table>

        <h5><a href="/films/${film.id}/comments">See Comments</a></h5>

        <c:if test="${film.lead.id == userId}">
            <div class="btn-group">
                <a class="btn-primary" href="/films/edit/${film.id}">Edit Film</a>
                <a class="btn-secondary" href="/films/delete/${film.id}">Delete Film</a>
            </div>
        </c:if>
    </div>

</div>

</body>
</html>
