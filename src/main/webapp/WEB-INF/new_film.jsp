<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <title>Create a new Film</title>
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

        .form-group {
            margin-bottom: 20px;
        }

        .input {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 16px;
            background-color: transparent;
            color: white;
        }

        .list-group-item {
            background-color: transparent;
            color: white;
            border-color: #ccc;
        }

        .linkBtn {
            color: white;
            text-decoration: none;
            background-color: darkred;
            padding: 10px 20px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        .linkBtn:hover {
            background-color: #9b0000;
        }

        .input[type="submit"] {
            background-color: darkred;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }

        .input[type="submit"]:hover {
            background-color: #9b0000;
        }
    </style>
</head>
<body>

<div class="container">
    <h2 class="text-light"><a href="/dashboard">Dashboard</a></h2>

    <h1 class="text-light">Create a new Film</h1>

    <form:form action="/films/new" method="post" modelAttribute="film">
        <div class="form-group">
            <label class="text-light">Film Title:</label>
            <form:errors path="title" cssClass="text-danger"/>
            <form:input class="form-control" path="title"/>
        </div>
        <div class="form-group">
            <label class="text-light">Film Director:</label>
            <form:errors path="director" cssClass="text-danger"/>
            <form:input class="form-control" path="director"/>
        </div>
        <div class="form-group">
            <label class="text-light">Film Actors:</label>
            <form:errors path="actors" cssClass="text-danger"/>
            <form:input class="form-control" path="actors"/>
        </div>
        <div class="form-group">
            <label class="text-light">Film Description:</label>
            <form:errors path="description" cssClass="text-danger"/>
            <form:textarea rows="4" class="form-control" path="description"/>
        </div>
        <div class="form-group">
            <label class="text-light">Genre:</label>
            <div class="list-group">
                <form:errors path="genre" cssClass="text-danger"/>
                <form:select path="genre" class="form-select list-group-item list-group-item-action">
                    <form:option value="">Select a genre...</form:option>
                    <form:option value="Action">Action</form:option>
                    <form:option value="Comedy">Comedy</form:option>
                    <form:option value="Drama">Drama</form:option>
                    <form:option value="Romance">Romance</form:option>
                    <form:option value="Horror">Horror</form:option>
                </form:select>
            </div>
        </div>

        <form:errors path="lead" cssClass="error"/>
        <form:input type="hidden" path="lead" value="${userId}" class="form-control"/>

        <div class="form-group">
            <a class="linkBtn" href="/dashboard">Cancel</a>
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </form:form>
</div>

</body>
</html>
