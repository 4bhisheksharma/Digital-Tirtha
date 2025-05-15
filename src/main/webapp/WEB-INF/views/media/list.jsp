<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Media Contributions - Digital-तीर्थ</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <!-- Custom CSS -->
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Poppins', sans-serif;
            color: #333;
        }

        .page-header {
            background-color: #5c2d91;
            color: white;
            padding: 40px 0;
            margin-bottom: 40px;
        }

        .page-header h1 {
            font-size: 2.5rem;
            margin-bottom: 10px;
        }

        .page-header p {
            font-size: 1.1rem;
            opacity: 0.8;
            max-width: 700px;
            margin: 0 auto;
        }

        .media-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
            gap: 30px;
            margin-bottom: 40px;
        }

        .media-card {
            background-color: #fff;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s, box-shadow 0.3s;
        }

        .media-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.15);
        }

        .media-thumbnail {
            height: 200px;
            background-color: #ddd;
            position: relative;
            overflow: hidden;
        }

        .media-thumbnail img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }

        .media-type {
            position: absolute;
            top: 10px;
            right: 10px;
            background-color: rgba(0, 0, 0, 0.7);
            color: white;
            padding: 5px 10px;
            border-radius: 20px;
            font-size: 0.8rem;
            font-weight: 500;
        }

        .media-content {
            padding: 20px;
        }

        .media-title {
            font-size: 1.2rem;
            margin-bottom: 10px;
            font-weight: 600;
            color: #333;
        }

        .media-site {
            font-size: 0.9rem;
            color: #666;
            margin-bottom: 15px;
        }

        .media-meta {
            display: flex;
            justify-content: space-between;
            font-size: 0.8rem;
            color: #666;
        }

        .meta-item {
            display: flex;
            align-items: center;
            gap: 5px;
        }

        .meta-item i {
            color: #5c2d91;
        }

        .filters {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
            margin-bottom: 30px;
        }

        .filter-title {
            font-size: 1.2rem;
            margin-bottom: 15px;
            color: #333;
            font-weight: 600;
        }

        .filter-group {
            margin-bottom: 15px;
        }

        .filter-label {
            font-weight: 500;
            margin-bottom: 8px;
            color: #333;
        }

        .form-select, .form-control {
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ddd;
        }

        .btn-filter {
            background-color: #5c2d91;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            font-weight: 600;
            transition: background-color 0.3s;
        }

        .btn-filter:hover {
            background-color: #4a2276;
        }

        .no-media {
            text-align: center;
            padding: 50px 0;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
        }

        .no-media i {
            font-size: 3rem;
            color: #ddd;
            margin-bottom: 20px;
        }

        .no-media h3 {
            font-size: 1.5rem;
            margin-bottom: 10px;
            color: #333;
        }

        .no-media p {
            color: #666;
            max-width: 500px;
            margin: 0 auto 20px;
        }
    </style>
</head>
<body>
    <div class="page-header text-center">
        <div class="container">
            <h1>Media Contributions</h1>
            <p>Explore photos, videos, and other media contributed by our community to document and preserve Nepal's heritage sites.</p>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div class="filters">
                    <h3 class="filter-title">Filters</h3>
                    <form action="${pageContext.request.contextPath}/media" method="get">
                        <div class="filter-group">
                            <label for="mediaType" class="filter-label">Media Type</label>
                            <select class="form-select" id="mediaType" name="mediaType">
                                <option value="">All Types</option>
                                <option value="Photo">Photos</option>
                                <option value="Video">Videos</option>
                                <option value="3D Scan">3D Scans</option>
                                <option value="Audio">Audio</option>
                            </select>
                        </div>

                        <div class="filter-group">
                            <label for="siteId" class="filter-label">Heritage Site</label>
                            <select class="form-select" id="siteId" name="siteId">
                                <option value="">All Sites</option>
                                <c:forEach var="site" items="${sites}">
                                    <option value="${site.id}">${site.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="filter-group">
                            <label for="sortBy" class="filter-label">Sort By</label>
                            <select class="form-select" id="sortBy" name="sortBy">
                                <option value="newest">Newest First</option>
                                <option value="oldest">Oldest First</option>
                                <option value="popular">Most Popular</option>
                                <option value="views">Most Viewed</option>
                            </select>
                        </div>

                        <button type="submit" class="btn btn-filter w-100">Apply Filters</button>
                    </form>
                </div>
            </div>

            <div class="col-lg-9">
                <c:choose>
                    <c:when test="${not empty mediaList}">
                        <div class="media-grid">
                            <c:forEach var="media" items="${mediaList}">
                                <div class="media-card">
                                    <div class="media-thumbnail">
                                        <c:choose>
                                            <c:when test="${media.mediaType == 'Photo'}">
                                                <img src="${pageContext.request.contextPath}/${media.fileUrl}" alt="${media.title}">
                                            </c:when>
                                            <c:otherwise>
                                                <div style="width: 100%; height: 100%; background-color: #333; display: flex; justify-content: center; align-items: center; color: white;">
                                                    <i class="fas fa-file-video fa-3x"></i>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                        <div class="media-type">${media.mediaType}</div>
                                    </div>
                                    <div class="media-content">
                                        <h3 class="media-title">${media.title}</h3>
                                        <div class="media-site">
                                            <i class="fas fa-landmark"></i> ${site.name}
                                        </div>
                                        <div class="media-meta">
                                            <div class="meta-item">
                                                <i class="fas fa-eye"></i>
                                                <span>${media.viewCount}</span>
                                            </div>
                                            <div class="meta-item">
                                                <i class="fas fa-heart"></i>
                                                <span>${media.likeCount}</span>
                                            </div>
                                            <div class="meta-item">
                                                <i class="fas fa-calendar"></i>
                                                <span>${media.uploadDate}</span>
                                            </div>
                                        </div>
                                        <a href="${pageContext.request.contextPath}/media/view/${media.id}" class="btn btn-primary w-100 mt-3">View Details</a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="no-media">
                            <i class="fas fa-photo-video"></i>
                            <h3>No Media Contributions Yet</h3>
                            <p>Be the first to contribute media to help document and preserve Nepal's heritage sites.</p>
                            <a href="${pageContext.request.contextPath}/sites" class="btn btn-primary">Explore Heritage Sites</a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
