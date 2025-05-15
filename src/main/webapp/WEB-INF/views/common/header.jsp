<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${param.title} - Digital-तीर्थ</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Yatra+One&display=swap" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        :root {
            --primary-color: #5c2d91;
            --primary-dark: #4a2276;
            --secondary-color: #ff9e1b;
            --text-color: #333;
            --light-bg: #f8f9fa;
            --border-color: #ddd;
        }

        body {
            font-family: 'Poppins', sans-serif;
            color: var(--text-color);
            background-color: var(--light-bg);
        }

        .navbar {
            background-color: #fff;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            padding: 15px 0;
        }

        .navbar-brand {
            font-family: 'Yatra One', cursive;
            font-size: 1.8rem;
            color: var(--primary-color);
            display: flex;
            align-items: center;
        }

        .navbar-brand span {
            color: var(--secondary-color);
        }

        .navbar-logo {
            width: 40px;
            height: 40px;
            margin-right: 10px;
            background-color: var(--primary-color);
            border-radius: 50%;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .navbar-logo img {
            width: 30px;
            height: 30px;
        }

        .nav-link {
            color: var(--text-color);
            font-weight: 500;
            padding: 10px 15px;
            transition: all 0.3s;
        }

        .nav-link:hover, .nav-link.active {
            color: var(--primary-color);
        }

        .nav-link.active {
            border-bottom: 2px solid var(--primary-color);
        }

        .dropdown-menu {
            border: none;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            padding: 10px;
        }

        .dropdown-item {
            padding: 8px 15px;
            border-radius: 5px;
            transition: all 0.3s;
        }

        .dropdown-item:hover {
            background-color: rgba(92, 45, 145, 0.1);
            color: var(--primary-color);
        }

        .btn-primary {
            background-color: var(--primary-color);
            border-color: var(--primary-color);
            padding: 8px 20px;
            font-weight: 500;
            border-radius: 5px;
            transition: all 0.3s;
        }

        .btn-primary:hover {
            background-color: var(--primary-dark);
            border-color: var(--primary-dark);
        }

        .btn-outline-primary {
            color: var(--primary-color);
            border-color: var(--primary-color);
            padding: 8px 20px;
            font-weight: 500;
            border-radius: 5px;
            transition: all 0.3s;
        }

        .btn-outline-primary:hover {
            background-color: var(--primary-color);
            color: white;
        }

        .user-avatar {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            background-color: var(--primary-color);
            color: white;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 1.2rem;
            margin-right: 10px;
        }

        .user-menu {
            display: flex;
            align-items: center;
        }

        .karma-badge {
            background-color: var(--primary-color);
            color: white;
            padding: 3px 8px;
            border-radius: 20px;
            font-size: 0.8rem;
            margin-left: 10px;
        }

        .main-content {
            min-height: calc(100vh - 180px);
            padding: 30px 0;
        }

        /* Cultural Design Elements */
        .cultural-pattern {
            background-image: url('https://www.transparenttextures.com/patterns/batik.png');
            background-color: rgba(92, 45, 145, 0.05);
            padding: 10px 0;
            border-bottom: 1px solid rgba(92, 45, 145, 0.1);
        }

        .mandala-divider {
            background-image: url('https://www.transparenttextures.com/patterns/diamond-upholstery.png');
            height: 10px;
            margin: 20px 0;
            background-color: rgba(92, 45, 145, 0.1);
        }
    </style>
    ${param.customCss}
</head>
<body>
    <div class="cultural-pattern d-none d-md-block"></div>

    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">
                <div class="navbar-logo">
                    <img src="https://cdn-icons-png.flaticon.com/512/2942/2942789.png" alt="Logo">
                </div>
                Digital-<span>तीर्थ</span>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link ${param.activeNav == 'home' ? 'active' : ''}" href="${pageContext.request.contextPath}/">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ${param.activeNav == 'sites' ? 'active' : ''}" href="${pageContext.request.contextPath}/sites">Heritage Sites</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ${param.activeNav == 'media' ? 'active' : ''}" href="${pageContext.request.contextPath}/media">Media</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle ${param.activeNav == 'contribute' ? 'active' : ''}" href="#" id="contributeDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Contribute
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="contributeDropdown">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/sites">Visit a Site</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/media/upload">Upload Media</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/donate">Make a Donation</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ${param.activeNav == 'about' ? 'active' : ''}" href="${pageContext.request.contextPath}/about">About</a>
                    </li>
                </ul>

                <div class="d-flex">
                    <c:choose>
                        <c:when test="${not empty sessionScope.user}">
                            <div class="user-menu dropdown">
                                <a class="nav-link dropdown-toggle d-flex align-items-center" href="#" id="userDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <div class="user-avatar">
                                        <i class="fas fa-user"></i>
                                    </div>
                                    <span>${sessionScope.username}</span>
                                    <span class="karma-badge"><i class="fas fa-star"></i> ${sessionScope.user.karmaPoints}</span>
                                </a>
                                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="userDropdown">
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/profile">My Profile</a></li>
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/profile#contributions">My Contributions</a></li>
                                    <li><hr class="dropdown-divider"></li>
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a></li>
                                </ul>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/login" class="btn btn-outline-primary me-2">Login</a>
                            <a href="${pageContext.request.contextPath}/signup" class="btn btn-primary">Sign Up</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </nav>

    <!-- Main content starts here -->
