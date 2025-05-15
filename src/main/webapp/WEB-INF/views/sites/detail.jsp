<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${site.name} - Digital-तीर्थ</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            margin: 0;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1, h2, h3 {
            color: #333;
        }
        .site-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
            padding-bottom: 20px;
            border-bottom: 1px solid #ddd;
        }
        .site-meta {
            display: flex;
            gap: 20px;
            margin-bottom: 20px;
        }
        .meta-item {
            padding: 10px;
            background-color: #f0f0f0;
            border-radius: 5px;
            flex: 1;
        }
        .meta-item h3 {
            margin-top: 0;
            font-size: 16px;
        }
        .meta-item p {
            margin-bottom: 0;
            font-size: 18px;
            font-weight: bold;
        }
        .risk-high {
            color: #ff0000;
        }
        .risk-medium {
            color: #ff9900;
        }
        .risk-low {
            color: #009900;
        }
        .site-description {
            margin-bottom: 30px;
            line-height: 1.8;
        }
        .virtual-tour {
            margin-bottom: 30px;
            padding: 20px;
            background-color: #f9f9f9;
            border-radius: 5px;
        }
        .virtual-tour-placeholder {
            width: 100%;
            height: 400px;
            background-color: #ddd;
            display: flex;
            justify-content: center;
            align-items: center;
            margin-bottom: 20px;
            border-radius: 5px;
        }
        .donation-section {
            margin-bottom: 30px;
            padding: 20px;
            background-color: #fff8e1;
            border-radius: 5px;
            border: 1px solid #ffe082;
        }
        .media-section {
            margin-bottom: 30px;
        }
        .media-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
            gap: 15px;
            margin-top: 20px;
        }
        .media-item {
            height: 150px;
            background-color: #ddd;
            border-radius: 5px;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .form-control {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .btn {
            display: inline-block;
            padding: 10px 15px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        .btn-primary {
            background-color: #2196F3;
        }
        .btn-success {
            background-color: #4CAF50;
        }
        .btn-warning {
            background-color: #FF9800;
        }
        .btn-danger {
            background-color: #f44336;
        }
        .btn:hover {
            opacity: 0.8;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="site-header">
            <h1>${site.name}</h1>
            <div>
                <a href="${pageContext.request.contextPath}/sites" class="btn btn-primary">Back to Sites</a>
                <form action="${pageContext.request.contextPath}/sites/${site.id}/visit" method="post" style="display:inline;">
                    <button type="submit" class="btn btn-success">Record Visit</button>
                </form>
            </div>
        </div>

        <div class="site-meta">
            <div class="meta-item">
                <h3>Location</h3>
                <p>${site.location}</p>
            </div>
            <div class="meta-item">
                <h3>Category</h3>
                <p>${site.category}</p>
            </div>
            <div class="meta-item">
                <h3>Risk Level</h3>
                <p class="risk-${site.riskLevel.toLowerCase()}">${site.riskLevel}</p>
            </div>
            <div class="meta-item">
                <h3>Visit Count</h3>
                <p>${site.visitCount}</p>
            </div>
            <div class="meta-item">
                <h3>Total Donations</h3>
                <p>$${site.donationAmount}</p>
            </div>
        </div>

        <div class="site-description">
            <h2>About this Heritage Site</h2>
            <p>${site.description}</p>
        </div>

        <div class="virtual-tour">
            <h2>Virtual Tour</h2>
            <div class="virtual-tour-placeholder">
                <p>Virtual Tour Experience Coming Soon</p>
            </div>
            <p>Embark on a virtual pilgrimage to explore this sacred site. Experience the beauty and cultural significance of ${site.name} from anywhere in the world.</p>
        </div>

        <div class="donation-section" id="donate">
            <h2>Support Preservation Efforts</h2>
            <p>Your contribution helps preserve this important cultural heritage site for future generations.</p>

            <form action="${pageContext.request.contextPath}/sites/${site.id}/donate" method="post">
                <div class="form-group">
                    <label for="amount">Donation Amount ($)</label>
                    <input type="number" id="amount" name="amount" class="form-control" min="1" step="1" required>
                </div>
                <div class="form-group">
                    <label for="name">Your Name</label>
                    <input type="text" id="name" name="name" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="message">Message (Optional)</label>
                    <textarea id="message" name="message" class="form-control" rows="3"></textarea>
                </div>
                <button type="submit" class="btn btn-warning">Make Donation</button>
            </form>
        </div>

        <div class="media-section">
            <h2>Media Contributions</h2>
            <p>Help document this heritage site by contributing photos and videos. Your contributions help monitor the site's condition and preservation needs.</p>

            <div class="media-grid">
                <div class="media-item">Photo 1</div>
                <div class="media-item">Photo 2</div>
                <div class="media-item">Photo 3</div>
                <div class="media-item">Photo 4</div>
            </div>

            <p style="margin-top: 20px;">
                <a href="#" class="btn btn-primary">Contribute Media</a>
            </p>
        </div>
    </div>
</body>
</html>
