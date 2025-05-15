<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${media.title} - Digital-तीर्थ</title>
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

        .media-container {
            max-width: 1000px;
            margin: 50px auto;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }

        .media-header {
            background-color: #5c2d91;
            color: white;
            padding: 20px 30px;
        }

        .media-header h1 {
            font-size: 1.8rem;
            margin-bottom: 5px;
        }

        .media-header p {
            margin-bottom: 0;
            opacity: 0.8;
        }

        .media-content {
            padding: 30px;
        }

        .media-display {
            margin-bottom: 30px;
            text-align: center;
            background-color: #000;
            border-radius: 5px;
            overflow: hidden;
        }

        .media-display img {
            max-width: 100%;
            max-height: 500px;
            object-fit: contain;
        }

        .media-display video {
            max-width: 100%;
            max-height: 500px;
        }

        .media-info {
            margin-bottom: 30px;
        }

        .media-meta {
            display: flex;
            flex-wrap: wrap;
            gap: 15px;
            margin-bottom: 20px;
        }

        .meta-item {
            display: flex;
            align-items: center;
            gap: 5px;
            color: #666;
            font-size: 0.9rem;
        }

        .meta-item i {
            color: #5c2d91;
        }

        .media-description {
            margin-bottom: 30px;
        }

        .media-actions {
            display: flex;
            gap: 15px;
            margin-bottom: 30px;
        }

        .btn-action {
            display: flex;
            align-items: center;
            gap: 5px;
            padding: 8px 15px;
            border-radius: 5px;
            font-weight: 500;
            font-size: 0.9rem;
            transition: all 0.3s;
        }

        .btn-like {
            background-color: #f8f9fa;
            color: #333;
            border: 1px solid #ddd;
        }

        .btn-like:hover {
            background-color: #e9ecef;
        }

        .btn-share {
            background-color: #f8f9fa;
            color: #333;
            border: 1px solid #ddd;
        }

        .btn-share:hover {
            background-color: #e9ecef;
        }

        .btn-report {
            background-color: #f8f9fa;
            color: #dc3545;
            border: 1px solid #ddd;
        }

        .btn-report:hover {
            background-color: #e9ecef;
        }

        .site-info {
            background-color: #f0f7ed;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
        }

        .site-info h3 {
            font-size: 1.2rem;
            margin-bottom: 10px;
            color: #333;
        }

        .site-info p {
            margin-bottom: 10px;
            color: #666;
        }

        .contributor-info {
            display: flex;
            align-items: center;
            gap: 15px;
            margin-bottom: 20px;
        }

        .contributor-avatar {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            background-color: #5c2d91;
            color: white;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 1.5rem;
        }

        .contributor-details h3 {
            font-size: 1.1rem;
            margin-bottom: 0;
        }

        .contributor-details p {
            font-size: 0.9rem;
            color: #666;
            margin-bottom: 0;
        }

        .damage-analysis {
            background-color: #fff3cd;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
            border-left: 4px solid #ffc107;
        }

        .damage-analysis h3 {
            font-size: 1.2rem;
            margin-bottom: 10px;
            color: #333;
        }

        .damage-analysis p {
            margin-bottom: 10px;
            color: #666;
        }

        .damage-level {
            font-weight: bold;
        }

        .damage-level.high {
            color: #dc3545;
        }

        .damage-level.medium {
            color: #fd7e14;
        }

        .damage-level.low {
            color: #ffc107;
        }

        .damage-level.none {
            color: #28a745;
        }

        .btn-back {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <a href="${pageContext.request.contextPath}/sites/${site.id}" class="btn btn-primary btn-back">
            <i class="fas fa-arrow-left"></i> Back to ${site.name}
        </a>

        <div class="media-container">
            <div class="media-header">
                <h1>${media.title}</h1>
                <p>Contributed to ${site.name}</p>
            </div>

            <div class="media-content">
                <div class="media-display">
                    <c:choose>
                        <c:when test="${media.mediaType == 'Photo'}">
                            <img src="${pageContext.request.contextPath}/${media.fileUrl}" alt="${media.title}">
                        </c:when>
                        <c:when test="${media.mediaType == 'Video'}">
                            <video controls>
                                <source src="${pageContext.request.contextPath}/${media.fileUrl}" type="video/mp4">
                                Your browser does not support the video tag.
                            </video>
                        </c:when>
                        <c:otherwise>
                            <div style="padding: 100px 0; color: white;">
                                ${media.mediaType} preview not available
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="media-info">
                    <div class="media-meta">
                        <div class="meta-item">
                            <i class="fas fa-calendar"></i>
                            <span>Uploaded on ${media.uploadDate}</span>
                        </div>
                        <div class="meta-item">
                            <i class="fas fa-eye"></i>
                            <span>${media.viewCount} views</span>
                        </div>
                        <div class="meta-item">
                            <i class="fas fa-heart"></i>
                            <span>${media.likeCount} likes</span>
                        </div>
                        <div class="meta-item">
                            <i class="fas fa-map-marker-alt"></i>
                            <span>${media.location != null ? media.location : 'Location not specified'}</span>
                        </div>
                    </div>

                    <div class="media-description">
                        <h3>Description</h3>
                        <p>${media.description}</p>
                    </div>

                    <div class="media-actions">
                        <form action="${pageContext.request.contextPath}/media/like/${media.id}" method="post" style="display:inline;">
                            <button type="submit" class="btn btn-action btn-like">
                                <i class="far fa-heart"></i> Like
                            </button>
                        </form>
                        <button class="btn btn-action btn-share" onclick="shareMedia()">
                            <i class="fas fa-share-alt"></i> Share
                        </button>
                        <button class="btn btn-action btn-report" onclick="reportMedia()">
                            <i class="fas fa-flag"></i> Report
                        </button>
                    </div>

                    <c:if test="${media.hasDamageDetection}">
                        <div class="damage-analysis">
                            <h3>AI Damage Analysis</h3>
                            <p>
                                Damage Level: 
                                <span class="damage-level ${media.damageLevel.toLowerCase()}">
                                    ${media.damageLevel}
                                </span>
                            </p>
                            <p>${media.damageDescription}</p>
                        </div>
                    </c:if>

                    <div class="contributor-info">
                        <div class="contributor-avatar">
                            <i class="fas fa-user"></i>
                        </div>
                        <div class="contributor-details">
                            <h3>${user.fullName}</h3>
                            <p>Contributed ${user.mediaContributed} media items</p>
                        </div>
                    </div>

                    <div class="site-info">
                        <h3>About ${site.name}</h3>
                        <p>${site.description}</p>
                        <a href="${pageContext.request.contextPath}/sites/${site.id}" class="btn btn-sm btn-primary">
                            View Heritage Site
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Custom JS -->
    <script>
        function shareMedia() {
            // In a real app, this would open a sharing dialog
            alert('Sharing functionality would be implemented here.');
        }

        function reportMedia() {
            // In a real app, this would open a report dialog
            alert('Reporting functionality would be implemented here.');
        }
    </script>
</body>
</html>
