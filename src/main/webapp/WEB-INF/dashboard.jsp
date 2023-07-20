<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <title>Film Club</title>
    <style>
        body {
            background-color: #f7f7f7;
        }

        .container {
            margin-top: 50px;
        }

        .header-box {
            background-color: #610000; /* Dark Red */
            background-image: url("https://sdavidmiller.com/octo/images/best_of_2018/best_of_2018.jpg");
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            color: #ffffff;
            text-align: center;
            padding: 50px;
            border-radius: 10px;
            margin-bottom: 30px;
        }

        .header-box h1  {
            color: #ffffff;
            font-weight: bold;
            background-color: #902a2a; /* Dark Red */
            padding: 10px 20px;
            border-radius: 10px;
            display: inline-block;
            margin-bottom: 20px;
        }

        .header-box p {
            color: #4c0e0e; /* Slightly darker red */
            font-size: 18px;
            font-weight: bold;
        }

        .film-table {
            background-color: #ffffff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            overflow: hidden;
        }

        .film-table th {
            background-color: #8a1f1f; /* Darker Red */
            color: #ffffff;
        }

        .film-table td, .film-table th {
            padding: 12px;
            text-align: center;
        }

        .film-table a {
            text-decoration: none;
            color: #610000; /* Dark Red */
        }

        .film-table a:hover {
            color: #4c0e0e; /* Slightly darker red on hover */
        }

        .btn-rounded {
            border-radius: 50px;
        }

        .btn-primary {
            background-color: #610000; /* Dark Red */
            border-color: #610000;
        }

        .btn-primary:hover {
            background-color: #4c0e0e; /* Slightly darker red on hover */
            border-color: #4c0e0e;
        }

        .btn-secondary {
            background-color: #771414; /* Darker Red */
            border-color: #771414;
        }

        .btn-secondary:hover {
            background-color: #660f0f; /* Slightly darker red on hover */
            border-color: #660f0f;
        }

        .btn-warning {
            background-color: #c27400; /* Dark Orange */
            border-color: #c27400;
        }

        .btn-warning:hover {
            background-color: #a65c00; /* Slightly darker orange on hover */
            border-color: #a65c00;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col">
            <div class="header-box">
                <h1>Film Club</h1>
                <p>Welcome, ${user.firstName}</p>
                <p><a href="/logout" class="btn btn-secondary btn-rounded">Log Out</a></p>
                <p><a href="/films/new" class="btn btn-primary btn-rounded">Add Film</a></p>
            </div>

            <div class="film-table">
                <h4 class="p-3 mb-0" style="background: linear-gradient(45deg, #610000, #771414); border-top-left-radius: 10px; border-top-right-radius: 10px; color: #ffffff;">All Films</h4>
                <table class="table table-striped m-0">
                    <thead>
                    <tr>
                        <th>Title</th>
                        <th>Director</th>
                        <th>Actors</th>
                        <th>Genre</th>
                        <th>Posted By</th>
                        <th>Watched</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="film" items="${unwatchedFilms}">
                        <tr>
                            <c:if test="${film.lead.id != user.id}">
                                <td><a href="/films/${film.id}">${film.title}</a></td>
                                <td>${film.director}</td>
                                <td>${film.actors}</td>
                                <td>${film.genre}</td>
                                <td>${film.lead.firstName}</td>
                                <td><a href="/dashboard/watched/${film.id}" class="btn btn-warning btn-sm btn-rounded">Have Watched</a></td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <hr class="my-4">

            <div class="film-table">
                <h4 class="p-3 mb-0" style="background: linear-gradient(45deg, #610000, #771414); border-top-left-radius: 10px; border-top-right-radius: 10px; color: #ffffff;">Your Posts</h4>
                <table class="table table-striped m-0">
                    <thead>
                    <tr>
                        <th>Title</th>
                        <th>Director</th>
                        <th>Actors</th>
                        <th>Genre</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="film" items="${watchedFilms}">
                        <tr>
                            <td><a href="/films/${film.id}">${film.title}</a></td>
                            <td>${film.director}</td>
                            <td>${film.actors}</td>
                            <td>${film.genre}</td>
                            <c:if test="${film.lead.id == user.id}">
                                <td><a href="/films/edit/${film.id}" class="btn btn-warning btn-sm btn-rounded">Edit Your Post</a></td>
                            </c:if>
                            <c:if test="${film.lead.id != user.id}">
                                <td><a href="/dashboard/leave/${film.id}" class="btn btn-warning btn-sm btn-rounded">Haven't Watched it Yet</a></td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
