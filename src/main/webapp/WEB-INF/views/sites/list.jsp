<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Heritage Sites - Digital-तीर्थ</title>
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
        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }
        .filter-info {
            margin-bottom: 20px;
            padding: 10px;
            background-color: #f0f0f0;
            border-radius: 5px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        .risk-high {
            color: #ff0000;
            font-weight: bold;
        }
        .risk-medium {
            color: #ff9900;
            font-weight: bold;
        }
        .risk-low {
            color: #009900;
            font-weight: bold;
        }
        .actions {
            display: flex;
            gap: 10px;
        }
        .btn {
            display: inline-block;
            padding: 8px 12px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            font-size: 14px;
        }
        .btn-view {
            background-color: #2196F3;
        }
        .btn-visit {
            background-color: #9C27B0;
        }
        .btn-donate {
            background-color: #FF9800;
        }
        .btn-add {
            background-color: #4CAF50;
            margin-bottom: 20px;
        }
        .btn:hover {
            opacity: 0.8;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Nepal's Heritage Sites</h1>

        <c:if test="${not empty filterType}">
            <div class="filter-info">
                <p>Filtered by ${filterType}: <strong>${filterValue}</strong></p>
                <a href="${pageContext.request.contextPath}/sites" class="btn">Show All Sites</a>
            </div>
        </c:if>

        <a href="${pageContext.request.contextPath}/sites/new" class="btn btn-add">Add New Heritage Site</a>

        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Location</th>
                    <th>Category</th>
                    <th>Risk Level</th>
                    <th>Visit Count</th>
                    <th>Donations</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="site" items="${sites}">
                    <tr>
                        <td>${site.name}</td>
                        <td>${site.location}</td>
                        <td>${site.category}</td>
                        <td class="risk-${site.riskLevel.toLowerCase()}">${site.riskLevel}</td>
                        <td>${site.visitCount}</td>
                        <td>$${site.donationAmount}</td>
                        <td class="actions">
                            <a href="${pageContext.request.contextPath}/sites/${site.id}" class="btn btn-view">View</a>
                            <form action="${pageContext.request.contextPath}/sites/${site.id}/visit" method="post" style="display:inline;">
                                <button type="submit" class="btn btn-visit">Visit</button>
                            </form>
                            <a href="${pageContext.request.contextPath}/sites/${site.id}#donate" class="btn btn-donate">Donate</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <c:if test="${empty sites}">
            <p>No heritage sites found.</p>
        </c:if>
    </div>
</body>
</html>
